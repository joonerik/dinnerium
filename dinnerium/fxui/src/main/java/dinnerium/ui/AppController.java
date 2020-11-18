package dinnerium.ui;

import dinnerium.core.Ingredient;
import dinnerium.core.User;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

/**
 * Class for controlling actions in the general application.
 */
public class AppController {

    User user = null;
    private DinneriumAccess dinneriumAccess;

    @FXML
    Pane msgPane;
    @FXML
    TextField usernameInput;
    @FXML
    Button loginButton;
    @FXML
    Button registerButton;
    @FXML
    NavbarController navbarViewController;


    @FXML
    void initialize() {
        try {
            dinneriumAccess = new RemoteDinneriumAccess(new URI("http://localhost:4567/"));
        } catch (URISyntaxException e) {
            FeedbackHandler.showMessage(msgPane, e.getMessage(), FeedbackHandler.ERROR);
        }
        navbarViewController.recipesViewController.setMsgPane(msgPane);
        navbarViewController.recipesViewController.setDinneriumAccess(dinneriumAccess);
        navbarViewController.fridgeViewController.setMsgPane(msgPane);
        navbarViewController.fridgeViewController.setDinneriumAccess(dinneriumAccess);
        navbarViewController.setAppController(this);
    }

    @FXML
    private void handleLogin() {
        startApplication(true);
    }

    @FXML
    private void handleRegister() {
        startApplication(false);
    }

    private void startApplication(Boolean login) {
        try {
            if (login) {
                this.user = dinneriumAccess.login(usernameInput.getText().toLowerCase());
            } else {
                this.user = dinneriumAccess.registerUser(usernameInput.getText().toLowerCase());
            }
        } catch (IllegalArgumentException e) {
            FeedbackHandler.showMessage(msgPane, e.getMessage(), FeedbackHandler.ERROR);
        }
        if (user != null) {
            navbarViewController.setUser(user);
            navbarViewController.fridgeViewController.updateTableView();
            navbarViewController.recipesViewController.showUserRecipes();
            usernameInput.setVisible(false);
            loginButton.setVisible(false);
            registerButton.setVisible(false);
            navbarViewController.setVisible(true);
            navbarViewController.handleChangeToFridge();
            navbarViewController.recipesViewController.handleChangeToRecipes();
        }
    }

    void handleLogout() {
        usernameInput.setVisible(true);
        loginButton.setVisible(true);
        registerButton.setVisible(true);
        usernameInput.clear();
        navbarViewController.setUser(null);
    }

    //Methods for testing the app controller is below here.

    /**
     * Method used during testing of the application for checking that the current user of the
     *      controller is correct.
     *
     * @return the user saved in the controller.
     */
    User getUser() {
        return this.user;
    }


    /**
     * Method used during testing of the application for returning the list of Ingredients
     *      added to the new recipe that is being created.
     *
     * @return a copy of the Ingredients added to the new recipe that is being created.
     */
    List<Ingredient> getNewRecipeIngredients() {
        return  new ArrayList<>(navbarViewController.recipesViewController.newRecipeIngredients);
    }

    /**
     * Method used during testing of the application for returning the list of recipeInstructions
     *      added to the new recipe that is being created.
     *
     * @return a copy of the RecipeInstructions added to the new recipe that is being created.
     */
    List<String> getNewRecipeInstructions() {
        return new ArrayList<>(navbarViewController.recipesViewController.newRecipeInstructions);
    }

    /**
     * Method for setting the dinneriumAccess to a mock object during the tests to mock answers
     *      from the server.
     *
     * @param dinneriumAccess the DinneriumAccess used to Mock an DinneriumAccess object during the
     *      testing of the application.
     */
    void setDinneriumAccess(DinneriumAccess dinneriumAccess) {
        this.dinneriumAccess = dinneriumAccess;
        navbarViewController.recipesViewController.setDinneriumAccess(dinneriumAccess);
        navbarViewController.fridgeViewController.setDinneriumAccess(dinneriumAccess);
    }
}