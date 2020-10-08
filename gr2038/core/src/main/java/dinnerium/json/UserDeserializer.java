package dinnerium.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import dinnerium.core.IngredientContainer;
import dinnerium.core.RecipeContainer;
import dinnerium.core.User;
import java.io.IOException;

class UserDeserializer extends JsonDeserializer<User> {

    private final ContainerDeserializer cd = new ContainerDeserializer();


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
            RecipeContainer recipeContainer;
            IngredientContainer ingredientContainer;
            String username;

            JsonNode recipeContainerNode = objectNode.get("recipeContainer");
            if (recipeContainerNode instanceof ObjectNode) {
                recipeContainer = (RecipeContainer) cd.deserialize(recipeContainerNode);
            } else {
                return null;
            }


            JsonNode ingredientContainerNode = objectNode.get("ingredientContainer");
            if (ingredientContainerNode instanceof ObjectNode) {
                ingredientContainer = (IngredientContainer) cd.deserialize(ingredientContainerNode);
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
