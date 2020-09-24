package dinnerium.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import dinnerium.core.Ingredient;
import java.io.IOException;

public class IngredientSerializer extends JsonSerializer<Ingredient> {

    // converts the Ingredient object to a string in json format
    @Override
    public void serialize(Ingredient ingredient, JsonGenerator jsonGen, SerializerProvider serializerProvider)
            throws IOException {
        jsonGen.writeStartObject();
        /*jsonGen.writeStringField("text", item.getText());
        jsonGen.writeBooleanField("checked", item.isChecked());*/
        jsonGen.writeObjectField("quantity", ingredient.getQuantity());
        jsonGen.writeStringField("name", ingredient.getName());
        jsonGen.writeEndObject();
    }
}
