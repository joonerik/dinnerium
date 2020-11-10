package dinnerium.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


import com.fasterxml.jackson.databind.ObjectMapper;
import dinnerium.core.Ingredient;
import dinnerium.core.IngredientContainer;
import dinnerium.core.RecipeMetadata;
import dinnerium.core.Quantity;
import dinnerium.core.Recipe;
import dinnerium.core.RecipeContainer;
import dinnerium.core.RecipeInstructions;
import dinnerium.core.User;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Iterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DinneriumModuleTest {

    private ObjectMapper mapper;
    private final HandlePersistency handlePersistency = new HandlePersistency();
    private final String expectedUserString = "" +
        "{\n" +
        "  \"ingredientContainer\" : {\n" +
        "    \"ingredients\" : [ {\n" +
        "      \"quantity\" : {\n" +
        "        \"unit\" : \"stk\",\n" +
        "        \"amount\" : 1.0\n" +
        "      },\n" +
        "      \"name\" : \"eggs\"\n" +
        "    }, {\n" +
        "      \"quantity\" : {\n" +
        "        \"unit\" : \"dl\",\n" +
        "        \"amount\" : 2.0\n" +
        "      },\n" +
        "      \"name\" : \"milk\"\n" +
        "    }, {\n" +
        "      \"quantity\" : {\n" +
        "        \"unit\" : \"gram\",\n" +
        "        \"amount\" : 3.0\n" +
        "      },\n" +
        "      \"name\" : \"sugar\"\n" +
        "    } ]\n" +
        "  },\n" +
        "  \"recipeContainer\" : {\n" +
        "    \"recipes\" : [ {\n" +
        "      \"ingredientContainer\" : {\n" +
        "        \"ingredients\" : [ {\n" +
        "          \"quantity\" : {\n" +
        "            \"unit\" : \"gram\",\n" +
        "            \"amount\" : 400.0\n" +
        "          },\n" +
        "          \"name\" : \"minced meat\"\n" +
        "        }, {\n" +
        "          \"quantity\" : {\n" +
        "            \"unit\" : \"gram\",\n" +
        "            \"amount\" : 200.0\n" +
        "          },\n" +
        "          \"name\" : \"cheese\"\n" +
        "        }, {\n" +
        "          \"quantity\" : {\n" +
        "            \"unit\" : \"stk\",\n" +
        "            \"amount\" : 9.0\n" +
        "          },\n" +
        "          \"name\" : \"lasagne plates\"\n" +
        "        } ]\n" +
        "      },\n" +
        "      \"recipeInstructions\" : [ \"cook\", \"bake\", \"eat\" ],\n" +
        "      \"metadata\" : {\n" +
        "        \"author\" : \"bestUsername\",\n" +
        "        \"portion\" : 4.0,\n" +
        "        \"recipeName\" : \"Lasagne\",\n" +
        "        \"recipeDescription\" : \"God og smakfull lasagne\",\n" +
        "        \"minutes\" : 90\n" +
        "      }\n" +
        "    }, {\n" +
        "      \"ingredientContainer\" : {\n" +
        "        \"ingredients\" : [ {\n" +
        "          \"quantity\" : {\n" +
        "            \"unit\" : \"stk\",\n" +
        "            \"amount\" : 2.0\n" +
        "          },\n" +
        "          \"name\" : \"eggs\"\n" +
        "        }, {\n" +
        "          \"quantity\" : {\n" +
        "            \"unit\" : \"dl\",\n" +
        "            \"amount\" : 3.0\n" +
        "          },\n" +
        "          \"name\" : \"tikka\"\n" +
        "        } ]\n" +
        "      },\n" +
        "      \"recipeInstructions\" : [ \"mix\", \"doStuff\", \"serve\" ],\n" +
        "      \"metadata\" : {\n" +
        "        \"author\" : \"bestUsername\",\n" +
        "        \"portion\" : 2.0,\n" +
        "        \"recipeName\" : \"Tikka masala\",\n" +
        "        \"recipeDescription\" : \"Describing description of tikka masala\",\n" +
        "        \"minutes\" : 60\n" +
        "      }\n" +
        "    } ]\n" +
        "  },\n" +
        "  \"username\" : \"bestUsername\"\n" +
        "}";

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
        mapper.registerModule(new DinneriumModule());
    }

    @Test
    public void testSerializers() {
        User expectedUser = createExceptedUser();

        assertTrue(true);

        try {
            StringWriter writer = new StringWriter();
            handlePersistency.writeUser(expectedUser, writer);
            String serializedObject = writer.toString().replaceAll("\\s+", "");
            assertEquals(expectedUserString.replaceAll("\\s+", ""), serializedObject);
        } catch (IOException e) {
            fail("User is not written to file as it is supposed to");
        }
    }

    @Test
    public void testDeserializers() {
        try {
            User jsonUser = mapper.readValue(expectedUserString, User.class);
            User expectedUser = createExceptedUser();
            compareUsers(expectedUser, jsonUser);
        } catch (IOException e) {
            fail("Could not deserialize object from file correctly");
        }
    }

    @Test
    public void testReader() {
        try {
            User jsonUser =
                handlePersistency.readUserFromReader(new StringReader(expectedUserString));
            compareUsers(createExceptedUser(), jsonUser);
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testWriter() {
        try {
            User user = createExceptedUser();
            StringWriter writer = new StringWriter();
            handlePersistency.writeUser(user, writer);
            String json = writer.toString();
            assertEquals(expectedUserString.replaceAll("\\s+", ""),
                json.replaceAll("\\s+", ""));
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testSerializeDeserialize() {
        try {
            User user = createExceptedUser();
            String userJson = mapper.writeValueAsString(user);
            User readUser = mapper.readValue(userJson, User.class);
            compareUsers(user, readUser);

        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    private void compareUsers(User expectedUser, User u2) {
        assertEquals(expectedUser.getUsername(), u2.getUsername());
        compareIngredientContainers(expectedUser.getIngredientContainer(),
            u2.getIngredientContainer(), expectedUser.getIngredientContainer().getContainerSize());
        compareRecipeContainers(expectedUser.getRecipeContainer(), u2.getRecipeContainer());
    }

    private void compareIngredientContainers(IngredientContainer expectedIngredientContainer,
                                             IngredientContainer ic2, int containerSize) {
        Iterator<Ingredient> expectedIterator =
            expectedIngredientContainer.getContainer().iterator();
        Iterator<Ingredient> it2 = ic2.getContainer().iterator();

        for (int i = 0; i < containerSize; i++) {
            assertTrue(it2.hasNext());
            compareIngredient(expectedIterator.next(), it2.next());
        }
        assertFalse(it2.hasNext());
    }

    private void compareRecipeContainers(RecipeContainer expectedRecipeContainer,
                                         RecipeContainer rc2) {
        Iterator<Recipe> expectedIterator = expectedRecipeContainer.getContainer().iterator();
        Iterator<Recipe> it2 = rc2.getContainer().iterator();

        assertTrue(it2.hasNext());
        compareRecipe(expectedIterator.next(), it2.next());
        assertTrue(it2.hasNext());
        compareRecipe(expectedIterator.next(), it2.next());
        assertFalse(it2.hasNext());
    }

    private void compareRecipe(Recipe expectedRecipe, Recipe r2) {
        compareIngredientContainers(expectedRecipe.getIngredientContainer(),
            r2.getIngredientContainer(),
            expectedRecipe.getIngredientContainer().getContainerSize());
        compareRecipeInstructions(expectedRecipe.getRecipeInstructions(),
            r2.getRecipeInstructions());
        compareMetadata(expectedRecipe.getRecipeMetadata(), r2.getRecipeMetadata());
    }

    private void compareRecipeInstructions(RecipeInstructions expectedInstructions,
                                           RecipeInstructions ri2) {
        Iterator<String> expectedIterator = expectedInstructions.iterator();
        Iterator<String> it2 = ri2.iterator();

        assertTrue(it2.hasNext());
        assertEquals(expectedIterator.next(), it2.next());
        assertTrue(it2.hasNext());
        assertEquals(expectedIterator.next(), it2.next());
        assertTrue(it2.hasNext());
        assertEquals(expectedIterator.next(), it2.next());
        assertFalse(it2.hasNext());
    }

    private void compareIngredient(Ingredient expectedIngredient, Ingredient i2) {
        assertEquals(expectedIngredient.getName(), i2.getName());
        assertEquals(expectedIngredient.getQuantity().getAmount(), i2.getQuantity().getAmount());
        assertEquals(expectedIngredient.getQuantity().getUnit(), i2.getQuantity().getUnit());
    }

    private void compareMetadata(RecipeMetadata expectedRecipeMetadata, RecipeMetadata m2) {
        assertEquals(expectedRecipeMetadata.getAuthor(), m2.getAuthor());
        assertEquals(expectedRecipeMetadata.getMinutes(), m2.getMinutes());
        assertEquals(expectedRecipeMetadata.getRecipeDescription(), m2.getRecipeDescription());
        assertEquals(expectedRecipeMetadata.getRecipeName(), m2.getRecipeName());
    }

    private User createExceptedUser() {
        IngredientContainer ic = new IngredientContainer();
        ic.addIngredient(new Ingredient(new Quantity(1, "stk"), "eggs"));
        ic.addIngredient(new Ingredient(new Quantity(2, "dl"), "milk"));
        ic.addIngredient(new Ingredient(new Quantity(3, "gram"), "sugar"));
        RecipeContainer rc = createExpectedRecipeContainer();

        return new User(ic, rc, "bestUsername");
    }

    private RecipeContainer createExpectedRecipeContainer() {
        RecipeContainer rc = new RecipeContainer();
        double[][] amounts = {{400, 200, 9}, {2, 3}};
        String[][] units = {{"gram", "gram", "stk"}, {"stk", "dl"}};
        String[][] names = {{"minced meat", "cheese", "lasagne plates"}, {"eggs", "tikka"}};
        String[][] instructions = {{"cook", "bake", "eat"}, {"mix", "doStuff", "serve"}};
        double[] portions = {4, 2};
        String[] recipeName = {"Lasagne", "Tikka masala"};
        String[] descriptions =
            {"God og smakfull lasagne", "Describing description of tikka masala"};
        int[] minutes = {90, 60};

        for (int i = 0; i < recipeName.length; i++) {
            IngredientContainer ic = new IngredientContainer();
            for (int j = 0; j < amounts[i].length; j++) {
                ic.addIngredient(new Ingredient(new Quantity(amounts[i][j], units[i][j]), names[i][j]));
            }
            RecipeMetadata md = new RecipeMetadata("bestUsername", portions[i], recipeName[i],
                descriptions[i], minutes[i]);
            rc.addRecipe(new Recipe(ic, new RecipeInstructions(Arrays.asList(instructions[i])), md));
        }
        return rc;
    }
}