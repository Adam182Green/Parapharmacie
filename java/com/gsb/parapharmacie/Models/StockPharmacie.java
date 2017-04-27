package com.gsb.parapharmacie.Models;

public class StockPharmacie {
    private int idPharmacie;
    private  Pharmacie pharmacie;
    private int idProduit;
    private Produit produit;
    private int stock;

    public int getIdPharmacie() {
        return idPharmacie;
    }
    public void setIdPharmacie(int idPharmacie) {
        this.idPharmacie = idPharmacie;
    }

    public Pharmacie getPharmacie() {
        return pharmacie;
    }
    public void setPharmacie(Pharmacie pharmacie) {
        this.pharmacie = pharmacie;
    }

    public int getIdProduit() {
        return idProduit;
    }
    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public Produit getProduit() {
        return produit;
    }
    public void setProduit(Produit produit) {
        this.produit = produit;
    }

    public int getStock() {
        return stock;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
}
