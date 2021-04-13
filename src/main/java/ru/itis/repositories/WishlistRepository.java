package ru.itis.repositories;

import ru.itis.models.Product;
import ru.itis.models.Wishlist;

public interface WishlistRepository {
    Wishlist findByUserId(long id);
    void saveByUser(long id);
    void addProduct(Product product, long id);
    void deleteProduct(Product product, long id);
}
