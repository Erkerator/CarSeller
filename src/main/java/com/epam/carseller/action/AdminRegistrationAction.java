package com.epam.carseller.action;

import com.epam.carseller.database.UserDAO;
import com.epam.carseller.entity.User;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminRegistrationAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String DEFAULT_ADMIN_ROLE = "Admin";
        UserDAO userDAO = new UserDAO();
        User user = new User();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String passwordRepeat = request.getParameter("passwordRepeat");
        String firstName = request.getParameter("firstName");
        String secondName = request.getParameter("secondName");
        if (!username.isEmpty() && !password.isEmpty() && password.equals(passwordRepeat) && !firstName.isEmpty()) {
            user.setUsername(username);
            user.setPassword(DigestUtils.md5Hex(password));
            user.setFirstName(firstName);
            user.setSecondName(secondName);
            user.setRole(DEFAULT_ADMIN_ROLE);
            userDAO.insert(user);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/result.jsp");
            requestDispatcher.forward(request,response);
        } else {
            request.setAttribute("incorrectData", 1);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/adminRegistration.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
