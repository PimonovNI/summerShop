package com.example.summerShop.controller;

import com.example.summerShop.dto.*;
import com.example.summerShop.security.UserDetails;
import com.example.summerShop.service.ProductsService;
import com.example.summerShop.util.exception.NonExistentIdException;
import com.example.summerShop.util.response.SimpleErrorResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.time.Instant;
import java.util.List;

@CrossOrigin
@Controller
@RequestMapping("/api/v1/product")
public class GoodsController {

    private final ProductsService productsService;

    @Autowired
    public GoodsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @ResponseBody
    @GetMapping()
    public ResponseEntity<List<ProductGetDto>> getAllProduct() {
        List<ProductGetDto> response = productsService.findAllProducts();
        return ResponseEntity.ok(response);
    }

    @ResponseBody
    @GetMapping("/{id}")
    public ResponseEntity<ProductDetailsDto> getProductDetailsWithHistory(@PathVariable("id") Long id,
                                                               @RequestParam(value = "u", required = false) Long userId) {
        ProductDetailsDto response;
        if (userId != null && userId != -1L && userId.equals(getUserId())) {
            response = productsService.findProductByIdAndSaveHistory(id, userId);
        } else {
            response = productsService.findProductById(id);
        }

        return ResponseEntity.ok(response);
    }

    @ResponseBody
    @GetMapping("/f")
    public ResponseEntity<List<ProductGetDto>> getProductFilter(
            @RequestParam(value = "brand", required = false) List<Integer> brands,
            @RequestParam(value = "category", required = false) List<Integer> categories,
            @RequestParam(value = "size", required = false) List<Integer> sizes,
            @RequestParam(value = "min_price", required = false) Double priceMin,
            @RequestParam(value = "max_price", required = false) Double priceMax,
            @RequestParam(value = "gender", required = false) String gender,
            @RequestParam(value = "sort", required = false) Integer sortType) {

        List<ProductGetDto> response = productsService.findUsingFilters(brands, categories, sizes, priceMin, priceMax, gender, sortType);
        return ResponseEntity.ok(response);
    }

    @ResponseBody
    @GetMapping("/h")
    public ResponseEntity<List<ProductGetDto>> getProductHistory() {
        List<ProductGetDto> response = productsService.findHistory(getUserId());
        return ResponseEntity.ok(response);
    }

    @ResponseBody
    @GetMapping("/r")
    public ResponseEntity<List<ProductGetDto>> getProductRecommend() {
        List<ProductGetDto> response = productsService.findRecommendation(getUserId());

        // TODO: Add K8 algorithm

        return ResponseEntity.ok(response);
    }

    @ResponseBody
    @GetMapping("/brand")
    public ResponseEntity<List<BrandGetDto>> getAllBrand() {
        List<BrandGetDto> response = productsService.findAllBrands();
        return ResponseEntity.ok(response);
    }

    @ResponseBody
    @GetMapping("/category")
    public ResponseEntity<List<CategoryGetDto>> getAllCategory() {
        List<CategoryGetDto> response = productsService.findAllCategories();
        return ResponseEntity.ok(response);
    }

    @ResponseBody
    @GetMapping("/size")
    public ResponseEntity<List<SizeGetDto>> getAllSize() {
        List<SizeGetDto> response = productsService.findAllSizes();
        return ResponseEntity.ok(response);
    }

    @ResponseBody
    @GetMapping(
            value = "/img",
            produces = {
                    "image/webp",
                    "image/jpg",
                    "image/png"
            }
    )
    public ResponseEntity<byte[]> getImage(@RequestParam("id") String id) {
        RestTemplate restTemplate = new RestTemplate();
        byte[] response = restTemplate.getForObject("https://ucarecdn.com/" + id + "/", byte[].class);

        return ResponseEntity.ok(response);
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
