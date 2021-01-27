/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.BarangDAO;
import DAO.PelangganDAO;
import DAO.PembelianDAO;
import Koneksi.Koneksi;
import Model.Barang;
import Model.Pelanggan;
import Model.Pembelian;
import View.FormPembelian;
import com.sun.corba.se.impl.orbutil.CorbaResourceUtil;
import com.sun.glass.ui.View;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author saniaasrimonica
 */
public class PembelianController {
    FormPembelian formPembelian;
    Koneksi k;
    Connection c;
    Pembelian pembelian;
    PembelianDAO pembelianDAO;
    SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");

    public PembelianController(FormPembelian formPembelian) {
        this.formPembelian = formPembelian;
        pembelian=new Pembelian();
        k=new Koneksi();
        pembelianDAO=new PembelianDAO();
        try {
            c=k.getKoneksi();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(formPembelian, e);
        }
        
    }
    
      
    public void Insert(){
        pembelian.setNo_Masuk(formPembelian.getTvNomor().getText());
        pembelian.setTgl_Masuk(formPembelian.getTvtglmasuk().getText());
        pembelian.setNamapemasok(formPembelian.getTvNama().getText());
        pembelian.setAlamatPemasok(formPembelian.getTvAlamat().getText());
        pembelian.setKodeBarang(formPembelian.getTvKode().getText());
        pembelian.setNamaBarang(formPembelian.getTvNamaBarang().getText());
        pembelian.setHarga(Double.parseDouble(formPembelian.getTvHarga().getText()));
        pembelian.setSatuan(formPembelian.getTvSatuan().getText());
        pembelian.setTotal(formPembelian.getTvTotal().getText());
        
        try {
            c=k.getKoneksi();
            pembelianDAO.insert(c, pembelian);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(formPembelian, e);
        }
    }
    
    public void Update(){
        pembelian.setNo_Masuk(formPembelian.getTvNomor().getText());
        pembelian.setTgl_Masuk(formPembelian.getTvtglmasuk().getText());
        pembelian.setNamapemasok(formPembelian.getTvNama().getText());
        pembelian.setAlamatPemasok(formPembelian.getTvAlamat().getText());
        pembelian.setKodeBarang(formPembelian.getTvKode().getText());
        pembelian.setNamaBarang(formPembelian.getTvNamaBarang().getText());
        pembelian.setHarga(Double.parseDouble(formPembelian.getTvHarga().getText()));
        pembelian.setSatuan(formPembelian.getTvSatuan().getText());
        pembelian.setTotal(formPembelian.getTvTotal().getText());
        
        try {
            c=k.getKoneksi();
            pembelianDAO.update(c,pembelian);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(formPembelian, e);
        }
    }

    public void delete(){
         try {
             String idDelete = formPembelian.getTvNomor().getText();
             c =k.getKoneksi();
             pembelianDAO.delete(c, idDelete);
             JOptionPane.showMessageDialog(formPembelian, "Delete Data OK");
         } catch (SQLException ex) {
            Logger.getLogger(PelangganController.class.getName()).log(Level.SEVERE, null, ex);
         }
     }
    
    public void tampilTabel(){
         try {
             
             DefaultTableModel tabelModel  = (DefaultTableModel) formPembelian.getTableData().getModel();
             tabelModel.setRowCount(0);
             c = k.getKoneksi();
             List<Pembelian> listAnggota = pembelianDAO.getAllPembelians(c);
             for (Pembelian a : listAnggota){
                 Object data[]= {
                     a.getNo_Masuk(),
                     a.getTgl_Masuk(),
                     a.getNamapemasok(),
                     a.getAlamatPemasok(),
                     a.getKodeBarang(),
                     a.getNamaBarang(),
                     a.getHarga(),
                     a.getSatuan(),
                     a.getTotal()
                    
                 };
                tabelModel.addRow(data);
             }
         } catch (SQLException ex) {
             Logger.getLogger(PelangganController.class.getName()).log(Level.SEVERE, null, ex);
         }
     }
    
    public void BersihForm(){
        formPembelian.getTvNomor().setText("");
        formPembelian.getTvNama().setText("");
        formPembelian.getTvAlamat().setText("");
        formPembelian.getTvKode().setText("");
        formPembelian.getTvNamaBarang().setText("");
        formPembelian.getTvHarga().setText("");
        formPembelian.getTvSatuan().setText("");
        formPembelian.getTvTotal().setText("");
        
    }
    
 
    
    
    public void cariData()  {
        String idCari=formPembelian.getTvKode().getText();
        if(idCari.length()>0){
            try {
                
                c=k.getKoneksi();
                Barang barang=new Barang();
                BarangDAO dao=new BarangDAO();
                barang=dao.getBarang(c, idCari);
                if(pembelian!=null){
         
                    formPembelian.getTvNamaBarang().setText(barang.getNama_Barang());
                    formPembelian.getTvHarga().setText(barang.getHarga_Beli()+"");
            
                }
                else{
                    JOptionPane.showMessageDialog(formPembelian, "Data Tidak Ada");
                }
            } catch (SQLException ex) {
                Logger.getLogger(PelangganController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        else{
            JOptionPane.showMessageDialog(formPembelian, "Input Id Cari");
            
        }
        
        
    }
    
    public void Transaksi(){
        double harga=Double.parseDouble(formPembelian.getTvHarga().getText());
        int jumla=Integer.parseInt(formPembelian.getTvJumlah().getText());
        double hasil;
        hasil=harga*jumla;
        formPembelian.getTvTotal().setText(String.valueOf(hasil));
}
    
    
}