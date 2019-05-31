package com.company.carseller.action;

import com.company.carseller.entity.Model;
import com.company.carseller.database.ModelDAO;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetModelsAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int brandId = Integer.parseInt(request.getParameter("brandId"));
        ModelDAO modelDAO = new ModelDAO();
        List<Model> listOfModels = modelDAO.getAll();
        Map<Integer, String> filteredModels = new HashMap<>();
        for (Model model:listOfModels) {
            int currentModelId = model.getModelId();
            String currentModel = model.getModel();
            if (model.getBrandId() == brandId) {
                filteredModels.put(currentModelId, currentModel);
            }
        }

        String json = new Gson().toJson(filteredModels);
        response.setContentType("application/json");
        response.getWriter().write(json);
    }
}
