package dinnerium.core;

import java.util.ArrayList;
import java.util.Collection;

public class IngredientContainer {

    private Collection<Ingredient> ingredients;

    public IngredientContainer(){
        this.ingredients = new ArrayList<>();
    }

    public IngredientContainer(Collection<Ingredient> ingredients) {
        this.setIngredients(ingredients);
    }

    public void setIngredients(Collection<Ingredient> ingredients) {
        if(ingredients.isEmpty())
            throw new IllegalArgumentException("ERROR: You cannot make an IngredientContainer based on an invalid collection");
        this.ingredients = ingredients;
    }

    public void addIngredient(Ingredient ingredient) {
        if(ingredient == null)
            throw new IllegalArgumentException("Ingredient null! Cannot add to IngredientContainer");
        this.ingredients.add(ingredient);
    }

    public Collection<Ingredient> getIngredients() {
        return ingredients;
    }

    public int getIngredientAmount() {
        return ingredients.size();
    }
}