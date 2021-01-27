/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Koneksi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author saniaasrimonica
 */
public class Koneksi {
    private String url="jdbc:mysql://localhost/apotek";
    private String username="root";
    private String password="";
    
    public Connection getKoneksi() throws SQLException{
       return DriverManager.getConnection(url, username, password);  
    }
}
    
