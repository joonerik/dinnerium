package dinnerium.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** Represents an ingredient. */
public class Ingredient {

    private Quantity quantity;
    private String name;

    /**Empty constructor is used in the IngredientDeserializer class. */
    public Ingredient() {}

    /**Each ingredient requires a quantity which contains a unit and a name. */
    public Ingredient(Quantity quantity, String name) {
        this.setName(name);
        this.quantity = quantity;
    }

    /**
     * ensures that the ingredient name is rather reasonable
     *
     * @param  name
     *         the name of the ingredient
     * @return true
     *         if name is on correct format
     */
    private boolean validateIngredientName(String name) {
        Pattern p = Pattern.compile("^[ A-Za-z]+$");
        Matcher m = p.matcher(name);
        return !(name.isEmpty() || !m.matches());

    }


    /**
     * @return Quantity object which belongs in this Ingredient (?).
     * */

    public Quantity getQuantity() {
        return quantity;
    }

    /**
     * @return name of ingredient.
     */
    public String getName() {
        return name;
    }

    /**
     * sets the name for ingredient.
     *
     * @param name
     *        of ingredient
     * @throws IllegalArgumentException
     *        if name is invalid
     */
    //Name is set to lowercase for practical reasons
    public void setName(String name) {
        if (validateIngredientName(name)) {
            this.name = name.toLowerCase();
        } else {
            throw new IllegalArgumentException(name + " is not valid");
        }


    }

    /**
     * sets the quantity for ingredient.
     *
     * @param quantity
     *        of ingredient
     */
    public void setQuantity(Quantity quantity) {
        this.quantity = quantity;
    }
}
