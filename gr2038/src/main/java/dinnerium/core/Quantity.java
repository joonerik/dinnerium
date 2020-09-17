package dinnerium.core;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Quantity {

    // the static final list is supposed to contain all (!) units imaginable. Will be expanded for next assignment
    public static final List<String> units = Collections.unmodifiableList(Arrays.asList("stk", "dl", "gram"));
    private double amount;
    private String unit;

    public Quantity(double amount, String unit) {
        this.setAmount(amount);
        this.setUnit(unit);
    }

    private String checkUnit(String unit) throws IllegalArgumentException {
        if(!units.contains(unit)) {
            throw new IllegalArgumentException("ERROR - Invalid unit!");
        }
        return unit;
    }

    public double getAmount() {
        return amount;
    }

    public String getUnit() {
        return unit;
    }

    public void setAmount(double amount) { this.amount = Math.round(amount * 100.0) / 100.0;}

    public void setUnit(String unit) {this.unit = checkUnit(unit);}

    // the toString is used when setting the itemColumn in the controller
    @Override
    public String toString() {
        return amount + " " + unit;
    }
}
