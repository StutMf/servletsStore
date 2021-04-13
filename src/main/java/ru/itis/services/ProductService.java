package ru.itis.services;

import ru.itis.dto.ProductDto;
import ru.itis.dto.WishlistDto;

import java.util.List;

public interface ProductService {
    List<ProductDto> getAllProducts();
    ProductDto getProductById(long id);
    void deleteProductById(long id);
    void saveProduct(ProductDto productDto);
    List<ProductDto> getProductsByCategory(long categoryId);
    List<ProductDto> getProductsByCategoryWishlist(long categoryId, WishlistDto userWishlist);
}
