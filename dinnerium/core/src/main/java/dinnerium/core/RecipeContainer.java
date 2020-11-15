package dinnerium.core;

import java.util.Collection;

/** An implementation of Container, containing Recipe objects. */
public class RecipeContainer extends Container<Recipe> {

    public RecipeContainer() {
        super();
    }

    public RecipeContainer(Collection<Recipe> container) {
        super(container);
    }

    public void addRecipe(Recipe recipe) {
        super.addItem(recipe);
    }

}
