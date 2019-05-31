package com.company.carseller.action;

import com.company.carseller.database.LanguagesDAO;
import com.company.carseller.database.StateDAO;
import com.company.carseller.entity.State;

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
        RequestDispatcher requestDispatcher;
        LanguagesDAO languagesDAO = new LanguagesDAO();
        String language = (String) session.getAttribute("lang");
        int languageId = languagesDAO.getLanguageIdByLocale(language);
        StateDAO stateDAO = new StateDAO();
        State state = new State();
        if (request.getParameterMap().containsKey("newState")) {
            String newState = request.getParameter("newState").trim();
            if (!newState.isEmpty()) {
                state.setLanguageId(languageId);
                state.setState(newState);
                stateDAO.insert(state);
                requestDispatcher = request.getRequestDispatcher("/pages/result.jsp");
                requestDispatcher.forward(request, response);
            } else {
                request.setAttribute("incorrectData", true);
                requestDispatcher = request.getRequestDispatcher("/pages/states.jsp");
                requestDispatcher.forward(request,response);
            }
        } else {
            request.setAttribute("incorrectData", true);
            requestDispatcher = request.getRequestDispatcher("/pages/states.jsp");
            requestDispatcher.forward(request,response);
        }




    }
}
