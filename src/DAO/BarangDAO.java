
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Barang;
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
public class BarangDAO {
    public void insert(Connection con, Barang barang) throws SQLException {
        String sql ="insert into barang values(?,?,?,?,?,?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, barang.getKode_Barang());
        ps.setString(2, barang.getKode_Produk());
        ps.setString(3, barang.getKode_Grup());
        ps.setString(4, barang.getNama_Barang());
        ps.setString(5, barang.getSatuan());
        ps.setInt(6, barang.getHarga_Beli());
        ps.setInt(7, barang.getHarga_Jual());
        ps.setInt(8, barang.getStok());
        ps.executeUpdate();
        
    }
     public Barang getBarang(Connection con , String Kode_Barang) throws SQLException{
         String sql="select * from barang where Kode_Barang=?";
         PreparedStatement ps = con.prepareStatement(sql);
         ps.setString(1, Kode_Barang);
         ResultSet rs =ps.executeQuery();
         Barang barang= null;
         if(rs.next()){
             barang=new Barang();
             barang.setKode_Barang(rs.getString(1));
             barang.setKode_Produk(rs.getString(2));
             barang.setKode_Grup(rs.getString(3));
             barang.setNama_Barang(rs.getString(4));
             barang.setSatuan(rs.getString(5));
             barang.setHarga_Beli(rs.getInt(6));
             barang.setHarga_Jual(rs.getInt(7));
             barang.setStok(rs.getInt(8));
         }
         return barang;
     
}
     public List<Barang> getAllBarang(Connection con) throws SQLException{
        String sql ="select * from barang";
        PreparedStatement ps = con.prepareStatement(sql);
        List<Barang> listBarang = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        Barang barang = null;
        while (rs.next()){
            barang = new Barang();
             barang.setKode_Barang(rs.getString(1));
             barang.setKode_Produk(rs.getString(2));
             barang.setKode_Grup(rs.getString(3));
             barang.setNama_Barang(rs.getString(4));
             barang.setSatuan(rs.getString(5));
             barang.setHarga_Beli(rs.getInt(6));
             barang.setHarga_Jual(rs.getInt(7));
             barang.setStok(rs.getInt(8));
            listBarang.add(barang);
         
        }
        return listBarang;
    }
     public void update(Connection con, Barang barang) throws SQLException{
      String sql ="update barang set Kode_Produk=?, Kode_Grup=?, Nama_Barang=?, Satuan=?, Harga_Beli=?, Harga_Jual=?, Stok=? where Kode_Barang=?";
      PreparedStatement ps = con.prepareStatement(sql);
      ps.setString(1, barang.getKode_Produk());
      ps.setString(2, barang.getKode_Grup());
      ps.setString(3, barang.getNama_Barang());
      ps.setString(4, barang.getSatuan());
      ps.setInt(5, barang.getHarga_Beli());
      ps.setInt(6, barang.getHarga_Jual());
      ps.setInt(7, barang.getStok());
      ps.setString(8, barang.getKode_Barang());
      ps.executeUpdate();
              
    }
     public void delete(Connection con,String Kode_Barang) throws SQLException{
        String sql="delete from barang where Kode_Barang=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,Kode_Barang);
        ps.executeUpdate();
    }
     
     public ResultSet getResultSet(Connection con,String query) throws SQLException{
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery(query);
        return rs;
    }
    
    
    
    
    
}
