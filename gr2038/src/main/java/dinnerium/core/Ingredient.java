package dinnerium.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ingredient {

    private Quantity quantity;
    private String name;

    // Empty constructor is used in the IngredientDeserializer class
    public Ingredient() {}

    // Each ingredient requires a quantity which contains a unit and a name
    public Ingredient(Quantity quantity, String name) {
        this.setName(name);
        this.quantity = quantity;
    }

    // ensures that the ingredient name is rather reasonable
    private String checkIngredientName(String name) throws IllegalArgumentException {
        Pattern p = Pattern.compile("^[ A-Za-z]+$");
        Matcher m = p.matcher(name);
        if(name.isEmpty() || !m.matches()) {
            throw new IllegalArgumentException("ERROR - Invalid ingredient name!");
        }
        return name;
    }

    public Quantity getQuantity() { return quantity;}

    public String getName() {
        return name;
    }

    //Name is set to lowercase for practical reasons
    public void setName(String name) {
        this.name = checkIngredientName(name).toLowerCase();
    }

    public void setQuantity(Quantity quantity) {
        this.quantity = quantity;
    }
}
