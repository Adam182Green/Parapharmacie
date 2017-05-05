package com.gsb.parapharmacie.Models;

public class Etat {
    private String id;
    private String typeEtat;
    private String dateModification;
    private String datePrete;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getTypeEtat() {
        return typeEtat;
    }
    public void setTypeEtat(String typeEtat) {
        this.typeEtat = typeEtat;
    }

    public String getDateModification() {
        return dateModification;
    }
    public void setDateModification(String dateModification) {
        this.dateModification = dateModification;
    }

    public String getDatePrete() {
        return datePrete;
    }
    public void setDatePrete(String datePrete) {
        this.datePrete = datePrete;
    }
}
