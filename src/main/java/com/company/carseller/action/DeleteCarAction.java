package com.company.carseller.action;

import com.company.carseller.database.CarDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DeleteCarAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if (session.getAttribute("userId") == null) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/login.jsp");
            requestDispatcher.forward(request, response);
        } else {
            int carId = Integer.parseInt(request.getParameter("carid"));
            CarDAO carDAO = new CarDAO();
            carDAO.delete(carId);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/account.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
