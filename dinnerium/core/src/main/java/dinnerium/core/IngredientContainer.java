package dinnerium.core;

import java.util.Collection;

/** An implementation of Container, containing Ingredient objects. */
public class IngredientContainer extends Container<Ingredient> {


    public IngredientContainer() {
    }

    public IngredientContainer(Collection<Ingredient> container) {
        super(container);
    }

    public void addIngredient(Ingredient ingredient) {
        super.addItem(ingredient);
    }


}