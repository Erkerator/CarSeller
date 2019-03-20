package com.epam.carseller.action;

import com.epam.carseller.database.ModelDAO;
import com.epam.carseller.entity.Model;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CorrectModelAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ModelDAO modelDAO = new ModelDAO();
        Model model = new Model();
        int modelId = Integer.parseInt(request.getParameter("modelToUpdate"));
        int brandId = Integer.parseInt(request.getParameter("brandToUpdateModel"));
        String modelChanges = request.getParameter("changedValue");
        model.setModelId(modelId);
        model.setBrandId(brandId);
        model.setModel(modelChanges);
        modelDAO.update(model);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/result.jsp");
        requestDispatcher.forward(request, response);
    }
}
