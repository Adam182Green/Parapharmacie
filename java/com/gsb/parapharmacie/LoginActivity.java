package com.gsb.parapharmacie;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.gsb.parapharmacie.Technical.Dialog;

public class LoginActivity extends AppCompatActivity {

    private TextView goToNewAccount;
    private Button connexion;
    private EditText emailET;
    private EditText mdpET;
    private Context context = this;

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

    private void setViews(){
        goToNewAccount = (TextView)findViewById(R.id.loginTVGoToNewAccount);
        connexion = (Button)findViewById(R.id.loginBConnexion);
        emailET = (EditText)findViewById(R.id.loginETEmail);
        mdpET = (EditText)findViewById(R.id.loginETMdp);
    }
}
