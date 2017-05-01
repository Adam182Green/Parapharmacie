package com.gsb.parapharmacie.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.gsb.parapharmacie.Application.Parapharmacie;
import com.gsb.parapharmacie.Models.Pharmacie;
import com.gsb.parapharmacie.Models.Ville;
import com.gsb.parapharmacie.R;
import com.gsb.parapharmacie.Technical.Dialog;
import com.gsb.parapharmacie.Technical.Utility;

import java.util.ArrayList;
import java.util.List;

public class PharmacieAdapter extends ArrayAdapter<Pharmacie> {

    private List<Pharmacie> pharmacies;
    public Context context;

    public PharmacieAdapter(Context context, List<Pharmacie> pharmacies) {
        super(context, 0, pharmacies);
        this.context = context;
        this.pharmacies = pharmacies;
    }

    @NonNull
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        final Pharmacie pharmacie = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.textview_pharmacie, parent, false);
        }

        TextView nom = (TextView) convertView.findViewById(R.id.pharmacieTVNom);
        TextView adresse = (TextView) convertView.findViewById(R.id.pharmacieTVAdresse);
        TextView ville = (TextView) convertView.findViewById(R.id.pharmacieTVVille);
        final Button choisir = (Button) convertView.findViewById(R.id.pharmacieBChoisir);

        if (pharmacie != null) {
            nom.setText(pharmacie.getLibelle());
            adresse.setText(pharmacie.getAdresse());
            Ville v = pharmacie.getVille();
            ville.setText(v.getNom() + ", " + v.getCodePostal());
        }

        choisir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utility.pharmacieChoisie = pharmacies.get(position);
                Dialog.custom(context, "Information", "Vous venez de choisir la pharmacie " + Utility.pharmacieChoisie.getLibelle());
            }
        });

        return convertView;
    }
}