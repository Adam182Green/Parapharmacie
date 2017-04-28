package com.gsb.parapharmacie.Adapters;


import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.gsb.parapharmacie.Models.Ville;
import com.gsb.parapharmacie.R;

import java.util.List;

public class VilleAdapter extends ArrayAdapter<Ville> {

    private List<Ville> villes;

    public VilleAdapter(Context context, List<Ville> villes) {
        super(context, 0, villes);
        this.villes = villes;
    }

    @NonNull
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        Ville ville = getItem(position);

        if (convertView ==  null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.textview_ville, parent, false);
        }

        TextView nomEtCodePostal = (TextView) convertView.findViewById(R.id.villeTVLibelleEtCodePostal);

        if (ville != null) {
            nomEtCodePostal.setText(ville.getNom() + " (" + ville.getCodePostal() +")");
        }

        Button supprimmer = (Button) convertView.findViewById(R.id.villeBSupprimer);
        supprimmer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                villes.remove(position);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }
}
