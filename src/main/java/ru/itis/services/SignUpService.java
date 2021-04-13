package ru.itis.services;

import ru.itis.dto.SignUpForm;
import ru.itis.dto.UserDto;

public interface SignUpService {
    UserDto signUp(SignUpForm signUpForm);
}
