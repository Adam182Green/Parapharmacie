package com.gsb.parapharmacie.Adapters;

import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.gsb.parapharmacie.Application.Parapharmacie;
import com.gsb.parapharmacie.ListeProduitsActivity;
import com.gsb.parapharmacie.Models.Produit;
import com.gsb.parapharmacie.Models.ProduitCommandeClient;
import com.gsb.parapharmacie.PanierActivity;
import com.gsb.parapharmacie.R;
import com.gsb.parapharmacie.Technical.Utility;

import java.util.List;

public class PanierModifiableAdapter extends ArrayAdapter<ProduitCommandeClient> {

    private Context context;
    private List<ProduitCommandeClient> produitCommandeClients;
    private TextView prixTV;

    public PanierModifiableAdapter(Context context, List<ProduitCommandeClient> produitCommandeClients, TextView prixTV) {
        super(context, 0, produitCommandeClients);
        this.context = context;
        this.produitCommandeClients = produitCommandeClients;
        this.prixTV = prixTV;
    }

    @NonNull
    public View getView(final int position, View convertView, @NonNull ViewGroup parent) {
        ProduitCommandeClient pCC = getItem(position);

        if (convertView ==  null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.textview_paniermodifiable, parent,
                    false);
        }

        TextView libelleProduitTV = (TextView) convertView.findViewById(R.id.textview_paniermodifiableTVLibelleProduit);
        TextView quantiteTV = (TextView) convertView.findViewById(R.id.textview_paniermodifiableTVQuantite);
        TextView prixUnitaireTV = (TextView) convertView.findViewById(R.id.textview_paniermodifiableTVPrixUnitaire);
        final TextView prixTotalTV = (TextView) convertView.findViewById(R.id.textview_paniermodifiableTVPrixTotal);
        Button supprimerB = (Button) convertView.findViewById(R.id.textview_paniermodifiableBSupprimer);

        if (pCC != null) {
            final Produit produit = pCC.getProduit();
            libelleProduitTV.setText(produit.getLibelle());
            quantiteTV.setText("Quantité : " + String.valueOf(pCC.getQuantite()));
            prixUnitaireTV.setText("Prix /unité : " + String.valueOf(produit.getPrix()));
            prixTotalTV.setText("Prix total : " + Utility.roundPrice(produit.getPrix() * pCC.getQuantite()));
            supprimerB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    new AlertDialog.Builder(context)
                            .setTitle("Attention")
                            .setMessage("Êtes vu sûr(e) de vouloir enlever le produit " + produit.getLibelle() + " de votre panier ?")
                            .setPositiveButton("Oui", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    produitCommandeClients.remove(position);
                                    notifyDataSetChanged();
                                    setPrixTotal();
                                    dialog.cancel();
                                }
                            })
                            .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            })
                            .show();
                }
            });
        }

        return convertView;
    }

    private void setPrixTotal() {
        if (produitCommandeClients.size() == 0) {
            prixTV.setText("Votre panier est vide.");
        } else {
            double prixTotal = 0;
            for (ProduitCommandeClient pCC : produitCommandeClients) {
                prixTotal += pCC.getProduit().getPrix() * pCC.getQuantite();
            }
            prixTV.setText("Prix total :" + Utility.roundPrice(prixTotal) + "€");
        }
    }
}
