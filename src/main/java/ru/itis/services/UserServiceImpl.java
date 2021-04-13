package ru.itis.services;

import ru.itis.dto.UserDto;
import ru.itis.models.User;
import ru.itis.repositories.UserRepository;

public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDto getUserByEmail(String email) {
        User user = userRepository.findByEmail(email);
        return user != null ? UserDto.from(user) : null;
    }
}
