package com.company.carseller.action;

import com.company.carseller.database.StateDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteStateAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StateDAO stateDAO = new StateDAO();
        RequestDispatcher requestDispatcher;
        if (request.getParameterMap().containsKey("stateToDelete")) {
            String stateToDelete = request.getParameter("stateToDelete");
            if (!stateToDelete.isEmpty()) {
                stateDAO.delete(Integer.parseInt(stateToDelete));
                requestDispatcher = request.getRequestDispatcher("/pages/result.jsp");
                requestDispatcher.forward(request, response);
            } else {
                request.setAttribute("incorrectData", true);
                requestDispatcher = request.getRequestDispatcher("/pages/states.jsp");
                requestDispatcher.forward(request, response);
            }
        } else {
            request.setAttribute("incorrectData", true);
            requestDispatcher = request.getRequestDispatcher("/pages/states.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
