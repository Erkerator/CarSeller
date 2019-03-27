package com.epam.carseller.controller;

import com.epam.carseller.action.Action;
import com.epam.carseller.action.ActionFactory;
import org.apache.log4j.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CarsellerController extends HttpServlet {

    final static Logger logger = Logger.getLogger(CarsellerController.class);

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("Controller Servlet is started!");
        String actionRequest = request.getRequestURI();
        ActionFactory actionFactory = ActionFactory.getInstance();
        Action action = actionFactory.getAction(actionRequest);
        if (action != null) {
            action.execute(request, response);
        } else {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/404.jsp");
            requestDispatcher.forward(request, response);
        }


    }
}
