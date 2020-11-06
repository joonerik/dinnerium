package dinnerium.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.DoubleNode;
import com.fasterxml.jackson.databind.node.IntNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.databind.node.TextNode;
import dinnerium.core.RecipeMetadata;
import java.io.IOException;

class RecipeMetadataDeserializer extends JsonDeserializer<RecipeMetadata> {

    @Override
    public RecipeMetadata deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
        TreeNode treeNode = parser.getCodec().readTree(parser);
        return deserialize((JsonNode) treeNode);
    }

    /**
     * Converts the RecipeMetadata from string in json file to a RecipeMetadata object.
     *
     * @param jsonNode the json node to be deserialized to a RecipeMetadata object.
     * @return RecipeMetadata
     *         RecipeMetadata that is read from file, null if the jsonNode is not on the correct format.
     *
     */

    public RecipeMetadata deserialize(JsonNode jsonNode) {
        if (jsonNode instanceof ObjectNode) {
            ObjectNode objectNode = (ObjectNode) jsonNode;
            String author;
            double portion;
            String recipeName;
            String recipeDescription;
            int minutes;

            JsonNode authorNode = objectNode.get("author");
            if (authorNode instanceof TextNode) {
                author = authorNode.asText();
            } else {
                return null;
            }
            JsonNode portionNode = objectNode.get("portion");
            if (portionNode instanceof DoubleNode || portionNode instanceof IntNode) {
                portion = portionNode.asDouble();
            } else {
                return null;
            }
            JsonNode recipeNameNode = objectNode.get("recipeName");
            if (recipeNameNode instanceof TextNode) {
                recipeName = recipeNameNode.asText();
            } else {
                return null;
            }
            JsonNode recipeDescriptionNode = objectNode.get("recipeDescription");
            if (recipeDescriptionNode instanceof TextNode) {
                recipeDescription = recipeDescriptionNode.asText();
            } else {
                return null;
            }

            JsonNode minutesNode = objectNode.get("minutes");
            if (minutesNode instanceof IntNode || minutesNode instanceof DoubleNode) {
                minutes = minutesNode.asInt();
            } else {
                return null;
            }
            return new RecipeMetadata(author, portion, recipeName, recipeDescription, minutes);
        }
        return null;
    }
}
