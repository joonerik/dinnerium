package dinnerium.ui;

import dinnerium.core.Ingredient;
import dinnerium.json.HandlePersistency;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Iterator;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class AppTest extends ApplicationTest {

    private AppController controller;

    @Override
    public void start(Stage stage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("appTest.fxml"));
        final Parent parent = loader.load();
        controller = loader.getController();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    @BeforeEach
    public void setupUser() {
        try (Reader reader = new InputStreamReader(
            getClass().getResourceAsStream("testUser.json"))) {
            controller.setUser(HandlePersistency.readUserFromReader(reader));
        } catch (IOException e) {
            fail(e.getMessage());
        }
        lookup("#ingredientTableView").queryTableView().getItems()
            .setAll(controller.getUser().getIngredientContainer().getContainer());
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
        assertFalse(lookup("#fridgePane").query().isVisible()
            || lookup("#recipesPane").query().isVisible()
            || lookup("#settingsPane").query().isVisible());

        clickOn("#fridgeText");
        assertTrue(lookup("#fridgePane").query().isVisible());
        clickOn("#yourRecipesText");
        assertTrue(lookup("#recipesPane").query().isVisible());
        assertTrue(lookup("#recipesScrollPane").query().isVisible());
        clickOn("#newRecipeSubMenuText");
        assertTrue(lookup("#newRecipePane").query().isVisible());
        assertFalse(lookup("#recipesScrollPane").query().isVisible());
        clickOn("#settingsText");
        assertTrue(lookup("#settingsPane").query().isVisible());
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
        assertFalse(actualList.hasNext());
    }

    @Test
    public void testAddIngredientToFridge() {
        clickOn("#fridgeText");
        clickOn("#nameInput").write("new item");
        clickOn("#amountInput").write("3");
        clickOn("#unitComboBox");
        //Selects the first item of the combobox (unit: stk)
        type(KeyCode.DOWN);
        type(KeyCode.ENTER);
        clickOn("#addButton");

        assertEquals(controller.getUser().getIngredientContainer().getContainerSize(),
            lookup("#ingredientTableView").queryTableView().getItems().size());
        Ingredient newIngredient =
            (Ingredient) lookup("#ingredientTableView").queryTableView().getItems().get(3);

        assertEquals("new item", newIngredient.getName());
        assertEquals("stk", newIngredient.getQuantity().getUnit());
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
        assertTrue(lookup("#msgPane").query().isVisible());
        assertTrue(lookup("#msgPane").query().getStyleClass().contains("error"));
    }

    private boolean compareIngredients(Ingredient expected, Ingredient actual) {
        return expected.getQuantity().getAmount() == actual.getQuantity().getAmount()
            && expected.getQuantity().getUnit().equals(actual.getQuantity().getUnit())
            && expected.getName().equals(actual.getName());
    }
}
