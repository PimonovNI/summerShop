package com.example.summerShop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDetailsDto {
    private Long id;
    private String name;
    private String description;
    private Double price;
    private String gender;
    private String photo;
    private BrandGetDto brand;
    private CategoryGetDto category;
    private List<StorageGetDto> size;
}
