package com.nys.bookstore.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCUtil {
    static {
        ClassLoader cl =
                JDBCUtil.class.getClassLoader();

        InputStream is =
                cl.getResourceAsStream("jdbc.properties");
        Properties properties = new Properties();
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        url = properties.getProperty("url");
        database = properties.getProperty("database");
        String driver = properties.getProperty("Driver");
        user = properties.getProperty("user");
        password = properties.getProperty("password");
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static String url;
    private static String database;
    private static String user;
    private static String password;
    private static Connection conn = null;

    public static Connection getConnection(){

        try {
             conn = DriverManager.getConnection(
                    url+database,user,password
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
}
