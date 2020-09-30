package dinnerium.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import dinnerium.core.*;

import java.io.IOException;

public class UserDeserializer extends JsonDeserializer<User> {

    private IngredientDeserializer ingredientDeserializer = new IngredientDeserializer();
    private RecipeDeserializer recipeDeserializer = new RecipeDeserializer();


    @Override
    public User deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
        TreeNode treeNode = parser.getCodec().readTree(parser);
        return deserialize((JsonNode) treeNode);
    }

    // converts the Ingredient from string in json file to an object
    // checks if the nodes are of correct type
    // finally we have converted the strings from json into actual objects
    public User deserialize(JsonNode jsonNode) throws IOException {
        if (jsonNode instanceof ObjectNode) {
            ObjectNode objectNode = (ObjectNode) jsonNode;
            RecipeContainer recipeContainer = new RecipeContainer();
            IngredientContainer ingredientContainer = new IngredientContainer();
            String username;


            JsonNode recipeContainerNode = objectNode.get("recipeContainer");
            if (recipeContainerNode instanceof ObjectNode) {
                for (JsonNode elementNode : (recipeContainerNode)) {
                    Recipe recipe = recipeDeserializer.deserialize(elementNode);
                    if (recipe != null) {
                        recipeContainer.addItem(recipe);
                    }
                }
            } else {
                return null;
            }

            JsonNode ingredientContainerNode = objectNode.get("ingredientContainer");
            if (ingredientContainerNode instanceof ObjectNode) {
                for (JsonNode elementNode : (ingredientContainerNode)) {
                    Ingredient ingredient = ingredientDeserializer.deserialize(elementNode);
                    if (ingredient != null) {
                        ingredientContainer.addItem(ingredient);
                    }
                }
            } else {
                return null;
            }

            JsonNode usernameNode = objectNode.get("username");
            if (usernameNode instanceof TextNode) {
                username = usernameNode.asText();
            } else {
                return null;
            }
            return new User(ingredientContainer, recipeContainer, username);
        }
        return null;
    }
}
