/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Grup;
import Model.Produk;
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
public class ProdukDAO {
    public void insert(Connection con, Produk produk) throws SQLException {
        String sql ="insert into produk values(?,?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, produk.getKode_Produk());
        ps.setString(2, produk.getKode_Grup());
        ps.setString(3, produk.getNama_Produk());
        ps.executeUpdate();
        
    }
     public Produk getProduk(Connection con , String Kode_Produk) throws SQLException{
         String sql="select * from grup where Kode_Produk=?";
         PreparedStatement ps = con.prepareStatement(sql);
         ps.setString(1, Kode_Produk);
         ResultSet rs =ps.executeQuery();
         Produk produk= null;
         if(rs.next()){
             produk=new Produk();
            ps.setString(1, produk.getKode_Produk());
            ps.setString(2, produk.getKode_Grup());
            ps.setString(3, produk.getNama_Produk());
         }
         return produk;
     
}
     public List<Produk> getAllProduk(Connection con) throws SQLException{
        String sql ="select * from produk";
        PreparedStatement ps = con.prepareStatement(sql);
        List<Produk> listProduk = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        Produk produk = null;
        while (rs.next()){
            produk = new Produk();
            produk.setKode_Produk(rs.getString(1));
            produk.setKode_Grup(rs.getString(2));
            produk.setNama_Produk(rs.getString(3));
            listProduk.add(produk);
         
        }
        return listProduk;
    }
     public void update(Connection con, Produk grup) throws SQLException{
      String sql ="update grup set Kode_Grup=?, Nama_Produk=? where Kode_Produk=?";
      PreparedStatement ps = con.prepareStatement(sql);
      ps.setString(1, grup.getKode_Grup());
      ps.setString(2, grup.getNama_Produk());
      ps.setString(3, grup.getKode_Produk());
      ps.executeUpdate();
              
    }
     public void delete(Connection con,String Kode_Produk) throws SQLException{
        String sql="delete from grup where Kode_Produk=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,Kode_Produk);
        ps.executeUpdate();
    }
     
    
     
     public ResultSet getResultSet(Connection con,String query) throws SQLException{
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery(query);
        return rs;
    }
     
     public  List<Grup> getAllGrups(Connection c) throws SQLException{
        String sql="select * from grup ";
        PreparedStatement ps=c.prepareCall(sql);
        Grup grup=null;
        List<Grup> list=new ArrayList<>();
        ResultSet rs=ps.executeQuery();
        while(rs.next()){
            grup=new Grup();
            grup.setKode_Grup(rs.getString(1));
            grup.setNama_Grup(rs.getString(2));
            list.add(grup);
        }
        return list;
    }
    
    
}
