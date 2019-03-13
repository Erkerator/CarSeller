package com.epam.carseller.action;

import com.epam.carseller.database.CategoryDAO;
import com.epam.carseller.database.LanguagesDAO;
import com.epam.carseller.entity.Category;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class ShowCategoriesAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        LanguagesDAO languagesDAO = new LanguagesDAO();
        String language = (String) session.getAttribute("lang");
        int languageId = languagesDAO.getLanguageIdByLocale(language);
        String role = (String) session.getAttribute("role");
        if (!role.equals("Admin") || role.isEmpty()) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/login.jsp");
        } else {
            CategoryDAO categoryDAO = new CategoryDAO();
            List<Category> listOfCategories = categoryDAO.getAll(languageId);
            request.setAttribute("categories", listOfCategories);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/categories.jsp");
            requestDispatcher.forward(request, response);
        }

    }
}
