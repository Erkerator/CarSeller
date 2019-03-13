package com.epam.carseller.action;

import com.epam.carseller.database.*;
import com.epam.carseller.entity.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AddCarPageAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String language = (String) session.getAttribute("lang");
        LanguagesDAO languagesDAO = new LanguagesDAO();
        int languageId = languagesDAO.getLanguageIdByLocale(language);

        ModelDAO modelDAO = new ModelDAO();
        TransmissionDAO transmissionDAO = new TransmissionDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
        StateDAO stateDAO = new StateDAO();


        List<State> listOfStates = stateDAO.getAll(languageId);
        List<Model> listOfModels = modelDAO.getAll();
        List<Category> listOfCategories = categoryDAO.getAll(languageId);
        List<Transmission> listOfTransmissions = transmissionDAO.getAll(languageId);


        request.setAttribute("statesList", listOfStates);
        request.setAttribute("categoriesList", listOfCategories);
        request.setAttribute("modelsList", listOfModels);
        request.setAttribute("transmissionsList", listOfTransmissions);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/addCar.jsp");
        requestDispatcher.forward(request, response);
    }
}
