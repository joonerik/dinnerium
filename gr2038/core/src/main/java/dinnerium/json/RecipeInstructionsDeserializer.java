package dinnerium.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import dinnerium.core.RecipeInstructions;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

class RecipeInstructionsDeserializer extends JsonDeserializer<RecipeInstructions> {

    @Override
    public RecipeInstructions deserialize(JsonParser parser, DeserializationContext ctxt)
                throws IOException {
        TreeNode treeNode = parser.getCodec().readTree(parser);
        return deserialize((JsonNode) treeNode);
    }

    public RecipeInstructions deserialize(JsonNode jsonNode) {
        if (jsonNode instanceof ArrayNode) {
            Collection<String> instructions = new ArrayList<>();
            for (JsonNode elementNode : (jsonNode)) {
                String instruction = elementNode.asText();
                instructions.add(instruction);
            }
            return new RecipeInstructions(instructions);
        }
        return null;
    }
}
