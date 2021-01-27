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
public class Pejualan_Detail {
    private String No_Nota;
    private String Kode_Barang;
    private Integer Harga_Jual;
    private Integer Jumlah;
    private Integer Subtotal;

    public String getNo_Nota() {
        return No_Nota;
    }

    public void setNo_Nota(String No_Nota) {
        this.No_Nota = No_Nota;
    }

    public String getKode_Barang() {
        return Kode_Barang;
    }

    public void setKode_Barang(String Kode_Barang) {
        this.Kode_Barang = Kode_Barang;
    }

    public Integer getHarga_Jual() {
        return Harga_Jual;
    }

    public void setHarga_Jual(Integer Harga_Jual) {
        this.Harga_Jual = Harga_Jual;
    }

    public Integer getJumlah() {
        return Jumlah;
    }

    public void setJumlah(Integer Jumlah) {
        this.Jumlah = Jumlah;
    }

    public Integer getSubtotal() {
        return Subtotal;
    }

    public void setSubtotal(Integer Subtotal) {
        this.Subtotal = Subtotal;
    }
    
    
}
