package dinnerium.json;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import dinnerium.core.*;

@SuppressWarnings("serial")
public class DinneriumModule extends SimpleModule {

    private static final String NAME = "DinneriumModule";

    /** Initializes our application/module with the necessary serializers and deserializers */
    public DinneriumModule() {
        super(NAME, Version.unknownVersion());
        addSerializer(Ingredient.class, new IngredientSerializer());
        addSerializer(Quantity.class, new QuantitySerializer());
        addSerializer(Container.class, new ContainerSerializer());
        addSerializer(Metadata.class, new MetadataSerializer());
        addDeserializer(Ingredient.class, new IngredientDeserializer());
        addDeserializer(Quantity.class, new QuantityDeserializer());
        addDeserializer(Container.class, new ContainerDeserializer());
        addDeserializer(Metadata.class, new MetadataDeserializer());
    }
}
