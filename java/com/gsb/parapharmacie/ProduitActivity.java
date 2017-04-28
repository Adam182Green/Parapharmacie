package com.gsb.parapharmacie;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;

import com.gsb.parapharmacie.Application.Parapharmacie;
import com.gsb.parapharmacie.Models.Composant;
import com.gsb.parapharmacie.Models.Produit;
import com.gsb.parapharmacie.Technical.Dialog;

public class ProduitActivity extends AppCompatActivity {

    private Produit produit;
    private TextView libelleProduitTV;
    private Button ajouterAuPanierB;
    private ListView composantsLV;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produit);

        produit = getIntent().getParcelableExtra("produit");
        setViews();

        libelleProduitTV.setText(produit.getLibelle());

        ArrayAdapter<Composant> adapter = new ArrayAdapter<Composant>(this, android.R.layout.simple_list_item_1, produit.getComposants());
        composantsLV.setAdapter(adapter);

        ajouterAuPanierB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProduitActivity.this, AjouterAuPanierActivity.class);
                intent.putExtra("produit", produit);
                startActivity(intent);

            }
        });
    }

    private void setViews() {
        libelleProduitTV = (TextView) findViewById(R.id.produitTVLibelleProduit);
        ajouterAuPanierB = (Button) findViewById(R.id.produitBAjouterAuPanier);
        composantsLV = (ListView) findViewById(R.id.produitLVComposants);
    }
}
