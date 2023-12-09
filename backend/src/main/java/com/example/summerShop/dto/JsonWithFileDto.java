package com.example.summerShop.dto;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class JsonWithFileDto {
    private MultipartFile file;
    private String json;
}
