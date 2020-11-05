package dinnerium.core;

public class Quantity {

    private double amount;
    private Units unit;

    /**
     * Constructs a Quantity object with the amount and unit.
     *
     * @param amount of substance.
     * @param unit   the unit the amount is measured in.
     */
    public Quantity(double amount, String unit) {
        this.setAmount(amount);
        this.setUnit(unit);
    }

    private boolean validateAmount(double amount) {
        return amount > 0.0;
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
        return unit.toString();
    }

    /**
     * Sets how much of the unit, ex: 3 stk where 3 is amount and stk is unit.
     *
     * @param amount the amount of this quantity
     */
    public void setAmount(double amount) {
        if (validateAmount(amount)) {
            this.amount = Math.round(amount * 100.0) / 100.0;
        } else {
            throw new IllegalArgumentException("Amount must be larger than 0");
        }
    }

    /**
     * Sets the unit of this quantity.
     *
     * @param unit to be set
     * @throws IllegalArgumentException if the unit is not valid
     */
    public void setUnit(String unit) {
        try {
            this.unit = Units.valueOf(unit);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Please select a valid unit");
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
