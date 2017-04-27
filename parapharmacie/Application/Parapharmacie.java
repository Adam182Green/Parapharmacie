package com.gsb.parapharmacie.Application;

import android.app.Application;

import com.gsb.parapharmacie.Models.Client;

public class Parapharmacie extends Application {

    private Client currentUser;
    public Client getCurrentUser() {
        return currentUser;
    }
    public void setCurrentUser(Client currentUser) {
        this.currentUser = currentUser;
    }
}
