package com.gsb.parapharmacie.Models;

import java.util.Date;
import java.util.List;

public class CommandeClient {
    private int id;
    private Date dateCreation;
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

    public Date getDateCreation() {
        return dateCreation;
    }
    public void setDateCreation(Date dateCreation) {
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


    public CommandeClient(Date dateCreation, int idClient, Client client, int idPharmacie, Pharmacie pharmacie, List<ProduitCommandeClient> produitCommandeClients) {
        this.dateCreation = dateCreation;
        this.idClient = idClient;
        this.client = client;
        this.idPharmacie = idPharmacie;
        this.pharmacie = pharmacie;
        this.produitCommandeClients = produitCommandeClients;
    }
}
