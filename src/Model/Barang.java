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
public class Barang {
    private String Kode_Barang;
    private String Kode_Produk;
    private String Kode_Grup;
    private String Nama_Barang;
    private String Satuan;
    private Integer Harga_Beli;
    private Integer Harga_Jual;
    private Integer Stok; 

    public String getKode_Grup() {
        return Kode_Grup;
    }

    public void setKode_Grup(String Kode_Grup) {
        this.Kode_Grup = Kode_Grup;
    }

    public String getKode_Barang() {
        return Kode_Barang;
    }

    public void setKode_Barang(String Kode_Barang) {
        this.Kode_Barang = Kode_Barang;
    }

    public String getKode_Produk() {
        return Kode_Produk;
    }

    public void setKode_Produk(String Kode_Produk) {
        this.Kode_Produk = Kode_Produk;
    }

    public String getNama_Barang() {
        return Nama_Barang;
    }

    public void setNama_Barang(String Nama_Barang) {
        this.Nama_Barang = Nama_Barang;
    }

    public String getSatuan() {
        return Satuan;
    }

    public void setSatuan(String Satuan) {
        this.Satuan = Satuan;
    }

    public Integer getHarga_Beli() {
        return Harga_Beli;
    }

    public void setHarga_Beli(Integer Harga_Beli) {
        this.Harga_Beli = Harga_Beli;
    }

    public Integer getHarga_Jual() {
        return Harga_Jual;
    }

    public void setHarga_Jual(Integer Harga_Jual) {
        this.Harga_Jual = Harga_Jual;
    }

    public Integer getStok() {
        return Stok;
    }

    public void setStok(Integer Stok) {
        this.Stok = Stok;
    }
    
}
