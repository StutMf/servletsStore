package ru.itis.services;

import ru.itis.dto.ProductDto;
import ru.itis.dto.WishlistDto;
import ru.itis.models.Category;
import ru.itis.models.Product;
import ru.itis.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products != null ? ProductDto.from(products) : null;

    }

    @Override
    public ProductDto getProductById(long id) {
        Product product = productRepository.findById(id);
        return product != null ? ProductDto.from(product) : null;
    }

    @Override
    public void deleteProductById(long id) {
        productRepository.deleteById(id);
    }

    @Override
    public void saveProduct(ProductDto product) {
        Category category = Category.builder()
                .id(product.getCategory().getId())
                .name(product.getCategory().getName())
                .build();

        Product newProduct = Product.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .imageName(product.getImageName())
                .category(category)
                .build();

        productRepository.save(newProduct);
    }

    @Override
    public List<ProductDto> getProductsByCategory(long categoryId) {
        List<Product> products = productRepository.findByCategory(categoryId);
        return products != null ? ProductDto.from(products) : null;
    }

    @Override
    public List<ProductDto> getProductsByCategoryWishlist(long categoryId, WishlistDto userWishlist) {
        List<ProductDto> products = getProductsByCategory(categoryId);
        Set<ProductDto> productsCategory = new HashSet<>(products);
        Set<ProductDto> productsWishlist = new HashSet<>(userWishlist.getProducts());

        productsCategory.retainAll(productsWishlist);

        return new ArrayList<>(productsCategory);
    }
}
