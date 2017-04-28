package com.gsb.parapharmacie;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.gsb.parapharmacie.Adapters.VilleAdapter;
import com.gsb.parapharmacie.Models.Departement;
import com.gsb.parapharmacie.Models.Ville;
import com.gsb.parapharmacie.Technical.DepartementService;
import com.gsb.parapharmacie.Technical.VilleService;
import com.gsb.parapharmacie.Technical.WebService;

import java.util.ArrayList;
import java.util.List;

public class NewCommandeActivity2 extends AppCompatActivity {

    private List<Ville> villesChoisies = new ArrayList<Ville>();

    private EditText rechercherPharmacieParLibelleET2;
    private EditText rechercherVilleParCodePostalET2;
    private Spinner departementsS2;
    private Spinner villesS2;
    private Button ajouterVilleB2;
    private ListView listeVillesChoisiesLV2;
    private Button rechercherB2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newcommande2rechercherpharmacies);

        setViews();

        new GetDepartementsTask().execute();

        rechercherVilleParCodePostalET2.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                String codePostal = rechercherVilleParCodePostalET2.getText().toString();
                if (codePostal.length() == 5) {
                    new GetVillesByCodePostalTask().execute(codePostal);
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            public void onTextChanged(CharSequence s, int start, int before, int count) { }
        });

        departementsS2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Departement departement = (Departement) departementsS2.getItemAtPosition(position);
                if(departement != null)
                    new GetVillesByDepartementTask().execute(departement.getNum());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        final VilleAdapter villeAdapter = new VilleAdapter(getApplicationContext(), villesChoisies);
        listeVillesChoisiesLV2.setAdapter(villeAdapter);

        ajouterVilleB2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(villesS2.getSelectedItem() != null){
                    Ville ville = (Ville)villesS2.getSelectedItem();
                    if(!villesChoisies.contains(ville)) {
                        villesChoisies.add((Ville) villesS2.getSelectedItem());
                        villeAdapter.notifyDataSetChanged();
                    }
                }
            }
        });

        rechercherB2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(NewCommandeActivity2.this, NewCommandeActivity3.class);
                intent.putExtra("libellePharmacie", rechercherPharmacieParLibelleET2.getText().toString());
                intent.putParcelableArrayListExtra("villesChoisies", (ArrayList<? extends Parcelable>) villesChoisies);
                startActivity(intent);
            }
        });
    }

    private class GetDepartementsTask extends AsyncTask<Void, Void, List<Departement>> {
        @Override
        protected List<Departement> doInBackground(Void... params){
            try {
                return DepartementService.getDepartements();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Departement> departements){
            ArrayAdapter<Departement> adapter = new ArrayAdapter<Departement>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, departements);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            departementsS2.setAdapter(adapter);
            WebService.disconnect();
        }
    }

    private class GetVillesByCodePostalTask extends AsyncTask<String, Void, List<Ville>> {
        @Override
        protected List<Ville> doInBackground(String... params) {
            try {
                return VilleService.getVillesByCodePostal(params[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Ville> result) {
            ArrayAdapter<Ville> villeArrayAdapter = new ArrayAdapter<Ville>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, result);
            villeArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            villesS2.setAdapter(villeArrayAdapter);
            WebService.disconnect();
        }
    }

    private class GetVillesByDepartementTask extends AsyncTask<String, Void, List<Ville>> {
        @Override
        protected List<Ville> doInBackground(String... params) {
            try {
                return VilleService.getVillesByDepartement(params[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<Ville> result) {
            if(result != null) {
                ArrayAdapter<Ville> villeArrayAdapter = new ArrayAdapter<Ville>(getApplicationContext(), android.R.layout.simple_spinner_dropdown_item, result);
                villeArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                villesS2.setAdapter(villeArrayAdapter);
            }
            WebService.disconnect();
        }
    }

    private void setViews(){
        rechercherPharmacieParLibelleET2 = (EditText) findViewById(R.id.newCommande2ETRechercherPharmacieParLibelle);
        rechercherVilleParCodePostalET2 = (EditText) findViewById(R.id.newCommande2ETRechercherVilleParCodePostal);
        departementsS2 = (Spinner) findViewById(R.id.newCommande2SDepartements);
        villesS2 = (Spinner) findViewById(R.id.newCommande2SVilles);
        ajouterVilleB2 = (Button)findViewById(R.id.newCommande2BAjouterVille);
        listeVillesChoisiesLV2 = (ListView)findViewById(R.id.newCommande2LVListeVillesChoisies);
        rechercherB2 = (Button)findViewById(R.id.newCommande2BRechercher);
    }
}
