package com.company.carseller.action;

import com.company.carseller.database.CarDAO;
import com.company.carseller.entity.Car;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ShowCarDetailsAction implements Action {

    @Override
    public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        CarDAO carDAO = new CarDAO();
        int carId = Integer.parseInt(request.getParameter("carid"));
        Car car = carDAO.getCarById(carId);
        request.setAttribute("carData", car);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/pages/carDetails.jsp");
        requestDispatcher.forward(request, response);
    }
}
