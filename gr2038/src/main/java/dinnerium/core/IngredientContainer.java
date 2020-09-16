package dinnerium.core;

import java.util.Collection;

public class IngredientContainer {

    Collection<Ingredient> ingredients;

    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }

    public Collection<Ingredient> getIngredients() {
        return ingredients;
    }

    public int getIngredientAmount() {
        return ingredients.size();
    }
}