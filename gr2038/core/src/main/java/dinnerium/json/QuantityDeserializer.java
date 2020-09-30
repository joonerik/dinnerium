package dinnerium.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.DoubleNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import dinnerium.core.Quantity;
import java.io.IOException;

public class QuantityDeserializer extends JsonDeserializer<Quantity> {

    @Override
    public Quantity deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
        TreeNode treeNode = parser.getCodec().readTree(parser);
        return deserialize((JsonNode) treeNode);
    }

    // converts the Ingredient from string in json file to an object
    // checks if the nodes are of correct type
    // finally we have converted the strings from json into actual objects
    Quantity deserialize(JsonNode jsonNode) {
        if (jsonNode instanceof ObjectNode) {
            ObjectNode objectNode = (ObjectNode) jsonNode;
            Double amount = null;
            String unit = null;
            
            JsonNode amountNode = objectNode.get("amount");
            if (amountNode instanceof DoubleNode) {
                amount = ((amountNode).asDouble());
            } else {
                return null;
            }

            JsonNode unitNode = objectNode.get("unit");
            if (unitNode instanceof TextNode) {
                unit = ((unitNode).asText());
            } else {
                return null;
            }
            return new Quantity(amount, unit);
        }
        return null;
    }
}