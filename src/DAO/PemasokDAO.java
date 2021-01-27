/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Pemasok;
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
public class PemasokDAO {
     public void insert(Connection con, Pemasok pemasok) throws SQLException{
      String sql ="insert into pemasok values(?,?,?,?,?,?,?,?)";
      PreparedStatement ps = con.prepareStatement(sql);
      ps.setString(1, pemasok.getKode_Pemasok());
      ps.setString(2, pemasok.getNama_Pemasok());
      ps.setString(3, pemasok.getAlamat());
      ps.setString(4, pemasok.getKota());
      ps.setString(5, pemasok.getPropinsi());
      ps.setString(6, pemasok.getNo_Telepon());
      ps.setString(7, pemasok.getNo_Fax());
      ps.setString(8, pemasok.getKontakP());
      ps.executeUpdate();
              
    }
    
     public List<Pemasok> getAllPemasok(Connection con) throws SQLException{
        String sql ="select * from pemasok";
        PreparedStatement ps = con.prepareStatement(sql);
        List<Pemasok> listPemasok = new ArrayList<>();
        ResultSet rs = ps.executeQuery();
        Pemasok pemasok = null;
        while (rs.next()){
            pemasok = new Pemasok();
             pemasok.setKode_Pemasok(rs.getString(1));
             pemasok.setNama_Pemasok(rs.getString(2));
             pemasok.setAlamat(rs.getString(3));
             pemasok.setKota(rs.getString(4));
             pemasok.setPropinsi(rs.getString(5));
             pemasok.setNo_Telepon(rs.getString(6));
             pemasok.setNo_Fax(rs.getString(7));
             pemasok.setKontakP(rs.getString(8));
            listPemasok.add(pemasok);
         
        }
        return listPemasok;
    }
    
     public void update(Connection con,Pemasok pemasok) throws SQLException{
      String sql ="update pemasok set Nama_Pemasok=?,"
              + " Alamat=?, Kota=?,"
              + " Provinsi=?, No_Telepon=?,"
              + " No_Fax=?, KontakP=?  where Kode_Pemasok=?";
      PreparedStatement ps = con.prepareStatement(sql);
      ps.setString(1, pemasok.getNama_Pemasok());
      ps.setString(2, pemasok.getAlamat());
      ps.setString(3, pemasok.getKota());
      ps.setString(4, pemasok.getPropinsi());
      ps.setString(5, pemasok.getNo_Telepon());
      ps.setString(6, pemasok.getNo_Fax());
      ps.setString(7, pemasok.getKontakP());
      ps.setString(8, pemasok.getKode_Pemasok());
      ps.executeUpdate();
    }
     
     public void delete(Connection con,String id) throws SQLException{
        String sql="delete from pemasok where kode_pemasok=?";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1,id);
        ps.executeUpdate();
    }
    
     
     public Pemasok getPemasok(Connection con , String id) throws SQLException{
         String sql="select * from pemasok where Kode_Pemasok=?";
         PreparedStatement ps = con.prepareStatement(sql);
         ps.setString(1, id);
         ResultSet rs =ps.executeQuery();
         Pemasok pemasok= null;
         if(rs.next()){
             pemasok=new Pemasok();
             pemasok.setKode_Pemasok(rs.getString(1));
             pemasok.setNama_Pemasok(rs.getString(2));
             pemasok.setAlamat(rs.getString(3));
             pemasok.setKota(rs.getString(4));
             pemasok.setPropinsi(rs.getString(5));
             pemasok.setNo_Telepon(rs.getString(6));
             pemasok.setNo_Fax(rs.getString(7));
             pemasok.setKontakP(rs.getString(8));
         }
         return pemasok;
     
}
     
     public ResultSet getResultSet(Connection con, String query) throws SQLException{
         Statement stat = con.createStatement();
         ResultSet rs = stat.executeQuery(query);
         return rs;
     }
}
