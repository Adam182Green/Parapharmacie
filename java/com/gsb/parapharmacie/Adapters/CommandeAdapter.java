package com.gsb.parapharmacie.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.gsb.parapharmacie.Models.CommandeClient;
import com.gsb.parapharmacie.Models.Pharmacie;
import com.gsb.parapharmacie.R;

import org.w3c.dom.Text;

import java.util.List;


public class CommandeAdapter extends ArrayAdapter<CommandeClient> {

    private Context context;

    public CommandeAdapter(Context context, List<CommandeClient> commandes) {
        super(context, 0, commandes);
        this.context = context;
    }

    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        CommandeClient commande = getItem(position);

        if (convertView ==  null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.textview_commande, parent,
                    false);
        }

        TextView nomPharmacie = (TextView) convertView.findViewById(R.id.textview_commandeTVPharmacieNom);
        TextView adressePharmacie = (TextView) convertView.findViewById(R.id.textview_commandeTVPharmacieAdresse);
        TextView dateCreation = (TextView) convertView.findViewById(R.id.textview_commandeTVDateCreation);
        ListView pCC = (ListView) convertView.findViewById(R.id.textview_commandeLVProduits);

        Pharmacie pharmacie = commande.getPharmacie();
        nomPharmacie.setText(pharmacie.getLibelle());
        adressePharmacie.setText(pharmacie.getAdresse());
        dateCreation.setText(commande.getDateCreation()); //TODO Afficher la date mieux
        pCC.setAdapter(new PanierAdapter(context, commande.getProduitCommandeClients()));

        return convertView;
    }
}
