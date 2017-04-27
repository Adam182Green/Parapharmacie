package com.gsb.parapharmacie.Technical;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gsb.parapharmacie.Models.Produit;
import com.gsb.parapharmacie.Models.Ville;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.List;

public abstract class ProduitService {

    private static final String getProduitsUrl = "http://poujat-thibault.fr/api/produit/get_all.php";

    public static List<Produit> getProduits() throws Exception {
        try {
            HttpURLConnection conn = WebService.get(getProduitsUrl);
            return new Gson().fromJson(new InputStreamReader(conn.getInputStream()), new TypeToken<List<Produit>>(){}.getType());
        } catch (Exception ignored){ }
        return null;
    }
}
