package com.epam.carseller.database;

import com.epam.carseller.entity.Category;
import com.epam.carseller.entity.Transmission;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TransmissionDAO {
    public static final String SELECT_ALL_TRANSMISSION = "SELECT * FROM Transmission WHERE language_id = ?";
    public static final String SELECT_TRANSMISSION_BY_ID = "SELECT * FROM Transmission WHERE transmission_id = ?";
    public static final String UPDATE_TRANSMISSION = "UPDATE Transmission SET language_id = ?, transmission = ? WHERE transmission_id = ?";
    public static final String DELETE_TRANSMISSION = "DELETE FROM Transmission WHERE transmission_id = ?";
    public static final String INSERT_TRANSMISSION = "INSERT INTO Transmission(language_id, transmission) VALUES (?,?)";

    public List<Transmission> getAll(int languageId) {
        List<Transmission> list = new ArrayList<>();
        ConnectionPool pool = null;
        Connection connection = null;
        try {
            ConnectionPool.init();
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_TRANSMISSION)){
                preparedStatement.setInt(1, languageId);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Transmission transmission = new Transmission();
                    transmission.setTransmissionId(resultSet.getInt(1));
                    transmission.setLanguageId(resultSet.getInt(2));
                    transmission.setTransmission(resultSet.getString(3));
                    list.add(transmission);
                }
            }
            pool.returnConnection(connection);
        } catch (SQLException e) {
            //log it
        }
        return list;
    }

    public Transmission getTransmissionById(int id) {
        Transmission transmission = new Transmission();
        ConnectionPool pool = null;
        Connection connection = null;
        try {
            ConnectionPool.init();
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TRANSMISSION_BY_ID)) {
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    transmission.setTransmissionId(resultSet.getInt(1));
                    transmission.setLanguageId(resultSet.getInt(2));
                    transmission.setTransmission(resultSet.getString(3));
                }
            }
            pool.returnConnection(connection);
        } catch (SQLException e) {
            //log it
        }
        return  transmission;
    }

    public void update(Transmission transmission) {
        ConnectionPool pool = null;
        Connection connection = null;
        try {
            ConnectionPool.init();
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TRANSMISSION)) {
                preparedStatement.setInt(1, transmission.getLanguageId());
                preparedStatement.setString(2, transmission.getTransmission());
                preparedStatement.setInt(3, transmission.getTransmissionId());
                preparedStatement.executeUpdate();
            }
            pool.returnConnection(connection);
        } catch (SQLException e) {
            //log it
        }
    }

    public void delete(int id) {
        ConnectionPool pool = null;
        Connection connection = null;
        try {
            ConnectionPool.init();
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_TRANSMISSION)){
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            }
            pool.returnConnection(connection);
        } catch (SQLException e) {
            //log it
        }
    }

    public void insert(Transmission transmission) {
        ConnectionPool pool = null;
        Connection connection = null;
        try {
            ConnectionPool.init();
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TRANSMISSION)) {
                preparedStatement.setInt(1,transmission.getLanguageId());
                preparedStatement.setString(2,transmission.getTransmission());
                preparedStatement.executeUpdate();
            }
            pool.returnConnection(connection);
        } catch (SQLException e) {
            //log it
        }
    }
}
