package dinnerium.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import dinnerium.core.Metadata;
import java.io.IOException;

class MetadataSerializer extends JsonSerializer<Metadata> {


    @Override
    public void serialize(Metadata metadata,
                          JsonGenerator jsonGen,
                          SerializerProvider serializerProvider) throws IOException {
        jsonGen.writeStartObject();
        jsonGen.writeStringField("author", metadata.getAuthor());
        jsonGen.writeNumberField("portion", metadata.getPortion());
        jsonGen.writeStringField("image", metadata.getImage());
        jsonGen.writeStringField("recipeName", metadata.getRecipeName());
        jsonGen.writeStringField("recipeDescription", metadata.getRecipeDescription());
        jsonGen.writeNumberField("minutes", metadata.getMinutes());
        jsonGen.writeEndObject();
    }
}
