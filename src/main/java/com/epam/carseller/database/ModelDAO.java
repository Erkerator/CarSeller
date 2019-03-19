package com.epam.carseller.database;

import com.epam.carseller.entity.Model;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModelDAO {
    final static Logger logger = Logger.getLogger(ModelDAO.class);
    public static final String SELECT_ALL_MODELS = "SELECT * FROM Model";
    public static final String SELECT_MODEL_BY_ID = "SELECT * FROM Model WHERE model_id = ?";
    public static final String UPDATE_MODEL = "UPDATE Model SET brand_id = ?, model = ? WHERE model_id = ?";
    public static final String DELETE_MODEL = "DELETE FROM Model WHERE model_id = ?";
    public static final String INSERT_MODEL = "INSERT INTO Model (brand_id, model) VALUES (?,?)";

    public List<Model> getAll() {
        List<Model> list = new ArrayList<>();
        ConnectionPool pool = null;
        Connection connection = null;
        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_MODELS)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Model model = new Model();
                    model.setModelId(resultSet.getInt(1));
                    model.setBrandId(resultSet.getInt(2));
                    model.setModel(resultSet.getString(3));
                    list.add(model);
                }
                pool.returnConnection(connection);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return list;
    }

    public Model getModelById(int id) {
        Model model = new Model();
        ConnectionPool pool = null;
        Connection connection = null;
        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_MODEL_BY_ID)){
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    model.setModelId(resultSet.getInt(1));
                    model.setBrandId(resultSet.getInt(2));
                    model.setModel(resultSet.getString(3));
                }
                pool.returnConnection(connection);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return model;
    }

    public void update(Model model) {
        ConnectionPool pool = null;
        Connection connection = null;
        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_MODEL)){
               preparedStatement.setInt(1, model.getBrandId());
               preparedStatement.setString(2, model.getModel());
               preparedStatement.setInt(3, model.getModelId());
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
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_MODEL)) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            }
            pool.returnConnection(connection);
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    public void insert(Model model) {
        ConnectionPool pool = null;
        Connection connection = null;
        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_MODEL)) {
                preparedStatement.setInt(1, model.getBrandId());
                preparedStatement.setString(2, model.getModel());
                preparedStatement.executeUpdate();
            }
            pool.returnConnection(connection);
        } catch (SQLException e) {
            logger.error(e);
        }
    }
}
