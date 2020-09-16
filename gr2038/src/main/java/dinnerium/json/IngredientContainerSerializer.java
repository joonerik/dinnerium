package dinnerium.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import java.io.IOException;
import dinnerium.core.Ingredient;
import dinnerium.core.IngredientContainer;

public class IngredientContainerSerializer extends JsonSerializer<IngredientContainer> {

    /*
     * format: { "text": "...", "checked": false }
     */

    @Override
    public void serialize(IngredientContainer ic, JsonGenerator jsonGen, SerializerProvider serializerProvider)
            throws IOException {
        jsonGen.writeStartObject();
        jsonGen.writeArrayFieldStart("ingredients");
        for (Ingredient ingredient: ic.getIngredients()) {
            jsonGen.writeObject(ingredient);
        }
        jsonGen.writeEndArray();
        jsonGen.writeEndObject();
    }
}

