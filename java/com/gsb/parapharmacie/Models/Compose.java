package com.gsb.parapharmacie.Models;

public class Compose {
    private int idProduit;
    private Produit produit;
    private int idComposant;
    private Composant composant;

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

    public int getIdComposant() {
        return idComposant;
    }
    public void setIdComposant(int idComposant) {
        this.idComposant = idComposant;
    }

    public Composant getComposant() {
        return composant;
    }
    public void setComposant(Composant composant) {
        this.composant = composant;
    }
}
