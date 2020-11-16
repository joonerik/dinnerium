package dinnerium.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import dinnerium.core.Container;
import dinnerium.core.Ingredient;
import dinnerium.core.IngredientContainer;
import dinnerium.core.Recipe;
import dinnerium.core.RecipeContainer;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Class for deserializing a Container object of either Recipes or Ingredients from a json format
 *      to a Container object.
 */
class ContainerDeserializer extends JsonDeserializer<Container> {

    private final IngredientDeserializer ingredientDeserializer = new IngredientDeserializer();
    private final RecipeDeserializer recipeDeserializer = new RecipeDeserializer();

    @Override
    public Container deserialize(JsonParser parser, DeserializationContext ctxt)
            throws IOException {
        TreeNode treeNode = parser.getCodec().readTree(parser);
        return deserialize((JsonNode) treeNode);
    }

    /**
     * Converts the IngredientContainer from string in json file to an object
     * We check if the nodes are of the correct type
     * finally we have ingredient objects which are added into the IngredientContainer list.
     *
     * @param jsonNode the json node to be deserialized to a Container object.
     * @return The Container
     *          that is read from file, null if the jsonNode is not on the correct format.
     * @throws IOException if it is not possible to use the deserializers.
     */
    public Container deserialize(JsonNode jsonNode)
            throws IOException {

        if (jsonNode instanceof  ObjectNode) {
            ObjectNode objectNode = (ObjectNode) jsonNode;
            if (objectNode.get("ingredients") != null) {
                Collection<Ingredient> ingredients = new ArrayList<>();
                JsonNode ingredientsNode = objectNode.get("ingredients");
                if (ingredientsNode instanceof ArrayNode) {
                    for (JsonNode elementNode : (ingredientsNode)) {
                        Ingredient ingredient = ingredientDeserializer.deserialize(elementNode);
                        if (ingredient != null) {
                            ingredients.add(ingredient);
                        }
                    }
                    return new IngredientContainer(ingredients);
                } else {
                    return null;
                }
            } else {
                Collection<Recipe> recipes = new ArrayList<>();
                JsonNode recipesNode = objectNode.get("recipes");
                if (recipesNode instanceof ArrayNode) {
                    for (JsonNode elementNode : recipesNode) {
                        Recipe r = recipeDeserializer.deserialize(elementNode);
                        if (r != null) {
                            recipes.add(r);
                        }
                    }
                }
                return new RecipeContainer(recipes);
            }
        }
        return null;
    }
}



