package ru.itis.controllers;

import org.apache.commons.io.IOUtils;
import ru.itis.utils.Constants;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@WebServlet("/img")
public class ImgDistributionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fileName = req.getParameter("name");
        resp.setContentType("image/png");
        try {
            IOUtils.copyLarge(
                    new FileInputStream(Constants.UPLOAD_DIR_IMG + File.separator + fileName),
                    resp.getOutputStream()
            );

        } catch (IOException ioExp) {
            ioExp.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
