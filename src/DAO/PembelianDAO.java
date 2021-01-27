/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Pemasok;
import Model.Pembelian;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author saniaasrimonica
 */
public class PembelianDAO {
     public void insert(Connection con, Pembelian pembelian) throws SQLException{
      String sql ="insert into pembelian values(?,?,?,?,?,?,?,?,?)";
      PreparedStatement ps = con.prepareStatement(sql);
      ps.setString(1, pembelian.getNo_Masuk());
      ps.setString(2, pembelian.getTgl_Masuk());
      ps.setString(3, pembelian.getNamapemasok());
      ps.setString(4, pembelian.getAlamatPemasok());
      ps.setString(5, pembelian.getKodeBarang());
      ps.setString(6, pembelian.getNamaBarang());
      ps.setDouble(7, pembelian.getHarga());
      ps.setString(8, pembelian.getSatuan());
      ps.setString(9, pembelian.getTotal());
      ps.executeUpdate();
              
    }
    
     public List<Pembelian> getAllPembelians(Connection con) throws SQLException{
        String sql ="select * from pembelian";
        PreparedStatement ps = con.prepareStatement(sql);
        List<Pembelian> list = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        Pembelian pembelian = null;
        while (rs.next()){
             pembelian = new Pembelian();
             pembelian.setNo_Masuk(rs.getString(1));
             pembelian.setTgl_Masuk(rs.getString(2));
             pembelian.setNamapemasok(rs.getString(3));
             pembelian.setAlamatPemasok(rs.getString(4));
             pembelian.setKodeBarang(rs.getString(5));
             pembelian.setNamaBarang(rs.getString(6));
             pembelian.setHarga(rs.getDouble(7));
             pembelian.setSatuan(rs.getString(8));
             pembelian.setTotal(rs.getString(9));
                list.add(pembelian);
         
        }
            return list;
    }
    
     public void update(Connection con,Pembelian pembelian) throws SQLException{
      String sql ="update pemasok set Nama_Pemasok=?,"
              + " Alamat=?, Kota=?,"
              + " Provinsi=?, No_Telepon=?,"
              + " No_Fax=?, KontakP=?  where Kode_Pemasok=?";
      PreparedStatement ps = con.prepareStatement(sql);
      
      ps.setString(1, pembelian.getTgl_Masuk());
      ps.setString(2, pembelian.getNamapemasok());
      ps.setString(3, pembelian.getAlamatPemasok());
      ps.setString(4, pembelian.getKodeBarang());
      ps.setString(5, pembelian.getNamaBarang());
      ps.setDouble(6, pembelian.getHarga());
      ps.setString(7, pembelian.getSatuan());
      ps.setString(8, pembelian.getTotal());
      ps.setString(9, pembelian.getNo_Masuk());
      ps.executeUpdate();
    }
     
     public void delete(Connection con,String id) throws SQLException{
        String sql="delete from pembelian where no_masuk=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,id);
        ps.executeUpdate();
    }
    
     
     public Pembelian getPembelian(Connection con , String id) throws SQLException{
         String sql="select * from pembelian where no_masuk=?";
         PreparedStatement ps = con.prepareStatement(sql);
         ps.setString(1, id);
         ResultSet rs =ps.executeQuery();
         Pembelian pembelian=null;
         if(rs.next()){
             pembelian=new Pembelian();
             pembelian.setNo_Masuk(rs.getString(1));
             pembelian.setTgl_Masuk(rs.getString(2));
             pembelian.setNamapemasok(rs.getString(3));
             pembelian.setAlamatPemasok(rs.getString(4));
             pembelian.setKodeBarang(rs.getString(5));
             pembelian.setNamaBarang(rs.getString(6));
             pembelian.setHarga(rs.getDouble(7));
             pembelian.setSatuan(rs.getString(8));
             pembelian.setTotal(rs.getString(9));
         }
         return pembelian;
     
}
     
     public ResultSet getResultSet(Connection con, String query) throws SQLException{
         Statement stat = con.createStatement();
         ResultSet rs = stat.executeQuery(query);
         return rs;
     }
}
