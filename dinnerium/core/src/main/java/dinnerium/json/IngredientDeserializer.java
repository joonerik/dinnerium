package dinnerium.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import dinnerium.core.Ingredient;
import dinnerium.core.Quantity;
import java.io.IOException;

/**
 * Class for deserializing an Ingredient object from a json format to an Ingredient Object.
 */
class IngredientDeserializer extends JsonDeserializer<Ingredient> {

    @Override
    public Ingredient deserialize(JsonParser parser, DeserializationContext ctxt)
            throws IOException {
        TreeNode treeNode = parser.getCodec().readTree(parser);
        return deserialize((JsonNode) treeNode);
    }

    /**
     * Converts the Ingredient from string in json file to a Ingredient object.
     *
     * @param jsonNode node
     * @return Ingredient Ingredient object
     *
     */
    public Ingredient deserialize(JsonNode jsonNode) {
        if (jsonNode instanceof ObjectNode) {
            ObjectNode objectNode = (ObjectNode) jsonNode;
            Quantity quantity;
            String name;

            JsonNode quantityNode = objectNode.get("quantity");
            QuantityDeserializer qd = new QuantityDeserializer();
            if (quantityNode instanceof ObjectNode) {
                quantity = qd.deserialize(quantityNode);
            } else {
                return null;
            }

            JsonNode nameNode = objectNode.get("name");
            if (nameNode instanceof TextNode) {
                name = nameNode.asText();
            } else {
                return null;
            }
            return new Ingredient(quantity, name);
        }
        return null;
    }
}
