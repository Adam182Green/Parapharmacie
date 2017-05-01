package com.gsb.parapharmacie;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.gsb.parapharmacie.Adapters.PanierAdapter;
import com.gsb.parapharmacie.Adapters.PanierModifiableAdapter;
import com.gsb.parapharmacie.Application.Parapharmacie;
import com.gsb.parapharmacie.Models.Produit;
import com.gsb.parapharmacie.Models.ProduitCommandeClient;
import com.gsb.parapharmacie.Technical.Dialog;
import com.gsb.parapharmacie.Technical.Utility;

import java.util.List;

public class PanierActivity extends AppCompatActivity {

    ListView panierLV;
    public TextView prixTotalTV;
    List<ProduitCommandeClient> panier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panier);

        setViews();

        panier = ((Parapharmacie) getApplication()).getPanier();

        setPrixTotal();

        PanierModifiableAdapter panierAdapter = new PanierModifiableAdapter(PanierActivity.this, panier, prixTotalTV);
        panierLV.setAdapter(panierAdapter);

        panierLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Produit produit = (Produit) panierLV.getItemAtPosition(position);
                Intent intent = new Intent(PanierActivity.this, ProduitActivity.class);
                intent.putExtra("produit", produit);
                startActivity(intent);
            }
        });
    }

    private void setPrixTotal() {
        if (panier.size() == 0) {
            prixTotalTV.setText("Votre panier est vide.");
        } else {
            double prixTotal = 0;
            for (ProduitCommandeClient pCC : panier) {
                prixTotal += pCC.getProduit().getPrix() * pCC.getQuantite();
            }
            prixTotalTV.setText("Prix total :" + Utility.roundPrice(prixTotal) + "€");
        }
    }

    private void setViews() {
        panierLV = (ListView) findViewById(R.id.panierLVPanier);
        prixTotalTV = (TextView) findViewById(R.id.panierTVPrixTotal);
    }
}
