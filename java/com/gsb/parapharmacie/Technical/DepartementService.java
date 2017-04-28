package com.gsb.parapharmacie.Technical;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gsb.parapharmacie.Models.Departement;
import com.gsb.parapharmacie.Models.Produit;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public abstract class DepartementService {

    private final static String getDepartementsUrl = "http://poujat-thibault.fr/api/departement/get_all.php";

    public static List<Departement> getDepartements() throws Exception {
        try {
            HttpURLConnection conn = WebService.get(getDepartementsUrl);
            return new Gson().fromJson(new InputStreamReader(conn.getInputStream()), new TypeToken<List<Departement>>(){}.getType());
        } catch (Exception ignored){ }
        return null;
    }
}
