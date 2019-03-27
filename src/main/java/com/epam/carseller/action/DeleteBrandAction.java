package com.epam.carseller.action;

import com.epam.carseller.database.BrandDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteBrandAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BrandDAO brandDAO = new BrandDAO();
        RequestDispatcher requestDispatcher;
        if (request.getParameterMap().containsKey("brandToDelete")) {
            String brandId = request.getParameter("brandToDelete");
            if (!brandId.isEmpty()) {
                brandDAO.delete(Integer.parseInt(brandId));
                requestDispatcher = request.getRequestDispatcher("/pages/result.jsp");
                requestDispatcher.forward(request, response);
            } else {
                request.setAttribute("incorrectData", true);
                requestDispatcher = request.getRequestDispatcher("/pages/brands.jsp");
                requestDispatcher.forward(request, response);
            }
        } else {
            request.setAttribute("incorrectData", true);
            requestDispatcher = request.getRequestDispatcher("/pages/brands.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
