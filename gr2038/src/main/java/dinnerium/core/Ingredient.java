package dinnerium.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Ingredient {

    Quantity quantity;
    String name;

    public Ingredient(String name, Quantity quantity) {

        checkIngredientName(name);
        this.quantity = quantity;

    }

    private void checkIngredientName(String name) throws IllegalArgumentException{
        Pattern p = Pattern.compile("^[ A-Za-z]+$");
        Matcher m = p.matcher(name);

        if(!name.isEmpty() && m.matches()) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("ERROR - Invalid ingredient name!");
        }
    }
}
