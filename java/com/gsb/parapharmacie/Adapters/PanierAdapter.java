package com.gsb.parapharmacie.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gsb.parapharmacie.Models.ProduitCommandeClient;
import com.gsb.parapharmacie.R;

import java.util.List;

public class PanierAdapter extends ArrayAdapter<ProduitCommandeClient> {

    public PanierAdapter(Context context, List<ProduitCommandeClient> produitCommandeClients) {
        super(context, 0, produitCommandeClients);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ProduitCommandeClient pCC = getItem(position);

        if (convertView ==  null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.textview_panier, parent,
                    false);
        }

        TextView libelleProduitTV = (TextView) convertView.findViewById(R.id.panierTVLibelleProduit);
        TextView quantiteTV = (TextView) convertView.findViewById(R.id.panierTVQuantite);

        libelleProduitTV.setText(pCC.getProduit().getLibelle());
        quantiteTV.setText(pCC.getQuantite());

        return convertView;
    }
}
