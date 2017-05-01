package com.gsb.parapharmacie.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gsb.parapharmacie.Models.Produit;
import com.gsb.parapharmacie.Models.ProduitCommandeClient;
import com.gsb.parapharmacie.R;
import com.gsb.parapharmacie.Technical.Utility;

import java.util.List;

public class PanierAdapter extends ArrayAdapter<ProduitCommandeClient> {

    public PanierAdapter(Context context, List<ProduitCommandeClient> produitCommandeClients) {
        super(context, 0, produitCommandeClients);
    }

    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ProduitCommandeClient pCC = getItem(position);

        if (convertView ==  null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.textview_panier, parent,
                    false);
        }

        TextView libelleProduitTV = (TextView) convertView.findViewById(R.id.textview_panierTVLibelleProduit);
        TextView quantiteTV = (TextView) convertView.findViewById(R.id.textview_panierTVQuantite);
        TextView prixUnitaireTV = (TextView) convertView.findViewById(R.id.textview_panierTVPrixUnitaire);
        TextView prixTotalTV = (TextView) convertView.findViewById(R.id.textview_panierTVPrixTotal);

        if (pCC != null) {
            Produit produit = pCC.getProduit();
            libelleProduitTV.setText(produit.getLibelle());
            quantiteTV.setText("Quantité : " + String.valueOf(pCC.getQuantite()));
            prixUnitaireTV.setText("Prix /unité : " + String.valueOf(produit.getPrix()));
            prixTotalTV.setText("Prix total : " + Utility.roundPrice(produit.getPrix() * pCC.getQuantite()));
        }

        return convertView;
    }

}
