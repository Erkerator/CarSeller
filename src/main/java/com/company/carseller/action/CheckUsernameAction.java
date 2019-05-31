package com.company.carseller.action;

import com.company.carseller.database.UserDAO;
import com.company.carseller.entity.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class CheckUsernameAction implements Action {
    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String newUsername = request.getParameter("username").trim().toUpperCase();
        UserDAO userDAO = new UserDAO();
        List<User> listOfUsers = userDAO.getAll();
        boolean userExists = false;
        for (User user:listOfUsers) {
            String usernameFromBD = user.getUsername().toUpperCase();
            if (usernameFromBD.equals(newUsername)) {
                userExists = true;
            }
        }
        if (userExists) {
            response.setContentType("text/plain");
            PrintWriter out = response.getWriter();
            out.print("X");
        } else {
            response.setContentType("text/plain");
            PrintWriter out = response.getWriter();
            out.print("V");
        }
    }
}
