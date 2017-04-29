package com.gsb.parapharmacie.Technical;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.gsb.parapharmacie.Models.Pharmacie;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public abstract class PharmacieService {


    private final static String getPharmaciesByLibelleAndVillesUrl = "http://poujat-thibault.fr/api/pharmacie/get_pharmacies_by_libelle_and_villes.php";
    private final static String getPharmaciesByLibelleUrl = "http://poujat-thibault.fr/api/pharmacie/get_pharmacies_by_libelle.php";
    private final static String getPharmaciesByVillesUrl = "http://poujat-thibault.fr/api/pharmacie/get_pharmacies_by_villes.php";

    public static List<Pharmacie> getPharmaciesByLibelleAndVilles(String libelle, List<Integer> idVilles) throws Exception{
        try {
            Map<String, String> params = new LinkedHashMap<>();
            Integer i = 1;
            Integer nbIds = idVilles.size();
            if(nbIds > 0){
                String data = String.valueOf(idVilles.get(0));
                while(i < nbIds){
                    data += "&" + String.valueOf(idVilles.get(i));
                    i++;
                }
                params.put("idVilles", data);
            }
            if(!libelle.equals(""))
                params.put("libelle", libelle);

            HttpURLConnection conn = WebService.post(getPharmaciesByLibelleAndVillesUrl, params);
            return new Gson().fromJson(new InputStreamReader(conn.getInputStream()), new TypeToken<List<Pharmacie>>(){}.getType());
        } catch (Exception ignored) {
        }
        return null;
    }

}
