package com.gsb.parapharmacie.Application;

import android.app.Application;

import com.gsb.parapharmacie.Models.Client;
import com.gsb.parapharmacie.Models.Produit;
import com.gsb.parapharmacie.Models.ProduitCommandeClient;

import java.util.ArrayList;
import java.util.List;

public class Parapharmacie extends Application {

    private Client currentUser;
    public Client getCurrentUser() {
        return currentUser;
    }
    public void setCurrentUser(Client currentUser) {
        this.currentUser = currentUser;
    }

    private List<ProduitCommandeClient> panier = new ArrayList<ProduitCommandeClient>();
    public List<ProduitCommandeClient> getPanier() {
        return panier;
    }
    public void addToPanier(Produit produit, int quantite){
        if(productAlreadyInPanier(produit))
            panier.remove(getIndex(produit));
        panier.add(new ProduitCommandeClient(produit, quantite));
    }
    public Boolean productAlreadyInPanier(Produit produit){
        int nbPdtsInCommande = panier.size();
        int i = 0;
        int idProduit = produit.getId();
        Boolean trouver = false;
        while(i < nbPdtsInCommande && !trouver){
            if(panier.get(i).getProduit().getId() == idProduit)
                trouver = true;
            i++;
        }
        return trouver;
    }
    public int getQuantiteInPanier(Produit produit){
        int  i = panier.get(getIndex(produit)).getQuantite();
        return i;
    }

    private int getIndex(Produit produit){
        int indexProduit = -1;
        int i = 0;
        int nbProduits = panier.size();
        int idProduit = produit.getId();
        while(i < nbProduits && indexProduit == -1){
            if(panier.get(i).getProduit().getId() == idProduit)
                indexProduit = i;
            i++;
        }
        return indexProduit;
    }
}
