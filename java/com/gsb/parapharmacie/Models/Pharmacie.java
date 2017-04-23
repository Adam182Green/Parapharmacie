package com.gsb.parapharmacie.Models;

import java.util.List;

public class Pharmacie {
    public int id;
    public String libelle;
    public String adresse;
    public int idVille;
    public String telephone;
    public List<StockPharmacie> stockPharmacies;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public int getIdVille() {
        return idVille;
    }

    public void setIdVille(int idVille) {
        this.idVille = idVille;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public List<StockPharmacie> getStockPharmacies() {
        return stockPharmacies;
    }

    public void setStockPharmacies(List<StockPharmacie> stockPharmacies) {
        this.stockPharmacies = stockPharmacies;
    }
}
