package com.gsb.parapharmacie;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.gsb.parapharmacie.Application.Parapharmacie;
import com.gsb.parapharmacie.Models.Client;
import com.gsb.parapharmacie.Models.CommandeClient;
import com.gsb.parapharmacie.Models.Pharmacie;
import com.gsb.parapharmacie.Models.Produit;
import com.gsb.parapharmacie.Models.ProduitCommandeClient;
import com.gsb.parapharmacie.Technical.CommandeClientService;
import com.gsb.parapharmacie.Technical.Dialog;
import com.gsb.parapharmacie.Technical.Utility;
import com.gsb.parapharmacie.Technical.WebService;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class NewCommandeActivity4 extends AppCompatActivity {

    private TextView clientInfosTV4;
    private TextView clientAdresseTV4;
    private TextView pharmacieInfosTV4;
    private TextView pharmacieAdresseTV4;
    private TextView panierInfosTV4;
    private Button creerB4;
    private Client client;
    private List<ProduitCommandeClient> panier;
    private Pharmacie pharmacieChosie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newcommande4confirmer);

        setViews();

        client = ((Parapharmacie)getApplication()).getCurrentUser();
        panier = ((Parapharmacie)getApplication()).getPanier();
        pharmacieChosie = getIntent().getParcelableExtra("pharmacieChoisie");

        clientInfosTV4.setText(client.getPrenom() + " " + client.getNom());
        clientAdresseTV4.setText(   client.getAdresse() + " \n"
                                    + client.getVille().getNom() + " " + client.getVille().getCodePostal());

        pharmacieInfosTV4.setText(pharmacieChosie.getLibelle());
        pharmacieAdresseTV4.setText(pharmacieChosie.getAdresse() + " \n"
                                    + pharmacieChosie.getVille().getNom() + " " + pharmacieChosie.getVille().getCodePostal());

        ProduitCommandeClient pCC = panier.get(0);
        Produit produit = pCC.getProduit();
        int quantite = pCC.getQuantite();
        String panierInfos = produit.getLibelle() + " x" + String.valueOf(quantite);
        Double prixTotal = produit.getPrix() * quantite;
        int nbProduits = panier.size();
        int i = 1;
        while(i < nbProduits){
            pCC = panier.get(i);
            produit = pCC.getProduit();
            quantite = pCC.getQuantite();
            panierInfos += " \n" + produit.getLibelle() + " x" + String.valueOf(quantite);
            prixTotal += produit.getPrix() * quantite;
            i++;
        }

        panierInfosTV4.setText( panierInfos + " \n"
                                + "Prix total :" + Utility.roundPrice(prixTotal));

        creerB4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar today = Calendar.getInstance();
                String day = String.valueOf(today.get(Calendar.DAY_OF_MONTH));
                if(day.length() == 1)
                    day = "0" + day;
                String month = String.valueOf(today.get(Calendar.MONTH) + 1);
                if(month.length() == 1)
                    month = "0" + month;
                String date = String.valueOf(today.get(Calendar.YEAR)) + "-" + month + "-" + day;
                CommandeClient commande = new CommandeClient(date, client.getId(), pharmacieChosie.getId(), panier);
                new CreateCommandeClientTask().execute(commande);
            }
        });
    }

    private class CreateCommandeClientTask extends AsyncTask<CommandeClient, Void, Boolean>{

        @Override
        protected Boolean doInBackground(CommandeClient... params) {
            try {
                return CommandeClientService.create(params[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        protected void onPostExecute(Boolean result){
            WebService.disconnect();
            if(result)
                Dialog.custom(NewCommandeActivity4.this, "Succès", "Commande créée.");
            else
                Dialog.custom(NewCommandeActivity4.this, "Erreur", "Commande non-créée.");
        }
    }

    private void setViews(){
        clientInfosTV4 = (TextView) findViewById(R.id.newCommande4TVClientInfos);
        clientAdresseTV4 = (TextView) findViewById(R.id.newCommande4TVClientAdresse);
        pharmacieInfosTV4 = (TextView) findViewById(R.id.newCommande4TVPharmacieInfos);
        pharmacieAdresseTV4 = (TextView) findViewById(R.id.newCommande4TVPharmacieAdresse);
        panierInfosTV4 = (TextView) findViewById(R.id.newCommande4TVPanierInfos);
        creerB4 = (Button)findViewById(R.id.newCommande4BCreer);
    }
}
