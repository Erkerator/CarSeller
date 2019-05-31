package com.company.carseller.action;

import com.company.carseller.database.BrandDAO;
import com.company.carseller.entity.Brand;
import com.company.carseller.entity.Model;
import com.company.carseller.database.ModelDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ShowModelsAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String role = (String) session.getAttribute("role");
        RequestDispatcher requestDispatcher;
        if (!role.equals("Admin")) {
            requestDispatcher = request.getRequestDispatcher("/pages/login.jsp");
            requestDispatcher.forward(request, response);
        } else {
            ModelDAO modelDAO = new ModelDAO();
            BrandDAO brandDAO = new BrandDAO();
            List<Model> listOfModels = modelDAO.getAll();
            List<Brand> listOfBrands = brandDAO.getAll();
            request.setAttribute("models", listOfModels);
            request.setAttribute("brands", listOfBrands);
            requestDispatcher = request.getRequestDispatcher("/pages/models.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
