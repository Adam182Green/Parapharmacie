package com.gsb.parapharmacie.Models;

import android.os.Parcel;
import android.os.Parcelable;

public class Ville implements Parcelable {
    private int id;
    private String numDepartement;
    private Departement departement;
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

    @Override
    public String toString(){
        return nom;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(numDepartement);
        dest.writeString(nom);
       dest.writeString(codePostal);
    }

    public static final Parcelable.Creator<Ville> CREATOR = new Parcelable.Creator<Ville>() {
        public Ville createFromParcel(Parcel in) {
            return new Ville(in);
        }

        public Ville[] newArray(int size) {
            return new Ville[size];
        }
    };

    public Ville(Parcel in){
        this.id = in.readInt();
        this.numDepartement = in.readString();
        this.nom = in.readString();
        this.codePostal = in.readString();
    }
}
