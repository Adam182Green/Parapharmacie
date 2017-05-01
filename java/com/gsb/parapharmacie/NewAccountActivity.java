package com.gsb.parapharmacie;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.gsb.parapharmacie.Models.Client;
import com.gsb.parapharmacie.Models.Ville;
import com.gsb.parapharmacie.Technical.ClientService;
import com.gsb.parapharmacie.Technical.Dialog;
import com.gsb.parapharmacie.Technical.VilleService;
import com.gsb.parapharmacie.Technical.WebService;

import java.util.Calendar;
import java.util.List;

public class NewAccountActivity extends AppCompatActivity {

    private EditText prenomET;
    private EditText nomET;
    private DatePicker dateNaissanceDP;
    private EditText adresseET;
    private EditText codePostalET;
    private Button rechercherVillesB;
    private Spinner villesS;
    private EditText telephoneET;
    private EditText numSSET;
    private EditText emailET;
    private EditText mdpET;
    private EditText mdpConfirmET;
    private Button newAccountB;
    private TextView goToLoginTV;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newaccount);

        setViews();

        goToLoginTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NewAccountActivity.this, LoginActivity.class));
            }
        });

        rechercherVillesB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Vérifier que le code postal saisi soit valide
                //TODO Modifier onClick => onChange, exécuter task si codePostal contient 5 caractères
                GetVillesByCodePostalTask task = new GetVillesByCodePostalTask();
                task.execute(codePostalET.getText().toString());
            }
        });

        newAccountB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Vérifier que le formulaire soit remplie et valide / (?) Âge > 16 ans (?)
                CreateNewClientTask task = new CreateNewClientTask();
                String day = String.valueOf(dateNaissanceDP.getDayOfMonth());
                if(day.length() == 1)
                    day = "0" + day;
                String month = String.valueOf(dateNaissanceDP.getMonth() + 1);
                if(month.length() == 1)
                    month = "0" + month;
                String date = String.valueOf(dateNaissanceDP.getYear()) + "-" + month + "-" + day;
                Client client = new Client(nomET.getText().toString(), prenomET.getText().toString(), date,
                        emailET.getText().toString(), mdpET.getText().toString(), telephoneET.getText().toString(),
                        adresseET.getText().toString(), ((Ville)villesS.getSelectedItem()).getId(), numSSET.getText().toString());
                task.execute(client);
            }
        });
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
            ArrayAdapter<Ville> villeArrayAdapter = new ArrayAdapter<Ville>(NewAccountActivity.this, android.R.layout.simple_spinner_dropdown_item, result);
            villeArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            villesS.setAdapter(villeArrayAdapter);
            WebService.disconnect();
        }
    }

    private class CreateNewClientTask extends AsyncTask<Client, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Client... params) {
            try {
                return ClientService.createClient(params[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            WebService.disconnect();
            if (result) {
                //TODO (?) Afficher "compte crée" (?)
                startActivity(new Intent(NewAccountActivity.this, LoginActivity.class));
            } else {
                Dialog.custom(context, "Erreur", "Erreur lors du création du compte.");
            }
        }
    }

    private void setViews() {
        prenomET = (EditText) findViewById(R.id.newAccountETPrenom);
        nomET = (EditText) findViewById(R.id.newAccountETNom);
        dateNaissanceDP = (DatePicker) findViewById(R.id.newAccountDPDateNaissance);
        adresseET = (EditText) findViewById(R.id.newAccountETAdresse);
        codePostalET = (EditText) findViewById(R.id.newAccountETCodePostal);
        rechercherVillesB = (Button) findViewById(R.id.newAccountBRechercherVille);
        villesS = (Spinner) findViewById(R.id.newAccountSVilles);
        telephoneET = (EditText) findViewById(R.id.newAccountETTelephone);
        numSSET = (EditText) findViewById(R.id.newAccountETNumSS);
        emailET = (EditText) findViewById(R.id.newAccountETEmail);
        mdpET = (EditText) findViewById(R.id.newAccountETMdp);
        mdpConfirmET = (EditText) findViewById(R.id.newAccountETMdpConfirmer);
        goToLoginTV = (TextView) findViewById(R.id.newAccountTVGoToLogin);
        newAccountB = (Button) findViewById(R.id.newAccountBNouvCompte);
    }
}