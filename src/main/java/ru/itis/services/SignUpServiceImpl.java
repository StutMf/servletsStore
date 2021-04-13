package ru.itis.services;

import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.dto.SignUpForm;
import ru.itis.dto.UserDto;
import ru.itis.models.User;
import ru.itis.repositories.UserRepository;

public class SignUpServiceImpl implements SignUpService {

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    public SignUpServiceImpl(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public UserDto signUp(SignUpForm signUpForm) {
        User user = userRepository.findByEmail(signUpForm.getEmail());

        if (user != null || !signUpForm.getPassword().equals(signUpForm.getPasswordAgain())) {
            return null;
        }

        User newUser = User.builder()
                .id(null)
                .firstname(signUpForm.getFirstname())
                .lastname(signUpForm.getLastname())
                .email(signUpForm.getEmail())
                .password(passwordEncoder.encode(signUpForm.getPassword()))
                .build();

        userRepository.save(newUser);

        return UserDto.from(userRepository.findByEmail(newUser.getEmail()));
    }
}
