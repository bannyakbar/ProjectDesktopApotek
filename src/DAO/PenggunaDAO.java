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
public class PenggunaDAO {
    public void insert(Connection con, Pengguna pengguna ) throws SQLException{
        String sql ="insert into user value(?,md5(?),?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, pengguna.getUserID());
        ps.setString(2, pengguna.getPassID());
        ps.setString(3, pengguna.getNama());
        ps.setString(3, pengguna.getLevel());
        ps.executeUpdate();
    }
    public Pengguna getPengguna(Connection con, String username ) throws SQLException{
        String sql ="select * from pengguna where username = ?";
        PreparedStatement ps = con.prepareStatement(sql);    
        ps.setString(1, username);
        ResultSet rs = ps.executeQuery();
        Pengguna pengguna = null;
        if(rs.next()){
            pengguna = new Pengguna();
            pengguna.setUserID(rs.getString(1));
            pengguna.setPassID(rs.getString(2));
            pengguna.setNama(rs.getString(3));
            pengguna.setLevel(rs.getString(4));
            
        }
        return pengguna;
    }
    public void update(Connection con, Pengguna pengguna ) throws SQLException{
        String sql ="update pengguna set password=?,nama=?,level=? where username=?";
        PreparedStatement ps = con.prepareStatement(sql); 
        ps.setString(1, pengguna.getPassID());
        ps.setString(2, pengguna.getNama());
        ps.setString(3, pengguna.getLevel());
        ps.setString(3, pengguna.getUserID());
        
        ps.executeUpdate();
    }
    public void delete(Connection con, String username ) throws SQLException{
        String sql ="delete from user where username=?";
        PreparedStatement ps = con.prepareStatement(sql);    
        ps.setString(1, username);
        ps.executeUpdate(); 
    }
    public Pengguna getPenggunaLogin(Connection con, String username, String password) throws SQLException{
        String sql ="select * from pengguna where username = ? and password =md5(?)";
        PreparedStatement ps = con.prepareStatement(sql);    
        ps.setString(1, username);
        ps.setString(2,password);
        ResultSet rs = ps.executeQuery();
        Pengguna pengguna = null;
        if(rs.next()){
            pengguna = new Pengguna();
            pengguna.setUserID(rs.getString(1));
            pengguna.setPassID(rs.getString(2));
            pengguna.setNama(rs.getString(3));
            pengguna.setLevel(rs.getString(4));
        }
        return pengguna;
    }
    
}
