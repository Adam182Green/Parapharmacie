package com.gsb.parapharmacie.Technical;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.gsb.parapharmacie.Models.Departement;
import com.gsb.parapharmacie.Models.Ville;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

public class WebService {

    public WebService() {
    }

    private InputStream getInputStream(URL url) throws Exception {
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                return conn.getInputStream();
            }
        } catch (Exception e) {
            throw new Exception("");
        }
        return null;
    }

    private HttpURLConnection post(URL url) throws Exception{
        try {
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.connect();
            return conn;
        } catch (Exception e) {
            throw new Exception("");
        }
    }

    public Departement getDepartement() throws Exception {
        InputStream is = null;
        try {
            is = getInputStream(new URL("http://poujat-thibault.fr/android_login_api/test.php"));
            if(is != null)
            return new Gson().fromJson(new InputStreamReader(is), Departement.class);
        } finally {
            if (is != null) {
                is.close();
            }
        }
        return null;
    }

    public List<Ville> getVillesByCodePostal(String codePostal) throws Exception {
        HttpURLConnection conn = null;
        InputStream is = null;
        BufferedReader reader;
        try {
            conn = post(new URL("http://poujat-thibault.fr/api/ville/get_ville_by_code_postal.php"));
            if(conn != null){
                String data = URLEncoder.encode("codePostal", "UTF-8")
                        + "=" + URLEncoder.encode("11420", "UTF-8");
                OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
                wr.write(data);
                wr.flush();
                wr.close();

                //reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                return new Gson().fromJson(new InputStreamReader(conn.getInputStream()), new TypeToken<List<Ville>>(){}.getType());
            }

        } finally {
            if (is != null) {
                is.close();
            }
        }
        return null;
    }

    private static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();

    }

}