package com.epam.carseller.database;

import com.epam.carseller.entity.State;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StateDAO {
    final static Logger logger = Logger.getLogger(StateDAO.class);
    public static final String SELECT_ALL_STATES = "SELECT * FROM State WHERE language_id = ?";
    public static final String SELECT_STATE_BY_ID = "SELECT * FROM State WHERE state_id = ?";
    public static final String UPDATE_STATE = "UPDATE State SET language_id = ?, state = ? WHERE state_id = ?";
    public static final String DELETE_STATE = "DELETE FROM State WHERE state_id = ?";
    public static final String INSERT_STATE = "INSERT INTO State (language_id, state) VALUES (?,?)";

    public List<State> getAll(int langugageId) {
        List<State> list = new ArrayList<>();
        ConnectionPool pool = null;
        Connection connection = null;
        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_STATES)){
                preparedStatement.setInt(1, langugageId);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    State state = new State();
                    state.setStateId(resultSet.getInt(1));
                    state.setLanguageId(resultSet.getInt(2));
                    state.setState(resultSet.getString(3));
                    list.add(state);
                }
               pool.returnConnection(connection);
            }
        }catch (SQLException e) {
            logger.error(e);
        }
        return list;
    }

    public State getStateById(int id) {
        State state = new State();
        ConnectionPool pool = null;
        Connection connection = null;
        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_STATE_BY_ID)){
                preparedStatement.setInt(1,id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    state.setStateId(resultSet.getInt(1));
                    state.setLanguageId(resultSet.getInt(2));
                    state.setState(resultSet.getString(3));
                }
            }
            pool.returnConnection(connection);
        } catch (SQLException e) {
            logger.error(e);
        }
        return state;
    }

    public void update(State state) {
        ConnectionPool pool = null;
        Connection connection = null;
        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_STATE)) {
                preparedStatement.setInt(1, state.getLanguageId());
                preparedStatement.setString(2, state.getState());
                preparedStatement.setInt(3, state.getStateId());
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
            connection= pool.takeConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_STATE)){
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            }
            pool.returnConnection(connection);
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    public void insert(State state) {
        ConnectionPool pool = null;
        Connection connection = null;
        try {
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_STATE)){
                preparedStatement.setInt(1, state.getLanguageId());
                preparedStatement.setString(2, state.getState());
                preparedStatement.executeUpdate();
            }
            pool.returnConnection(connection);
        } catch (SQLException e) {
            logger.error(e);
        }
    }
}
