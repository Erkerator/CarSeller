package com.epam.carseller.database;

import com.epam.carseller.entity.Brand;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BrandDAO {
    final static Logger logger = Logger.getLogger(BrandDAO.class);
    public static final String SELECT_ALL_BRANDS = "SELECT * FROM Brand";
    public static final String SELECT_BRAND_BY_ID = "SELECT * FROM Brand WHERE brand_id = ?";
    public static final String UPDATE_BRAND = "UPDATE Brand SET brand = ? WHERE brand_id = ?";
    public static final String DELETE_BRAND = "DELETE FROM Brand WHERE brand_id = ?";
    public static final String INSERT_BRAND = "INSERT INTO Brand (brand) VALUES (?)";

    public List<Brand> getAll() {
        List<Brand> list = new ArrayList<>();
        ConnectionPool pool = null;
        Connection connection = null;
        try {
            ConnectionPool.init();
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BRANDS)) {
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    Brand brand = new Brand();
                    brand.setBrandId(resultSet.getInt(1));
                    brand.setBrand(resultSet.getString(2));
                    list.add(brand);
                }
            pool.returnConnection(connection);
            }

        } catch (SQLException e) {
            logger.error(e);
        }
        return list;
    }

    public Brand getBrandById(int id) {
        Brand brand = new Brand();
        ConnectionPool pool = null;
        Connection connection = null;
        try {
            ConnectionPool.init();
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            try(PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BRAND_BY_ID)) {
                preparedStatement.setInt(1, id);
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    brand.setBrandId(resultSet.getInt(1));
                    brand.setBrand(resultSet.getString(2));
                }
                pool.returnConnection(connection);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return brand;
    }

    public void update(Brand brand) {
        ConnectionPool pool = null;
        Connection connection = null;
        try {
            ConnectionPool.init();
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            try(PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_BRAND)) {
                preparedStatement.setString(1, brand.getBrand());
                preparedStatement.setInt(2, brand.getBrandId());
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
            try(PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BRAND)) {
                preparedStatement.setInt(1,id);
                preparedStatement.executeUpdate();
            }
            pool.returnConnection(connection);
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    public void insert(Brand brand) {
        ConnectionPool pool = null;
        Connection connection = null;
        try {
            ConnectionPool.init();
            pool = ConnectionPool.getInstance();
            connection = pool.takeConnection();
            try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BRAND)){
                preparedStatement.setString(1, brand.getBrand());
                preparedStatement.executeUpdate();
            }
            pool.returnConnection(connection);
        } catch (SQLException e) {
            logger.error(e);
        }
    }
}
