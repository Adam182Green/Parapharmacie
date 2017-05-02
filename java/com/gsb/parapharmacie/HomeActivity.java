package com.gsb.parapharmacie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.Toast;

import com.gsb.parapharmacie.Application.Parapharmacie;
import com.gsb.parapharmacie.Models.Client;


public class HomeActivity extends AppCompatActivity {

    private ImageButton panierIB;
    private ImageButton produitIB;
    private ImageButton commandeIB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(((Parapharmacie)getApplication()).getCurrentUser() == null){
            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
            this.finish();
        }
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);

        setViews();

        panierIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, PanierActivity.class));
            }
        });

        commandeIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, NewCommandeActivity1.class));
            }
        });

        produitIB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeActivity.this, ListeProduitsActivity.class));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuItemCommandes:
                startActivity(new Intent(HomeActivity.this, CommandeActivity.class));
                return true;
            case R.id.menuItemPanier:
                startActivity(new Intent(HomeActivity.this, PanierActivity.class));
                return true;
            case R.id.menuItemProfil:
                startActivity(new Intent(HomeActivity.this, ProfilActivity.class));
                return true;
            case R.id.menuItemLogout:
                ((Parapharmacie)getApplication()).setCurrentUser(null);
                startActivity(new Intent(HomeActivity.this, LoginActivity.class));
                finishAffinity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void setViews(){
        panierIB = (ImageButton)findViewById(R.id.panierIB);
        commandeIB = (ImageButton)findViewById(R.id.commandeIB);
        produitIB = (ImageButton)findViewById(R.id.produitIB);
    }
}
