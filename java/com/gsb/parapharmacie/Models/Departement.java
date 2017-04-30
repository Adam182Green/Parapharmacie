package com.gsb.parapharmacie.Models;

public class Departement {
    private String num;
    private String libelle;

    public String getNum() {
        return num;
    }
    public void setNum(String num) {
        this.num = num;
    }

    public String getLibelle() {
        return libelle;
    }
    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    @Override
    public String toString(){
        return libelle + " (" + num + ")";
    }
}
