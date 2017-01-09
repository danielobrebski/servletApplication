package pl.epoint.dobrebski.model;

import java.util.HashMap;

/**
 * Created by dobrebski on 04.01.17.
 */
public class ProductDatabase {
    private static Integer productCounter = 0;
    public static HashMap<Integer, Product> productsMap = new HashMap<>();

    public static Integer getNextPK() {
        return productCounter++;
    }
}
