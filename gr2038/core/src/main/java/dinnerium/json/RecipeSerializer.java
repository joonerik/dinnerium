package dinnerium.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import dinnerium.core.Recipe;
import java.io.IOException;

public class RecipeSerializer extends JsonSerializer<Recipe> {

    @Override
    public void serialize(Recipe recipe,
                          JsonGenerator jsonGen,
                          SerializerProvider serializerProvider) throws IOException {
        jsonGen.writeStartObject();
        jsonGen.writeObjectField("ingredientContainer", recipe.getIngredientContainer());
        jsonGen.writeObjectField("recipeInstructions", recipe.getRecipeInstructions());
        jsonGen.writeObjectField("metadata", recipe.getMetadata());
        jsonGen.writeEndObject();
    }
}
