package ru.itis.listeners;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.itis.controllers.WishlistServlet;
import ru.itis.repositories.*;
import ru.itis.services.*;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.util.Properties;

@WebListener()
public class ContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Properties properties = new Properties();
        try {
            properties.load(servletContextEvent.getServletContext().getResourceAsStream("/WEB-INF/resources/db.properties"));
        } catch (IOException e) {
            throw new IllegalArgumentException(e);
        }

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setJdbcUrl(properties.getProperty("url"));
        hikariConfig.setUsername(properties.getProperty("user"));
        hikariConfig.setPassword(properties.getProperty("password"));
        hikariConfig.setDriverClassName(properties.getProperty("driver"));
        HikariDataSource dataSource = new HikariDataSource(hikariConfig);

        JdbcTemplate template = new JdbcTemplate(dataSource);
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        UserRepository userRepository = new UserRepositoryImpl(template);
        UserService userService = new UserServiceImpl(userRepository);

        SignInService signInService = new SignInServiceImpl(passwordEncoder, userRepository);
        SignUpService signUpService = new SignUpServiceImpl(passwordEncoder, userRepository);

        CategoryRepository categoryRepository = new CategoryRepositoryImpl(template);
        CategoryService categoryService = new CategoryServiceImpl(categoryRepository);

        ProductRepository productRepository = new ProductRepositoryImpl(template, categoryRepository);
        ProductService productService = new ProductServiceImpl(productRepository);

        WishlistRepository wishlistRepository = new WishlistRepositoryImpl(template, categoryRepository);
        WishlistService wishlistService = new WishlistServiceImpl(wishlistRepository);

        servletContextEvent.getServletContext().setAttribute("userService", userService);
        servletContextEvent.getServletContext().setAttribute("signInService", signInService);
        servletContextEvent.getServletContext().setAttribute("signUpService", signUpService);
        servletContextEvent.getServletContext().setAttribute("productService", productService);
        servletContextEvent.getServletContext().setAttribute("categoryService", categoryService);
        servletContextEvent.getServletContext().setAttribute("wishlistService", wishlistService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }
}
