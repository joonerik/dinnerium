package dinnerium.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ingredient {

    private Quantity quantity;
    private String name;

    public Ingredient() {
    }

    public Ingredient(Quantity quantity, String name) {

        this.name = checkIngredientName(name);
        this.quantity = quantity;

    }

    private String checkIngredientName(String name) throws IllegalArgumentException {

        Pattern p = Pattern.compile("^[ A-Za-z]+$");
        Matcher m = p.matcher(name);
        if(name.isEmpty() || !m.matches()) {
            throw new IllegalArgumentException("ERROR - Invalid ingredient name!");
        }

        return name;
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = checkIngredientName(name);
    }


    public void setQuantity(Quantity quantity) {
        this.quantity = quantity;
    }
}
