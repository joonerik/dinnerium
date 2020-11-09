package dinnerium.ui;

import dinnerium.core.Ingredient;
import dinnerium.core.IngredientContainer;
import dinnerium.core.Metadata;
import dinnerium.core.Quantity;
import dinnerium.core.Recipe;
import dinnerium.core.RecipeContainer;
import dinnerium.core.RecipeInstructions;
import dinnerium.core.Units;
import dinnerium.core.User;
import java.util.Iterator;
import java.util.List;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import static org.mockito.Mockito.*;


import org.testfx.framework.junit5.ApplicationTest;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class AppTest extends ApplicationTest {

    private AppController controller;

    private static final User user =
        new User(new IngredientContainer(), new RecipeContainer(), "testuser");
    private static final User user2 =
        new User(new IngredientContainer(), new RecipeContainer(), "testuser");
    private static final DinneriumAccess mockAccess = mock(DinneriumAccess.class);

    @Override
    public void start(Stage stage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("app_test.fxml"));
        final Parent parent = loader.load();
        controller = loader.getController();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    @BeforeAll
    static void setupTestUsers() {
        Ingredient ing1 = new Ingredient(new Quantity(1.0, "stk"), "eggs");
        Ingredient ing2 = new Ingredient(new Quantity(2.0, "dl"), "milk");
        Ingredient ing3 = new Ingredient(new Quantity(3.0, "gram"), "sugar");
        Ingredient ing4 = new Ingredient(new Quantity(3.0, "dl"), "new item");
        addIngredientsToUser(user, ing1, ing2, ing3);
        addIngredientsToUser(user2, ing1, ing2, ing3, ing4);

        IngredientContainer ic = new IngredientContainer();
        ic.addIngredient(new Ingredient(new Quantity(2.0, "dl"), "milk"));
        RecipeInstructions ri = new RecipeInstructions(List.of("one", "two", "three"));
        Metadata md = new Metadata("testuser", 2.0, "name", "delicious", 30);
        Recipe recipe = new Recipe(ic, ri, md);

        user.getRecipeContainer().addRecipe(recipe);
        user2.getRecipeContainer().addRecipe(recipe);
        user2.getRecipeContainer().addRecipe(recipe);
    }

    @BeforeAll
    static void setupMockito() {
        when(mockAccess.login("testuser")).thenReturn(user);
        when(mockAccess.login("user")).thenThrow(new IllegalArgumentException("not valid"));
        when(mockAccess.registerUser("user")).thenThrow(new IllegalArgumentException("not valid"));
        when(mockAccess.getUnits()).thenReturn(Units.values());
        when(mockAccess.postIngredient(eq("testuser"), any(Ingredient.class))).thenReturn(user2);
        when(mockAccess.postRecipe(eq("testuser"), any(Recipe.class))).thenReturn(user2);
    }

    @BeforeEach
    public void setupUser() {
        controller.setDinneriumAccess(mockAccess);
        clickOn("#usernameInput").write("testUser");
        clickOn("#loginButton");
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
        assertEquals(1,
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
        clickOn("#usernameInput").write("user");
        clickOn("#registerButton");
        checkMsgPaneVisible();
        lookup("#usernameInput").queryTextInputControl().clear();

        clickOn("#usernameInput").write("user");
        clickOn("#loginButton");
        checkMsgPaneVisible();

        clickOn("#registerButton");
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

        assertEquals(1,
            controller.navbarViewController.recipesViewController.getNewRecipeIngredients().size());
        assertEquals(1,
            controller.navbarViewController.recipesViewController.getNewRecipeIngredientsButtons()
                .size());

        clickOn("#instructionTextArea").write("don't delete me");
        clickOn("#newRecipeAddInstruction");
        clickOn("#instructionTextArea").write("delete me");
        clickOn("#newRecipeAddInstruction");
        clickOn("#deleteInst_2");

        assertEquals(1,
            controller.navbarViewController.recipesViewController.getNewRecipeInstructions()
                .size());
        assertEquals(1,
            controller.navbarViewController.recipesViewController.getNewRecipeInstructionsButtons()
                .size());
    }

    private static void addIngredientsToUser(User user, Ingredient ...ingredients) {
        for (Ingredient i : ingredients) {
            user.getIngredientContainer().addIngredient(i);
        }
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
