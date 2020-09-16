package dinnerium.core;

import java.util.ArrayList;


public class Quantity {

    public static final ArrayList<String> units = new ArrayList<String>();
    private double amount;
    private String unit;

    public Quantity(double amount, String unit) {
        this.amount = Math.round(amount);
        units.add("stk");
        if(units.contains(unit)) {
            this.unit = unit;
        } else {
            throw new IllegalArgumentException("ERROR - Invalid unit!");
        }
    }

    public double getAmount() {
        return amount;
    }

    public String getUnit() {
        return unit;
    }
}
