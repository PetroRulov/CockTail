package com.app.mysqlcon;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by prulov on 14.10.2016.
 */
public class MySQL_DB_Worker {

    private final String URL = "jdbc:mysql://localhost:3306/cocktail?useSSL=false";
    private final String USERNAME = "root";
    private final String PASSWORD = "89082d6PRDB";

    private Connection connection;


    public MySQL_DB_Worker(){
        try{
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("DB \"cocktail\" connection is successfully established!");
        }catch(SQLException e){
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}

