package ru.itis.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.dto.SignInForm;
import ru.itis.dto.UserDto;
import ru.itis.models.User;
import ru.itis.repositories.UserRepository;

public class SignInServiceImpl implements SignInService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public SignInServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public UserDto signIn(SignInForm signInForm) {
        User user = userRepository.findByEmail(signInForm.getEmail());

        if ( user != null && passwordEncoder.matches(signInForm.getPassword(), user.getPassword())) {
            return UserDto.from(user);
        }

        return null;
    }
}
