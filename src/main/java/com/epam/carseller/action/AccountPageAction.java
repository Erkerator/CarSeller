package com.epam.carseller.action;

import com.epam.carseller.database.CarDAO;
import com.epam.carseller.database.LanguagesDAO;
import com.epam.carseller.entity.Car;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class AccountPageAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        CarDAO carDAO = new CarDAO();
        LanguagesDAO languagesDAO = new LanguagesDAO();
        String language = (String) request.getSession().getAttribute("lang");
        int languageId = languagesDAO.getLanguageIdByLocale(language);
        if (session.getAttribute("userId") == null) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/login.jsp");
            requestDispatcher.forward(request, response);
        } else {
            List<Car> userCars = carDAO.getAllUsersCar((Integer)session.getAttribute("userId"), languageId);
            request.setAttribute("myCars", userCars);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/account.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
