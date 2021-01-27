/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.PemasokDAO;
import Koneksi.Koneksi;
import Model.Pemasok;
import View.DialogPemasok;
import View.FormPemasok;
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
public class PemasokController {
    FormPemasok view;
    Pemasok pemasok;
    PemasokDAO dao;
    Koneksi k;
    Connection c;
    DialogPemasok dialogPemasok;

    public PemasokController(DialogPemasok dialogPemasok) {
        this.dialogPemasok = dialogPemasok;
    }
    
    
    
    public PemasokController( FormPemasok view){
        this.view= view;
        dao=new PemasokDAO();
        pemasok=new Pemasok();
        k=new Koneksi();
        try {
            c=k.getKoneksi();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(view, e);
        }
    }
    
   /** public PemasokController( DialogPemasok dialogPemasok){
        this.dialogPemasok=dialogPemasok;
    }*/
    
    public void insert(){
        pemasok.setKode_Pemasok(view.getTxtKodePemasok().getText());
        pemasok.setKontakP(view.getTxtKontakPerson().getText());
        pemasok.setNama_Pemasok(view.getTxtNamaPemasok().getText());
        pemasok.setAlamat(view.getTxtAlmat().getText());
        pemasok.setKota(view.getTxtKota().getText());
        pemasok.setPropinsi(view.getTxtPropinsi().getText());
        pemasok.setNo_Telepon(view.getTxtNoTelepon().getText());
        pemasok.setNo_Fax(view.getTxtNoFax().getText());

        try {
           c = k.getKoneksi();
           dao.insert(c, pemasok); 
           JOptionPane.showMessageDialog(view, "Entri Data OK");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage());
        }      
    }
    
    public void cariData(){
        String idCari=view.getTxtKodePemasok().getText();
        if(idCari.length()>0){
            try {
                PemasokDAO dao=new PemasokDAO();
                Koneksi k= new Koneksi();
                Connection c=k.getKoneksi();
                pemasok=dao.getPemasok(c, idCari);
                if(pemasok!=null){
                    view.getTxtKontakPerson().setText(pemasok.getKontakP());
                    view.getTxtNamaPemasok().setText(pemasok.getNama_Pemasok());
                    view.getTxtAlmat().setText(pemasok.getAlamat());
                    view.getTxtKota().setText(pemasok.getKota());
                    view.getTxtPropinsi().setText(pemasok.getPropinsi());
                    view.getTxtNoTelepon().setText(pemasok.getNo_Telepon());
                    view.getTxtNoFax().setText(pemasok.getNo_Fax());
                }
                else{
                    JOptionPane.showMessageDialog(view, "Data Tidak Ada");
                }
            } catch (SQLException ex) {
                Logger.getLogger(PemasokController.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }
        else{
            JOptionPane.showMessageDialog(view, "Input Id Cari");
            
        }
    }
    
    public void bersihForm(){
            view.getTxtKodePemasok().setText("");
            view.getTxtKontakPerson().setText("");
            view.getTxtNamaPemasok().setText("");
            view.getTxtAlmat().setText("");
            view.getTxtKota().setText("");
            view.getTxtPropinsi().setText("");
            view.getTxtNoTelepon().setText("");
            view.getTxtNoFax().setText("");
        }
    
    /**public void isiProdi(){
        try {
            PemasokDAO dao=new PemasokDAO();
            Koneksi k= new Koneksi();
            Connection c=k.getKoneksi();
            List<Pemasok> listProdi=dao.getAllPemasok(c);
            
            view.getCbo().removeAllItems();
            for (Prodi prodi : listProdi){
                view.getCboProdi().addItem(prodi.getId()+"-"+prodi.getNama());
            }
        } catch (SQLException ex) {
            Logger.getLogger(PemasokController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    
     public void update(){
        pemasok= new Pemasok();
        pemasok.setKode_Pemasok(view.getTxtKodePemasok().getText());
        pemasok.setKontakP(view.getTxtKontakPerson().getText());
        pemasok.setNama_Pemasok(view.getTxtNamaPemasok().getText());
        pemasok.setAlamat(view.getTxtAlmat().getText());
        pemasok.setKota(view.getTxtKota().getText());
        pemasok.setPropinsi(view.getTxtPropinsi().getText());
        pemasok.setNo_Telepon(view.getTxtNoTelepon().getText());
        pemasok.setNo_Fax(view.getTxtNoFax().getText());
        try {
           c = k.getKoneksi();
           dao.update(c, pemasok); 
           JOptionPane.showMessageDialog(view, "Update Data OK");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage());
        }      
    }
     
     public void delete(){
         try {
             String idDelete = view.getTxtKodePemasok().getText();
             PemasokDAO dao= new PemasokDAO();
             Koneksi k = new Koneksi();
             Connection c =k.getKoneksi();
             dao.delete(c, idDelete);
             JOptionPane.showMessageDialog(view, "Delete Data OK");
         } catch (SQLException ex) {
            Logger.getLogger(PemasokController.class.getName()).log(Level.SEVERE, null, ex);
         }
     }
     
     public void tampilTabel(){
         try {
             DefaultTableModel tabelModel = (DefaultTableModel) view.getTabelPemasok().getModel();
             tabelModel.setRowCount(0);
             PemasokDAO dao = new PemasokDAO();
             Koneksi k= new Koneksi();
             Connection c = k.getKoneksi();
             List<Pemasok> listPemasok = dao.getAllPemasok(c);
             for (Pemasok a : listPemasok){
                 Object data[]= {
                     a.getKode_Pemasok(),
                     a.getNama_Pemasok(),
                     a.getAlamat(),
                     a.getKota(),
                     a.getPropinsi(),
                     a.getNo_Telepon(),
                     a.getNo_Fax(),
                     a.getKontakP(),
                 };
                tabelModel.addRow(data);
             }
         } catch (SQLException ex) {
             Logger.getLogger(PemasokController.class.getName()).log(Level.SEVERE, null, ex);
         }
     }
       public void PilihTabel(){
            String id=view.getTabelPemasok().getValueAt(view.getTabelPemasok().getSelectedRow(),0).toString();
            
            try {
            pemasok=dao.getPemasok(c, id);
            view.getTxtKodePemasok().setText(pemasok.getKode_Pemasok());
            view.getTxtNamaPemasok().setText(pemasok.getNama_Pemasok());
            view.getTxtAlmat().setText(pemasok.getAlamat());
            view.getTxtKota().setText(pemasok.getKota());
            view.getTxtPropinsi().setText(pemasok.getPropinsi());
            view.getTxtNoTelepon().setText(pemasok.getNo_Telepon());
            view.getTxtNoFax().setText(pemasok.getNo_Fax());
            view.getTxtKontakPerson().setText(pemasok.getKontakP());
            
           
            
        } catch (SQLException ex) {
            Logger.getLogger(GrupController.class.getName()).log(Level.SEVERE, null, ex);
        }
     }

    public void viewDataDialog()  {
        DefaultTableModel tabelModel = (DefaultTableModel) dialogPemasok.getTabelData().getModel();
        tabelModel.setRowCount(0);
        PemasokDAO dao = new PemasokDAO();
        Koneksi k = new Koneksi();
        String cari = dialogPemasok.getTxtKode().getText();
        
        try {
            Connection c =k.getKoneksi();
            String sql = "select kode_pemasok,nama_pemasok,alamat "
                    +"from pemasok where "
                    +"kode_pemasok like '" + cari +"%' "
                    +"or nama_pemasok like '" + cari +"%'";
            ResultSet rs = dao.getResultSet(c, sql);
            while (rs.next()){
                Object data[] = {
                    rs.getString(1),
                    rs.getString(2),
                    rs.getString(3)
                };
                tabelModel.addRow(data);
            }
            }catch (SQLException ex){
                 Logger.getLogger(PemasokController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
