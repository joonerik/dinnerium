package dinnerium.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import dinnerium.core.Container;
import dinnerium.core.IngredientContainer;
import dinnerium.core.RecipeContainer;
import java.io.IOException;

class ContainerSerializer extends JsonSerializer<Container> {

    /**
     * Converts the IngredientContainer object into a string in json format.
     *
     * @param c the user to be serialized to a json string.
     * @param jsonGen the jsonGen used to generate the json objects.
     * @param serializerProvider the serializerProvider.
     * @throws IOException if it is not possible to format the Container to a json string.
     */
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

