/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.GrupDAO;
import Koneksi.Koneksi;
import Model.Grup;
import View.DialogGrup;
import View.FormGrupBarang;
import java.sql.Connection;
import java.sql.ResultSet;
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
public class GrupController {
    FormGrupBarang view;
    Grup grup;
    DialogGrup dialogGrup;

    public GrupController(DialogGrup dialogGrup) {
        this.dialogGrup = dialogGrup;
    }

    public GrupController(FormGrupBarang view) {
       this.view= view;
    }
    public void viewDataDialogcs(){
        try {
            DefaultTableModel tableModel=(DefaultTableModel)dialogGrup.getTabelData().getModel();
            tableModel.setRowCount(0);
            GrupDAO dao = new GrupDAO();
            Koneksi conn = new Koneksi();
            String cari = dialogGrup.getTxtSearch().getText();
            Connection c = conn.getKoneksi();
            String sql = "select kode_grup,nama_grup "
                    + "from grup where "
                    + "kode_grup like '" + cari + "%'"
                    + "or nama_grup like '" + cari + "%'";
            ResultSet rs =dao.getResultSet(c, sql);
            while(rs.next()){
                Object data[] = {
                    rs.getString(1),
                    rs.getString(2)
                };
                tableModel.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(GrupController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
   
   
    
    public void insert(){
        grup= new Grup();
        grup.setKode_Grup(view.getTxtKodeGrup().getText());
        grup.setNama_Grup(view.getTxtNamaGrup().getText());

       
        
        GrupDAO dao= new GrupDAO ();
        Koneksi k= new Koneksi();
        try {
           Connection c = k.getKoneksi();
           dao.insert(c, grup); 
           JOptionPane.showMessageDialog(view, "Entri Data OK");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage());
        }      
    }
    
    public void cariData(){
        String idCari=view.getTxtKodeGrup().getText();
        if(idCari.length()>0){
            try {
                GrupDAO dao=new GrupDAO();
                Koneksi k= new Koneksi();
                Connection c=k.getKoneksi();
                grup=dao.getGrup(c, idCari);
                if(grup!=null){
                    view.getTxtKodeGrup().setText(grup.getKode_Grup());
                    view.getTxtNamaGrup().setText(grup.getNama_Grup());
                   
                }
                else{
                    JOptionPane.showMessageDialog(view, "Data Tidak Ada");
                }
            } catch (SQLException ex) {
                Logger.getLogger(GrupController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        else{
            JOptionPane.showMessageDialog(view, "Input Id Cari");
            
        }
    }
    
    public void bersihForm(){
            view.getTxtKodeGrup().setText("");
            view.getTxtNamaGrup().setText("");
           
            
        }
    
    
     public void update(){
        grup= new Grup();
        grup.setKode_Grup(view.getTxtKodeGrup().getText());
        grup.setNama_Grup(view.getTxtNamaGrup().getText());
      
   
        
        GrupDAO dao= new GrupDAO();
        Koneksi k= new Koneksi();
        try {
           Connection c = k.getKoneksi();
           dao.update(c, grup); 
           JOptionPane.showMessageDialog(view, "Update Data OK");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage());
        }      
    }
     
     public void delete(){
         try {
           
                 
            
                 
             String idDelete = view.getTxtKodeGrup().getText();
             GrupDAO dao= new GrupDAO();
             Koneksi k = new Koneksi();
             Connection c =k.getKoneksi();
             dao.delete(c, idDelete);
             JOptionPane.showMessageDialog(view, "Delete Data OK");
         } catch (SQLException ex) {
            Logger.getLogger(GrupController.class.getName()).log(Level.SEVERE, null, ex);
         }
     }
     
     public void tampilTabel(){
         try {
             
             DefaultTableModel tabelModel  = (DefaultTableModel) view.getTabelGrupBarang().getModel();
             tabelModel.setRowCount(0);
             GrupDAO dao = new GrupDAO();
             Koneksi k= new Koneksi();
             Connection c = k.getKoneksi();
             List<Grup> listAnggota = dao.getAllGrup(c);
             for (Grup a : listAnggota){
                 Object data[]= {
                     a.getKode_Grup(),
                     a.getNama_Grup(),
                    
                 };
                tabelModel.addRow(data);
             }
         } catch (SQLException ex) {
             Logger.getLogger(GrupController.class.getName()).log(Level.SEVERE, null, ex);
         }
     }

     public void PilihTabel(){
            String id=view.getTabelGrupBarang().getValueAt(view.getTabelGrupBarang().getSelectedRow(),0).toString();
            String nama=view.getTabelGrupBarang().getValueAt(view.getTabelGrupBarang().getSelectedRow(),1).toString();

            
            
            GrupDAO dao=new GrupDAO();
            Koneksi conn = new Koneksi();
            
            try {
            Connection c = conn.getKoneksi();
            view.getTxtKodeGrup().setText(id);
            view.getTxtNamaGrup().setText(nama);
           
            
        } catch (SQLException ex) {
            Logger.getLogger(GrupController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
   

    
}
