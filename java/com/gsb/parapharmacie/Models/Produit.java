package com.gsb.parapharmacie.Models;

import java.util.List;

public class Produit {
    public int id;
    public String libelle;
    public float prix;
    public List<Compose> composes;
    public List<Composant> composants;

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

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public List<Compose> getComposes() {
        return composes;
    }

    public void setComposes(List<Compose> composes) {
        this.composes = composes;
    }

    public List<Composant> getComposants() {
        return composants;
    }

    public void setComposants(List<Composant> composants) {
        this.composants = composants;
    }
}
