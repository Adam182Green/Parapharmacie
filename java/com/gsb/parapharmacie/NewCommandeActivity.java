package com.gsb.parapharmacie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.gsb.parapharmacie.Adapters.PanierAdapter;
import com.gsb.parapharmacie.Application.Parapharmacie;
import com.gsb.parapharmacie.Models.Produit;
import com.gsb.parapharmacie.Models.ProduitCommandeClient;

import java.util.List;

public class NewCommandeActivity extends AppCompatActivity {

    private ListView panierLV;
    private Button suivant1B;
    private Button suivant2B;
    private Button creer3B;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newcommande1);

        setViews();

        List<ProduitCommandeClient> panier = ((Parapharmacie)getApplication()).getPanier();
        if(panier.size() > 0) {
            PanierAdapter adapter = new PanierAdapter(this, panier);
            panierLV.setAdapter(adapter);
        }

        panierLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Produit produit = (Produit) panierLV.getItemAtPosition(position);
                Intent intent = new Intent(NewCommandeActivity.this, ProduitActivity.class);
                intent.putExtra("produit",produit);
                startActivity(intent);
            }
        });

        suivant1B.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                setContentView(R.layout.activity_newcommande2);
            }
        });
    }

    private void setViews(){
        panierLV = (ListView) findViewById(R.id.newCommande1LVPanier);
        suivant1B = (Button) findViewById(R.id.newCommande1BSuivant);
        suivant2B = (Button) findViewById(R.id.newCommande2BSuivant);
        creer3B = (Button) findViewById(R.id.newCommande3BCreer);
    }
}