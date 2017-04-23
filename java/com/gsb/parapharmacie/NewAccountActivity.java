package com.gsb.parapharmacie;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.gsb.parapharmacie.Models.Client;
import com.gsb.parapharmacie.Models.CommandeClient;
import com.gsb.parapharmacie.Models.Departement;
import com.gsb.parapharmacie.Models.Ville;
import com.gsb.parapharmacie.Technical.Dialog;
import com.gsb.parapharmacie.Technical.WebService;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NewAccountActivity extends AppCompatActivity {

    private Boolean success;
    private EditText prenomET;
    private EditText nomET;
    private EditText adresseET;
    private Spinner villesS;
    private Ville ville;
    private EditText telephoneET;
    private EditText emailET;
    private EditText mdpET;
    private EditText mdpConfirmET;
    private Button newAccountB;
    private TextView goToLoginTV;
    private List<Client> clients;
    private List<Ville> villes;
    private Departement departement;
    private String addClientUrl = "http://localhost:64684/API/Clients/Add";
    private String getClientsUrl = "http://localhost:64684/API/Clients/GetAll/";
    private String getVillesUrl = "http://localhost:64684/API/Villes/GetAll";
    private WebService webService = new WebService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newaccount);

        setViews();
        getData();
//        ArrayAdapter<Ville> villeArrayAdapter = new ArrayAdapter<Ville>(this, android.R.layout.simple_spinner_item, villes);
//        villesS.setAdapter(villeArrayAdapter);

        goToLoginTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_login);
            }
        });
    }

    private void getData() {
        AsyncTask<Void, Void, List<Ville>> execute = new AsyncTask<Void, Void, List<Ville>>() {
            @Override
            protected List<Ville> doInBackground(Void... params) {
                try {
                    return webService.getVillesByCodePostal("Hello");
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(List<Ville> result) {
                prenomET.setText(result.get(0).toString());
            }
        }.execute();
    }

    private void setViews(){
        prenomET = (EditText) findViewById(R.id.newAccountETPrenom);
        nomET = (EditText) findViewById(R.id.newAccountETNom);
        adresseET = (EditText) findViewById(R.id.newAccountETAdresse);
        villesS = (Spinner) findViewById(R.id.newAccountSVilles);
        telephoneET = (EditText) findViewById(R.id.newAccountETTelephone);
        emailET = (EditText) findViewById(R.id.newAccountETEmail);
        mdpET = (EditText) findViewById(R.id.newAccountETMdp);
        mdpConfirmET = (EditText) findViewById(R.id.newAccountETMdpConfirmer);
        goToLoginTV = (TextView) findViewById(R.id.newAccountTVGoToLogin);
        newAccountB = (Button) findViewById(R.id.newAccountBNouvCompte);
    }
}