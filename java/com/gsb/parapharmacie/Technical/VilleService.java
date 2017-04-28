package com.gsb.parapharmacie.Technical;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gsb.parapharmacie.Models.Ville;

import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class VilleService {

    private final static String getVillesByCodePostalUrl = "http://poujat-thibault.fr/api/ville/get_ville_by_code_postal.php";
    private final static String getVillesByDepartementUrl = "http://poujat-thibault.fr/api/ville/get_ville_by_numero_departement.php";
    private final static String getVillesByIdUrl = "http://poujat-thibault.fr/api/ville/get_ville_by_id.php";

    public static List<Ville> getVillesByCodePostal(String codePostal) throws Exception {
        Map<String,String> params = new LinkedHashMap<>();
        params.put("codePostal", codePostal);
        try {
            HttpURLConnection conn = WebService.post(getVillesByCodePostalUrl, params);
            return new Gson().fromJson(new InputStreamReader(conn.getInputStream()), new TypeToken<List<Ville>>(){}.getType());
        } catch (Exception ignored){ }
        return null;
    }

    public static List<Ville> getVillesByDepartement(String numDepartement) throws Exception{
        Map<String,String> params = new LinkedHashMap<>();
        params.put("numDepartement", numDepartement);
        try {
            HttpURLConnection conn = WebService.post(getVillesByDepartementUrl, params);
            return new Gson().fromJson(new InputStreamReader(conn.getInputStream()), new TypeToken<List<Ville>>(){}.getType());
        } catch (Exception ignored){ }
        return null;
    }

    public static Ville getVilleById(String id) throws Exception {
        Map<String,String> params = new LinkedHashMap<>();
        params.put("id", id);
        try {
            HttpURLConnection conn = WebService.post(getVillesByIdUrl, params);
            return new Gson().fromJson(new InputStreamReader(conn.getInputStream()), Ville.class);
        } catch (Exception ignored){ }
        return null;
    }
}
