package com.example.summerShop.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ProductReadDto {

    @NotEmpty(message = "Введіть назву")
    @Size(min = 2, max = 127, message = "Назва має бути більше 2 і меньша 127 символів")
    private String name;

    @Size(min = 0, max = 255, message = "Опис має бути меньшим за 255 символів")
    private String description;

    @Min(value = 0, message = "Ціна повина бути більше нуля")
    private Double price;

    private BrandGetDto brand;
    private CategoryGetDto category;
    private String gender;
    private MultipartFile photo;
    private List<SizeReadDto> size;
}
