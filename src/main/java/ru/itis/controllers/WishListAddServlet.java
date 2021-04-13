package ru.itis.controllers;

import ru.itis.dto.ProductDto;
import ru.itis.dto.UserDto;
import ru.itis.dto.WishlistDto;
import ru.itis.models.Wishlist;
import ru.itis.services.ProductService;
import ru.itis.services.WishlistService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/wishlist/action/add")
public class WishListAddServlet extends HttpServlet {

    private WishlistService wishlistService;

    private ProductService productService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        wishlistService = (WishlistService) config.getServletContext().getAttribute("wishlistService");
        productService = (ProductService) config.getServletContext().getAttribute("productService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long productId = Long.parseLong(req.getParameter("id"));

        UserDto user = (UserDto) req.getSession().getAttribute("user");
        WishlistDto wishlist = wishlistService.userWishlist(user.getId());

        if (wishlist == null) {
            wishlistService.saveWishListByUser(user.getId());
            wishlist = wishlistService.userWishlist(user.getId());
        }

        ProductDto productDto = productService.getProductById(productId);

        wishlistService.addProduct(productDto, wishlist.getId());

        req.getRequestDispatcher("/wishlist").forward(req, resp);
    }
}
