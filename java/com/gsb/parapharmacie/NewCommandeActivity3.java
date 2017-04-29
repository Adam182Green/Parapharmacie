package com.gsb.parapharmacie;

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
import com.gsb.parapharmacie.Models.Pharmacie;
import com.gsb.parapharmacie.Models.Ville;
import com.gsb.parapharmacie.PostObjects.PharmacieFilterByLibelleAndVilles;
import com.gsb.parapharmacie.Technical.Dialog;
import com.gsb.parapharmacie.Technical.PharmacieService;

import java.util.ArrayList;
import java.util.List;

public class NewCommandeActivity3 extends AppCompatActivity {

    private ListView pharmaciesLV3;
    private Button suivantB3;
    private Pharmacie pharmacieChoisie = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newcommande3listepharmacies);

        setViews();

        PharmacieFilterByLibelleAndVilles filterParams = new PharmacieFilterByLibelleAndVilles();
        filterParams.Libelle = getIntent().getStringExtra("libellePharmacie");
        filterParams.VillesChoisies = getIntent().getParcelableArrayListExtra("villesChoisies");

        suivantB3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (pharmacieChoisie == null) {
                    Dialog.custom(getApplicationContext(), "Attention", "Veuillez sélectionner une pharmacie.");
                } else {
                    Intent intent = new Intent(NewCommandeActivity3.this, NewCommandeActivity4.class);
                    intent.putExtra("pharmacieChoisie", pharmacieChoisie);
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
                return PharmacieService.getPharmaciesByLibelleAndVilles(params[0].Libelle, idVilles);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Pharmacie> pharmacies) {
            if (pharmacies == null || pharmacies.size() == 0) {
                Dialog.custom(getApplicationContext(), "Pas de pharmacies", "Il n'y a pas de pharmacies correspondant aux critères de recherche.");
            } else {
                PharmacieAdapter adapter = new PharmacieAdapter(getApplicationContext(), pharmacies, pharmacieChoisie);
                pharmaciesLV3.setAdapter(adapter);
            }
        }
    }

    private void setViews() {
        pharmaciesLV3 = (ListView) findViewById(R.id.newCommande3LVPharmacies);
        suivantB3 = (Button) findViewById(R.id.newCommande3BSuivant);
    }
}
