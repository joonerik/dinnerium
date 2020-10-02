package dinnerium.core;

import java.util.Collection;

public class RecipeContainer extends Container<Recipe> {

    public RecipeContainer() {
        super();
    }

    public RecipeContainer(Collection<Recipe> container) {
        super(container);
    }
}
