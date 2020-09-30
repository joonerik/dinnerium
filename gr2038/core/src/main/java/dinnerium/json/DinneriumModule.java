package dinnerium.json;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import dinnerium.core.Ingredient;
import dinnerium.core.IngredientContainer;
import dinnerium.core.Metadata;
import dinnerium.core.Quantity;

@SuppressWarnings("serial")
public class DinneriumModule extends SimpleModule {

    private static final String NAME = "DinneriumModule";

    /** Initializes our application/module with the necessary serializers and deserializers */
    public DinneriumModule() {
        super(NAME, Version.unknownVersion());
        addSerializer(Ingredient.class, new IngredientSerializer());
        addSerializer(Quantity.class, new QuantitySerializer());
        addSerializer(IngredientContainer.class, new IngredientContainerSerializer());
        addSerializer(Metadata.class, new MetadataSerializer());
        addDeserializer(Ingredient.class, new IngredientDeserializer());
        addDeserializer(Quantity.class, new QuantityDeserializer());
        addDeserializer(IngredientContainer.class, new IngredientContainerDeserializer());
        addDeserializer(Metadata.class, new MetadataDeserializer())

    }
}
