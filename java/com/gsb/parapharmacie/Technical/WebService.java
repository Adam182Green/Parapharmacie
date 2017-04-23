package com.gsb.parapharmacie.Technical;

import com.google.gson.Gson;
import com.gsb.parapharmacie.Models.Departement;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WebService {

    public WebService() {
    }

    private InputStream sendRequest(URL url) throws Exception {
        try {
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.connect();
            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                return urlConnection.getInputStream();
            }
        } catch (Exception e) {
            throw new Exception("");
        }
        return null;
    }

    public Departement getDepartement(String url) throws Exception {
        InputStream is = null;
        try {
            is = sendRequest(new URL(url));
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