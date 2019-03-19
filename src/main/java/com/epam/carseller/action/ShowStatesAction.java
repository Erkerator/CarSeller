package com.epam.carseller.action;

import com.epam.carseller.database.LanguagesDAO;
import com.epam.carseller.database.StateDAO;
import com.epam.carseller.entity.State;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ShowStatesAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        LanguagesDAO languagesDAO = new LanguagesDAO();
        String language = (String) session.getAttribute("lang");
        int languageId = languagesDAO.getLanguageIdByLocale(language);
        StateDAO stateDAO = new StateDAO();
        List<State> listOfStates = stateDAO.getAll(languageId);
        request.setAttribute("states", listOfStates);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/states.jsp");
        requestDispatcher.forward(request,response);
    }
}
