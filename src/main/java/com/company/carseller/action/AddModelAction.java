package com.company.carseller.action;

import com.company.carseller.entity.Model;
import com.company.carseller.database.ModelDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddModelAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ModelDAO modelDAO = new ModelDAO();
        Model model = new Model();
        int brandId = Integer.parseInt(request.getParameter("brand"));
        String newModel = request.getParameter("newModel");
        model.setBrandId(brandId);
        model.setModel(newModel);
        modelDAO.insert(model);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/result.jsp");
        requestDispatcher.forward(request, response);
    }
}
