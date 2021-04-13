package ru.itis.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter({"/wishlist/*"})
public class SecurityFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Boolean authenticated = (Boolean) servletRequest.getServletContext().getAttribute("authenticated");
        if (authenticated != null && authenticated) {
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }

        response.sendRedirect("/signIn");
    }

    @Override
    public void destroy() {

    }
}
