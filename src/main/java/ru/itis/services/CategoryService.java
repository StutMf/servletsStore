package ru.itis.services;

import ru.itis.dto.CategoryDto;

import java.util.List;

public interface CategoryService {
    List<CategoryDto> getAllCategories();
    CategoryDto getCategoryById(long id);
    void saveCategory(CategoryDto categoryDto);
    void deleteCategoryById(long id);
}
