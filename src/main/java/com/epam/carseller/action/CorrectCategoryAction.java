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

public class CorrectCategoryAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        LanguagesDAO languagesDAO = new LanguagesDAO();
        String language = (String) session.getAttribute("lang");
        int languageId = languagesDAO.getLanguageIdByLocale(language);
        CategoryDAO categoryDAO = new CategoryDAO();
        Category category = new Category();
        int categoryId = Integer.parseInt(request.getParameter("categoryToUpdate"));
        String categoryChanges = request.getParameter("changedValue");
        category.setCategoryId(categoryId);
        category.setLanguageId(languageId);
        category.setCategory(categoryChanges);
        categoryDAO.update(category);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/result.jsp");
        requestDispatcher.forward(request, response);
    }
}
