package com.company.carseller.action;

import com.company.carseller.database.*;
import com.company.carseller.entity.*;
import com.company.carseller.util.CarsellerConstants;
import com.company.carseller.database.*;
import com.company.carseller.entity.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class GetListOfCarsAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String categoryParameter = request.getParameter("Category");
        String modelParameter = request.getParameter("Model");
        String transmissionParameter = request.getParameter("Transmission");
        String stateParameter = request.getParameter("State");
        String language = (String) request.getSession().getAttribute("lang");

        CarDAO carDAO = new CarDAO();
        LanguagesDAO languagesDAO = new LanguagesDAO();
        int languageId = languagesDAO.getLanguageIdByLocale(language);
        System.out.println(languageId);
        StateDAO stateDAO = new StateDAO();
        ModelDAO modelDAO = new ModelDAO();
        CategoryDAO categoryDAO = new CategoryDAO();
        TransmissionDAO transmissionDAO = new TransmissionDAO();

        List<State> listOfStates = stateDAO.getAll(languageId);
        List<Model> listOfModels = modelDAO.getAll();
        List<Category> listOfCategories = categoryDAO.getAll(languageId);
        List<Transmission> listOfTransmissions = transmissionDAO.getAll(languageId);
        List<Car> listOfCars = carDAO.getCarsByParameters(categoryParameter, modelParameter, transmissionParameter, stateParameter, languageId);

        request.setAttribute("carsList", listOfCars);
        request.setAttribute("statesList", listOfStates);
        request.setAttribute("categoriesList", listOfCategories);
        request.setAttribute("modelsList", listOfModels);
        request.setAttribute("transmissionsList", listOfTransmissions);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(CarsellerConstants.SEARCH_CAR);
        requestDispatcher.forward(request, response);
    }
}
