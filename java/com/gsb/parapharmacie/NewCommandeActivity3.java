package com.gsb.parapharmacie;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.gsb.parapharmacie.Models.Pharmacie;
import com.gsb.parapharmacie.Models.Ville;
import com.gsb.parapharmacie.PostObjects.PharmacieFilterByLibelleAndVilles;

import java.util.List;

public class NewCommandeActivity3 extends AppCompatActivity {

    private ListView pharmaciesLV3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newcommande3listepharmacies);

        setViews();

        String libellePharmacie = getIntent().getStringExtra("libellePharmacie");
        List<Ville> villesChoisies = getIntent().getParcelableArrayListExtra("villesChoisies");
    }

    private class GetPharmaciesByLibelleAndVilles extends AsyncTask<PharmacieFilterByLibelleAndVilles, Void, List<Pharmacie>>{

        @Override
        protected List<Pharmacie> doInBackground(PharmacieFilterByLibelleAndVilles... params) {
            //TODO Requête filter
            return null;
        }

        @Override
        protected void onPostExecute(List<Pharmacie> pharmacies){

        }
    }

    private void setViews(){
        pharmaciesLV3 = (ListView)findViewById(R.id.newCommande3LVPharmacies);
    }
}
