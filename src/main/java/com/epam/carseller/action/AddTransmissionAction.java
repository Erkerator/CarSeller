package com.epam.carseller.action;

import com.epam.carseller.database.LanguagesDAO;
import com.epam.carseller.database.TransmissionDAO;
import com.epam.carseller.entity.Transmission;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class AddTransmissionAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        LanguagesDAO languagesDAO = new LanguagesDAO();
        String language = (String) session.getAttribute("lang");
        int languageId = languagesDAO.getLanguageIdByLocale(language);
        TransmissionDAO transmissionDAO = new TransmissionDAO();
        Transmission transmission = new Transmission();
        String newTransmission = request.getParameter("newTransmission");
        transmission.setLanguageId(languageId);
        transmission.setTransmission(newTransmission);
        transmissionDAO.insert(transmission);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/result.jsp");
        requestDispatcher.forward(request, response);
    }
}
