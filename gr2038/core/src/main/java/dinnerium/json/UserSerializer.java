package dinnerium.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import dinnerium.core.Ingredient;
import dinnerium.core.IngredientContainer;
import dinnerium.core.User;
import java.io.IOException;

public class UserSerializer extends JsonSerializer<User> {

    // converts the IngredientContainer object into a string in json format
    @Override
    public void serialize(User user,
                          JsonGenerator jsonGen,
                          SerializerProvider serializerProvider) throws IOException {

        jsonGen.writeStartObject();
        jsonGen.writeStringField("user", user.getUserName());
        jsonGen.writeStringField("ingedientContainer", user.getIngredientContainer());
        jsonGen.writeStringField("recipeContainer", user.getRecipeContainer());
        jsonGen.writeEndObject();

    }
}

