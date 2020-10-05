package dinnerium.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import dinnerium.core.Container;
import dinnerium.core.IngredientContainer;
import dinnerium.core.RecipeContainer;
import java.io.IOException;

class ContainerSerializer extends JsonSerializer<Container> {

    // converts the IngredientContainer object into a string in json format
    @Override
    public void serialize(Container c,
                          JsonGenerator jsonGen,
                          SerializerProvider serializerProvider) throws IOException {

        jsonGen.writeStartObject();
        
        if (c.getClass().equals(IngredientContainer.class)) {
            jsonGen.writeArrayFieldStart("ingredients");
            for (Object ingredient : c.getContainer()) {
                jsonGen.writeObject(ingredient);
            }
        }

        if (c.getClass().equals(RecipeContainer.class)) {
            jsonGen.writeArrayFieldStart("recipes");
            for (Object recipe : c.getContainer()) {
                jsonGen.writeObject(recipe);
            }
        }

        jsonGen.writeEndArray();
        jsonGen.writeEndObject();
    }
}

