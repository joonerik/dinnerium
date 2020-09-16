package dinnerium.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import java.io.IOException;
import dinnerium.core.Ingredient;
import dinnerium.core.IngredientContainer;

public class IngredientContainerDeserializer extends JsonDeserializer<IngredientContainer> {

    private IngredientDeserializer ingredientDeserializer = new IngredientDeserializer();

    @Override
    public IngredientContainer deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        TreeNode treeNode = p.getCodec().readTree(p);
        if (treeNode instanceof  ObjectNode) {
            ObjectNode objectNode = (ObjectNode) treeNode;
            IngredientContainer ingredients = new IngredientContainer();
            JsonNode ingredientsNode = objectNode.get("ingredients");
            if (ingredientsNode instanceof ArrayNode) {
                for (JsonNode elementNode : ((ArrayNode) ingredientsNode)) {
                    Ingredient ingredient = ingredientDeserializer.deserialize(elementNode);
                    if (ingredient != null) {
                        ingredients.addIngredient(ingredient);
                    }
                }
            }
            return ingredients;
        }
        return null;
    }
}



