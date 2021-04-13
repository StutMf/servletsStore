package ru.itis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.models.Wishlist;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class WishlistDto {
    private Long id;
    private List<ProductDto> products;

    public static WishlistDto from(Wishlist wishlist) {
        return WishlistDto.builder()
                .id(wishlist.getId())
                .products(ProductDto.from(wishlist.getProducts()))
                .build();
    }
}
