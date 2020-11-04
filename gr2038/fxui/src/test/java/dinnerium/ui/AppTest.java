package dinnerium.ui;

import dinnerium.core.Ingredient;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Iterator;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppTest extends ApplicationTest {

    private AppController controller;
    private static final String standardUserString = "{\n" +
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
        "        \"image\" : \"http://folk.ntnu.no/anderobs/images/tikkaMasala.png\",\n" +
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
        "        \"image\" : \"http://folk.ntnu.no/anderobs/images/tikkaMasala.png\",\n" +
        "        \"recipeName\" : \"Tikka masala\",\n" +
        "        \"recipeDescription\" : \"Describing description of tikka masala\",\n" +
        "        \"minutes\" : 60\n" +
        "      }\n" +
        "    } ]\n" +
        "  },\n" +
        "  \"username\" : \"testuser\"\n" +
        "}";

    @Override
    public void start(Stage stage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("app_test.fxml"));
        final Parent parent = loader.load();
        controller = loader.getController();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    @BeforeEach
    public void setupUser() {
        clickOn("#usernameInput").write("testUser");
        clickOn("#loginButton");
    }

    @AfterAll
    static void removeFileCreated() {
        File file = Paths.get("../restapi/src/main/resources/storage/testuser.json").toFile();
        assertTrue(file.delete());
    }

    @BeforeAll
    static void createFileForTest() throws IOException {
        File file = new File("../restapi/src/main/resources/storage/testuser.json");
        assertTrue(file.createNewFile());
        FileWriter writer = new FileWriter("../restapi/src/main/resources/storage/testuser.json");
        writer.write(standardUserString);
        writer.close();
    }

    @Test
    public void testControllerInitializing() {
        assertNotNull(controller);
    }

    @Test
    public void testSetupUser() {
        assertNotNull(controller.getUser());
    }

    @Test
    public void testChangingScene() {
        assertFalse(!lookup("#fridgePane").query().isVisible()
            || lookup("#recipesPane").query().isVisible()
            || lookup("#settingsPane").query().isVisible());
        assertFalse(lookup("#loginButton").query().isVisible()
                || lookup("#usernameInput").query().isVisible());
        clickOn("#yourRecipesText");
        assertTrue(lookup("#recipesPane").query().isVisible());
        assertTrue(lookup("#recipesScrollPane").query().isVisible());
        clickOn("#newRecipeSubMenuText");
        assertTrue(lookup("#newRecipePane").query().isVisible());
        assertFalse(lookup("#recipesScrollPane").query().isVisible());
        clickOn("#settingsText");
        assertTrue(lookup("#settingsPane").query().isVisible());
        clickOn("#fridgeText");
        assertTrue(lookup("#fridgePane").query().isVisible());
    }

    @Test
    public void testFridgeTableView() {
        Iterator<Object> actualList =
            lookup("#ingredientTableView").queryTableView().getItems().iterator();
        Iterator<Ingredient> expectedIterator =
            controller.getUser().getIngredientContainer().iterator();

        assertTrue(actualList.hasNext());
        assertTrue(compareIngredients(expectedIterator.next(), (Ingredient) actualList.next()));
        assertTrue(actualList.hasNext());
        assertTrue(compareIngredients(expectedIterator.next(), (Ingredient) actualList.next()));
        assertTrue(actualList.hasNext());
        assertTrue(compareIngredients(expectedIterator.next(), (Ingredient) actualList.next()));
        assertTrue(actualList.hasNext());
        assertTrue(compareIngredients(expectedIterator.next(), (Ingredient) actualList.next()));
        assertFalse(actualList.hasNext());
    }

    @Test
    public void testAddIngredientToFridge() {
        clickOn("#fridgeText");
        clickOn("#nameInput").write("new item");
        clickOn("#amountInput").write("3");
        clickOn("#unitComboBox");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("#addButton");

        assertEquals(controller.getUser().getIngredientContainer().getContainerSize(),
            lookup("#ingredientTableView").queryTableView().getItems().size());
        Ingredient newIngredient =
            (Ingredient) lookup("#ingredientTableView").queryTableView().getItems().get(3);

        assertEquals("new item", newIngredient.getName());
        assertEquals("dl", newIngredient.getQuantity().getUnit());
        assertEquals(3.0, newIngredient.getQuantity().getAmount());
    }

    @Test
    public void testShowRecipeInformation() {
        clickOn("#yourRecipesText");
        clickOn(lookup("#recipesAnchorPane").queryParent().getChildrenUnmodifiable().get(0));
        assertEquals(1,
            lookup("#recipesAnchorPane").queryParent().getChildrenUnmodifiable().size());
        clickOn("#hideRecipeInformationButton");
        assertEquals(2,
            lookup("#recipesAnchorPane").queryParent().getChildrenUnmodifiable().size());
    }

    @Test
    public void testAddRecipe() {
        clickOn("#yourRecipesText");
        clickOn("#newRecipeSubMenuText");
        clickOn("#newRecipeRecipeName").write("Taco");
        clickOn("#newRecipeRecipeDescription").write("Describing description");
        clickOn("#newRecipePortions").write("2.5");
        clickOn("#newRecipeMinutes").write("20");
        clickOn("#newRecipeNameIngredientInput").write("minced meat");
        clickOn("#newRecipeAmountInput").write("400");
        clickOn("#newRecipeUnitComboBox");
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("#newRecipeAddButton");
        assertEquals(1, controller.getNewRecipeIngredients().size());
        clickOn("#instructionTextArea").write("cook the meat");
        clickOn("#newRecipeAddInstruction");
        clickOn("#instructionTextArea").write("serve");
        clickOn("#newRecipeAddInstruction");
        assertEquals(2, controller.getNewRecipeInstructions().size());
        clickOn("#addRecipeButton");
    }

    @Test
    public void testFeedbackHandling() {
        assertFalse(lookup("#msgPane").query().isVisible());
        assertEquals(0, lookup("#msgPane").query().getStyleClass().size());

        clickOn("#fridgeText");
        clickOn("#nameInput").write("illegal amount");
        clickOn("#amountInput").write("tre og en halv");
        clickOn("#unitComboBox");
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("#addButton");
        checkMsgPaneVisible();
    }
    @Test
    public void testRegisterUser() {
        clickOn("#settingsText");
        clickOn("#logoutButton");
        clickOn("#usernameInput").write("testUser");
        clickOn("#registerButton");
        checkMsgPaneVisible();
        lookup("#usernameInput").queryTextInputControl().clear();

        clickOn("#usernameInput").write("userdontexist");
        clickOn("#loginButton");
        checkMsgPaneVisible();

        clickOn("#registerButton");
        File file = Paths.get("../restapi/src/main/resources/storage/userdontexist.json").toFile();
        assertTrue(file.delete());
    }
    @Test
    void testInvalidInputsForNewRecipe() {
        clickOn("#yourRecipesText");
        clickOn("#newRecipeSubMenuText");

        clickOn("#newRecipeAddInstruction");
        checkMsgPaneVisible();

        clickOn("#newRecipeAddButton");
        checkMsgPaneVisible();

        clickOn("#newRecipePortions").write("femtifem");
        clickOn("#newRecipeMinutes").write("sekstiseks");
        clickOn("#addRecipeButton");
        checkMsgPaneVisible();
    }
    @Test
    void testDeleteInstructionsAndIngredients() {
        clickOn("#yourRecipesText");
        clickOn("#newRecipeSubMenuText");

        for (int i = 0; i < 2; i++) {
            clickOn("#newRecipeNameIngredientInput").write(i == 0 ? "delete me" : "dont delete");
            clickOn("#newRecipeAmountInput").write("400");
            clickOn("#newRecipeUnitComboBox");
            type(KeyCode.DOWN);
            type(KeyCode.DOWN);
            type(KeyCode.DOWN);
            type(KeyCode.ENTER);
            clickOn("#newRecipeAddButton");
        }
        clickOn("#deleteIng_1");

        assertEquals(1, controller.navbarViewController.recipesViewController.getNewRecipeIngredients().size());
        assertEquals(1, controller.navbarViewController.recipesViewController.getNewRecipeIngredientsButtons().size());

        clickOn("#instructionTextArea").write("don't delete me");
        clickOn("#newRecipeAddInstruction");
        clickOn("#instructionTextArea").write("delete me");
        clickOn("#newRecipeAddInstruction");
        clickOn("#deleteInst_2");

        assertEquals(1, controller.navbarViewController.recipesViewController.getNewRecipeInstructions().size());
        assertEquals(1, controller.navbarViewController.recipesViewController.getNewRecipeInstructionsButtons().size());
    }

    private void checkMsgPaneVisible() {
        assertTrue(lookup("#msgPane").query().isVisible());
        assertTrue(lookup("#msgPane").query().getStyleClass().contains("error"));
        lookup("#msgPane").query().setVisible(false);
    }

    private boolean compareIngredients(Ingredient expected, Ingredient actual) {
        return expected.getQuantity().getAmount() == actual.getQuantity().getAmount()
            && expected.getQuantity().getUnit().equals(actual.getQuantity().getUnit())
            && expected.getName().equals(actual.getName());
    }
}
