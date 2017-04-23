package com.gsb.parapharmacie.Models;

import java.util.Date;
import java.util.List;

public class CommandeClient {
    public int id;
    public Date dateCreation;
    public int idClient;
    public Client client;
    public int idPharmacie;
    public Pharmacie pharmacie;
    public List<ProduitCommandeClient> produitCommandeClients;

    public CommandeClient(Date dateCreation, int idClient, Client client, int idPharmacie, Pharmacie pharmacie, List<ProduitCommandeClient> produitCommandeClients) {
        this.dateCreation = dateCreation;
        this.idClient = idClient;
        this.client = client;
        this.idPharmacie = idPharmacie;
        this.pharmacie = pharmacie;
        this.produitCommandeClients = produitCommandeClients;
    }
}
