package com.gsb.parapharmacie.Technical;

import com.google.gson.Gson;
import com.gsb.parapharmacie.Models.CommandeClient;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class CommandeClientService {

    private final static String getCommandeClientById = "http://poujat-thibault.fr/api/commande_client/getCommandeClientById.php";

    public static CommandeClient getCommandeClientById(Integer id) throws Exception {
        try {
            Map<String, String> params = new LinkedHashMap<>();
            params.put("id", String.valueOf(id));
            HttpURLConnection conn = WebService.post(getCommandeClientById, params);
            return new Gson().fromJson(new InputStreamReader(conn.getInputStream()), CommandeClient.class);
        } catch (Exception ignored) {
        }
        return null;
    }
}
