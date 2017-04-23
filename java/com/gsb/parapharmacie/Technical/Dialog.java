package com.gsb.parapharmacie.Technical;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

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

    public static void custom(Context context, String string){
        new AlertDialog.Builder(context)
                .setTitle("Custom")
                .setMessage(string)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }
}
