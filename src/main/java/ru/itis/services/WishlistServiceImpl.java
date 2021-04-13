package ru.itis.services;

import ru.itis.dto.ProductDto;
import ru.itis.dto.WishlistDto;
import ru.itis.models.Product;
import ru.itis.models.Wishlist;
import ru.itis.repositories.WishlistRepository;

public class WishlistServiceImpl implements WishlistService {

    private final WishlistRepository wishlistRepository;

    public WishlistServiceImpl(WishlistRepository wishlistRepository) {
        this.wishlistRepository = wishlistRepository;
    }

    @Override
    public WishlistDto userWishlist(long id) {
        Wishlist wishlist = wishlistRepository.findByUserId(id);
        return wishlist != null ? WishlistDto.from(wishlist) : null;
    }

    @Override
    public void saveWishListByUser(Long id) {
        wishlistRepository.saveByUser(id);
    }

    @Override
    public void addProduct(ProductDto productDto, long id) {
        Product product = Product.builder()
                .id(productDto.getId())
                .build();

        wishlistRepository.addProduct(product, id);
    }

    @Override
    public void deleteProduct(ProductDto productDto, long id) {
        Product product = Product.builder()
                .id(productDto.getId())
                .build();

        wishlistRepository.deleteProduct(product, id);
    }
}
