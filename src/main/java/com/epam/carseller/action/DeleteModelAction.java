package com.epam.carseller.action;

import com.epam.carseller.database.ModelDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteModelAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ModelDAO modelDAO = new ModelDAO();
        RequestDispatcher requestDispatcher;
        if (request.getParameterMap().containsKey("modelToDelete")) {
            String modelToDelete = request.getParameter("modelToDelete");
            if (!modelToDelete.isEmpty()) {
                modelDAO.delete(Integer.parseInt(modelToDelete));
                requestDispatcher = request.getRequestDispatcher("/pages/result.jsp");
                requestDispatcher.forward(request, response);
            } else {
                request.setAttribute("incorrectData", true);
                requestDispatcher = request.getRequestDispatcher("/pages/models.jsp");
                requestDispatcher.forward(request, response);
            }
        } else {
            request.setAttribute("incorrectData", true);
            requestDispatcher = request.getRequestDispatcher("/pages/models.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
