package com.gsb.parapharmacie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.gsb.parapharmacie.Application.Parapharmacie;


public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(((Parapharmacie)getApplication()).getCurrentUser() == null){
            startActivity(new Intent(HomeActivity.this, LoginActivity.class));
            this.finish();
        }
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_home);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        ((TextView) findViewById(R.id.texte)).setText("Menu");
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuActionCommande:
                startActivity(new Intent(HomeActivity.this, NewCommandeActivity.class));
                return true;
            case R.id.menuActionPharmacie:
                Toast.makeText(getApplicationContext(), "Pharmacie", Toast.LENGTH_LONG).show();
                return true;
            case R.id.menuItemProfil:
                Toast.makeText(getApplicationContext(), "Mon Profil", Toast.LENGTH_LONG).show();
                return true;
            case R.id.menuItemCommandes:
                Toast.makeText(getApplicationContext(), "Mes Commandes", Toast.LENGTH_LONG).show();
                return true;
            case R.id.menuItemProduits:
                startActivity(new Intent(HomeActivity.this, ListeProduitsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}