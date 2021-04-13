package ru.itis.controllers;

import ru.itis.dto.ProductDto;
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

@WebServlet("/wishlist/action/delete")
public class WishlistDeleteServlet extends HttpServlet {

    private WishlistService wishlistService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        wishlistService = (WishlistService) config.getServletContext().getAttribute("wishlistService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = Long.parseLong(req.getParameter("id"));

        UserDto userDto = (UserDto) req.getSession().getAttribute("user");
        WishlistDto wishlistDto = wishlistService.userWishlist(userDto.getId());

        ProductDto productDto = ProductDto.builder()
                .id(id)
                .build();

        wishlistService.deleteProduct(productDto, wishlistDto.getId());

        resp.sendRedirect("/wishlist");
    }
}
