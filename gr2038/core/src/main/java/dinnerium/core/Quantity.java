package dinnerium.core;

import java.util.List;


public class Quantity {

    // the static final list is supposed to contain all (!) units imaginable.
    // Will be expanded for next assignment
    public static final List<String> units =
            List.of("stk", "dl", "gram");
    private double amount;
    private String unit;

    /**
     * Quantity constructor
     *
     * @param amount of substance
     *
     * @param unit which amount is measured in
     */
    public Quantity(double amount, String unit) {
        this.setAmount(amount);
        this.setUnit(unit);
    }

    /**
     * Checks if input-unit is valid
     *
     * @param unit
     * @return true if valid
     * @throws IllegalArgumentException
     */
    private boolean validateUnit(String unit) throws IllegalArgumentException {
        return units.contains(unit);
    }

    /**
     * Getter for unit variable
     *
     * @return amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Getter for unit variable
     *
     * @return unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     *
     * @param amount
     */
    public void setAmount(double amount) {
        this.amount = Math.round(amount * 100.0) / 100.0;
    }

    /**
     * Sets the unit variable
     *
     * @param unit to be set
     */
    public void setUnit(String unit) {
        if (validateUnit(unit)) {
            this.unit = unit;
        } else {
            throw new IllegalArgumentException(unit + " is not valid");
        }
    }

    /**
     * toString used when setting the itemColumn in the controller
     *
     * @return string of Quantity
     */
    @Override
    public String toString() {
        return amount + " " + unit;
    }
}
