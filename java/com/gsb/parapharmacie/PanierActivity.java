package com.gsb.parapharmacie;

import android.content.pm.PackageInstaller;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.gsb.parapharmacie.Adapters.PanierAdapter;
import com.gsb.parapharmacie.Models.Client;
import com.gsb.parapharmacie.Models.CommandeClient;
import com.gsb.parapharmacie.Models.Produit;
import com.gsb.parapharmacie.Models.ProduitCommandeClient;
import com.gsb.parapharmacie.Technical.ClientService;
import com.gsb.parapharmacie.Technical.ProduitCommandeClientService;
import com.gsb.parapharmacie.Technical.ProduitService;

import java.util.ArrayList;
import java.util.List;

public class PanierActivity extends AppCompatActivity {

    ListView monPanierLVPanier;
    List<ProduitCommandeClient> listeDuPanier = new ArrayList<ProduitCommandeClient>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panier);

        monPanierLVPanier = (ListView) findViewById(R.id.monPanierLVPanier);

        new GetProduitsTask().execute();

        PanierAdapter panierAdapter = new PanierAdapter(getApplicationContext(), listeDuPanier);
        monPanierLVPanier.setAdapter(panierAdapter);
    }

    private class GetProduitsTask extends AsyncTask<Void, Void, List<Produit>>{

        @Override
        protected List<Produit> doInBackground(Void... params){
            try {
                return ProduitService.getProduits();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        //@Override
        protected void onPostExecute(List<Produit> result){
            if(result != null){
                ArrayAdapter<Produit> adapter = new ArrayAdapter<Produit>(getApplicationContext(), android.R.layout.simple_list_item_1,
                        result);
                monPanierLVPanier.setAdapter(adapter);
                monPanierLVPanier.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Produit produit = (Produit) monPanierLVPanier.getItemAtPosition(position);
                        Intent intent = new Intent(PanierActivity.this, ProduitActivity.class);
                        intent.putExtra("produit", produit);
                        startActivity(intent);
                    }
                });
            }
        }
    }
}
