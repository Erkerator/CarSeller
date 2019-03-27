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

import static com.epam.carseller.util.CarsellerConstants.LOGIN_PAGE;

public class UserRegistrationAction implements Action {

    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String DEFAULT_USER_ROLE = "User";
        boolean existence = false;
        UserDAO userDAO = new UserDAO();
        User user = new User();
        List<User> listOfUsers = userDAO.getAll();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String passwordRepeat = request.getParameter("passwordRepeat");
        String firstName = request.getParameter("firstName");
        String secondName = request.getParameter("secondName");
        String phoneNumber = request.getParameter("phoneNumber");
        for (User element:listOfUsers) {
            if (element.getUsername().equals(username)) {
                existence = true;
            }
        }
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
            request.setAttribute("incorrectData", 1);
            if (existence) {
                request.setAttribute("userExists", true);
            }
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/registration.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
