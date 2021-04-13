package ru.itis.repositories;

import ru.itis.models.User;

public interface UserRepository {
    boolean save(User user);
    User findById(long id);
    User findByEmail(String email);
    User findByEmailAndPassword(String email, String password);
}
