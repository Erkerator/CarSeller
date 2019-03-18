package com.epam.carseller.action;

import com.epam.carseller.database.TransmissionDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteTransmissionAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int transmissionId = Integer.parseInt(request.getParameter("transmissionToDelete"));
        TransmissionDAO transmissionDAO = new TransmissionDAO();
        transmissionDAO.delete(transmissionId);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/result.jsp");
        requestDispatcher.forward(request, response);
    }
}
