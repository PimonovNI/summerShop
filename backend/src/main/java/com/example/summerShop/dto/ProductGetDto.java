package com.example.summerShop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductGetDto {
    private Long id;
    private String name;
    private String photo;
    private Double price;
    private String brand;
    private String category;
}
