package com.epam.carseller.database;

import com.epam.carseller.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    public static final String SELECT_ALL_USER = "SELECT * FROM User";
    public static final String SELECT_USER_BY_USERNAME = "SELECT * FROM User WHERE username = ?";
    public static final String UPDATE_USER = "UPDATE User SET username = ?, password = ?, first_name = ?, second_name = ?, phone_number = ?, role = ? WHERE user_id = ?";
    public static final String DELETE_USER = "DELETE FROM Uses WHERE user_id = ?";
    public static final String INSERT_USER = "INSERT INTO User(username, password, first_name, second_name, phone_number, role) VALUES (?,?,?,?,?,?)";

    public List<User> getAll() {
        List<User> list = new ArrayList<>();
        ConnectionPool pool = null;
        Connection connection = null;
        try {
            ConnectionPool.init();
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USER)){
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    User user = new User();
                    user.setUserId(resultSet.getInt(1));
                    user.setUsername(resultSet.getString(2));
                    user.setPassword(resultSet.getString(3));
                    user.setFirstName(resultSet.getString(4));
                    user.setSecondName(resultSet.getString(5));
                    user.setPhoneNumber(resultSet.getString(6));
                    user.setRole(resultSet.getString(7));
                    list.add(user);
                }
            }
            pool.returnConnection(connection);
        } catch (SQLException e) {
            //log it
        }
        return list;
    }

    public User getUserByUsername(String username) {
        User user = new User();
        ConnectionPool pool = null;
        Connection connection = null;
        try {
            ConnectionPool.init();
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_USERNAME)) {
                preparedStatement.setString(1, username);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    user.setUserId(resultSet.getInt(1));
                    user.setUsername(resultSet.getString(2));
                    user.setPassword(resultSet.getString(3));
                    user.setFirstName(resultSet.getString(4));
                    user.setSecondName(resultSet.getString(5));
                    user.setPhoneNumber(resultSet.getString(6));
                    user.setRole(resultSet.getString(7));
                }
            }
            pool.returnConnection(connection);
        } catch (SQLException e) {
            //log it
        }
        return user;
    }

    public void update(User user) {
        ConnectionPool pool = null;
        Connection connection = null;
        try {
            ConnectionPool.init();
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER)) {
                preparedStatement.setString(1,user.getUsername());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getFirstName());
                preparedStatement.setString(4, user.getSecondName());
                preparedStatement.setString(5, user.getPhoneNumber());
                preparedStatement.setString(6, user.getRole());
                preparedStatement.setInt(7, user.getUserId());
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
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER)){
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            }
            pool.returnConnection(connection);
        } catch (SQLException e) {
            //log it
        }
    }

    public void insert(User user) {
        ConnectionPool pool = null;
        Connection connection = null;
        try {
            ConnectionPool.init();
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER)) {
                preparedStatement.setString(1, user.getUsername());
                preparedStatement.setString(2, user.getPassword());
                preparedStatement.setString(3, user.getFirstName());
                preparedStatement.setString(4, user.getSecondName());
                preparedStatement.setString(5, user.getPhoneNumber());
                preparedStatement.setString(6, user.getRole());
                preparedStatement.executeUpdate();
            }
            pool.returnConnection(connection);
        } catch (SQLException e) {
            //log it
        }
    }
}
