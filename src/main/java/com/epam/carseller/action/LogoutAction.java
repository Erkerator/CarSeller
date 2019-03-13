package com.epam.carseller.action;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LogoutAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().removeAttribute("username");
        request.getSession().removeAttribute("userId");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher( "/pages/welcome.jsp");
        requestDispatcher.forward(request, response);
    }
}
