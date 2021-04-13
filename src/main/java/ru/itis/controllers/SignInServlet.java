package ru.itis.controllers;

import ru.itis.dto.SignInForm;
import ru.itis.dto.UserDto;
import ru.itis.services.SignInService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {

    private SignInService signInService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        signInService = (SignInService) config.getServletContext().getAttribute("signInService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/signIn.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        SignInForm signInForm = new SignInForm(email, password);

        UserDto userDto = signInService.signIn(signInForm);

        if (userDto != null) {
            req.getSession().setAttribute("user", userDto);
            req.getServletContext().setAttribute("authenticated", true);
            resp.sendRedirect("/index");
            return;
        }

        resp.sendRedirect("/signIn");
    }
}
