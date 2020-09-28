package dinnerium.core;

import java.lang.Iterable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/** Contains ingredients. */
public class IngredientContainer extends Container<Ingredient> {


    public IngredientContainer() {
    }

    public IngredientContainer(Collection<Ingredient> container) {
        super(container);
    }


}