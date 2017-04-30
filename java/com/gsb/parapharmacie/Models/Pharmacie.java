package com.gsb.parapharmacie.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Pharmacie implements Parcelable {
    private int id;
    private String libelle;
    private String adresse;
    private int idVille;
    private Ville ville;
    private String telephone;
    private List<StockPharmacie> stockPharmacies;

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

    public Ville getVille() {
        return ville;
    }
    public void setVille(Ville ville) {
        this.ville = ville;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.libelle);
        dest.writeString(this.adresse);
        dest.writeParcelable(this.ville, flags);
        dest.writeString(this.telephone);
    }

    public static final Parcelable.Creator<Pharmacie> CREATOR = new Parcelable.Creator<Pharmacie>() {
        public Pharmacie createFromParcel(Parcel in) {
            return new Pharmacie(in);
        }

        public Pharmacie[] newArray(int size) {
            return new Pharmacie[size];
        }
    };

    private Pharmacie(Parcel in) {
        this.id = in.readInt();
        this.libelle = in.readString();
        this.adresse = in.readString();
        this.ville = in.readParcelable(Ville.class.getClassLoader());
        this.telephone = in.readString();
    }
}
