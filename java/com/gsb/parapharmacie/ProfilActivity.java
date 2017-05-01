package com.gsb.parapharmacie;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.gsb.parapharmacie.Application.Parapharmacie;
import com.gsb.parapharmacie.Models.Client;
import com.gsb.parapharmacie.Models.Ville;
import com.gsb.parapharmacie.Technical.ClientService;
import com.gsb.parapharmacie.Technical.Dialog;
import com.gsb.parapharmacie.Technical.VilleService;
import com.gsb.parapharmacie.Technical.WebService;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class ProfilActivity extends AppCompatActivity {

    private EditText prenomET;
    private EditText nomET;
    private DatePicker dateNaissanceDP;
    private EditText adresseET;
    private EditText codePostalET;
    private Spinner villesS;
    private EditText telephoneET;
    private EditText numSSET;
    private EditText emailET;
    private EditText mdpET;
    private EditText mdpConfirmET;
    private Button modifierB;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        setViews();
        try {
            setProfil();
        } catch (Exception e) {
            e.printStackTrace();
        }

        modifierB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Véifier que le formulaire soit complet et valide
                new EditClientTask().execute(new Client("edit", "edit", "2017-08-14", "edit", "edit", "80080808", "edit", 1, "edit"));
            }
        });

        codePostalET.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                String codePostal = codePostalET.getText().toString();
                if (codePostal.length() == 5) {
                    new GetVillesByCodePostalTask().execute(codePostal);
                }
            }

            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            public void onTextChanged(CharSequence s, int start, int before, int count) {
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
            ArrayAdapter<Ville> villeArrayAdapter = new ArrayAdapter<Ville>(ProfilActivity.this, android.R.layout.simple_spinner_dropdown_item, result);
            villeArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            villesS.setAdapter(villeArrayAdapter);
            WebService.disconnect();
        }
    }

    private class EditClientTask extends AsyncTask<Client, Void, Boolean> {
        @Override
        protected Boolean doInBackground(Client... params) {
            try {
                return ClientService.updateClient(params[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result)
                Dialog.custom(ProfilActivity.this, "Succès", "Votre profil a été mis à jour.");
            else
                Dialog.custom(ProfilActivity.this, "Echec", "Il y a eu un erreur lors de la mise à jour de votre profil.");
            WebService.disconnect();
        }
    }

    private void setViews() {
        prenomET = (EditText) findViewById(R.id.profilETPrenom);
        nomET = (EditText) findViewById(R.id.profilETNom);
        dateNaissanceDP = (DatePicker) findViewById(R.id.profilDPDateNaissance);
        adresseET = (EditText) findViewById(R.id.profilETAdresse);
        codePostalET = (EditText) findViewById(R.id.profilETCodePostal);
        villesS = (Spinner) findViewById(R.id.profilSVilles);
        telephoneET = (EditText) findViewById(R.id.profilETTelephone);
        numSSET = (EditText) findViewById(R.id.profilETNumSS);
        emailET = (EditText) findViewById(R.id.profilETEmail);
        mdpET = (EditText) findViewById(R.id.profilETMdp);
        mdpConfirmET = (EditText) findViewById(R.id.profilETMdpConfirmer);
        modifierB = (Button) findViewById(R.id.profilBModifier);
    }

    private void setProfil() throws Exception {
        Client currentUser = ((Parapharmacie) getApplication()).getCurrentUser();
        prenomET.setText(currentUser.getPrenom());
        nomET.setText(currentUser.getNom());
        Calendar date = stringToCalendar(currentUser.getDateNaissance());
        dateNaissanceDP.updateDate(date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH));
        adresseET.setText(currentUser.getAdresse());
        codePostalET.setText(currentUser.getVille().getCodePostal());
        telephoneET.setText(currentUser.getTelephone());
        numSSET.setText(currentUser.getNumSS());
        emailET.setText(currentUser.getEmail());
        mdpET.setText(currentUser.getMotDePasse());
    }

    private Calendar stringToCalendar(String date) throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new SimpleDateFormat("yyyy-MM-dd").parse(date));
        return calendar;
    }
}
