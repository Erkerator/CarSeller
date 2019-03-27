package com.epam.carseller.action;

import com.epam.carseller.database.CategoryDAO;
import com.epam.carseller.database.LanguagesDAO;
import com.epam.carseller.entity.Category;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
