package com.company.carseller.action;

import com.company.carseller.database.UserDAO;
import com.company.carseller.entity.User;
import com.company.carseller.util.Validator;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class UserRegistrationAction implements Action {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String DEFAULT_USER_ROLE = "User";
        boolean existence = false;
        UserDAO userDAO = new UserDAO();
        User user = new User();
        List<User> listOfUsers = userDAO.getAll();
        String username = request.getParameter("username").trim().toUpperCase();
        String password = request.getParameter("password").trim();
        String passwordRepeat = request.getParameter("passwordRepeat").trim();
        String firstName = request.getParameter("firstName").trim();
        String secondName = request.getParameter("secondName").trim();
        String phoneNumber = request.getParameter("phoneNumber").trim();
        for (User element:listOfUsers) {
            if (element.getUsername().toUpperCase().equals(username)) {
                existence = true;
            }
        }
        boolean noSignsInInput = Validator.checkToSigns(username);
        boolean rightPhoneNumber  = Validator.checkPhoneNumberInput(phoneNumber);
        if (noSignsInInput && rightPhoneNumber) {
            if (!username.isEmpty() && !password.isEmpty() && password.equals(passwordRepeat) && !firstName.isEmpty() && !phoneNumber.isEmpty() && !existence) {
                user.setUsername(username);
                user.setPassword(DigestUtils.md5Hex(password));
                user.setFirstName(firstName);
                user.setSecondName(secondName);
                user.setPhoneNumber(phoneNumber);
                user.setRole(DEFAULT_USER_ROLE);
                userDAO.insert(user);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/login.jsp");
                requestDispatcher.forward(request,response);
            } else {
                request.setAttribute("incorrectData", true);
                if (existence) {
                    request.setAttribute("userExists", true);
                }
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/registration.jsp");
                requestDispatcher.forward(request, response);
            }
        } else {
            if (!noSignsInInput) {
                request.setAttribute("incorrectInputText", true);
            }
            if (!rightPhoneNumber) {
                request.setAttribute("incorrectPhoneNumber", true);
            }
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/registration.jsp");
            requestDispatcher.forward(request, response);
        }

    }
}
