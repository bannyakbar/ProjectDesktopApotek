/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.BarangDAO;
import Koneksi.Koneksi;
import Model.Barang;
import View.DialogProduk;
import View.FormBarang;
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
public class BarangController {
    FormBarang view;
    Barang barang;
    DialogProduk dialogGroupBarang;
    BarangDAO dao;

    public BarangController(DialogProduk dialogGroupBarang) {
        this.dialogGroupBarang = dialogGroupBarang;
    }
    
    public void viewDataDialogcs(){
        try {
            DefaultTableModel tableModel=(DefaultTableModel) dialogGroupBarang.getTableData().getModel();
            tableModel.setRowCount(0);
            dao = new BarangDAO();
            Koneksi conn = new Koneksi();
            String cari = dialogGroupBarang.getTvSearc().getText();
            Connection c = conn.getKoneksi();
            String sql = "select golongan,gaji "
                    + "from golongan where "
                    + "golongan like '" + cari + "%'"
                    + "or gaji like '" + cari + "%'";
            ResultSet rs =dao.getResultSet(c, sql);
            while(rs.next()){
                Object data[] = {
                    rs.getString(1),
                    rs.getString(2)
                };
                tableModel.addRow(data);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BarangController.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    

    public BarangController(FormBarang view) {
       this.view= view;
    }
    public void getKodebarang(){
        String kodebarang = view.getTxtGrupBarang().getText() + "." + view.getTxtProdukBarang().getText();
        view.getTxtKodeBarang().setText(kodebarang);
    }
   
    
    public void insert(){
        barang= new Barang();
        barang.setKode_Barang(view.getTxtKodeBarang().getText());
        barang.setKode_Produk(view.getTxtProdukBarang().getText());
        barang.setKode_Grup(view.getTxtGrupBarang().getText());
        barang.setNama_Barang(view.getTxtNamaBarang().getText());
        barang.setSatuan(view.getCmbSatuan().getSelectedItem().toString());
        barang.setHarga_Beli(Integer.parseInt(view.getTxtHargaBeli().getText()));
        barang.setHarga_Jual(Integer.parseInt(view.getTxtHargaJual().getText()));
        barang.setStok(Integer.parseInt(view.getTxtStokBarang().getText()));
       
        
        BarangDAO dao= new BarangDAO ();
        Koneksi k= new Koneksi();
        try {
           Connection c = k.getKoneksi();
           dao.insert(c, barang); 
           JOptionPane.showMessageDialog(view, "Entri Data OK");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage());
        }      
    }
    
    public void cariData(){
        String idCari=view.getTxtKodeBarang().getText();
        if(idCari.length()>0){
            try {
                BarangDAO dao=new BarangDAO();
                Koneksi k= new Koneksi();
                Connection c=k.getKoneksi();
                barang=dao.getBarang(c, idCari);
                if(barang!=null){
                    view.getTxtKodeBarang().setText(barang.getKode_Barang());
                    view.getTxtProdukBarang().setText(barang.getKode_Produk());
                    view.getTxtGrupBarang().setText(barang.getKode_Grup());
                    view.getTxtNamaBarang().setText(barang.getNama_Barang());
                    view.getCmbSatuan().setSelectedItem(barang.getSatuan());
                    view.getTxtHargaBeli().setText(barang.getHarga_Beli()+"");
                    view.getTxtHargaJual().setText(barang.getHarga_Jual()+"");
                    view.getTxtStokBarang().setText(barang.getStok()+"");
                }
                else{
                    JOptionPane.showMessageDialog(view, "Data Tidak Ada");
                }
            } catch (SQLException ex) {
                Logger.getLogger(BarangController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        else{
            JOptionPane.showMessageDialog(view, "Input Id Cari");
            
        }
    }
    
    public void bersihForm(){
            view.getTxtKodeBarang().setText("");
            view.getTxtGrupBarang().setText("");
            view.getTxtProdukBarang().setText("");
            view.getTxtNamaBarang().setText("");
            view.getTxtHargaBeli().setText("");
            view.getTxtHargaJual().setText("");
            view.getTxtStokBarang().setText("");
            
        }
    
    
     public void update(){
        barang= new Barang();
        barang.setKode_Barang(view.getTxtKodeBarang().getText());
        barang.setKode_Produk(view.getTxtProdukBarang().getText());
        barang.setKode_Grup(view.getTxtGrupBarang().getText());
        barang.setNama_Barang(view.getTxtNamaBarang().getText());
        barang.setSatuan(view.getCmbSatuan().getSelectedItem().toString());
        barang.setHarga_Beli(Integer.parseInt(view.getTxtHargaBeli().getText()));
        barang.setHarga_Jual(Integer.parseInt(view.getTxtHargaJual().getText()));
        barang.setStok(Integer.parseInt(view.getTxtStokBarang().getText()));
      
   
        
        BarangDAO dao= new BarangDAO();
        Koneksi k= new Koneksi();
        try {
           Connection c = k.getKoneksi();
           dao.update(c, barang); 
           JOptionPane.showMessageDialog(view, "Update Data OK");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage());
        }      
    }
     
     public void delete(){
         try {
           
                 
            
                 
             String idDelete = view.getTxtKodeBarang().getText();
             BarangDAO dao= new BarangDAO();
             Koneksi k = new Koneksi();
             Connection c =k.getKoneksi();
             dao.delete(c, idDelete);
             JOptionPane.showMessageDialog(view, "Delete Data OK");
         } catch (SQLException ex) {
            Logger.getLogger(BarangController.class.getName()).log(Level.SEVERE, null, ex);
         }
     }
     
     public void tampilTabel(){
         try {
             
             DefaultTableModel tabelModel  = (DefaultTableModel) view.getTabelBarang().getModel();
             tabelModel.setRowCount(0);
             BarangDAO dao = new BarangDAO();
             Koneksi k= new Koneksi();
             Connection c = k.getKoneksi();
             List<Barang> listAnggota = dao.getAllBarang(c);
             for (Barang a : listAnggota){
                 Object data[]= {
                     a.getKode_Barang(),
                     a.getKode_Produk(),
                     a.getKode_Grup(),
                     a.getNama_Barang(),
                     a.getSatuan(),
                     a.getHarga_Beli(),
                     a.getHarga_Jual(),
                     a.getStok(),
                     
                    
                 };
                tabelModel.addRow(data);
             }
         } catch (SQLException ex) {
             Logger.getLogger(BarangController.class.getName()).log(Level.SEVERE, null, ex);
         }
     }
}
    

