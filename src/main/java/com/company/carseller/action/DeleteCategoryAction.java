package com.company.carseller.action;

import com.company.carseller.database.CategoryDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteCategoryAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CategoryDAO categoryDAO = new CategoryDAO();
        RequestDispatcher requestDispatcher;
        if (request.getParameterMap().containsKey("categoryToDelete")) {
            String categoryToDelete = request.getParameter("categoryToDelete");
            if (!categoryToDelete.isEmpty()) {
                categoryDAO.delete(Integer.parseInt(categoryToDelete));
                requestDispatcher = request.getRequestDispatcher("/pages/result.jsp");
                requestDispatcher.forward(request,response);
            } else {
                request.setAttribute("incorrectData", true);
                requestDispatcher = request.getRequestDispatcher("/pages/categories.jsp");
                requestDispatcher.forward(request, response);
            }
        } else {
            request.setAttribute("incorrectData", true);
            requestDispatcher = request.getRequestDispatcher("/pages/categories.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
