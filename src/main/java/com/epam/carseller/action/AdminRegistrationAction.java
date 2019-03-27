package com.epam.carseller.action;

import com.epam.carseller.database.UserDAO;
import com.epam.carseller.entity.User;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AdminRegistrationAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String DEFAULT_ADMIN_ROLE = "Admin";
        boolean existence = false;
        UserDAO userDAO = new UserDAO();
        User user = new User();
        List<User> listOfUsers = userDAO.getAll();
        String username = request.getParameter("username").trim().toUpperCase();
        String password = request.getParameter("password").trim();
        String passwordRepeat = request.getParameter("passwordRepeat").trim();
        String firstName = request.getParameter("firstName").trim();
        String secondName = request.getParameter("secondName").trim();
        for (User element:listOfUsers) {
            if (element.getUsername().toUpperCase().equals(username)) {
                existence = true;
            }
        }
        if (!username.isEmpty() && !password.isEmpty() && password.equals(passwordRepeat) && !firstName.isEmpty() && !existence) {
            user.setUsername(username);
            user.setPassword(DigestUtils.md5Hex(password));
            user.setFirstName(firstName);
            user.setSecondName(secondName);
            user.setRole(DEFAULT_ADMIN_ROLE);
            userDAO.insert(user);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/result.jsp");
            requestDispatcher.forward(request,response);
        } else {
            request.setAttribute("incorrectData", true);
            if (existence) {
                request.setAttribute("userExists", true);
            }
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/adminRegistration.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
