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
public class GrupDAO {
    public void insert(Connection con, Grup grup) throws SQLException {
        String sql ="insert into grup values(?,?)";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, grup.getKode_Grup());
        ps.setString(2, grup.getNama_Grup());
        ps.executeUpdate();
        
    }
     public Grup getGrup(Connection con , String Kode_Grup) throws SQLException{
         String sql="select * from grup where Kode_Grup=?";
         PreparedStatement ps = con.prepareStatement(sql);
         ps.setString(1, Kode_Grup);
         ResultSet rs =ps.executeQuery();
         Grup grup= null;
         if(rs.next()){
             grup=new Grup();
             grup.setKode_Grup(rs.getString(1));
             grup.setNama_Grup(rs.getString(2));
         }
         return grup;
     
}
     public List<Grup> getAllGrup(Connection con) throws SQLException{
        String sql ="select * from grup";
        PreparedStatement ps = con.prepareStatement(sql);
        List<Grup> listGrup = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        Grup grup = null;
        while (rs.next()){
            grup = new Grup();
            grup.setKode_Grup(rs.getString(1));
            grup.setNama_Grup(rs.getString(2));
            listGrup.add(grup);
         
        }
        return listGrup;
    }
     public void update(Connection con, Grup grup) throws SQLException{
      String sql ="update grup set Nama_Grup=? where Kode_Grup=?";
      PreparedStatement ps = con.prepareStatement(sql);
      ps.setString(1, grup.getNama_Grup());
      ps.setString(2, grup.getKode_Grup());
      ps.executeUpdate();
              
    }
     public void delete(Connection con,String Kode_Grup) throws SQLException{
        String sql="delete from grup where Kode_Grup=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,Kode_Grup);
        ps.executeUpdate();
    }
     public ResultSet getResultSet(Connection con,String query) throws SQLException{
        Statement stat = con.createStatement();
        ResultSet rs = stat.executeQuery(query);
        return rs;
    }
      public List<Grup> getGrup(Connection c) throws SQLException{
         String sql="select * from produk ";
         PreparedStatement ps=c.prepareCall(sql);
         List<Grup> listJenis = new ArrayList<>();
         ResultSet rs=ps.executeQuery();
         Grup grup=null;
         while(rs.next()){
           grup=new Grup();
           grup.setKode_Grup(rs.getString(1));
           grup.setNama_Grup(rs.getString(2));
           listJenis.add(grup);
         }
         return listJenis;
     }
    
    
    
}
