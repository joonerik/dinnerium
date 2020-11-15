package dinnerium.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import dinnerium.core.Ingredient;
import java.io.IOException;

/**
 * Class for serializing an Ingredient object to json format.
 */
class IngredientSerializer extends JsonSerializer<Ingredient> {


    /**
     * Converts the Ingredient object to a string in json format.
     *
     * @param ingredient the Ingredient to be serialized to a json string.
     * @param jsonGen the jsonGen used to generate the json objects.
     * @param serializerProvider the serializerProvider.
     * @throws IOException if it is not possible to format the Ingredient to a json string.
     */
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
