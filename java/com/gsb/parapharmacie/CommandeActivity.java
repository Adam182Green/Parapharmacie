package com.gsb.parapharmacie;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.gsb.parapharmacie.Adapters.CommandeAdapter;
import com.gsb.parapharmacie.Application.Parapharmacie;
import com.gsb.parapharmacie.Models.CommandeClient;
import com.gsb.parapharmacie.Technical.CommandeClientService;
import com.gsb.parapharmacie.Technical.WebService;

import java.util.List;

public class CommandeActivity extends AppCompatActivity {

    private ListView commandesLV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commande);

        setViews();

        new getCommandeClientsByClientId().execute(((Parapharmacie)getApplication()).getCurrentUser().getId());
    }

    private class getCommandeClientsByClientId extends AsyncTask<Integer, Void, List<CommandeClient>>{

        @Override
        protected List<CommandeClient> doInBackground(Integer... params) {
            try {
                return CommandeClientService.getCommandeClientsByClientId(params[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(List<CommandeClient> commandes){
            if(commandes != null && commandes.size() != 0){
                commandesLV.setAdapter(new CommandeAdapter(CommandeActivity.this, commandes));
            }
            WebService.disconnect();
        }
    }

    private void setViews(){
        commandesLV = (ListView) findViewById(R.id.commandeLVCommandes);
    }
}