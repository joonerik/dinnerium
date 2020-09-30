package dinnerium.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import dinnerium.core.*;

import java.io.IOException;

public class ContainerDeserializer extends JsonDeserializer<Container> {

    private IngredientDeserializer ingredientDeserializer = new IngredientDeserializer();
    private RecipeDeserializer recipeDeserializer = new RecipeDeserializer();

    @Override
    public Container deserialize(JsonParser parser, DeserializationContext ctxt)
            throws IOException {
        TreeNode treeNode = parser.getCodec().readTree(parser);
        return deserialize((JsonNode) treeNode);
    }

    // converts the IngredientContainer from string in json file to an object
    // We check if the nodes are of the correct type
    // finally we have ingredient objects which are added into the IngredientContainer list
    public Container deserialize(JsonNode jsonNode)
            throws IOException {

        if (jsonNode instanceof  ObjectNode) {
            ObjectNode objectNode = (ObjectNode) jsonNode;

            if (objectNode.get("ingredients") != null) {
                IngredientContainer ingredients = new IngredientContainer();
                JsonNode ingredientsNode = objectNode.get("ingredients");
                if (ingredientsNode instanceof ArrayNode) {
                    for (JsonNode elementNode : (ingredientsNode)) {
                        Ingredient ingredient = ingredientDeserializer.deserialize(elementNode);
                        if (ingredient != null) {
                            ingredients.addItem(ingredient);
                        }
                    }
                return ingredients;
                }
            } else {
                RecipeContainer recipes = new RecipeContainer();
                JsonNode recipesNode = objectNode.get("recipes");
                if (recipesNode instanceof ArrayNode) {
                    for (JsonNode elementNode : (recipesNode)) {
                        Recipe recipe = recipeDeserializer.deserialize(elementNode);
                        if (recipe != null) {
                            recipes.addItem(recipe);
                        }
                    }
                }
                return recipes;
            }
        }
        return null;
    }
}



