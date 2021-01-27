/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Pengguna;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author saniaasrimonica
 */
public class LoginDAO {
    public Pengguna getPengguna(Connection con, String username, String password) throws SQLException{
        String sql ="select * from pengguna where username = ? and password =md5(?)";
        PreparedStatement ps = con.prepareStatement(sql);    
        ps.setString(1, username);
        ps.setString(2,password);
        ResultSet rs = ps.executeQuery();
        Pengguna pengguna= null;
        if(rs.next()){
            pengguna = new Pengguna();
        }
        return pengguna;
    }
}

