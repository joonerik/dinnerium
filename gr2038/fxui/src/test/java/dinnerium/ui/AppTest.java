package dinnerium.ui;

import dinnerium.core.Ingredient;
import dinnerium.json.HandlePersistency;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Iterator;
import javafx.collections.ObservableList;
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

    private boolean compareIngredients(Ingredient expected, Ingredient actual) {
        return expected.getQuantity().getAmount() == actual.getQuantity().getAmount()
            && expected.getQuantity().getUnit().equals(actual.getQuantity().getUnit())
            && expected.getName().equals(actual.getName());
    }
}
