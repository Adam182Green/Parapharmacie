package com.gsb.parapharmacie.Models;

public class Departement {
    private String num;
    private String nom;


    public String getNum() {
        return num;
    }
    public void setNum(String num) {
        this.num = num;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }


    public Departement(String num, String nom) {
        this.num = num;
        this.nom = nom;
    }
}
