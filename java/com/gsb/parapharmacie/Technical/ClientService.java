package com.gsb.parapharmacie.Technical;

import com.google.gson.Gson;
import com.gsb.parapharmacie.Models.Client;
import com.gsb.parapharmacie.Models.Ville;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class ClientService {

    private final static String verifyIfClientExistsUrl = "http://poujat-thibault.fr/api/client/verifyIfClientExists.php";
    private final static String createClientUrl = "http://poujat-thibault.fr/api/client/create.php";

    public static Client verifyIfClientExists(String email, String password) throws Exception {
        try {
            Map<String, String> params = new LinkedHashMap<>();
            params.put("email", email);
            params.put("password", password);
            HttpURLConnection conn = WebService.post(verifyIfClientExistsUrl, params);
            return new Gson().fromJson(new InputStreamReader(conn.getInputStream()), Client.class);
        } catch (Exception ignored) {
        }
        return null;
    }

    public static Boolean createClient(Client client) throws Exception {
        try {
            Map<String, String> params = new LinkedHashMap<>();
            params.put("nom", client.getNom());
            params.put("prenom", client.getPrenom());
            params.put("dateNaissance", client.getDateNaissance());
            params.put("email", client.getEmail());
            params.put("motDePasse", client.getMotDePasse());
            params.put("telephone", client.getTelephone());
            params.put("adresse", client.getAdresse());
            params.put("idVille", String.valueOf(client.getIdVille()));
            params.put("numSS", client.getNumSS());
            HttpURLConnection conn = WebService.post(createClientUrl, params);
            return new Gson().fromJson(new InputStreamReader(conn.getInputStream()), Boolean.class);
        } catch (Exception ignored) {
        }
        return false;
    }
}
