package com.gsb.parapharmacie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.gsb.parapharmacie.Models.Composant;
import com.gsb.parapharmacie.Models.Produit;

public class ProduitActivity extends AppCompatActivity {

    private Produit produit;
    private TextView libelleProduitTV;
    private ListView composantsLV;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produit);

        produit = getIntent().getParcelableExtra("produit");
        setViews();

        libelleProduitTV.setText(produit.getLibelle());

        ArrayAdapter<Composant> adapter = new ArrayAdapter<Composant>(this, android.R.layout.simple_list_item_1, produit.getComposants());
        composantsLV.setAdapter(adapter);
    }

    private void setViews() {
        libelleProduitTV = (TextView) findViewById(R.id.produitTVLibelleProduit);
        composantsLV = (ListView) findViewById(R.id.produitLVComposants);
    }
}
