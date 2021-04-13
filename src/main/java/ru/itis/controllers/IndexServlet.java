package ru.itis.controllers;

import ru.itis.dto.CategoryDto;
import ru.itis.dto.ProductDto;
import ru.itis.dto.UserDto;
import ru.itis.dto.WishlistDto;
import ru.itis.models.Wishlist;
import ru.itis.repositories.WishlistRepository;
import ru.itis.services.CategoryService;
import ru.itis.services.ProductService;
import ru.itis.services.WishlistService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/index")
public class IndexServlet extends HttpServlet {

    private ProductService productService;
    private CategoryService categoryService;

    private WishlistService wishlistService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        productService = (ProductService) config.getServletContext().getAttribute("productService");
        categoryService = (CategoryService) config.getServletContext().getAttribute("categoryService");
        wishlistService = (WishlistService) config.getServletContext().getAttribute("wishlistService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String category = req.getParameter("category");
        String wishlist = req.getParameter("wishlist");

        List<ProductDto> allProducts;

        UserDto user = (UserDto) req.getSession().getAttribute("user");
        WishlistDto wishlistDto = null;

        if (user != null) {
            wishlistDto = wishlistService.userWishlist(user.getId());

            if (wishlistDto == null) {
                wishlistService.saveWishListByUser(user.getId());
                wishlistDto = wishlistService.userWishlist(user.getId());
            }
        }

        if (category != null) {
            long categoryId = Long.parseLong(category);
            if (wishlist != null) {
                allProducts = productService.getProductsByCategoryWishlist(categoryId, wishlistDto);
            } else {
                allProducts = productService.getProductsByCategory(categoryId);
            }
        } else if (wishlist != null) {
            allProducts = wishlistDto.getProducts();
        } else {
            allProducts = productService.getAllProducts();
        }


        List<CategoryDto> allCategories = categoryService.getAllCategories();

        req.getSession().setAttribute("products", allProducts);
        req.getSession().setAttribute("categories", allCategories);

        req.getRequestDispatcher("/index.ftl").forward(req, resp);
    }
}
