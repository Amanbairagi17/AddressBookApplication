package com.example.demo.repository;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	private static final String URL = "jdbc:mysql://localhost:3306/demodb";
    private static final String USER = "root";
    private static final String PASSWORD = "Sql@0001";

    public static Connection getConnection() throws Exception {
    	Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
	
}

