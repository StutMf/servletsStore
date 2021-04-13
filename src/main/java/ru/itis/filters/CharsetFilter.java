package ru.itis.filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter({"/product/*", "/index", "/signIn", "/signUp", "/category/*", "/wishlist/*", "/img"})
public class CharsetFilter implements Filter {
    private String encoding;

    public void init(FilterConfig config) {
        encoding = "UTF-8";
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {
        request.setCharacterEncoding(encoding);
        response.setContentType("text/html;charset=UTF-8");
        filterChain.doFilter(request, response);
    }

    public void destroy() {
    }
}
