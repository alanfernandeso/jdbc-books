package br.com.alandev;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFacttory {

    public Connection conectar(){

        Connection conn = null;
        
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/books?user=root");
        } catch (SQLException e) {
            System.out.println("Não foi possível se conectar ao banco de dados");
            System.out.println(e);
        }
        
        return conn;
        
    }

}
