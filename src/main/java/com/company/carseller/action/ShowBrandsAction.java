package com.company.carseller.action;

import com.company.carseller.database.BrandDAO;
import com.company.carseller.entity.Brand;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ShowBrandsAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");
        RequestDispatcher requestDispatcher;
        if (!role.equals("Admin")) {
            requestDispatcher = request.getRequestDispatcher("/pages/login.jsp");
            requestDispatcher.forward(request, response);
        } else {
            BrandDAO brandDAO = new BrandDAO();
            List<Brand> listOfBrands = brandDAO.getAll();
            request.setAttribute("brands", listOfBrands);
            requestDispatcher = request.getRequestDispatcher("/pages/brands.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
