package dinnerium.core;

import java.util.*;

public class Quantity {

    public static final Map<String, List<String>> units = new HashMap<>();
    private double amount;
    private String unit;

    public Quantity(double amount, String unit) {
        this.amount = Math.round(amount);
        this.unit = checkUnit(unit);
    }

    private String checkUnit(String unit) throws IllegalArgumentException {

        Iterator it = units.entrySet().iterator();
        while(it.hasNext()) {
            if() {
                throw new IllegalArgumentException("ERROR - Invalid unit!");
            } else {
                return unit;
            }
        }

    }

    public getAmount(String unit) {
        checkUnit(unit);



    }
}
