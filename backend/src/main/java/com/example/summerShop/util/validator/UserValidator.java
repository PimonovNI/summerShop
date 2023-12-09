package com.example.summerShop.util.validator;

import com.example.summerShop.dto.UserRegistrationDto;
import com.example.summerShop.model.User;
import com.example.summerShop.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Optional;

@Component
public class UserValidator implements Validator {

    private final UsersRepository usersRepository;

    @Autowired
    public UserValidator(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return UserRegistrationDto.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        UserRegistrationDto dto = (UserRegistrationDto) target;
        Optional<User> user = usersRepository.findByUsername(dto.getUsername());
        if (user.isPresent()) {
            errors.rejectValue("username", "",
                    "Таке ім'я користувача вже існує, оберіть інше");
        }

        if (dto.getPassword().matches("^[^a-zA-Z0-9_+=*/.,?#@%$^&!-]$")
                || !dto.getPassword().matches("^.*\\d.*$")
                || !dto.getPassword().matches("^.*[a-z].*$")
                || !dto.getPassword().matches("^.*[A-Z].*$")) {
            errors.rejectValue("password", "",
                    "Неправильний формат паролю. Він моє обов'язково включати латинску літеру в обох реєстрах та цифру, за бажанням можна додати спеціальній символ");
        }
    }
}
