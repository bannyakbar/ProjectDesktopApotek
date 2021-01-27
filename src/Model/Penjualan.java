/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author saniaasrimonica
 */
public class Penjualan {
    private String No_Nota;
    private String Tgl_Nota;
    private Integer Total_Bayar;
    private String Kode_Pelanggan;
    private String UserID;

    public String getNo_Nota() {
        return No_Nota;
    }

    public void setNo_Nota(String No_Nota) {
        this.No_Nota = No_Nota;
    }

    public String getTgl_Nota() {
        return Tgl_Nota;
    }

    public void setTgl_Nota(String Tgl_Nota) {
        this.Tgl_Nota = Tgl_Nota;
    }

    public Integer getTotal_Bayar() {
        return Total_Bayar;
    }

    public void setTotal_Bayar(Integer Total_Bayar) {
        this.Total_Bayar = Total_Bayar;
    }

    public String getKode_Pelanggan() {
        return Kode_Pelanggan;
    }

    public void setKode_Pelanggan(String Kode_Pelanggan) {
        this.Kode_Pelanggan = Kode_Pelanggan;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String UserID) {
        this.UserID = UserID;
    }
    
    
    
}
