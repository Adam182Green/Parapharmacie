package com.gsb.parapharmacie.Technical;

import com.google.gson.Gson;
import com.gsb.parapharmacie.Models.Departement;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public abstract class DepartementService {

    private final String getDepartementUrl = "http://poujat-thibault.fr/android_login_api/test.php";

    public Departement getDepartement() throws Exception {
        InputStream is = null;
        try {
            HttpURLConnection conn = WebService.get(getDepartementUrl);
            if (conn != null) {
                is = conn.getInputStream();
            }
            if(is != null)
                return new Gson().fromJson(new InputStreamReader(is), Departement.class);
        } finally {
            if (is != null) {
                is.close();
            }
        }
        return null;
    }
}
