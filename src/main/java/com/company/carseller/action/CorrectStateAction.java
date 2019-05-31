package com.company.carseller.action;

import com.company.carseller.database.LanguagesDAO;
import com.company.carseller.entity.State;
import com.company.carseller.database.StateDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class CorrectStateAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        LanguagesDAO languagesDAO = new LanguagesDAO();
        String language = (String) session.getAttribute("lang");
        int languageId = languagesDAO.getLanguageIdByLocale(language);
        StateDAO stateDAO = new StateDAO();
        State state = new State();
        int stateId = Integer.parseInt(request.getParameter("stateToUpdate"));
        String stateChanges = request.getParameter("changedValue");
        state.setStateId(stateId);
        state.setLanguageId(languageId);
        state.setState(stateChanges);
        stateDAO.update(state);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/result.jsp");
        requestDispatcher.forward(request, response);
    }
}
