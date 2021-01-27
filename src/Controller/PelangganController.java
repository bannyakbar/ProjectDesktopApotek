/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.PelangganDAO;
import Koneksi.Koneksi;
import Model.Pelanggan;
import View.FormPelanggan;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author saniaasrimonica
 */
public class PelangganController {
    FormPelanggan view;
    Pelanggan pelanggan;
    PelangganDAO dao;
    Koneksi k;
    Connection c;

    public PelangganController(FormPelanggan view) {
       this.view= view;
       dao=new PelangganDAO();
       pelanggan=new Pelanggan();
       k=new Koneksi();
       try {
           c=k.getKoneksi();
        } catch (Exception e) {    
           JOptionPane.showMessageDialog(view, e);
       }
    }
   
   
    
    public void insert(){
        pelanggan= new Pelanggan();
        pelanggan.setKode_Pelanggan(view.getTxtKodePelanggan().getText());
        pelanggan.setNama_Pelanggan(view.getTxtNamaPelanggan().getText());
        pelanggan.setAlamat(view.getTxtAreaAlamat().getText());
        pelanggan.setNo_Telepon(view.getTxtNoTelpon().getText());

    
        try {
           c = k.getKoneksi();
           dao.insert(c, pelanggan); 
            JOptionPane.showMessageDialog(view, "Entri Data OK");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage());
        }      
    }
     public void cariData(){
        String idCari=view.getTxtKodePelanggan().getText();
        if(idCari.length()>0){
            try {
                PelangganDAO dao=new PelangganDAO();
                Koneksi k= new Koneksi();
                Connection c=k.getKoneksi();
                pelanggan=dao.getPelanggan(c, idCari);
                if(pelanggan!=null){
                    view.getTxtNamaPelanggan().setText(pelanggan.getNama_Pelanggan());
                    view.getTxtAreaAlamat().setText(pelanggan.getAlamat());
                    view.getTxtNoTelpon().setText(pelanggan.getNo_Telepon());
                }
                else{
                    JOptionPane.showMessageDialog(view, "Data Tidak Ada");
                }
            } catch (SQLException ex) {
                Logger.getLogger(PelangganController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        else{
            JOptionPane.showMessageDialog(view, "Input Id Cari");
            
        }
    }
    
    
    
    public void bersihForm(){
            view.getTxtKodePelanggan().setText("");
            view.getTxtNamaPelanggan().setText("");
            view.getTxtAreaAlamat().setText("");
            view.getTxtNoTelpon().setText("");
           
            
        }
    
    
     public void update(){
        pelanggan= new Pelanggan();
        pelanggan.setKode_Pelanggan(view.getTxtKodePelanggan().getText());
        pelanggan.setNama_Pelanggan(view.getTxtNamaPelanggan().getText());
        pelanggan.setAlamat(view.getTxtAreaAlamat().getText());
        pelanggan.setNo_Telepon(view.getTxtNoTelpon().getText());
        
        try {
           c = k.getKoneksi();
           dao.update(c, pelanggan); 
           JOptionPane.showMessageDialog(view, "Update Data OK");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage());
        }      
    }
     
     public void delete(){
         try {
             String idDelete = view.getTxtKodePelanggan().getText();
             PelangganDAO dao= new PelangganDAO();
             Koneksi k = new Koneksi();
             Connection c =k.getKoneksi();
             dao.delete(c, idDelete);
             JOptionPane.showMessageDialog(view, "Delete Data OK");
         } catch (SQLException ex) {
            Logger.getLogger(PelangganController.class.getName()).log(Level.SEVERE, null, ex);
         }
     }
     
     public void tampilTabel(){
         try {
             
             DefaultTableModel tabelModel  = (DefaultTableModel) view.getTabelPelanggan().getModel();
             tabelModel.setRowCount(0);
             PelangganDAO dao = new PelangganDAO();
             Koneksi k= new Koneksi();
             Connection c = k.getKoneksi();
             List<Pelanggan> listAnggota = dao.getAllPelanggan(c);
             for (Pelanggan a : listAnggota){
                 Object data[]= {
                     a.getKode_Pelanggan(),
                     a.getNama_Pelanggan(),
                     a.getNo_Telepon(),
                     a.getAlamat()
                    
                 };
                tabelModel.addRow(data);
             }
         } catch (SQLException ex) {
             Logger.getLogger(PelangganController.class.getName()).log(Level.SEVERE, null, ex);
         }
     }
     public void PilihTabel(){
            String id=view.getTabelPelanggan().getValueAt(view.getTabelPelanggan().getSelectedRow(),0).toString();
            
            try {
            pelanggan=dao.getPelanggan(c, id);
            view.getTxtKodePelanggan().setText(pelanggan.getKode_Pelanggan());
            view.getTxtNamaPelanggan().setText(pelanggan.getNama_Pelanggan());
            view.getTxtAreaAlamat().setText(pelanggan.getAlamat());
            view.getTxtNoTelpon().setText(pelanggan.getNo_Telepon());
            
        } catch (SQLException ex) {
            Logger.getLogger(PelangganController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }

}

