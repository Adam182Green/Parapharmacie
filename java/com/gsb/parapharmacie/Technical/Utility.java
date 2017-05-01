package com.gsb.parapharmacie.Technical;

import java.math.BigDecimal;

public abstract class Utility {

    public static String roundPrice(double d) {
        BigDecimal bd = new BigDecimal(Double.toString(d));
        bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
        return String.valueOf(bd);
    }
}
