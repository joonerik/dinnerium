package dinnerium.core;

import java.util.ArrayList;
import java.util.Arrays;


public class Quantity {

    public static final ArrayList<String> units = new ArrayList<String>(Arrays.asList("stk", "dl", "gram"));
    private double amount;
    private String unit;

    public Quantity() {
    }

    public Quantity(double amount, String unit) {
        this.amount = roundAmount(amount);
        this.unit = checkUnit(unit);
    }

    private double roundAmount(double amount) {
        return Math.round(amount * 100.0) / 100.0;
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

    public void setAmount(double amount) {
        this.amount = roundAmount(amount);
    }

    public void setUnit(String unit) {
        this.unit = checkUnit(unit);
    }

    public static void main(String[] args) {
        Quantity q = new Quantity(1.0, "dl");
        System.out.println(q.getAmount());
    }
}
