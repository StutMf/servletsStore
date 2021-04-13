package ru.itis.services;

import ru.itis.dto.ProductDto;
import ru.itis.dto.WishlistDto;

public interface WishlistService {
    WishlistDto userWishlist(long id);

    void saveWishListByUser(Long id);

    void addProduct(ProductDto productDto, long id);

    void deleteProduct(ProductDto productDto, long id);
}
