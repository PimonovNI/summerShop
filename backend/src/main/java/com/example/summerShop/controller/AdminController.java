package com.example.summerShop.controller;

import com.example.summerShop.dto.ProductReadDto;
import com.example.summerShop.dto.JsonWithFileDto;
import com.example.summerShop.service.ProductsService;
import com.example.summerShop.util.exception.FileHandingException;
import com.example.summerShop.util.exception.NonExistentIdException;
import com.example.summerShop.util.exception.ValidationException;
import com.example.summerShop.util.response.ErrorResponse;
import com.example.summerShop.util.response.ResponseUtils;
import com.example.summerShop.util.response.SimpleErrorResponse;
import com.example.summerShop.util.validator.ProductValidator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.Instant;

@CrossOrigin
@Controller
@RequestMapping("/api/v1/admin")
public class AdminController {

    private final ObjectMapper objectMapper;
    private final ProductValidator productValidator;
    private final ProductsService productsService;

    @Autowired
    public AdminController(ObjectMapper objectMapper, ProductValidator productValidator,
                           ProductsService productsService) {
        this.objectMapper = objectMapper;
        this.productValidator = productValidator;
        this.productsService = productsService;
    }
    
    @ResponseBody
    @PostMapping("/product/create")
    public ResponseEntity<HttpStatus> createProduct(@ModelAttribute JsonWithFileDto data)
            throws IOException, ValidationException {

        ProductReadDto dto = extractAndValidateProductDto(data);
        productsService.save(dto);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @ResponseBody
    @PatchMapping("/product/{id}")
    public ResponseEntity<HttpStatus> updateProduct(@PathVariable("id") Long id, @ModelAttribute JsonWithFileDto data)
            throws IOException, ValidationException {

        ProductReadDto dto = extractAndValidateProductDto(data);
        productsService.update(id, dto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @ResponseBody
    @DeleteMapping("/product/{id}")
    public ResponseEntity<HttpStatus> deleteProduct(@PathVariable("id") Long id)
            throws FileHandingException {

        productsService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    private ProductReadDto extractAndValidateProductDto(JsonWithFileDto data) throws JsonProcessingException {
        ProductReadDto dto = objectMapper.readValue(data.getJson(), ProductReadDto.class);
        dto.setPhoto(data.getFile());

        Errors errors = new BeanPropertyBindingResult(dto, "Read dto");
        productValidator.validate(dto, errors);

        if (errors.hasErrors()) {
            throw new ValidationException(
                    "Помилка валідації данних",
                    ResponseUtils.createErrorsResponse(errors)
            );
        }

        return dto;
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

    @ExceptionHandler({NonExistentIdException.class})
    private ResponseEntity<SimpleErrorResponse> invalidIdErrorResponse(NonExistentIdException e) {
        return generateSimpleResponse(e);
    }

    @ExceptionHandler({JsonProcessingException.class})
    private ResponseEntity<SimpleErrorResponse> jsonErrorResponse(JsonProcessingException e) {
        return generateSimpleResponse(e);
    }

    @ExceptionHandler({FileHandingException.class})
    private ResponseEntity<SimpleErrorResponse> ioErrorResponse(FileHandingException e) {
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

}
