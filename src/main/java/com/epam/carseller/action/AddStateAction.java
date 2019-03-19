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

public class AddStateAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        LanguagesDAO languagesDAO = new LanguagesDAO();
        String language = (String) session.getAttribute("lang");
        int languageId = languagesDAO.getLanguageIdByLocale(language);
        StateDAO stateDAO = new StateDAO();
        State state = new State();
        String newState = request.getParameter("newState");
        state.setLanguageId(languageId);
        state.setState(newState);
        stateDAO.insert(state);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/result.jsp");
        requestDispatcher.forward(request, response);
    }
}
