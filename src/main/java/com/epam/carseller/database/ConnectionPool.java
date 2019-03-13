package com.epam.carseller.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {

    public static final String PROPERTIES_FILE = "database";
    private static ConnectionPool instance;
    private BlockingQueue<Connection> connectionQueue;

    public static void init() throws SQLException {
        if (instance == null) {
            ResourceBundle rb = ResourceBundle.getBundle(PROPERTIES_FILE);
            String driver = rb.getString("db.driver");
            String url = rb.getString("db.url");
            String user = rb.getString("db.user");
            String password = rb.getString("db.password");
            int poolSize = Integer.parseInt(rb.getString("db.poolSize"));

            try {
                instance = new ConnectionPool(driver, url, user, password, poolSize);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static ConnectionPool getInstance() {
        return instance;
    }

    private ConnectionPool (String driver, String url, String user, String password, int poolSize) throws ClassNotFoundException, SQLException {
        Class.forName(driver);
        connectionQueue = new ArrayBlockingQueue<>(poolSize);

        for (int i=0; i<poolSize; i++) {
            Connection connection = DriverManager.getConnection(url, user, password);
            connectionQueue.offer(connection);
        }
    }

    public Connection takeConnection() {
        Connection connection = null;
        try {
            connection = connectionQueue.take();
        } catch (InterruptedException e) {
            //log it;
        }
        return connection;
    }

    public void returnConnection(Connection connection) {
        if(connection != null) {
            try {
                connectionQueue.put(connection);
            } catch (InterruptedException e) {
                //log it
            }
        }
    }
}
