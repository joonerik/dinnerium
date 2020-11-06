package dinnerium.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import dinnerium.core.RecipeMetadata;
import java.io.IOException;

class RecipeMetadataSerializer extends JsonSerializer<RecipeMetadata> {

    /**
     * Converts the RecipeMetadata object to a string in json format.
     *
     * @param recipeMetadata the RecipeMetadata to be serialized to a json string.
     * @param jsonGen the jsonGen used to generate the json objects.
     * @param serializerProvider the serializerProvider.
     * @throws IOException if it is not possible to format the RecipeMetadata to a json string.
     */
    @Override
    public void serialize(RecipeMetadata recipeMetadata,
                          JsonGenerator jsonGen,
                          SerializerProvider serializerProvider) throws IOException {
        jsonGen.writeStartObject();
        jsonGen.writeStringField("author", recipeMetadata.getAuthor());
        jsonGen.writeNumberField("portion", recipeMetadata.getPortion());
        jsonGen.writeStringField("recipeName", recipeMetadata.getRecipeName());
        jsonGen.writeStringField("recipeDescription", recipeMetadata.getRecipeDescription());
        jsonGen.writeNumberField("minutes", recipeMetadata.getMinutes());
        jsonGen.writeEndObject();
    }
}
