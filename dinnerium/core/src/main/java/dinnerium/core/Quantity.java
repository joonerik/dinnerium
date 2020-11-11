package dinnerium.core;

public class Quantity {

    private final double amount;
    private final Units unit;

    /**
     * Constructs a Quantity object with the amount and unit. Validates if the amount if larger
     * than 0, if not throws IllegalArgumentException. Validates if the unit is in the Enum Units,
     * if not, throws IllegalArgumentException.
     *
     * @param amount of substance.
     * @param unit   the unit the amount is measured in.
     * @throws IllegalArgumentException if the amount is smaller than 0 or the unit is not in the
     *      Enum Units.
     */
    public Quantity(double amount, String unit) {
        if (amount > 0.0) {
            this.amount = Math.round(amount * 100.0) / 100.0;
        } else {
            throw new IllegalArgumentException("Amount must be larger than 0");
        }
        try {
            this.unit = Units.valueOf(unit);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Please select a valid unit");
        }
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
     * toString is used to return the amount and unit in a string format.
     *
     * @return string of Quantity
     */
    @Override
    public String toString() {
        return amount + " " + unit;
    }
}
