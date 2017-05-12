package com.gsb.parapharmacie.Technical;

import com.gsb.parapharmacie.Models.Pharmacie;

import java.math.BigDecimal;

public abstract class Utility {

    public static Pharmacie pharmacieChoisie;

    public static String roundPrice(double d) {
        BigDecimal bd = new BigDecimal(Double.toString(d));
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        return String.valueOf(bd);
    }

    public static String convertDateString(String date){
        String[] strings = date.split("-");
        return strings[2] + "/" + strings[1] + "/" + strings[0];
    }
}
