package com.gsb.parapharmacie.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gsb.parapharmacie.Models.Pharmacie;
import com.gsb.parapharmacie.Models.Ville;
import com.gsb.parapharmacie.R;

import java.util.List;

public class PharmacieAdapter extends ArrayAdapter<Pharmacie> {

    public PharmacieAdapter(Context context, List<Pharmacie> pharmacies) {
        super(context, 0, pharmacies);
    }

    @NonNull
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Pharmacie pharmacie = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.textview_pharmacie, parent, false);
        }

        TextView nom = (TextView) convertView.findViewById(R.id.pharmacieTVNom);
        TextView adresse = (TextView) convertView.findViewById(R.id.pharmacieTVAdresse);
        TextView ville = (TextView) convertView.findViewById(R.id.pharmacieTVVille);

        if (pharmacie != null) {
            nom.setText(pharmacie.getLibelle());
            adresse.setText(pharmacie.getAdresse());
            Ville v = pharmacie.getVille();
            ville.setText(v.getNom() + ", " + v.getCodePostal());
        }

        return convertView;
    }
}