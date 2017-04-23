package com.gsb.parapharmacie.Models;

import java.util.Date;
import java.util.List;

public class Client {
    private int id;
    private String nom;
    private String prenom;
    private Date dateNaissance;
    private String email;
    private String motDePasse;
    private String telephone;
    private String adresse;
    private int idVille;
    private Ville ville;
    private String numSS;
    private Boolean valide;
    private List<CommandeClient> commandeClients;


    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }
    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getMotDePasse() {
        return motDePasse;
    }
    public void setMotDePasse(String motDePasse) {
        this.motDePasse = motDePasse;
    }

    public String getTelephone() {
        return telephone;
    }
    public void setTelephone(String telephone) {
        this.telephone = telephone;
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

    public String getNumSS() {
        return numSS;
    }
    public void setNumSS(String numSS) {
        this.numSS = numSS;
    }

    public boolean isValide() {
        return valide;
    }
    public void setValide(boolean valide) {
        this.valide = valide;
    }

    public List<CommandeClient> getCommandeClients() {
        return commandeClients;
    }
    public void setCommandeClients(List<CommandeClient> commandeClients) {
        this.commandeClients = commandeClients;
    }


    public Client(String nom, String prenom, Date dateNaissance, String email, String motDePasse, String telephone, String adresse, int idVille, Ville ville, String numSS, Boolean valide, List<CommandeClient> commandeClients) {
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.email = email;
        this.motDePasse = motDePasse;
        this.telephone = telephone;
        this.adresse = adresse;
        this.idVille = idVille;
        this.ville = ville;
        this.numSS = numSS;
        this.valide = valide;
        this.commandeClients = commandeClients;
    }

}
