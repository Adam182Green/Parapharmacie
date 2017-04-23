package com.gsb.parapharmacie.Models;

public class ProduitCommandeClient {
    private int idProduit;
    private Produit produit;
    private int idCommandeClient;
    private CommandeClient commandeClient;
    private int quantite;

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

    public int getIdCommandeClient() {
        return idCommandeClient;
    }
    public void setIdCommandeClient(int idCommandeClient) {
        this.idCommandeClient = idCommandeClient;
    }

    public CommandeClient getCommandeClient() {
        return commandeClient;
    }
    public void setCommandeClient(CommandeClient commandeClient) {
        this.commandeClient = commandeClient;
    }

    public int getQuantite() {
        return quantite;
    }
    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}
