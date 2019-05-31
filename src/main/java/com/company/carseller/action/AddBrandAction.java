package com.company.carseller.action;

import com.company.carseller.database.BrandDAO;
import com.company.carseller.entity.Brand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddBrandAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BrandDAO brandDAO = new BrandDAO();
        Brand brand = new Brand();
        RequestDispatcher requestDispatcher;
        String newBrand = request.getParameter("newBrand").trim();
        if (!newBrand.isEmpty()) {
            brand.setBrand(newBrand);
            brandDAO.insert(brand);
            requestDispatcher = request.getRequestDispatcher("/pages/result.jsp");
            requestDispatcher.forward(request, response);
        } else {
            request.setAttribute("incorrectData", true);
            requestDispatcher = request.getRequestDispatcher("/pages/brands.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
