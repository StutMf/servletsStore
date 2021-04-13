package ru.itis.repositories;

import ru.itis.models.Category;

import java.util.List;

public interface CategoryRepository {
    Category findById(long id);
    List<Category> findAll();
    void save(Category category);
    void delete(long id);
}
