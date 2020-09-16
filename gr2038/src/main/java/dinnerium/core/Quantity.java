package dinnerium.core;


import java.util.Arrays;
import java.util.List;

public class Quantity {

    public static final List<String> units = Arrays.asList("stk", "dl", "g");
    private double amount;
    private String unit;

    public Quantity(double amount, String unit) {
        this.amount = Math.round(amount * 100.0) / 100.0;
        this.unit = checkUnit(unit);
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
}
