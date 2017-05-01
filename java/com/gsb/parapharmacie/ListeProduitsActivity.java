package com.gsb.parapharmacie;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.gsb.parapharmacie.Models.Produit;
import com.gsb.parapharmacie.Technical.ProduitService;

import java.util.List;

public class ListeProduitsActivity extends AppCompatActivity {

    List<Produit> produits;
    ListView produitsLV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listeproduits);

        produitsLV = (ListView) findViewById(R.id.listeProduitsLVProduits);

        new GetProduitsTask().execute();
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

        @Override
        protected void onPostExecute(List<Produit> result){
            if(result != null){
                ArrayAdapter<Produit> adapter = new ArrayAdapter<Produit>(getApplicationContext(), android.R.layout.simple_list_item_1,
                        result);
                produitsLV.setAdapter(adapter);
                produitsLV.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Produit produit = (Produit) produitsLV.getItemAtPosition(position);
                        Intent intent = new Intent(ListeProduitsActivity.this, ProduitActivity.class);
                        intent.putExtra("produit", produit);
                        startActivity(intent);
                    }
                });
            }
        }
    }
}
