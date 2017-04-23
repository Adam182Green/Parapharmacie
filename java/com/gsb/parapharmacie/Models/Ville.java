package com.gsb.parapharmacie.Models;

public class Ville {
    private int id;
    private String numDepartement;
    //private Departement departement;
    private String nom;
    private String codePostal;

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

//    public Departement getDepartement() {
//        return departement;
//    }
//    public void setDepartement(Departement departement) {
//        this.departement = departement;
//    }

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
