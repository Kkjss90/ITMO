package com.example.beans;

import com.example.models.Coordinates;
import jakarta.ejb.Stateless;

import java.math.BigDecimal;

@Stateless
public class CoordCheck {
    public Boolean check(Coordinates coordinates) {
        BigDecimal x = new BigDecimal(String.valueOf(coordinates.getX()).replace(',', '.'));
        BigDecimal y = new BigDecimal(String.valueOf(coordinates.getY()).replace(',', '.'));
        BigDecimal r = new BigDecimal(String.valueOf(coordinates.getR()).replace(',', '.'));
        if(r.compareTo(BigDecimal.ZERO) >= 0) {

            boolean circle = x.pow(2).add(y.pow(2)).compareTo(r.pow(2).divide(BigDecimal.valueOf(4))) <= 0
                    && x.compareTo(BigDecimal.ZERO) >= 0
                    && y.compareTo(BigDecimal.ZERO) <= 0;

            boolean triangle = x.compareTo(BigDecimal.ZERO) <= 0
                    && y.compareTo(BigDecimal.ZERO) <= 0
                    && y.compareTo(BigDecimal.valueOf(-1).multiply(x).subtract(r)) >= 0;

            boolean rectangle = x.compareTo(BigDecimal.ZERO) >= 0
                    && x.compareTo(r.divide(BigDecimal.valueOf(2))) <= 0
                    && y.compareTo(BigDecimal.ZERO) >= 0
                    && y.compareTo(r) <= 0;

            return circle || triangle || rectangle;
        }else {
            boolean circle = x.pow(2).add(y.pow(2)).compareTo(r.pow(2).divide(BigDecimal.valueOf(4))) <= 0
                    && x.compareTo(BigDecimal.ZERO) <= 0
                    && y.compareTo(BigDecimal.ZERO) >= 0;

            boolean triangle = x.compareTo(BigDecimal.ZERO) >= 0
                    && y.compareTo(BigDecimal.ZERO) >= 0
                    && y.compareTo(BigDecimal.valueOf(-1).multiply(x).add(BigDecimal.valueOf(-1).multiply(r))) <= 0;

            boolean rectangle = x.compareTo(BigDecimal.ZERO) <= 0
                    && x.compareTo(r.divide(BigDecimal.valueOf(2))) >= 0
                    && y.compareTo(BigDecimal.ZERO) <= 0
                    && y.compareTo(r) >= 0;
            return circle || triangle || rectangle;
        }
    }
}
