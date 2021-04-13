package ru.itis.services;

import ru.itis.dto.UserDto;

public interface UserService {
    UserDto getUserByEmail(String email);
}
