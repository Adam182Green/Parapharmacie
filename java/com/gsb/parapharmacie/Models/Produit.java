package com.gsb.parapharmacie.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Produit implements Parcelable {
    private int id;
    private String libelle;
    private float prix;
    private List<Composant> composants;

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

    public List<Composant> getComposants() {
        return composants;
    }
    public void setComposants(List<Composant> composants) {
        this.composants = composants;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.libelle);
        dest.writeFloat(this.prix);
        dest.writeList(this.composants);
    }

    public static final Parcelable.Creator<Produit> CREATOR = new Parcelable.Creator<Produit>() {
        public Produit createFromParcel(Parcel in) {
            return new Produit(in);
        }

        public Produit[] newArray(int size) {
            return new Produit[size];
        }
    };

    private Produit(Parcel in) {
        this.id = in.readInt();
        this.libelle = in.readString();
        this.prix = in.readFloat();
        this.composants = new ArrayList<Composant>();
        in.readList(this.composants, Composant.class.getClassLoader());
    }

    @Override
    public String toString(){
        return libelle;
    }
}
