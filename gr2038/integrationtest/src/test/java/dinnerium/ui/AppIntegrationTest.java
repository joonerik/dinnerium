package dinnerium.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;


import dinnerium.core.Ingredient;
import dinnerium.core.Units;
import dinnerium.server.RestServer;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Arrays;
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
import spark.Spark;

public class AppIntegrationTest extends ApplicationTest {

    private AppController controller;

    @BeforeAll
    public static void startServer() {
        RestServer.main(new String[] {});
        Spark.awaitInitialization();
    }

    @BeforeEach
    void login() {
        clickOn("#usernameInput").write("testUser");
        clickOn("#loginButton");
    }

    @AfterAll
    static void teardown() {
        Spark.stop();
    }

    @Override
    public void start(Stage stage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("app_test_IT.fxml"));
        final Parent parent = loader.load();
        controller = loader.getController();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    @BeforeAll
    static void createTestFile() {
        File file = Paths.get("../restapi/src/main/resources/storage/bestusername.json").toFile();
        try {
            assertTrue(file.createNewFile());
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

/*    @AfterAll
    static void removeFileCreated() {
        File file = Paths.get("../restapi/src/main/resources/storage/bestusername.json").toFile();
        assertTrue(file.delete());
    }*/

    @Test
    public void testSetupUser() {
        assertNotNull(controller.getUser());
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
        type(KeyCode.ENTER);
        clickOn("#newRecipeAddButton");
        assertEquals(1, controller.getNewRecipeIngredients().size());
        clickOn("#instructionTextArea").write("cook the meat");
        clickOn("#newRecipeAddInstruction");
        clickOn("#instructionTextArea").write("serve");
        clickOn("#newRecipeAddInstruction");
        assertEquals(2, controller.getNewRecipeInstructions().size());
        clickOn("#addRecipeButton");
        assertEquals(2,
            lookup("#recipesAnchorPane").queryParent().getChildrenUnmodifiable().size());
    }

    @Test
    void testGetUnits() {
        Iterator<Object> unitsComboBox =
            lookup("#unitComboBox").queryComboBox().getItems().iterator();
        Iterator<Units> unitsIterator = Arrays.stream(Units.values()).iterator();

        while (unitsIterator.hasNext()) {
            assertTrue(unitsComboBox.hasNext());
            String currentUnit = unitsIterator.next().toString();
            assertEquals(currentUnit, unitsComboBox.next().toString());
        }
    }

    @Test
    public void testRegisterUser() {
        clickOn("#settingsText");
        clickOn("#logoutButton");
        clickOn("#usernameInput").write("userDontExist");
        clickOn("#registerButton");

        assertEquals("userdontexist", controller.getUser().getUsername());
        assertEquals(0, controller.getUser().getRecipeContainer().getContainerSize());
        assertEquals(0, controller.getUser().getIngredientContainer().getContainerSize());
        assertEquals(0, lookup("#ingredientTableView").queryTableView().getItems().size());
        File f = Paths.get("../restapi/src/main/resources/storage/userdontexist.json").toFile();
        assertTrue(f.delete());
    }
}
