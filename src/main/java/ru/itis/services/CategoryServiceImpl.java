package ru.itis.services;

import ru.itis.dto.CategoryDto;
import ru.itis.models.Category;
import ru.itis.repositories.CategoryRepository;

import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();
        return categories != null ? CategoryDto.from(categories) : null;
    }

    @Override
    public CategoryDto getCategoryById(long id) {
        Category category = categoryRepository.findById(id);
        return category != null ? CategoryDto.from(category) : null;
    }

    @Override
    public void saveCategory(CategoryDto categoryDto) {
        Category category = Category.builder()
                .name(categoryDto.getName())
                .build();

        categoryRepository.save(category);
    }

    @Override
    public void deleteCategoryById(long id) {
        categoryRepository.delete(id);
    }
}
