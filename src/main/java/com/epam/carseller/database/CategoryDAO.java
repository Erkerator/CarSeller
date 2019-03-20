package com.epam.carseller.database;

import com.epam.carseller.entity.Category;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    final static Logger logger = Logger.getLogger(CategoryDAO.class);
    public static final String SELECT_ALL_CATEGORIES = "SELECT * FROM Category WHERE language_id = ?";
    public static final String SELECT_CATEGORY_BY_ID = "SELECT * FROM Category WHERE category_id = ?";
    public static final String UPDATE_CATEGORY = "UPDATE Category SET language_id = ?, category = ? WHERE category_id = ?";
    public static final String DELETE_CATEGORY = "DELETE FROM Category WHERE category_id = ?";
    public static final String INSERT_CATEGORY = "INSERT INTO Category(language_id, category) VALUES (?,?)";

    public List<Category> getAll(int languageId) {
        List<Category> list = new ArrayList<>();
        ConnectionPool pool = null;
        Connection connection = null;
        try {
            ConnectionPool.init();
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_CATEGORIES)){
                preparedStatement.setInt(1, languageId);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    Category category = new Category();
                    category.setCategoryId(resultSet.getInt(1));
                    category.setLanguageId(resultSet.getInt(2));
                    category.setCategory(resultSet.getString(3));
                    list.add(category);
                }
            }
            pool.returnConnection(connection);
        } catch (SQLException e) {
            logger.error(e);
        }
        return list;
    }

    public Category getCategotyById(int id) {
        Category category = new Category();
        ConnectionPool pool = null;
        Connection connection = null;
        try {
            ConnectionPool.init();
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CATEGORY_BY_ID)) {
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()){
                    category.setCategoryId(resultSet.getInt(1));
                    category.setLanguageId(resultSet.getInt(2));
                    category.setCategory(resultSet.getString(3));
                }
            }
            pool.returnConnection(connection);
        } catch (SQLException e) {
            logger.error(e);
        }
        return category;
    }

    public void update(Category category) {
        ConnectionPool pool = null;
        Connection connection = null;
        try {
            ConnectionPool.init();
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CATEGORY)){
                preparedStatement.setInt(1, category.getLanguageId());
                preparedStatement.setString(2, category.getCategory());
                preparedStatement.setInt(3, category.getCategoryId());
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
            ConnectionPool.init();
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_CATEGORY)){
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            }
            pool.returnConnection(connection);
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    public void insert(Category category) {
        ConnectionPool pool = null;
        Connection connection = null;
        try {
            ConnectionPool.init();
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CATEGORY)){
                preparedStatement.setInt(1, category.getLanguageId());
                preparedStatement.setString(2, category.getCategory());
                preparedStatement.executeUpdate();
            }
            pool.returnConnection(connection);
        } catch (SQLException e) {
            logger.error(e);
        }
    }

}
