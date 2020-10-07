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
     *        which unit
     * @return true if valid
     */
    private boolean validateUnit(String unit) {
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
     * Sets how much of the unit, ex: 3 stk where 3 is amount and stk is unit
     *
     * @param amount
     *        of the unit
     */
    public void setAmount(double amount) {
        this.amount = Math.round(amount * 100.0) / 100.0;
    }

    /**
     * Sets the unit variable
     *
     * @param unit
     *        to be set
     * @throws IllegalArgumentException
     *         if the unit is not valid
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
