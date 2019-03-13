package com.epam.carseller.action;

import com.epam.carseller.database.UserDAO;
import com.epam.carseller.entity.User;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.epam.carseller.util.CarsellerConstants.LOGIN_PAGE;

public class UserRegistrationAction implements Action {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String DEFAULT_USER_ROLE = "User";
        UserDAO userDAO = new UserDAO();
        User user = new User();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String passwordRepeat = request.getParameter("passwordRepeat");
        String firstName = request.getParameter("firstName");
        String secondName = request.getParameter("secondName");
        String phoneNumber = request.getParameter("phoneNumber");
        String newPassword = DigestUtils.md5Hex(password);
        System.out.println(newPassword);
        //userDAO.getUserByUsername(username);
        if (!username.isEmpty() && !password.isEmpty() && password.equals(passwordRepeat) && !firstName.isEmpty() && !phoneNumber.isEmpty()) {
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
            request.setAttribute("incorrectData", 1);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/registration.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
