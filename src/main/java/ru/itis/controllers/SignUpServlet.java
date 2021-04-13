package ru.itis.controllers;

import ru.itis.dto.SignUpForm;
import ru.itis.dto.UserDto;
import ru.itis.services.SignUpService;
import ru.itis.services.UserService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {

    private SignUpService signUpService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        signUpService = (SignUpService) config.getServletContext().getAttribute("signUpService");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/signUp.ftl").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String password = req.getParameter("password");
        String passwordAgain = req.getParameter("passwordAgain");

        SignUpForm signUpForm = new SignUpForm(firstname, lastname, email, password, passwordAgain);

        UserDto userDto = signUpService.signUp(signUpForm);

        if (userDto != null) {
            req.getSession().setAttribute("user", userDto);
            req.getServletContext().setAttribute("authenticated", true);
            resp.sendRedirect("/index");
            return;
        }

        resp.sendRedirect("/signUp");
    }
}
