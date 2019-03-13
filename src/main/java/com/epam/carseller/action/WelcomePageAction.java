package com.epam.carseller.action;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import static com.epam.carseller.util.CarsellerConstants.WELCOME_PAGE;

public class WelcomePageAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/welcome.jsp");
        requestDispatcher.forward(request, response);
    }
}
