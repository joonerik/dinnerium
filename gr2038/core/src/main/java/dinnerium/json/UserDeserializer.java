package dinnerium.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.DoubleNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import dinnerium.core.User;
import dinnerium.core.Ingredient;
import dinnerium.core.IngredientContainer;
import java.io.IOException;

public class UserDeserializer extends JsonDeserializer<User> {

    private IngredientDeserializer ingredientDeserializer = new IngredientDeserializer();
    private RecipeDeserializer recipeDeserializer = new RecipeDeserializer();


    @Override
    public UserDeserializer deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
        TreeNode treeNode = parser.getCodec().readTree(parser);
        return deserialize((JsonNode) treeNode);
    }

    // converts the Ingredient from string in json file to an object
    // checks if the nodes are of correct type
    // finally we have converted the strings from json into actual objects
    UserDeserializer deserialize(JsonNode jsonNode) {
        if (jsonNode instanceof ObjectNode) {
            ObjectNode objectNode = (ObjectNode) jsonNode;
            RecipeContainer recipeContainer = null;
            IngredientContainer ingredientContainer = null;

            JsonNode recipeContainerNode = objectNode.get("recipeContainer");
            if (recipeContainerNode instanceof ObjectNode) {
                for (JsonNode elementNode : (recipeContainerNode)) {
                    Recipe recipe = recipeDeserializer.deserialize(elementNode);
                    if (recipe != null) {
                        recipeContainer.addItem(recipe);
                    }
                }
            }

            JsonNode ingredientContainerNode = objectNode.get("ingredientContainer");
            if (ingredientContainerNode instanceof ObjectNode) {
                for (JsonNode elementNode : (ingredientContainerNode)) {
                    Ingredient ingredient = ingredientDeserializer.deserialize(elementNode);
                    if (ingredient != null) {
                        ingredientContainer.addItem(ingredient);
                    }
                }
            }
            return recipeContainer, ingredientContainer;
        }
        return null;
    }
}
