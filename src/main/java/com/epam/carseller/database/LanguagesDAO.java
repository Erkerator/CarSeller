package com.epam.carseller.database;

import com.epam.carseller.entity.Languages;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LanguagesDAO {
    final static Logger logger = Logger.getLogger(LanguagesDAO.class);
    public static final String SELECT_ALL_LANGUAGES = "SELECT * FROM Languages";
    public static final String SELECT_LANGUAGE_BY_ID = "SELECT * FROM Languages WHERE language_id = ?";
    public static final String SELECT_LANGUAGE_ID_BY_LOCALE = "SELECT language_id FROM Languages WHERE locale = ?";
    public static final String UPDATE_LANGUAGE = "UPDATE Languages SET locale = ? WHERE language_id = ?";
    public static final String DELETE_LANGUAGE = "DELETE FROM Languages WHERE language_id = ?";
    public static final String INSERT_LANGUAGE = "INSERT INTO Languages (locale) VALUES (?)";

    public List<Languages> getAll() {
        List<Languages> list = new ArrayList<>();
        ConnectionPool pool = null;
        Connection connection = null;
        try {
            ConnectionPool.init();
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_LANGUAGES)){
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Languages languages = new Languages();
                    languages.setLanguageId(resultSet.getInt(1));
                    languages.setLocale(resultSet.getString(2));
                    list.add(languages);
                }
            }
            pool.returnConnection(connection);
        } catch (SQLException e) {
            logger.error(e);
        }
        return list;
    }

    public Languages getLanguageById(int id) {
        Languages languages = new Languages();
        ConnectionPool pool = null;
        Connection connection = null;
        try {
            ConnectionPool.init();
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LANGUAGE_BY_ID)) {
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    languages.setLanguageId(resultSet.getInt(1));
                    languages.setLocale(resultSet.getString(2));
                }
            }
            pool.returnConnection(connection);
        } catch (SQLException e) {
            logger.error(e);
        }
        return languages;
    }

    public int getLanguageIdByLocale(String locale) {
        int language_id = 0;
        ConnectionPool pool = null;
        Connection connection = null;
        try {
            ConnectionPool.init();
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LANGUAGE_ID_BY_LOCALE)){
                preparedStatement.setString(1, locale);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    language_id = resultSet.getInt(1);
                }
            }
            pool.returnConnection(connection);
        } catch (SQLException e) {
            logger.error(e);
        }
        return language_id;
    }

    public void update(Languages languages) {
        ConnectionPool pool = null;
        Connection connection = null;
        try {
            ConnectionPool.init();
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_LANGUAGE)) {
                preparedStatement.setInt(1,languages.getLanguageId());
                preparedStatement.setString(2, languages.getLocale());
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
            try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_LANGUAGE)) {
                preparedStatement.setInt(1, id);
                preparedStatement.executeUpdate();
            }
            pool.returnConnection(connection);
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    public void insert(Languages languages) {
        ConnectionPool pool = null;
        Connection connection = null;
        try {
            ConnectionPool.init();
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_LANGUAGE)){
                preparedStatement.setString(1,languages.getLocale());
                preparedStatement.executeUpdate();
            }
            pool.returnConnection(connection);
        } catch (SQLException e) {
            logger.error(e);
        }
    }
}
