package com.example.summerShop.controller;

import com.example.summerShop.dto.CartGetDto;
import com.example.summerShop.dto.CartReadDto;
import com.example.summerShop.security.UserDetails;
import com.example.summerShop.service.CartsService;
import com.example.summerShop.util.exception.NonExistentIdException;
import com.example.summerShop.util.response.SimpleErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/api/v1/cart")
public class CartsController {

    private final CartsService cartsService;

    @Autowired
    public CartsController(CartsService cartsService) {
        this.cartsService = cartsService;
    }

    @ResponseBody
    @GetMapping()
    public ResponseEntity<List<CartGetDto>> getAllProductsFromCart() {
        Long userId = getUserId();
        if (userId == -1L) {
            throw new NonExistentIdException("Login please");
        }
        List<CartGetDto> response = cartsService.findAllByUserId(userId);
        return ResponseEntity.ok(response);
    }

    @ResponseBody
    @PostMapping()
    public ResponseEntity<HttpStatus> addToCart(@RequestBody CartReadDto dto) {
        Long userId = getUserId();
        if (userId == -1L) {
            throw new NonExistentIdException("Login please");
        }
        cartsService.save(dto, userId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping("/buy")
    public ResponseEntity<HttpStatus> buyAllFromCart() {
        Long userId = getUserId();
        if (userId == -1L) {
            throw new NonExistentIdException("Login please");
        }
        cartsService.buy(userId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ResponseBody
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteFromCart(@PathVariable("id") Long cartId) {
        Long userId = getUserId();
        if (userId == -1L) {
            throw new NonExistentIdException("Login please");
        }
        cartsService.delete(cartId, userId);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ExceptionHandler({NonExistentIdException.class})
    private ResponseEntity<SimpleErrorResponse> invalidIdErrorResponse(NonExistentIdException e) {
        return generateSimpleResponse(e);
    }

    private ResponseEntity<SimpleErrorResponse> generateSimpleResponse(Exception e) {
        SimpleErrorResponse response = new SimpleErrorResponse(
                e.getClass().getSimpleName(),
                e.getMessage(),
                Instant.now()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    private Long getUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!authentication.isAuthenticated()) {
            return -1L;
        }

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        return  userDetails.getId();
    }

}
