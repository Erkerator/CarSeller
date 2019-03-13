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
        Float engineVolume = Float.parseFloat(request.getParameter("EngineVolume"));

        System.out.println(model);
        System.out.println(transmission);
        System.out.println(category);
        System.out.println(state);
        System.out.println(yearOfProduce);
        System.out.println(engineVolume);
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        String currentDate = dateFormat.format(date);
        System.out.println(currentDate);

        CarDAO carDAO = new CarDAO();
        Car car = new Car();

        InputStream inputStream = null;
        Part filePart = request.getPart("photo");

        if (filePart != null) {
            System.out.println(filePart.getName());
            System.out.println(filePart.getSize());
            System.out.println(filePart.getContentType());

            inputStream = filePart.getInputStream();
        }
        car.setModelId(request.getParameter("Model"));
        car.setTransmissionId(request.getParameter("Transmission"));
        car.setCategoryId(request.getParameter("Category"));
        car.setStateId(request.getParameter("State"));
        try {
            car.setYearOfProduce(new SimpleDateFormat("yyyy").parse(request.getParameter("YearOfProduce")));
            car.setDateOfCreation(new SimpleDateFormat("dd-MM-yyyy").parse(currentDate));
        } catch (ParseException e) {
            logger.error("Date converting: " + e);
        }
        car.setEngineVolume(Float.parseFloat(request.getParameter("EngineVolume")));
        byte image[] = IOUtils.toByteArray(inputStream);
        car.setPhoto(image);
        car.setLanguageId(languageId);
        car.setUserId(Integer.toString(userId));
        carDAO.insert(car);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/account.jsp");
        requestDispatcher.forward(request,response);
    }
}
