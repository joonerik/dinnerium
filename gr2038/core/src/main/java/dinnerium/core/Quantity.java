package dinnerium.core;

import java.util.List;


public class Quantity {

    // the static final list is supposed to contain all (!) units imaginable.
    // Will be expanded for next assignment
    public static final List<String> units =
            List.of("stk", "dl", "gram", "liter", "kg", "ss", "ts");
    private double amount;
    private String unit;

    /**
     * Constructs a Quantity object with the amount and unit.
     *
     * @param amount of substance
     * @param unit which amount is measured in
     */
    public Quantity(double amount, String unit) {
        this.setAmount(amount);
        this.setUnit(unit);
    }

    /**
     * Checks if input-unit is valid.
     *
     * @param unit
     *        which unit
     * @return true if valid
     */
    private boolean validateUnit(String unit) {
        return units.contains(unit);
    }

    /**
     * Returns the amount of this quantity.
     *
     * @return amount
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Returns the unit of this quantity.
     *
     * @return unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * Sets how much of the unit, ex: 3 stk where 3 is amount and stk is unit.
     *
     * @param amount the amount of this quantity
     *
     */
    public void setAmount(double amount) {
        this.amount = Math.round(amount * 100.0) / 100.0;
    }

    /**
     * Sets the unit of this quantity.
     *
     * @param unit to be set
     *
     * @throws IllegalArgumentException if the unit is not valid
     */
    public void setUnit(String unit) {
        if (validateUnit(unit)) {
            this.unit = unit;
        } else {
            throw new IllegalArgumentException(unit + " is not valid");
        }
    }

    /**
     * toString is used to return the amount and unit in a string format.
     *
     * @return string of Quantity
     */
    @Override
    public String toString() {
        return amount + " " + unit;
    }
}
