package com.gsb.parapharmacie.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

public class Composant implements Parcelable {
    private int id;
    private String libelle;

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

    @Override
    public String toString(){
        return libelle;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeString(libelle);
    }

    public static final Parcelable.Creator<Composant> CREATOR = new Parcelable.Creator<Composant>() {
        public Composant createFromParcel(Parcel in) {
            return new Composant(in);
        }

        public Composant[] newArray(int size) {
            return new Composant[size];
        }
    };

    private Composant(Parcel in) {
        this.id = in.readInt();
        this.libelle = in.readString();
    }
}