/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;


import DAO.GrupDAO;
import DAO.ProdukDAO;
import Koneksi.Koneksi;
import Model.Grup;
import Model.Produk;
import View.DialogProduk;
import View.FormProduk;
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
public class ProdukController {
    FormProduk view;
    Produk produk;
    DialogProduk dialogProduk;
    ProdukDAO dao;
    Koneksi k;
    Connection c;

    public ProdukController(DialogProduk dialogProduk) {
        this.dialogProduk = dialogProduk;
    }
    
    
     public void viewDataDialogcs(){
        try {
            DefaultTableModel tableModel=(DefaultTableModel) dialogProduk.getTableData().getModel();
            tableModel.setRowCount(0);
            dao = new ProdukDAO();
            Koneksi conn = new Koneksi();
            String cari = dialogProduk.getTvSearc().getText();
            Connection c = conn.getKoneksi();
            String sql = "select Kode_Produk,Nama_Produk "
                    + "from Produk where "
                    + "Kode_Produk like '" + cari + "%'"
                    + "or Nama_Produk like '" + cari + "%'";
            ResultSet rs =dao.getResultSet(c, sql);
            while(rs.next()){
                Object data[] = {
                    rs.getString(1),
                    rs.getString(2)
                };
                tableModel.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdukController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
public ProdukController(FormProduk view) {
       this.view= view;
       k=new Koneksi();
       dao=new ProdukDAO();
       try {
        c=k.getKoneksi();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(view, e);
    }
    }
   
   
    
    public void insert(){
        produk= new Produk();
        produk.setKode_Produk(view.getTxtKodeProduk().getText());
         String t_kodegrup[] =
                view.getCmbKodeGrup().getSelectedItem().toString().split("-");
        produk.setKode_Grup(t_kodegrup[0]);
        produk.setNama_Produk(view.getTxtNamaProduk().getText());
       
        ProdukDAO dao= new ProdukDAO ();
        Koneksi k= new Koneksi();
        try {
           Connection c = k.getKoneksi();
           dao.insert(c, produk); 
           JOptionPane.showMessageDialog(view, "Entri Data OK");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage());
        }      
    }
    
    public void cariData(){
        String idCari=view.getTxtKodeProduk().getText();
        if(idCari.length()>0){
            try {
                ProdukDAO dao=new ProdukDAO();
                Koneksi k= new Koneksi();
                Connection c=k.getKoneksi();
                produk=dao.getProduk(c, idCari);
                if(produk!=null){
                    view.getTxtKodeProduk().setText(produk.getKode_Produk());
                    view.getCmbKodeGrup().setSelectedItem(produk.getKode_Grup());
                    view.getTxtNamaProduk().setText(produk.getNama_Produk());
                   
                }
                else{
                    JOptionPane.showMessageDialog(view, "Data Tidak Ada");
                }
            } catch (SQLException ex) {
                Logger.getLogger(ProdukController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        else{
            JOptionPane.showMessageDialog(view, "Input Id Cari");
            
        }
    }
    
    public void bersihForm(){
            view.getTxtKodeProduk().setText("");
            view.getTxtKodeProduk().setText("");
            view.getTxtNamaProduk().setText("");
         
            
        }
    
    
     public void update(){
        produk= new Produk();
        produk.setKode_Produk(view.getTxtKodeProduk().getText());
        produk.setKode_Grup(view.getCmbKodeGrup().getSelectedItem().toString());
        produk.setNama_Produk(view.getTxtNamaProduk().getText());
       
        ProdukDAO dao= new ProdukDAO();
        Koneksi k= new Koneksi();
        try {
           Connection c = k.getKoneksi();
           dao.update(c, produk); 
           JOptionPane.showMessageDialog(view, "Update Data OK");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage());
        }      
    }
     
     public void delete(){
         try {
                 
             String idDelete = view.getTxtKodeProduk().getText();
             ProdukDAO dao= new ProdukDAO();
             Koneksi k = new Koneksi();
             Connection c =k.getKoneksi();
             dao.delete(c, idDelete);
             JOptionPane.showMessageDialog(view, "Delete Data OK");
         } catch (SQLException ex) {
            Logger.getLogger(ProdukController.class.getName()).log(Level.SEVERE, null, ex);
         }
     }
     
     public void tampilTabel(){
         try {
             
             DefaultTableModel tabelModel  = (DefaultTableModel) view.getTabelProduk().getModel();
             tabelModel.setRowCount(0);
             ProdukDAO dao = new ProdukDAO();
             Koneksi k= new Koneksi();
             Connection c = k.getKoneksi();
             List<Produk> listAnggota = dao.getAllProduk(c);
             for (Produk a : listAnggota){
                 Object data[]= {
                     a.getKode_Produk(),
                     a.getKode_Grup(),
                     a.getNama_Produk(),
                    
                 };
                tabelModel.addRow(data);
             }
         } catch (SQLException ex) {
             Logger.getLogger(ProdukController.class.getName()).log(Level.SEVERE, null, ex);
         }
         
         
     }

   

     public void IsiGolongan(){
        try {
            List<Grup> list=dao.getAllGrups(c);
            view.getCmbKodeGrup().removeAllItems();
            for(Grup grup :list){
                view.getCmbKodeGrup().addItem(grup.getKode_Grup()+"-"+grup.getNama_Grup());
                
            } 
            
        } catch (Exception e) {
            
        }
    }
     public void PilihTabel(){
            String id=view.getTabelProduk().getValueAt(view.getTabelProduk().getSelectedRow(),0).toString();
            String nama=view.getTabelProduk().getValueAt(view.getTabelProduk().getSelectedRow(),1).toString();

            
            
            GrupDAO dao=new GrupDAO();
            Koneksi conn = new Koneksi();
            
            try {
            Connection c = conn.getKoneksi();
            view.getTxtKodeProduk().setText(id);
            view.getTxtNamaProduk().setText(nama);
           
            
        } catch (SQLException ex) {
            Logger.getLogger(ProdukController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }
     
}
