package com.epam.carseller.action;

import com.epam.carseller.database.UserDAO;
import com.epam.carseller.entity.User;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginUserAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int incorrectIsTrue = 1;
        HttpSession session = request.getSession();
        UserDAO userDAO = new UserDAO();
        String username = request.getParameter("username");
        String password = DigestUtils.md5Hex(request.getParameter("password"));
        RequestDispatcher requestDispatcher;
        if (!username.isEmpty() && !password.isEmpty()) {
            User user = userDAO.getUserByUsername(username);
            if (password.equals(user.getPassword())) {
                session.setAttribute("username", username);
                session.setAttribute("userId", user.getUserId());
                session.setAttribute("role", user.getRole());
                if (user.getRole().equals("Admin")) {
                    requestDispatcher = request.getRequestDispatcher("/pages/admin.jsp");
                    requestDispatcher.forward(request, response);
                } else {
                    requestDispatcher = request.getRequestDispatcher("/pages/account.jsp");
                    requestDispatcher.forward(request, response);
                }

            } else {
                request.setAttribute("incorrectValues", incorrectIsTrue);
                requestDispatcher = request.getRequestDispatcher("/pages/login.jsp");
                requestDispatcher.forward(request, response);
            }
        } else {
            request.setAttribute("emptyValues", incorrectIsTrue);
            requestDispatcher = request.getRequestDispatcher("/pages/login.jsp");
            requestDispatcher.forward(request, response);
        }
    }
}
