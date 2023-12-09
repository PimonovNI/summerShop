package com.example.summerShop.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRegistrationDto {

    @NotEmpty(message = "Введіть ім'я користувача")
    @Size(min = 2, max = 127, message = "Довжина ім'я має бути більшою за 2 символи і меншою за 127 символів")
    @Pattern(regexp = "^[a-zA-Z0-9_]{2,127}$", message = "Ім'я користувача може складатися тільки з латинських літер, в бьдь-якому реєстрі, цифр та нижньої риски")
    private String username;

    @NotEmpty(message = "Введіть пароль аккаунту")
    @Size(min = 5, max = 127, message = "Довжина паролю має бути більшою за 5 символи і меншою за 127 символів")
    private String password;

    @NotEmpty(message = "Введіть адрусу електроної скриньки")
    @Email(message = "Неправильний формат електроної адреси")
    private String email;
}
