package com.gsb.parapharmacie.Application;

import android.app.Application;

import com.gsb.parapharmacie.Models.Client;
import com.gsb.parapharmacie.Models.Produit;
import com.gsb.parapharmacie.Models.ProduitCommandeClient;

import java.util.List;

public class Parapharmacie extends Application {

    private Client currentUser;
    public Client getCurrentUser() {
        return currentUser;
    }
    public void setCurrentUser(Client currentUser) {
        this.currentUser = currentUser;
    }

    private List<ProduitCommandeClient> panier;
    public List<ProduitCommandeClient> getPanier() {
        return panier;
    }
    public void addToPanier(Produit produit, int quantite){
        panier.add(new ProduitCommandeClient(produit, quantite));
    }
}
