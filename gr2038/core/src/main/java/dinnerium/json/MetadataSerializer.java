package dinnerium.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import dinnerium.core.Metadata;
import java.io.IOException;

class MetadataSerializer extends JsonSerializer<Metadata> {

    /**
     * Converts the Metadata object to a string in json format.
     *
     * @param metadata the Metadata to be serialized to a json string.
     * @param jsonGen the jsonGen used to generate the json objects.
     * @param serializerProvider the serializerProvider.
     * @throws IOException if it is not possible to format the Metadata to a json string.
     */
    @Override
    public void serialize(Metadata metadata,
                          JsonGenerator jsonGen,
                          SerializerProvider serializerProvider) throws IOException {
        jsonGen.writeStartObject();
        jsonGen.writeStringField("author", metadata.getAuthor());
        jsonGen.writeNumberField("portion", metadata.getPortion());
        jsonGen.writeStringField("recipeName", metadata.getRecipeName());
        jsonGen.writeStringField("recipeDescription", metadata.getRecipeDescription());
        jsonGen.writeNumberField("minutes", metadata.getMinutes());
        jsonGen.writeEndObject();
    }
}
