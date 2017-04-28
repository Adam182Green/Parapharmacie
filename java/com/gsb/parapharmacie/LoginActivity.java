package com.gsb.parapharmacie;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gsb.parapharmacie.Application.Parapharmacie;
import com.gsb.parapharmacie.Models.Client;
import com.gsb.parapharmacie.Technical.ClientService;
import com.gsb.parapharmacie.Technical.Dialog;
import com.gsb.parapharmacie.Technical.WebService;

public class LoginActivity extends AppCompatActivity {

    private TextView goToNewAccount;
    private Button connexion;
    private EditText emailET;
    private EditText mdpET;
    private Context context = this;
    private String email;
    private String password;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setViews();

        connexion.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                if(!formIsValid())
                    Dialog.formInvalide(context);
                else
                {
                    email = emailET.getText().toString();
                    password = mdpET.getText().toString();
                    verifyIfClientExists();
                }
            }
        });

        goToNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, NewAccountActivity.class));
            }
        });
    }

    private Boolean formIsValid(){
        return !emailET.getText().toString().equals("") && !mdpET.getText().toString().equals("");
    }

    private void verifyIfClientExists() {
        AsyncTask<Void, Void, Client> execute = new AsyncTask<Void, Void, Client>() {
            @Override
            protected Client doInBackground(Void... params) {
                try {
                    return ClientService.verifyIfClientExists(email, password);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(Client result) {
                if(result == null)
                    Dialog.custom(context, "Erreur", "Vous avez saisi un email ou un mot de passe incorrect.");
                else {
                    Dialog.custom(context, "Succes", "Vous avez saisi un email et un mot de passe correct.");
                    ((Parapharmacie) getApplication()).setCurrentUser(result);
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                    finishAffinity();
                }
                WebService.disconnect();
            }
        }.execute();
    }

    private void setViews(){
        goToNewAccount = (TextView)findViewById(R.id.loginTVGoToNewAccount);
        connexion = (Button)findViewById(R.id.loginBConnexion);
        emailET = (EditText)findViewById(R.id.loginETEmail);
        mdpET = (EditText)findViewById(R.id.loginETMdp);
    }
}