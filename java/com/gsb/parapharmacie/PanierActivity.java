package com.gsb.parapharmacie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;
import android.widget.TextView;

import com.gsb.parapharmacie.Adapters.PanierAdapter;
import com.gsb.parapharmacie.Application.Parapharmacie;
import com.gsb.parapharmacie.Models.ProduitCommandeClient;
import com.gsb.parapharmacie.Technical.Utility;

import java.util.List;

public class PanierActivity extends AppCompatActivity {

    ListView panierLV;
    TextView prixTotalTV;
    List<ProduitCommandeClient> panier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panier);

        setViews();

        panier = ((Parapharmacie) getApplication()).getPanier();

        if (panier.size() == 0) {
            prixTotalTV.setText("Votre panier est vide.");
        } else {
            double prixTotal = 0;
            for (ProduitCommandeClient pCC : panier) {
                prixTotal += pCC.getProduit().getPrix() * pCC.getQuantite();
            }
            prixTotalTV.setText("Prix total :" + Utility.roundPrice(prixTotal));
        }


        PanierAdapter panierAdapter = new PanierAdapter(getApplicationContext(), panier);
        panierLV.setAdapter(panierAdapter);
    }

    private void setViews() {
        panierLV = (ListView) findViewById(R.id.panierLVPanier);
        prixTotalTV = (TextView) findViewById(R.id.panierTVPrixTotal);
    }
}
