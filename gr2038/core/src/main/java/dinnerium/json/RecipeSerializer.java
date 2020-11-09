package dinnerium.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import dinnerium.core.Recipe;
import java.io.IOException;

class RecipeSerializer extends JsonSerializer<Recipe> {

    /**
     * Converts the recipe object to a string in json format.
     *
     * @param recipe the recipe to be serialized to a json string.
     * @param jsonGen the jsonGen used to generate the json objects.
     * @param serializerProvider the serializerProvider.
     * @throws IOException if it is not possible to format the recipe to a json string.
     */
    @Override
    public void serialize(Recipe recipe,
                          JsonGenerator jsonGen,
                          SerializerProvider serializerProvider) throws IOException {
        jsonGen.writeStartObject();
        jsonGen.writeObjectField("ingredientContainer", recipe.getIngredientContainer());
        jsonGen.writeObjectField("recipeInstructions", recipe.getRecipeInstructions());
        jsonGen.writeObjectField("metadata", recipe.getRecipeMetadata());
        jsonGen.writeEndObject();
    }
}
