package ru.itis.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    private Long id;
    private String email;
    private String firstname;
    private String lastname;
    @Builder.Default
    private String role = "USER";
    private String password;
    private Wishlist wishlist;
}
