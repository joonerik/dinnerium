package dinnerium.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import dinnerium.core.User;
import java.io.IOException;

class UserSerializer extends JsonSerializer<User> {

    /**
     * Converts the User object to a string in json format.
     *
     * @param user the user to be serialized to a json string.
     * @param jsonGen the jsonGen used to generate the json objects.
     * @param serializerProvider the serializerProvider.
     * @throws IOException if it is not possible to format the user to a json string.
     */
    @Override
    public void serialize(User user,
                          JsonGenerator jsonGen,
                          SerializerProvider serializerProvider) throws IOException {

        jsonGen.writeStartObject();
        jsonGen.writeObjectField("ingredientContainer", user.getIngredientContainer());
        jsonGen.writeObjectField("recipeContainer", user.getRecipeContainer());
        jsonGen.writeStringField("username", user.getUsername());
        jsonGen.writeEndObject();
    }
}

