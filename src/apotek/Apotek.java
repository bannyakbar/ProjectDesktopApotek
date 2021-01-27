/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package apotek;

import Koneksi.Koneksi;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author saniaasrimonica
 */
public class Apotek {
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        Koneksi k = new Koneksi();
        try {
            k.getKoneksi(); 
            JOptionPane.showMessageDialog(null,"Koneksi OK");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
}
