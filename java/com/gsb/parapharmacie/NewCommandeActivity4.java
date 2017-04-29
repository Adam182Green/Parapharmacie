package com.gsb.parapharmacie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.gsb.parapharmacie.Models.Pharmacie;

public class NewCommandeActivity4 extends AppCompatActivity {

    private Button creerB4;
    private Pharmacie pharmacieChosie;
    // TODO Ajouter les autres

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newcommande1panier);

        setViews();

        pharmacieChosie = getIntent().getParcelableExtra("pharmacieChoisie");
    }

    private void setViews(){
        creerB4 = (Button)findViewById(R.id.newCommande4BCreer);
    }
}
