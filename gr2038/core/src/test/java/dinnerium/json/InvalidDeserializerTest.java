package dinnerium.json;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;


import com.fasterxml.jackson.databind.ObjectMapper;
import dinnerium.core.Container;
import dinnerium.core.Ingredient;
import dinnerium.core.Metadata;
import dinnerium.core.Quantity;
import dinnerium.core.Recipe;
import dinnerium.core.RecipeInstructions;
import dinnerium.core.User;
import java.io.IOException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class InvalidDeserializerTest {

    private ObjectMapper mapper;

    @BeforeEach
    void setMapper() {
        mapper = new ObjectMapper();
        mapper.registerModule(new DinneriumModule());
    }

    @Test
    void testInvalidUser() {
        String userJson = "{\n" +
            "  \"ingredientContainer\" : \"string\",\n" +
            "  \"recipeContainer\" : 24,\n" +
            "  \"username\" : {\"attribute\":\"value\"}\n" +
            "}";
        User u;
        try {
            u = mapper.readValue("[\"notObject\"]", User.class);
            assertNull(u, "User should have been null here");

            u = mapper.readValue(userJson, User.class);
            assertNull(u, "User should have been null here");
            userJson = userJson.replace("24", "{\n" +
                "    \"recipes\" : [ ]\n" +
                "  }");

            u = mapper.readValue(userJson, User.class);
            assertNull(u, "User should have been null here");
            userJson = userJson.replace("\"string\"", "{\n" +
                "    \"ingredients\" : [ ]\n" +
                "  }");

            u = mapper.readValue(userJson, User.class);
            assertNull(u, "User should have been null here");
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void testInvalidContainer() {
        Container c;
        try {
            c = mapper.readValue("[\"list\", \"notObject\"]", Container.class);
            assertNull(c, "Container should have been null here");
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void testInvalidRecipeInstructions() {
        try {
            RecipeInstructions ri = mapper.readValue("{\"attribute\":\"value\"}", RecipeInstructions.class);
            assertNull(ri, "RecipeInstructions should have been null here");
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void testInvalidRecipe() {
        String recipeJson = "{\n" +
            "      \"ingredientContainer\" : 99,\n" +
            "      \"recipeInstructions\" : 54,\n" +
            "      \"metadata\" : \"not an object\" " +
            "    }";
        Recipe r;
        try {
            r = mapper.readValue(recipeJson, Recipe.class);
            assertNull(r, "The recipe should have been null");
            recipeJson = recipeJson.replace("99", "{\n" +
                "    \"ingredients\": [\n" +
                "      {\n" +
                "        \"quantity\": {\n" +
                "          \"unit\": \"stk\",\n" +
                "          \"amount\": 2.0\n" +
                "        },\n" +
                "        \"name\": \"bolle\"\n" +
                "      }]}");

            r = mapper.readValue(recipeJson, Recipe.class);
            assertNull(r, "The recipe should have been null");
            recipeJson = recipeJson.replace("54", "[\"mixIt\"]");

            r = mapper.readValue(recipeJson, Recipe.class);
            assertNull(r, "The recipe should have been null");

            r = mapper.readValue("\"attribute\":\"value\"", Recipe.class);
            assertNull(r, "The recipe should have been null");

        } catch(IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void testInvalidQuantity() {
        Quantity q;
        String quantityJson = "{\n" +
            "        \"unit\" : 45,\n" +
            "        \"amount\" : \"to\"\n" +
            "      }";
        try {

            q = mapper.readValue(quantityJson, Quantity.class);
            assertNull(q, "Quantity should have been null");
            quantityJson = quantityJson.replace("\"to\"", "99");

            q = mapper.readValue(quantityJson, Quantity.class);
            assertNull(q, "Quantity should have been null");

            q = mapper.readValue("\"attribute\":\"NotObject\"", Quantity.class);
            assertNull(q, "Quantity should have been null");
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testInvalidMetadata() {
        String metadataJson = "      {\n" +
            "        \"author\" : 55,\n" +
            "        \"portion\" : \"sekstiseks\",\n" +
            "        \"recipeName\" : 99,\n" +
            "        \"recipeDescription\" : 22,\n" +
            "        \"minutes\" : \"tretti\"\n" +
            "      }";
        Metadata md;
        try {
            md = mapper.readValue(metadataJson, Metadata.class);
            assertNull(md, "Metadata should have been null");

            metadataJson = metadataJson.replace("55", "\"user\"");
            md = mapper.readValue(metadataJson, Metadata.class);
            assertNull(md, "Metadata should have been null");

            metadataJson = metadataJson.replace("\"sekstiseks\"", "4.0");
            md = mapper.readValue(metadataJson, Metadata.class);
            assertNull(md, "Metadata should have been null");

            metadataJson = metadataJson.replace("99", "\"pastaBrokkoli\"");
            md = mapper.readValue(metadataJson, Metadata.class);
            assertNull(md, "Metadata should have been null");

            metadataJson = metadataJson.replace("22", "\"Description\"");
            md = mapper.readValue(metadataJson, Metadata.class);
            assertNull(md, "Metadata should have been null");

            md = mapper.readValue("\"attribute\":\"NotObject\"", Metadata.class);
            assertNull(md, "Metadata should have been null");
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testInvalidIngredient() {
        String ingredientJson = "{\n" +
            "      \"quantity\" : 56,\n" +
            "      \"name\" : 233\n" +
            "    }";
        Ingredient i;
        try {
            i = mapper.readValue(ingredientJson, Ingredient.class);
            assertNull(i, "The ingredient should have been null");
            ingredientJson = ingredientJson.replace("56", "{\"unit\" : \"stk\",\"amount\" : 2.0}");

            i = mapper.readValue(ingredientJson, Ingredient.class);
            assertNull(i, "The ingredient should have been null");

            i = mapper.readValue("\"attribute\":\"NotObject\"", Ingredient.class);
            assertNull(i, "The ingredient should have been null");

        } catch (IOException e) {
            fail(e.getMessage());
        }
    }
}
