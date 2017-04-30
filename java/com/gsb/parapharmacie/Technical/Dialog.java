package com.gsb.parapharmacie.Technical;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.gsb.parapharmacie.Models.Produit;
import com.gsb.parapharmacie.R;

public abstract class Dialog {

    public static void formInvalide(Context context) {
        new AlertDialog.Builder(context)
                .setTitle("Formulaire invalide")
                .setMessage("Veuillez remplir tous les champs")
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public static void custom(Context context, String title, String message) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    public static void ajouterAuPanier(Context context, Produit produit) {


        new AlertDialog.Builder(context)
                .setTitle("Ajouter " + produit.getLibelle() + " à votre panier")
                .setView(R.layout.activity_ajouteraupanier)
                .setPositiveButton("Ajouter", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel();
                    }
                })
                .setPositiveButton("Annuler", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
    }
}
