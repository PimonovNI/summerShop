package com.example.summerShop.util.validator;

import com.example.summerShop.dto.ProductReadDto;
import com.example.summerShop.model.enums.Gender;
import jakarta.validation.ConstraintViolation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ProductValidator implements Validator {

    private final jakarta.validation.Validator validator;

    @Autowired
    public ProductValidator(jakarta.validation.Validator validator) {
        this.validator = validator;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return ProductReadDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ProductReadDto dto = (ProductReadDto) target;
        for (ConstraintViolation<ProductReadDto> error : validator.validate(dto)) {
            errors.rejectValue(error.getPropertyPath().toString(), "", error.getMessage());
        }

        if (dto.getBrand().getId() == -1 && (dto.getBrand().getName() == null || dto.getBrand().getName().isEmpty())) {
            errors.rejectValue("brandNewName", "", "Введіть ім'я нового бренду");
        }

        if (dto.getCategory().getId() == -1 && (dto.getCategory().getName() == null || dto.getCategory().getName().isEmpty())) {
            errors.rejectValue("categoryNewName", "", "Введіть ім'я нової категорії");
        }

        try {
            Gender.valueOf(dto.getGender().toUpperCase());
        } catch (IllegalArgumentException e) {
            errors.rejectValue("gender", "", "Неправильний формат статі");
        }

        if (dto.getSize().stream().anyMatch(s -> s.getCount() < 0)) {
            errors.rejectValue("size", "", "Кількість товару має бути більша за 0");
        }
    }
}
