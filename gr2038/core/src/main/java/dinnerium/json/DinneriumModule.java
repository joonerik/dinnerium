package dinnerium.json;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import dinnerium.core.Container;
import dinnerium.core.Ingredient;
import dinnerium.core.Metadata;
import dinnerium.core.Quantity;
import dinnerium.core.Recipe;
import dinnerium.core.RecipeInstructions;
import dinnerium.core.User;

@SuppressWarnings("serial")
class DinneriumModule extends SimpleModule {

    private static final String NAME = "DinneriumModule";

    /** Initializes our application/module with the necessary serializers and deserializers */
    public DinneriumModule() {
        super(NAME, Version.unknownVersion());
        addSerializer(Ingredient.class, new IngredientSerializer());
        addSerializer(Quantity.class, new QuantitySerializer());
        addSerializer(Container.class, new ContainerSerializer());
        addSerializer(Metadata.class, new MetadataSerializer());
        addSerializer(RecipeInstructions.class, new RecipeInstructionsSerializer());
        addSerializer(User.class, new UserSerializer());
        addSerializer(Recipe.class, new RecipeSerializer());
        addDeserializer(Ingredient.class, new IngredientDeserializer());
        addDeserializer(Quantity.class, new QuantityDeserializer());
        addDeserializer(Container.class, new ContainerDeserializer());
        addDeserializer(Metadata.class, new MetadataDeserializer());
        addDeserializer(RecipeInstructions.class, new RecipeInstructionsDeserializer());
        addDeserializer(User.class, new UserDeserializer());
        addDeserializer(Recipe.class, new RecipeDeserializer());
    }
}
