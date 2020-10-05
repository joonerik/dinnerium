package dinnerium.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import dinnerium.core.RecipeInstructions;
import java.io.IOException;

class RecipeInstructionsSerializer extends JsonSerializer<RecipeInstructions> {


    // converts the Quantity object to a string in json format
    @Override
    public void serialize(
            RecipeInstructions recipeInstructions,
            JsonGenerator jsonGen,
            SerializerProvider serializerProvider) throws IOException {

        jsonGen.writeStartObject();
        for (String instruction : recipeInstructions) {
            jsonGen.writeStringField("recipeInstructions", instruction);
        }
        jsonGen.writeEndObject();
    }
}
