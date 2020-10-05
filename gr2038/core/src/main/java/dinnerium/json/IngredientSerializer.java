package dinnerium.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import dinnerium.core.Ingredient;
import java.io.IOException;

class IngredientSerializer extends JsonSerializer<Ingredient> {

    // converts the Ingredient object to a string in json format
    @Override
    public void serialize(Ingredient ingredient,
                          JsonGenerator jsonGen,
                          SerializerProvider serializerProvider)
            throws IOException {
        jsonGen.writeStartObject();
        jsonGen.writeObjectField("quantity", ingredient.getQuantity());
        jsonGen.writeStringField("name", ingredient.getName());
        jsonGen.writeEndObject();
    }
}
