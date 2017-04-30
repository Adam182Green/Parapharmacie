package com.gsb.parapharmacie.Technical;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gsb.parapharmacie.Models.Pharmacie;
import com.gsb.parapharmacie.Models.ProduitCommandeClient;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class ProduitCommandeClientService {


    private final static String getProduitCommandeClientByIdUrl = "http://poujat-thibault.fr/api/produit_commande_client/get_produit_by_id_client.php";

    public static ProduitCommandeClient getProduitCommandeClientById(Integer id) throws Exception {
        Map<String,String> params = new LinkedHashMap<>();
        params.put("id", String.valueOf(id));
        try {
            HttpURLConnection conn = WebService.post(getProduitCommandeClientByIdUrl, params);
            return new Gson().fromJson(new InputStreamReader(conn.getInputStream()), ProduitCommandeClient.class);
        } catch (Exception ignored){ }
        return null;
    }

}
