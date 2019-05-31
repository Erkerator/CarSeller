package com.company.carseller.action;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


public class AdministrateAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        RequestDispatcher requestDispatcher;
        if (session.getAttribute("role") == null) {
            requestDispatcher = request.getRequestDispatcher("/pages/login.jsp");
            requestDispatcher.forward(request, response);
        } else {
            String role = (String) session.getAttribute("role");
            if (!role.equals("Admin")){
                requestDispatcher = request.getRequestDispatcher("/pages/login.jsp");
                requestDispatcher.forward(request, response);
            } else {
                requestDispatcher = request.getRequestDispatcher("/pages/admin.jsp");
                requestDispatcher.forward(request, response);
            }
        }
    }
}
