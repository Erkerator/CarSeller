package com.company.carseller.action;

import com.company.carseller.database.*;
import com.company.carseller.entity.*;
import com.epam.carseller.database.*;
import com.epam.carseller.entity.*;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class SaveCarAction implements Action {
    Logger logger = Logger.getLogger(SaveCarAction.class);

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String language = (String) session.getAttribute("lang");
        LanguagesDAO languagesDAO = new LanguagesDAO();
        int languageId = languagesDAO.getLanguageIdByLocale(language);
        int userId = (Integer) session.getAttribute("userId");
        String model = request.getParameter("Model");
        String transmission = request.getParameter("Transmission");
        String category = request.getParameter("Category");
        String state = request.getParameter("State");
        String yearOfProduce = request.getParameter("YearOfProduce");
        String engineVolume = request.getParameter("EngineVolume");
        RequestDispatcher requestDispatcher;

        if (model != null && transmission != null && category != null && state != null && yearOfProduce != null && engineVolume != null) {
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date date = new Date();
            String currentDate = dateFormat.format(date);
            CarDAO carDAO = new CarDAO();
            Car car = new Car();
            InputStream inputStream = null;
            Part filePart = request.getPart("photo");
            if (filePart != null) {
                inputStream = filePart.getInputStream();
            }
            car.setModelId(model);
            car.setTransmissionId(transmission);
            car.setCategoryId(category);
            car.setStateId(state);
            try {
                car.setYearOfProduce(new SimpleDateFormat("yyyy").parse(yearOfProduce));
                car.setDateOfCreation(new SimpleDateFormat("dd-MM-yyyy").parse(currentDate));
            } catch (ParseException e) {
                logger.error("Date converting: " + e);
            }
            car.setEngineVolume(Float.parseFloat(engineVolume));
            byte image[] = IOUtils.toByteArray(inputStream);
            car.setPhoto(image);
            car.setLanguageId(languageId);
            car.setUserId(Integer.toString(userId));
            carDAO.insert(car);
            requestDispatcher = request.getRequestDispatcher("/pages/account.jsp");
            requestDispatcher.forward(request,response);
        } else {
            ModelDAO modelDAO = new ModelDAO();
            TransmissionDAO transmissionDAO = new TransmissionDAO();
            CategoryDAO categoryDAO = new CategoryDAO();
            StateDAO stateDAO = new StateDAO();
            List<Model> listOfModels = modelDAO.getAll();
            List<Transmission> listOfTransmissions = transmissionDAO.getAll(languageId);
            List<Category> listOfCategories = categoryDAO.getAll(languageId);
            List<State> listOfStates = stateDAO.getAll(languageId);
            request.setAttribute("statesList", listOfStates);
            request.setAttribute("categoriesList", listOfCategories);
            request.setAttribute("modelsList", listOfModels);
            request.setAttribute("transmissionsList", listOfTransmissions);
            request.setAttribute("emptyFields", true);
            requestDispatcher = request.getRequestDispatcher("/pages/addCar.jsp");
            requestDispatcher.forward(request,response);
        }
    }
}
