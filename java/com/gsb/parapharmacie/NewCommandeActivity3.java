package com.gsb.parapharmacie;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.view.menu.MenuBuilder;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.gsb.parapharmacie.Adapters.PharmacieAdapter;
import com.gsb.parapharmacie.Application.Parapharmacie;
import com.gsb.parapharmacie.Models.Pharmacie;
import com.gsb.parapharmacie.Models.ProduitCommandeClient;
import com.gsb.parapharmacie.Models.Ville;
import com.gsb.parapharmacie.PostObjects.PharmacieFilterByLibelleAndVilles;
import com.gsb.parapharmacie.Technical.Dialog;
import com.gsb.parapharmacie.Technical.PharmacieService;
import com.gsb.parapharmacie.Technical.Utility;

import java.util.ArrayList;
import java.util.List;

public class NewCommandeActivity3 extends AppCompatActivity {

    private ListView pharmaciesLV3;
    private Button suivantB3;
    private List<ProduitCommandeClient> panier;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newcommande3listepharmacies);

        panier = ((Parapharmacie)getApplication()).getPanier();

        Utility.pharmacieChoisie = null;

        setViews();

        PharmacieFilterByLibelleAndVilles filterParams = new PharmacieFilterByLibelleAndVilles();
        filterParams.Libelle = getIntent().getStringExtra("libellePharmacie");
        filterParams.VillesChoisies = getIntent().getParcelableArrayListExtra("villesChoisies");

        suivantB3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Utility.pharmacieChoisie == null) {
                    Dialog.custom(NewCommandeActivity3.this, "Attention", "Veuillez sélectionner une pharmacie.");
                } else {
                    Intent intent = new Intent(NewCommandeActivity3.this, NewCommandeActivity4.class);
                    intent.putExtra("pharmacieChoisie", Utility.pharmacieChoisie);
                    startActivity(intent);
                }
            }
        });

        new GetPharmaciesByLibelleAndVilles().execute(filterParams);
    }

    private class GetPharmaciesByLibelleAndVilles extends AsyncTask<PharmacieFilterByLibelleAndVilles, Void, List<Pharmacie>> {

        @Override
        protected List<Pharmacie> doInBackground(PharmacieFilterByLibelleAndVilles... params) {
            try {
                List<Integer> idVilles = new ArrayList<Integer>();
                for (Ville ville : params[0].VillesChoisies)
                    idVilles.add(ville.getId());
                return PharmacieService.getPharmaciesByLibelleAndVilles(params[0].Libelle, idVilles, panier);
            } catch (Exception e) {

                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Pharmacie> pharmacies) {
            if (pharmacies == null || pharmacies.size() == 0) {
                Dialog.custom(NewCommandeActivity3.this, "Pas de pharmacies", "Il n'y a pas de pharmacies correspondant aux critères de recherche.");
            } else {
                PharmacieAdapter adapter = new PharmacieAdapter(NewCommandeActivity3.this, pharmacies);
                pharmaciesLV3.setAdapter(adapter);
            }
        }
    }

    private void setViews() {
        pharmaciesLV3 = (ListView) findViewById(R.id.newCommande3LVPharmacies);
        suivantB3 = (Button) findViewById(R.id.newCommande3BSuivant);
    }
}
