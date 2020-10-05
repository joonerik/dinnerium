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
import dinnerium.core.Metadata;
import java.io.IOException;

class MetadataDeserializer extends JsonDeserializer<Metadata> {

    @Override
    public Metadata deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
        TreeNode treeNode = parser.getCodec().readTree(parser);
        return deserialize((JsonNode) treeNode);
    }

    public Metadata deserialize(JsonNode jsonNode) {
        if (jsonNode instanceof ObjectNode) {
            ObjectNode objectNode = (ObjectNode) jsonNode;
            String author;
            double portion;
            String image;
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
            if (portionNode instanceof DoubleNode) {
                portion = portionNode.asDouble();
            } else {
                return null;
            }
            JsonNode imageNode = objectNode.get("image");
            if (imageNode instanceof TextNode) {
                image = imageNode.asText();
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
            if (minutesNode instanceof IntNode) {
                minutes = minutesNode.asInt();
            } else {
                return null;
            }
            return new Metadata(author, portion, image, recipeName, recipeDescription, minutes);
        }
        return null;
    }
}
