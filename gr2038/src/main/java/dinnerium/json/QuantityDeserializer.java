package dinnerium.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import com.fasterxml.jackson.databind.node.DoubleNode;
import java.io.IOException;
import dinnerium.core.Quantity;

public class QuantityDeserializer extends JsonDeserializer<Quantity> {

    @Override
    public Quantity deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
        TreeNode treeNode = parser.getCodec().readTree(parser);
        return deserialize((JsonNode) treeNode);
    }

    Quantity deserialize(JsonNode jsonNode) {
        if (jsonNode instanceof ObjectNode) {
            ObjectNode objectNode = (ObjectNode) jsonNode;
            Quantity quantity = new Quantity();
            // remove empty object. Save the variables underneath and construct the object afterwards

            JsonNode amountNode = objectNode.get("amount");
            if (amountNode instanceof DoubleNode) {
                quantity.setAmount((amountNode).asDouble());
            }

            JsonNode unitNode = objectNode.get("unit");
            if (unitNode instanceof TextNode) {
                quantity.setUnit((unitNode).asText());
            }
            return quantity;
        }
        return null;
    }
}
