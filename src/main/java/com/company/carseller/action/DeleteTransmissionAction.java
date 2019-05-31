package com.company.carseller.action;

import com.company.carseller.database.TransmissionDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteTransmissionAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TransmissionDAO transmissionDAO = new TransmissionDAO();
        RequestDispatcher requestDispatcher;
        if (request.getParameterMap().containsKey("transmissionToDelete")) {
            String transmissionToDelete = request.getParameter("transmissionToDelete");
            if (!transmissionToDelete.isEmpty()) {
                transmissionDAO.delete(Integer.parseInt(transmissionToDelete));
                requestDispatcher = request.getRequestDispatcher("/pages/result.jsp");
                requestDispatcher.forward(request, response);
            } else {
                request.setAttribute("incorrectData", true);
                requestDispatcher = request.getRequestDispatcher("/pages/transmissions.jsp");
                requestDispatcher.forward(request, response);
            }
        } else {
            request.setAttribute("incorrectData", true);
            requestDispatcher = request.getRequestDispatcher("/pages/transmissions.jsp");
            requestDispatcher.forward(request, response);
        }



    }
}
