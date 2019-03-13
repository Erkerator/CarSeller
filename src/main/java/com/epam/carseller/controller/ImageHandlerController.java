package com.epam.carseller.controller;

import com.epam.carseller.database.CarDAO;
import com.epam.carseller.entity.Car;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;
import java.util.List;

public class ImageHandlerController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("image/jpeg");
        CarDAO carDAO = new CarDAO();

        try (OutputStream outputStream = response.getOutputStream()){
            int id = Integer.valueOf(request.getParameter("id"));
            Car car = carDAO.getCarById(id);
            byte bytesOfPhoto[] = car.getPhoto();/*.getBytes(1, (int) car.getPhoto().length());*/
            outputStream.write(bytesOfPhoto);
        } /*catch (SQLException e) {
            //log it
        }*/
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }
}
