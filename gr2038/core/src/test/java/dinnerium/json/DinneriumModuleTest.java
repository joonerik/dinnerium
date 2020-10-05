package dinnerium.json;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


import com.fasterxml.jackson.databind.ObjectMapper;
import dinnerium.core.Ingredient;
import dinnerium.core.IngredientContainer;
import dinnerium.core.Metadata;
import dinnerium.core.Quantity;
import dinnerium.core.Recipe;
import dinnerium.core.RecipeContainer;
import dinnerium.core.RecipeInstructions;
import dinnerium.core.User;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DinneriumModuleTest {

    private ObjectMapper mapper;
    //Fiks formateringen på recipeInstructions!!!
    private String expectedUserString = "" +
        "{\n" +
        "  \"ingredientContainer\" : {\n" +
        "    \"container\" : [ {\n" +
        "      \"quantity\" : {\n" +
        "        \"amount\" : 1.0,\n" +
        "        \"unit\" : \"stk\"\n" +
        "      },\n" +
        "      \"name\" : \"eggs\"\n" +
        "    }, {\n" +
        "      \"quantity\" : {\n" +
        "        \"amount\" : 2.0,\n" +
        "        \"unit\" : \"dl\"\n" +
        "      },\n" +
        "      \"name\" : \"milk\"\n" +
        "    }, {\n" +
        "      \"quantity\" : {\n" +
        "        \"amount\" : 3.0,\n" +
        "        \"unit\" : \"gram\"\n" +
        "      },\n" +
        "      \"name\" : \"sugar\"\n" +
        "    } ],\n" +
        "    \"containerSize\" : 3\n" +
        "  },\n" +
        "  \"recipeContainer\" : {\n" +
        "    \"container\" : [ {\n" +
        "      \"ingredientContainer\" : {\n" +
        "        \"container\" : [ {\n" +
        "          \"quantity\" : {\n" +
        "            \"amount\" : 400.0,\n" +
        "            \"unit\" : \"gram\"\n" +
        "          },\n" +
        "          \"name\" : \"minced meat\"\n" +
        "        }, {\n" +
        "          \"quantity\" : {\n" +
        "            \"amount\" : 200.0,\n" +
        "            \"unit\" : \"gram\"\n" +
        "          },\n" +
        "          \"name\" : \"cheese\"\n" +
        "        }, {\n" +
        "          \"quantity\" : {\n" +
        "            \"amount\" : 9.0,\n" +
        "            \"unit\" : \"stk\"\n" +
        "          },\n" +
        "          \"name\" : \"lasagne plates\"\n" +
        "        } ],\n" +
        "        \"containerSize\" : 3\n" +
        "      },\n" +
        "      \"recipeInstructions\" : {\n" +
        "        \"instructions\" : [ \"cook\", \"bake\", \"eat\" ]\n" +
        "      },\n" +
        "      \"metadata\" : {\n" +
        "        \"author\" : \"bestUsername\",\n" +
        "        \"portion\" : 4.0,\n" +
        "        \"image\" : \"http://folk.ntnu.no/anderobs/images/tikkaMasala.png\",\n" +
        "        \"recipeName\" : \"Lasagne\",\n" +
        "        \"recipeDescription\" : \"God og smakfull lasagne\",\n" +
        "        \"minutes\" : 90\n" +
        "      }\n" +
        "    }, {\n" +
        "      \"ingredientContainer\" : {\n" +
        "        \"container\" : [ {\n" +
        "          \"quantity\" : {\n" +
        "            \"amount\" : 2.0,\n" +
        "            \"unit\" : \"stk\"\n" +
        "          },\n" +
        "          \"name\" : \"eggs\"\n" +
        "        }, {\n" +
        "          \"quantity\" : {\n" +
        "            \"amount\" : 3.0,\n" +
        "            \"unit\" : \"dl\"\n" +
        "          },\n" +
        "          \"name\" : \"tikka\"\n" +
        "        } ],\n" +
        "        \"containerSize\" : 2\n" +
        "      },\n" +
        "      \"recipeInstructions\" : {\n" +
        "        \"instructions\" : [ \"mix\", \"doStuff\", \"serve\" ]\n" +
        "      },\n" +
        "      \"metadata\" : {\n" +
        "        \"author\" : \"bestUsername\",\n" +
        "        \"portion\" : 2.0,\n" +
        "        \"image\" : \"http://folk.ntnu.no/anderobs/images/tikkaMasala.png\",\n" +
        "        \"recipeName\" : \"Tikka masala\",\n" +
        "        \"recipeDescription\" : \"Describing description of tikka masala\",\n" +
        "        \"minutes\" : 60\n" +
        "      }\n" +
        "    } ],\n" +
        "    \"containerSize\" : 2\n" +
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
            String serializedObject =
                mapper.writerWithDefaultPrettyPrinter().writeValueAsString(expectedUser);
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

    private void compareUsers(User expectedUser, User u2) {
        assertEquals(expectedUser.getUsername(), u2.getUsername());
        compareIngredientContainers(expectedUser.getIngredientContainer(),
            u2.getIngredientContainer());
        compareRecipeContainers(expectedUser.getRecipeContainer(), u2.getRecipeContainer());
    }

    private void compareIngredientContainers(IngredientContainer expectedIngredientContainer,
                                             IngredientContainer ic2) {
        Iterator<Ingredient> expectedIterator =
            expectedIngredientContainer.getContainer().iterator();
        Iterator<Ingredient> it2 = ic2.getContainer().iterator();

        assertTrue(it2.hasNext());
        compareIngredient(expectedIterator.next(), it2.next());
        assertTrue(it2.hasNext());
        compareIngredient(expectedIterator.next(), it2.next());
        assertTrue(it2.hasNext());
        compareIngredient(expectedIterator.next(), it2.next());
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
        compareIngredientContainers(expectedRecipe.getIngredientContainer(), r2.getIngredientContainer());
        compareRecipeInstructions(expectedRecipe.getRecipeInstructions(), r2.getRecipeInstructions());
        compareMetadata(expectedRecipe.getMetadata(), r2.getMetadata());
    }
    private void compareRecipeInstructions(RecipeInstructions expectedInstructions, RecipeInstructions ri2) {
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
    private void compareMetadata(Metadata expectedMetadata, Metadata m2) {
        assertEquals(expectedMetadata.getAuthor(), m2.getAuthor());
        assertEquals(expectedMetadata.getImage(), m2.getImage());
        assertEquals(expectedMetadata.getMinutes(), m2.getMinutes());
        assertEquals(expectedMetadata.getRecipeDescription(), m2.getRecipeDescription());
        assertEquals(expectedMetadata.getRecipeName(), m2.getRecipeName());
    }

    private void compareIngredient(Ingredient expectedIngredient, Ingredient i2) {
        assertEquals(expectedIngredient.getName(), i2.getName());
        assertEquals(expectedIngredient.getQuantity().getAmount(), i2.getQuantity().getAmount());
        assertEquals(expectedIngredient.getQuantity().getUnit(), i2.getQuantity().getUnit());
    }

    private User createExceptedUser() {
        IngredientContainer ic = new IngredientContainer();
        ic.addItem(new Ingredient(new Quantity(1, "stk"), "eggs"));
        ic.addItem(new Ingredient(new Quantity(2, "dl"), "milk"));
        ic.addItem(new Ingredient(new Quantity(3, "gram"), "sugar"));
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
                ic.addItem(new Ingredient(new Quantity(amounts[i][j], units[i][j]), names[i][j]));
            }
            Metadata md = new Metadata("bestUsername", portions[i],
                "http://folk.ntnu.no/anderobs/images/tikkaMasala.png", recipeName[i],
                descriptions[i], minutes[i]);
            rc.addItem(new Recipe(ic, new RecipeInstructions(Arrays.asList(instructions[i])), md));
        }
        return rc;
    }
