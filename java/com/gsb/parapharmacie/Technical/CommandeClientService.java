package com.gsb.parapharmacie.Technical;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gsb.parapharmacie.Models.CommandeClient;
import com.gsb.parapharmacie.Models.Pharmacie;
import com.gsb.parapharmacie.Models.ProduitCommandeClient;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class CommandeClientService {

    private static final String createUrl = "http://poujat-thibault.fr/api/commande_client/create.php";
    private final static String getCommandeClientById = "http://poujat-thibault.fr/api/commande_client/getCommandeClientById.php";
  
    public static Boolean create(CommandeClient commande) throws Exception {
        try {
            Map<String, String> params = new LinkedHashMap<>();
            List<ProduitCommandeClient> pCCs = commande.getProduitCommandeClients();
            Integer i = 1;
            Integer nbPCC = pCCs.size();
            if(nbPCC > 0){
                ProduitCommandeClient pCC = pCCs.get(0);
                String idProduits = String.valueOf(pCC.getProduit().getId());
                String quantites = String.valueOf(pCC.getQuantite());
                while(i < nbPCC){
                    pCC = pCCs.get(i);
                    idProduits += "&" + String.valueOf(pCC.getProduit().getId());
                    quantites += "&" + String.valueOf(pCC.getQuantite());
                    i++;
                }
                params.put("idProduits", idProduits);
                params.put("quantites", quantites);
            }

            params.put("dateCreation", commande.getDateCreation());
            params.put("idClient", String.valueOf(commande.getIdClient()));
            params.put("idPharmacie", String.valueOf(commande.getIdPharmacie()));
            HttpURLConnection conn = WebService.post(createUrl, params);
            return new Gson().fromJson(new InputStreamReader(conn.getInputStream()), Boolean.class);
        } catch (Exception ignored) {
        }
        return false;
    }

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
