/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Pelanggan;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author saniaasrimonica
 */
public class PelangganDAO {
    public void insert(Connection con, Pelanggan pelanggan) throws SQLException {
        String sql ="insert into pelanggan values(?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, pelanggan.getKode_Pelanggan());
        ps.setString(2, pelanggan.getNama_Pelanggan());
        ps.setString(3, pelanggan.getAlamat());
        ps.setString(4, pelanggan.getNo_Telepon());
        ps.executeUpdate();
        
    }
     public Pelanggan getPelanggan(Connection con , String Kode_Pelanggan) throws SQLException{
         String sql="select * from pelanggan where Kode_Pelanggan=?";
         PreparedStatement ps = con.prepareStatement(sql);
         ps.setString(1, Kode_Pelanggan);
         ResultSet rs =ps.executeQuery();
         Pelanggan pelanggan= null;
         if(rs.next()){
             pelanggan=new Pelanggan();
             pelanggan.setKode_Pelanggan(rs.getString(1));
             pelanggan.setNama_Pelanggan(rs.getString(2));
             pelanggan.setAlamat(rs.getString(3));
             pelanggan.setNo_Telepon(rs.getString(4));
         }
         return pelanggan;
     
}
     public List<Pelanggan> getAllPelanggan(Connection con) throws SQLException{
        String sql ="select * from pelanggan";
        PreparedStatement ps = con.prepareStatement(sql);
        List<Pelanggan> listPelanggan = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        Pelanggan pelanggan = null;
        while (rs.next()){
            pelanggan = new Pelanggan();
            pelanggan.setKode_Pelanggan(rs.getString(1));
            pelanggan.setNama_Pelanggan(rs.getString(2));
            pelanggan.setAlamat(rs.getString(3));
            pelanggan.setNo_Telepon(rs.getString(4));
            listPelanggan.add(pelanggan);
         
        }
        return listPelanggan;
    }
     public void update(Connection con, Pelanggan pelanggan) throws SQLException{
      String sql ="update pelanggan set Nama_Pelanggan=?,Alamat=?,No_Telepon=? where Kode_Pelanggan=?";
      PreparedStatement ps = con.prepareStatement(sql);
      ps.setString(1, pelanggan.getNama_Pelanggan());
      ps.setString(2, pelanggan.getAlamat());
      ps.setString(3, pelanggan.getNo_Telepon());
      ps.setString(4, pelanggan.getKode_Pelanggan());
      ps.executeUpdate();
              
    }
     public void delete(Connection con,String Kode_Pelanggan) throws SQLException{
        String sql="delete from pelanggan"
                + " where Kode_Pelanggan=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,Kode_Pelanggan);
        ps.executeUpdate();
    }
    
    
}
    

