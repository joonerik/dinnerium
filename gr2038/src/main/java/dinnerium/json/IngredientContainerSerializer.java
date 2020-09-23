package dinnerium.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import dinnerium.core.Ingredient;
import dinnerium.core.IngredientContainer;
import java.io.IOException;

public class IngredientContainerSerializer extends JsonSerializer<IngredientContainer> {

    // converts the IngredientContainer object into a string in json format
    @Override
    public void serialize(IngredientContainer ic,
                          JsonGenerator jsonGen,
                          SerializerProvider serializerProvider) throws IOException {
        jsonGen.writeStartObject();
        jsonGen.writeArrayFieldStart("ingredients");
        for (Ingredient ingredient : ic.getIngredients()) {
            jsonGen.writeObject(ingredient);
        }
        jsonGen.writeEndArray();
        jsonGen.writeEndObject();
    }
}