/*
    @Test
    public void testSerializers() throws JsonProcessingException {
        IngredientContainer container = new IngredientContainer();
        container.addItem(new Ingredient(new Quantity(10, "stk"), "egg"));
        container.addItem(new Ingredient(new Quantity(500, "gram"), "mel"));
        container.addItem(new Ingredient(new Quantity(5, "dl"), "melk"));

        try {
            String serializedObject = mapper.writeValueAsString(container);
            assertEquals(exampleIngredientContainer.replaceAll("\\s+", ""), serializedObject);
        } catch (JsonParseException e) {
            fail();
        }
    }

    @Test
    public void testDeserializers() {
        try {
            IngredientContainer container = mapper.readValue(exampleIngredientContainer, IngredientContainer.class);
            IngredientContainer exampleContainer = new IngredientContainer(createSampleIngredient());
            Iterator<Ingredient> it =  container.iterator();
            for (Ingredient i : exampleContainer) {
                Ingredient readIngredient = it.next();
                assertEquals(i.getName(), readIngredient.getName());
                assertEquals(i.getQuantity().getAmount(), readIngredient.getQuantity().getAmount());
                assertEquals(i.getQuantity().getUnit(), readIngredient.getQuantity().getUnit());
            }
        } catch (JsonProcessingException e) {
            fail();
        }
    }*/

}