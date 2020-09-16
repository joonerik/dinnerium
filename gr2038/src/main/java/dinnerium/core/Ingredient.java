package dinnerium.core;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import com.fasterxml.jackson.databind.ObjectMapper;


public class Ingredient {

    Quantity quantity;
    String name;

    public Ingredient(Quantity quantity, String name) {

        this.quantity = quantity;

        Pattern p = Pattern.compile("^[ A-Za-z]+$");
        Matcher m = p.matcher(name);

        if(!name.isEmpty() && m.matches()) {
            this.name = name;
        } else {
            throw new IllegalArgumentException("ERROR - Invalid ingredient name!");
        }
    }

    public Quantity getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }
}
