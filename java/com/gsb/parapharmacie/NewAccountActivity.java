package com.gsb.parapharmacie;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class NewAccountActivity extends AppCompatActivity {

    private TextView _goToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newaccount);

        _goToLogin = (TextView) findViewById(R.id.newAccountTVGoToLogin);
        _goToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_login);
            }
        });
    }
}
