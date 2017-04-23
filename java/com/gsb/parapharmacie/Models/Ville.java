package com.gsb.parapharmacie.Models;

public class Ville {
    public int id;
    public String numDepartement;
    public Departement departement;
    public String nom;
    public String codePostal;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumDepartement() {
        return numDepartement;
    }

    public void setNumDepartement(String numDepartement) {
        this.numDepartement = numDepartement;
    }

    public Departement getDepartement() {
        return departement;
    }

    public void setDepartement(Departement departement) {
        this.departement = departement;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCodePostal() {
        return codePostal;
    }

    public void setCodePostal(String codePostal) {
        this.codePostal = codePostal;
    }

    public String toString(){
        return nom;
    }
}
