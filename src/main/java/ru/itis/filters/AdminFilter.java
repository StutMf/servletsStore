package ru.itis.filters;

import ru.itis.dto.UserDto;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({"/product/action/*", "/category/action/*"})
public class AdminFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        UserDto user = (UserDto) request.getSession().getAttribute("user");

        if (user != null && user.getRole().equals("ADMIN")) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        response.sendRedirect("/index");
    }

    @Override
    public void destroy() {

    }
}
