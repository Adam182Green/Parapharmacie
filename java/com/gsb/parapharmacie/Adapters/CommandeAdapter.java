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
import com.gsb.parapharmacie.Models.Etat;
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

        TextView etat = (TextView) convertView.findViewById(R.id.textview_commandeTVEtat);
        TextView dateCreation = (TextView) convertView.findViewById(R.id.textview_commandeTVDateCreation);
        TextView dateModification = (TextView) convertView.findViewById(R.id.textview_commandeTVDateModification);
        TextView nomPharmacie = (TextView) convertView.findViewById(R.id.textview_commandeTVPharmacieNom);
        TextView adressePharmacie = (TextView) convertView.findViewById(R.id.textview_commandeTVPharmacieAdresse);
        ListView pCC = (ListView) convertView.findViewById(R.id.textview_commandeLVProduits);

        Etat e = commande.getEtat();

        etat.setText("Etat : " + e.getTypeEtat());
        dateCreation.setText("Créée le " + commande.getDateCreation()); //TODO Afficher la date mieux
        String dateMod = "Modifiée le " + e.getDateModification();
        if(e.getTypeEtat().equals("Prête"))
            dateMod += " \n " + "Votre commande sera prête le " + e.getDatePrete();
        dateModification.setText(dateMod);
        Pharmacie pharmacie = commande.getPharmacie();
        nomPharmacie.setText(pharmacie.getLibelle());
        adressePharmacie.setText(pharmacie.getAdresse());

        pCC.setAdapter(new PanierAdapter(context, commande.getProduitCommandeClients()));

        return convertView;
    }
}
