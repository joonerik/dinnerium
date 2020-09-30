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
            boolean notFound = true;
            for (JsonNode element : objectNode.get("container")) {
                if (element.get("ingredientContainer") != null) {
                    notFound = false;
                }
            }
            if (notFound) {
                IngredientContainer ingredients = new IngredientContainer();
                JsonNode ingredientsNode = objectNode.get("container");
                if (ingredientsNode instanceof ArrayNode) {
                    for (JsonNode elementNode : (ingredientsNode)) {
                        Ingredient ingredient = ingredientDeserializer.deserialize(elementNode);
                        if (ingredient != null) {
                            ingredients.addItem(ingredient);
                        }
                    }
                    return ingredients;
                } else {
                    return null;
                }
            } else {
                RecipeContainer recipes = new RecipeContainer();
                System.out.println("nu går vi inn i recipesCotnainer serializeringen");
                JsonNode recipesNode = objectNode.get("container");
                if (recipesNode instanceof ArrayNode) {
                    System.out.println("deserializerer container");
                    for (JsonNode elementNode : recipesNode) {
                        Recipe r = recipeDeserializer.deserialize(elementNode);
                        System.out.println("lager recipe: " + r);
                        if (r != null) {
                            System.out.println("adder recipe inn i container");
                            recipes.addItem(r);
                        }
                    }
                }
                return recipes;
            }
        }
        return null;
    }
}



