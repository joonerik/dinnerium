package dinnerium.core;

import java.util.Collection;

/** Contains ingredients. */
public class IngredientContainer extends Container<Ingredient> {


    public IngredientContainer() {
    }

    public IngredientContainer(Collection<Ingredient> container) {
        super(container);
    }


}