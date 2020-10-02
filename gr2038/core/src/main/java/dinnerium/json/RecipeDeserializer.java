package dinnerium.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import dinnerium.core.IngredientContainer;
import dinnerium.core.Metadata;
import dinnerium.core.Recipe;
import dinnerium.core.RecipeInstructions;
import java.io.IOException;

public class RecipeDeserializer extends JsonDeserializer<Recipe> {

    @Override
    public Recipe deserialize(JsonParser parser, DeserializationContext ctxt)
            throws IOException {
        TreeNode treeNode = parser.getCodec().readTree(parser);
        return deserialize((JsonNode) treeNode);
    }

    // converts the Ingredient from string in json file to an object
    // checks if the nodes are of correct type
    // finally we have converted the strings from json into actual objects
    /**
     * Deserializer for Ingredient
     *
     * @param jsonNode
     *
     */
    public Recipe deserialize(JsonNode jsonNode) throws IOException {
        if (jsonNode instanceof ObjectNode) {
            ObjectNode objectNode = (ObjectNode) jsonNode;
            IngredientContainer ic;
            RecipeInstructions ri;
            Metadata md;

            JsonNode containerNode = objectNode.get("ingredientContainer");
            ContainerDeserializer containerDeserializer = new ContainerDeserializer();
            if (containerNode instanceof ObjectNode) {
                ic = (IngredientContainer) containerDeserializer.deserialize(containerNode);
            } else {
                return null;
            }

            JsonNode recipeInstructionsNode = objectNode.get("recipeInstructions");
            RecipeInstructionsDeserializer rid = new RecipeInstructionsDeserializer();
            if (recipeInstructionsNode instanceof ArrayNode) {
                ri = rid.deserialize(recipeInstructionsNode);
            }  else {
                return null;
            }

            JsonNode metadataContainerNode = objectNode.get("metadata");
            MetadataDeserializer metadataDeserializer = new MetadataDeserializer();
            if (metadataContainerNode instanceof ObjectNode) {
                md = metadataDeserializer.deserialize(metadataContainerNode);
            } else {
                return null;
            }

            return new Recipe(ic, ri, md);

        }
        return null;
    }

}
