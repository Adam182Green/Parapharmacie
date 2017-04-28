package com.gsb.parapharmacie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.gsb.parapharmacie.Application.Parapharmacie;
import com.gsb.parapharmacie.Models.Produit;
import com.gsb.parapharmacie.Technical.Dialog;

public class AjouterAuPanierActivity extends AppCompatActivity {

    private Produit produit;
    private TextView quantiteTV;
    private NumberPicker quantiteNP;
    private Button confirmerAjoutAuPanierB;
    private Button annulerAjoutAuPanierB;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouteraupanier);

        setViews();

        produit = getIntent().getParcelableExtra("produit");

        quantiteNP.setMaxValue(100);
        quantiteNP.setMinValue(1);

        if (((Parapharmacie) getApplication()).productAlreadyInPanier(produit)) {
            quantiteTV.setText("Ce produit est déjà dans votre panier. Modifiez sa quantité :");
            int  i = ((Parapharmacie) getApplication()).getQuantiteInPanier(produit);
            quantiteNP.setValue(i);
        }
        else
            quantiteTV.setText("Quantité à ajouter à votre panier :");



        confirmerAjoutAuPanierB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((Parapharmacie) getApplication()).addToPanier(produit, quantiteNP.getValue());
                finish();
            }
        });

        annulerAjoutAuPanierB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void setViews() {
        quantiteTV = (TextView) findViewById(R.id.ajouterAuPanierTVQuantite);
        quantiteNP = (NumberPicker) findViewById(R.id.ajouterAuPanierNPQuantite);
        confirmerAjoutAuPanierB = (Button) findViewById(R.id.ajouterAuPanierBAjouter);
        annulerAjoutAuPanierB = (Button) findViewById(R.id.ajouterAuPanierBAnnuler);
    }
}