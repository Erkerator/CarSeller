package com.epam.carseller.database;

import com.epam.carseller.entity.Car;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CarDAO {
    final static Logger logger = Logger.getLogger(CarDAO.class);

    public static final String SELECT_ALL_CAR = "SELECT * FROM Car WHERE language_id = ?";
    public static final String SELECT_CAR_BY_ID = "SELECT Car.car_id, Model.model, Transmission.transmission, Category.category, State.state, Car.year_of_produce, Car.date_of_creation, Car.engine_volume, Car.language_id, Car.photo, User.username FROM Car INNER JOIN Model ON Car.model_id = Model.model_id INNER JOIN Transmission ON Car.transmission_id = Transmission.transmission_id INNER JOIN Category ON Car.category_id = Category.category_id INNER JOIN State ON Car.state_id = State.state_id INNER JOIN User ON Car.user_id = User.user_id WHERE Car.car_id = ?";
    public static final String UPDATE_CAR = "UPDATE Car SET model_id = ?, transmission_id = ?, category_id = ?, state_id = ?, year_of_produce = ?, date_of_creation = ?, engine_volume = ?, language_id = ?, photo = ?, user_id = ? WHERE car_id = ?";
    public static final String DELETE_CAR = "DELETE FROM Car WHERE car_id = ?";
    public static final String INSERT_CAR = "INSERT INTO Car(model_id, transmission_id, category_id, state_id, year_of_produce, date_of_creation, engine_volume, language_id, photo, user_id) VALUES (?,?,?,?,?,?,?,?,?,?)";

    //public static final String SELECT_CARS_BY_PARAMETERS = "SELECT * FROM Car WHERE (model_id = ? OR @model IS NULL) AND (transmission_id = ? OR @transmission IS NULL) AND (category_id = ? OR @category IS NULL) AND (state_id = ? OR @state IS NULL)";
    public static final String SELECT_CARS_BY_PARAMETERS = "SELECT Car.car_id, Model.model, Transmission.transmission, Category.category, State.state, Car.year_of_produce, Car.date_of_creation, Car.engine_volume, Car.language_id, User.username FROM Car INNER JOIN Model ON Car.model_id = Model.model_id INNER JOIN Transmission ON Car.transmission_id = Transmission.transmission_id INNER JOIN Category ON Car.category_id = Category.category_id INNER JOIN State ON Car.state_id = State.state_id INNER JOIN User ON Car.user_id = User.user_id WHERE (Car.category_id = ? OR ? IS NULL) AND (Car.model_id = ? OR ? IS NULL) AND (Car.transmission_id = ? OR ? IS NULL) AND (Car.state_id = ? OR ? IS NULL) AND Car.language_id = ?";
    public static final String SELECT_CARS_BY_USERS_ID = "SELECT Car.car_id, Model.model, Transmission.transmission, Category.category, State.state, Car.year_of_produce, Car.date_of_creation, Car.engine_volume, Car.language_id, User.username FROM Car INNER JOIN Model ON Car.model_id = Model.model_id INNER JOIN Transmission ON Car.transmission_id = Transmission.transmission_id INNER JOIN Category ON Car.category_id = Category.category_id INNER JOIN State ON Car.state_id = State.state_id INNER JOIN User ON Car.user_id = User.user_id WHERE Car.user_id = ? AND Car.language_id = ?";

    public List<Car> getAll(int languageId) {
        List<Car> list = new ArrayList<>();
        ConnectionPool pool = null;
        Connection connection = null;
        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CAR)){
                preparedStatement.setInt(1,languageId);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Car car = new Car();
                    car.setCarId(resultSet.getInt(1));
                    car.setModelId(resultSet.getString(2));
                    car.setTransmissionId(resultSet.getString(3));
                    car.setCategoryId(resultSet.getString(4));
                    car.setStateId(resultSet.getString(5));
                    car.setYearOfProduce(resultSet.getDate(6));
                    car.setDateOfCreation(resultSet.getDate(7));
                    car.setEngineVolume(resultSet.getFloat(8));
                    car.setLanguageId(resultSet.getInt(9));
                    car.setPhoto(resultSet.getBytes(10));
                    car.setUserId(resultSet.getString(11));
                    list.add(car);
                }
            }
            pool.returnConnection(connection);
        } catch (SQLException e) {
            logger.error(e);
        }
        return list;
    }

    public List<Car> getAllUsersCar(int userId, int languageId) {
        List<Car> list = new ArrayList<>();
        ConnectionPool pool = null;
        Connection connection = null;
        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CARS_BY_USERS_ID)) {
                preparedStatement.setInt(1, userId);
                preparedStatement.setInt(2, languageId);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Car car = new Car();
                    car.setCarId(resultSet.getInt(1));
                    car.setModelId(resultSet.getString(2));
                    car.setTransmissionId(resultSet.getString(3));
                    car.setCategoryId(resultSet.getString(4));
                    car.setStateId(resultSet.getString(5));
                    car.setYearOfProduce(resultSet.getDate(6));
                    car.setDateOfCreation(resultSet.getDate(7));
                    car.setEngineVolume(resultSet.getFloat(8));
                    car.setPhoto(resultSet.getBytes(9));
                    car.setUserId(resultSet.getString(10));
                    list.add(car);
                }
            }
            pool.returnConnection(connection);
        } catch (SQLException e) {
            logger.error(e);
        }
        return list;
    }

    public Car getCarById(int id) {
        Car car = new Car();
        ConnectionPool pool = null;
        Connection connection = null;
        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CAR_BY_ID)){
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    car.setCarId(resultSet.getInt(1));
                    car.setModelId(resultSet.getString(2));
                    car.setTransmissionId(resultSet.getString(3));
                    car.setCategoryId(resultSet.getString(4));
                    car.setStateId(resultSet.getString(5));
                    car.setYearOfProduce(resultSet.getDate(6));
                    car.setDateOfCreation(resultSet.getDate(7));
                    car.setEngineVolume(resultSet.getFloat(8));
                    car.setLanguageId(resultSet.getInt(9));
                    car.setPhoto(resultSet.getBytes(10));
                    car.setUserId(resultSet.getString(11));
                }
            }
            pool.returnConnection(connection);
        } catch (SQLException e) {
            logger.error(e);
        }
        return car;
    }

    public List<Car> getCarsByParameters(String category, String model, String transmission, String state, int language_id) {
        List<Car> list = new ArrayList<>();
        ConnectionPool pool = null;
        Connection connection = null;
        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CARS_BY_PARAMETERS)){
                preparedStatement.setString(1, category);
                preparedStatement.setString(2, category);
                preparedStatement.setString(3, model);
                preparedStatement.setString(4, model);
                preparedStatement.setString(5, transmission);
                preparedStatement.setString(6, transmission);
                preparedStatement.setString(7, state);
                preparedStatement.setString(8, state);
                preparedStatement.setInt(9, language_id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    Car car = new Car();
                    car.setCarId(resultSet.getInt(1));
                    car.setModelId(resultSet.getString(2));
                    car.setTransmissionId(resultSet.getString(3));
                    car.setCategoryId(resultSet.getString(4));
                    car.setStateId(resultSet.getString(5));
                    car.setYearOfProduce(resultSet.getDate(6));
                    car.setDateOfCreation(resultSet.getDate(7));
                    car.setEngineVolume(resultSet.getFloat(8));
                    car.setLanguageId(resultSet.getInt(9));
                    car.setUserId(resultSet.getString(10));
                    list.add(car);
                }
            }
            pool.returnConnection(connection);
        } catch (SQLException e) {
            logger.error(e);
        }
        return list;
    }

    public void update(Car car) {
        ConnectionPool pool = null;
        Connection connection = null;
        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CAR)){
                preparedStatement.setString(1, car.getModelId());
                preparedStatement.setString(2, car.getTransmissionId());
                preparedStatement.setString(3, car.getCategoryId());
                preparedStatement.setString(4, car.getStateId());
                preparedStatement.setDate(5, new java.sql.Date(car.getYearOfProduce().getTime()));
                preparedStatement.setDate(6, new java.sql.Date(car.getDateOfCreation().getTime()));
                preparedStatement.setFloat(7, car.getEngineVolume());
                preparedStatement.setInt(8, car.getLanguageId());
                preparedStatement.setBytes(9, car.getPhoto());
                preparedStatement.setString(10, car.getUserId());
                preparedStatement.setInt(11, car.getCarId());
                preparedStatement.executeUpdate();
            }
            pool.returnConnection(connection);
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    public void delete(int id) {
        ConnectionPool pool = null;
        Connection connection = null;
        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CAR)){
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            }
            pool.returnConnection(connection);
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    public void insert(Car car) {
        ConnectionPool pool = null;
        Connection connection = null;
        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CAR)) {
                preparedStatement.setString(1, car.getModelId());
                preparedStatement.setString(2, car.getTransmissionId());
                preparedStatement.setString(3, car.getCategoryId());
                preparedStatement.setString(4, car.getStateId());
                preparedStatement.setDate(5, new java.sql.Date(car.getYearOfProduce().getTime()));
                preparedStatement.setDate(6, new java.sql.Date(car.getDateOfCreation().getTime()));
                preparedStatement.setFloat(7, car.getEngineVolume());
                preparedStatement.setInt(8, car.getLanguageId());
                preparedStatement.setBytes(9, car.getPhoto());
                preparedStatement.setString(10, car.getUserId());
                preparedStatement.executeUpdate();
            }
            pool.returnConnection(connection);
        } catch (SQLException e) {
            logger.error(e);
        }
    }
}
