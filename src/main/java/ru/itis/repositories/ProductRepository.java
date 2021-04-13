package ru.itis.repositories;

import ru.itis.models.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> findAll();
    void save(Product product);
    Product findById(Long id);
    void deleteById(Long id);
    List<Product> findByCategory(long categoryId);
}
