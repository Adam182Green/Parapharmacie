package com.gsb.parapharmacie.Models;

import java.util.Date;
import java.util.List;

public class CommandeClient {
    private int id;
    private String dateCreation;
    private int idClient;
    private Client client;
    private int idPharmacie;
    private Pharmacie pharmacie;
    private List<ProduitCommandeClient> produitCommandeClients;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getDateCreation() {
        return dateCreation;
    }
    public void setDateCreation(String dateCreation) {
        this.dateCreation = dateCreation;
    }

    public int getIdClient() {
        return idClient;
    }
    public void setIdClient(int idClient) {
        this.idClient = idClient;
    }

    public Client getClient() {
        return client;
    }
    public void setClient(Client client) {
        this.client = client;
    }

    public int getIdPharmacie() {
        return idPharmacie;
    }
    public void setIdPharmacie(int idPharmacie) {
        this.idPharmacie = idPharmacie;
    }

    public Pharmacie getPharmacie() {
        return pharmacie;
    }
    public void setPharmacie(Pharmacie pharmacie) {
        this.pharmacie = pharmacie;
    }

    public List<ProduitCommandeClient> getProduitCommandeClients() {
        return produitCommandeClients;
    }
    public void setProduitCommandeClients(List<ProduitCommandeClient> produitCommandeClients) {
        this.produitCommandeClients = produitCommandeClients;
    }


    public CommandeClient(String dateCreation, int idClient, Client client, int idPharmacie, Pharmacie pharmacie, List<ProduitCommandeClient> produitCommandeClients) {
        this.dateCreation = dateCreation;
        this.idClient = idClient;
        this.client = client;
        this.idPharmacie = idPharmacie;
        this.pharmacie = pharmacie;
        this.produitCommandeClients = produitCommandeClients;
    }
}
