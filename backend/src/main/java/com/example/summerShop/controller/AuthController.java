package com.example.summerShop.controller;

import com.example.summerShop.dto.RefreshTokenDto;
import com.example.summerShop.dto.UserRegistrationDto;
import com.example.summerShop.dto.UserLoginDto;
import com.example.summerShop.service.UsersService;
import com.example.summerShop.util.exception.JwtRefreshException;
import com.example.summerShop.util.exception.NotVerifiedEmailException;
import com.example.summerShop.util.exception.ValidationException;
import com.example.summerShop.util.response.ErrorResponse;
import com.example.summerShop.util.response.ResponseUtils;
import com.example.summerShop.util.response.SimpleErrorResponse;
import com.example.summerShop.util.validator.UserValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.Map;

@CrossOrigin
@Controller
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final UsersService usersService;
    private final UserValidator userValidator;

    @Autowired
    public AuthController(UsersService usersService, UserValidator userValidator) {
        this.usersService = usersService;
        this.userValidator = userValidator;
    }

    @ResponseBody
    @PostMapping("/log")
    public ResponseEntity<Map<String, Object>> loginUser(@RequestBody UserLoginDto dto)
            throws BadCredentialsException, UsernameNotFoundException, NotVerifiedEmailException {

        Map<String, Object> response = usersService.login(dto);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    // TODO: Do logout

    @ResponseBody
    @PostMapping("/reg")
    public ResponseEntity<HttpStatus> registrationNewUser(
            @RequestBody @Valid UserRegistrationDto dto, BindingResult bindingResult) {

        userValidator.validate(dto, bindingResult);
        if (bindingResult.hasErrors()) {
            throw new ValidationException(
                    "Помилка валідації данних",
                    ResponseUtils.createErrorsResponse(bindingResult)
            );
        }

        usersService.registration(dto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping("/refresh")
    public ResponseEntity<Map<String, Object>> refreshAuthByToken(@RequestBody RefreshTokenDto dto)
            throws JwtRefreshException {

        Map<String, Object> response = usersService.refresh(dto.getRefreshToken());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/activation/{code}")
    public String activationEmail(@PathVariable("code") String activationCode) {
        usersService.activation(activationCode);
        return "redirect:https://summer-shop-v1.vercel.app/";
    }

    @ExceptionHandler({ValidationException.class})
    private ResponseEntity<ErrorResponse> validateErrorResponse(ValidationException e) {
        ErrorResponse response = new ErrorResponse(
                e.getClass().getSimpleName(),
                e.getMessage(),
                Instant.now(),
                e.getInfo()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({BadCredentialsException.class, UsernameNotFoundException.class, NotVerifiedEmailException.class})
    private ResponseEntity<SimpleErrorResponse> authenticationErrorResponse(AuthenticationException e) {
        SimpleErrorResponse response = new SimpleErrorResponse(
                e.getClass().getSimpleName(),
                e.getMessage(),
                Instant.now()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({JwtRefreshException.class})
    private ResponseEntity<SimpleErrorResponse> jwtErrorResponse(JwtRefreshException e) {
        SimpleErrorResponse response = new SimpleErrorResponse(
                e.getClass().getSimpleName(),
                e.getMessage(),
                Instant.now()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
