/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.PenggunaDAO;
import Koneksi.Koneksi;
import Model.Pengguna;
import View.FormLogin;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author saniaasrimonica
 */
public class LoginController {
    FormLogin view;
    PenggunaDAO dao;
    Connection c;
    Koneksi k;
    Pengguna pengguna;
    public LoginController(FormLogin view) {
        this.view = view;
        dao = new PenggunaDAO();
        k = new Koneksi();
        pengguna = new Pengguna();
        try {
            c = k.getKoneksi();
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void cekLogin() {
        String username = view.getTxtUsername().getText();
        String password = view.getTxtPassword().getText();
        pengguna = null;
        try {
            pengguna = dao.getPenggunaLogin(c, username, password);
            if (pengguna != null) {
                JOptionPane.showMessageDialog(view, "Login Berhasil");
                
            } else {
                JOptionPane.showMessageDialog(view, "Login Gagal");
            }
        } catch (SQLException ex) {
            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
