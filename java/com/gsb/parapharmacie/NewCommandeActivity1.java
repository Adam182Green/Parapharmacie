package com.gsb.parapharmacie;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.gsb.parapharmacie.Adapters.PanierAdapter;
import com.gsb.parapharmacie.Application.Parapharmacie;
import com.gsb.parapharmacie.Models.CommandeClient;
import com.gsb.parapharmacie.Models.Departement;
import com.gsb.parapharmacie.Models.Produit;
import com.gsb.parapharmacie.Models.ProduitCommandeClient;
import com.gsb.parapharmacie.Models.Ville;
import com.gsb.parapharmacie.Technical.DepartementService;
import com.gsb.parapharmacie.Technical.VilleService;
import com.gsb.parapharmacie.Technical.WebService;

import java.util.List;

public class NewCommandeActivity1 extends AppCompatActivity {

    private ListView panierLV1;
    private Button suivantB1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newcommande1panier);

        setViews();

        List<ProduitCommandeClient> panier = ((Parapharmacie) getApplication()).getPanier();
        if (panier.size() > 0) {
            PanierAdapter adapter = new PanierAdapter(this, panier);
            panierLV1.setAdapter(adapter);
        }

        panierLV1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Produit produit = (Produit) panierLV1.getItemAtPosition(position);
                Intent intent = new Intent(NewCommandeActivity1.this, ProduitActivity.class);
                intent.putExtra("produit", produit);
                startActivity(intent);
            }
        });

        suivantB1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NewCommandeActivity1.this, NewCommandeActivity2.class));
            }
        });
    }

    private void setViews() {
        panierLV1 = (ListView)findViewById(R.id.newCommande1LVPanier);
        suivantB1 = (Button) findViewById(R.id.newCommande1BSuivant);
    }
}