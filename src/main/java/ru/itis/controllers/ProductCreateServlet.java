package ru.itis.controllers;

import ru.itis.dto.CategoryDto;
import ru.itis.dto.ProductDto;
import ru.itis.services.CategoryService;
import ru.itis.services.ProductService;
import ru.itis.utils.Constants;
import ru.itis.utils.FileSaver;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;

@WebServlet("/product/action/save")
@MultipartConfig
public class ProductCreateServlet extends HttpServlet {

    private CategoryService categoryService;

    private ProductService productService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        categoryService = (CategoryService) config.getServletContext().getAttribute("categoryService");
        productService = (ProductService) config.getServletContext().getAttribute("productService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().setAttribute("categories", categoryService.getAllCategories());

        req.getRequestDispatcher("/product_create.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Part img = req.getPart("image");
        String name = req.getParameter("name");
        Double price = Double.parseDouble(req.getParameter("price"));
        
        CategoryDto category = categoryService.getCategoryById(Long.parseLong(req.getParameter("category")));

        String imgName = FileSaver.save(img, Constants.UPLOAD_DIR_IMG);

        ProductDto productDto = ProductDto.builder()
                .name(name)
                .category(category)
                .imageName(imgName)
                .price(price)
                .build();

        productService.saveProduct(productDto);

        resp.sendRedirect("/index");
    }
}
