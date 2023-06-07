package com.jpmc.theater;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Utils {

    public static BigDecimal scaleAndRound(BigDecimal bd){
        return scaleAndRound(bd, RoundingMode.CEILING);
    }

    public static BigDecimal scaleAndRound(BigDecimal bd, RoundingMode roundingMode){
        return scaleAndRound(bd, roundingMode, 2);
    }

    public static BigDecimal scaleAndRound(BigDecimal bd, RoundingMode roundingMode, int scale){
        return bd.setScale(scale, roundingMode);
    }

}
