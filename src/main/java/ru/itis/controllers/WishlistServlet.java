package ru.itis.controllers;

import ru.itis.dto.UserDto;
import ru.itis.dto.WishlistDto;
import ru.itis.services.WishlistService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/wishlist")
public class WishlistServlet extends HttpServlet {

    private WishlistService wishlistService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        wishlistService = (WishlistService) config.getServletContext().getAttribute("wishlistService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserDto user = (UserDto) req.getSession().getAttribute("user");
        System.out.println(user);
        WishlistDto wishlist = wishlistService.userWishlist(user.getId());
        if (wishlist == null) {
            wishlistService.saveWishListByUser(user.getId());
            wishlist = wishlistService.userWishlist(user.getId());
        }

        req.getSession().setAttribute("products", wishlist.getProducts());

        req.getRequestDispatcher("/wishlist.ftl").forward(req, resp);
    }
}
