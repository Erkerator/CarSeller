package com.epam.carseller.action;

import com.epam.carseller.database.CarDAO;
import com.epam.carseller.database.LanguagesDAO;
import com.epam.carseller.entity.Car;
import com.epam.carseller.entity.Languages;
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
import java.sql.Blob;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
            request.setAttribute("emptyFields", true);
            requestDispatcher = request.getRequestDispatcher("/pages/addCar.jsp");
            requestDispatcher.forward(request,response);
        }
    }
}
