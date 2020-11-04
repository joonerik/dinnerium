package dinnerium.ui;

import dinnerium.core.User;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class NavbarController {

    private AppController appController;

    @FXML
    Pane navigationBarPane;
    @FXML
    Text settingsText;
    @FXML
    Text yourRecipesText;
    @FXML
    Text fridgeText;
    @FXML
    SettingsController settingsViewController;
    @FXML
    FridgeController fridgeViewController;
    @FXML
    RecipesController recipesViewController;

    @FXML
    private void initialize() {
        settingsViewController.setNavbarController(this);
        fridgeViewController.setNavbarController(this);
    }

    @FXML
    private void handleChangeToSettings() {
        changeScene("settings");
    }

    @FXML
    private void handleChangeToYourRecipes() {
        changeScene("recipes");
    }

    @FXML
    void handleChangeToFridge() {
        changeScene("fridge");
    }

    //Changes between the scenes of the application, and sets the color of the header text
    private void changeScene(String newScene) {
        settingsText.setFill(Color.valueOf(newScene.equals("settings") ? "#f4c20d" : "#ebe8bf"));
        fridgeText.setFill(Color.valueOf(newScene.equals("fridge") ? "#f4c20d" : "#ebe8bf"));
        yourRecipesText.setFill(Color.valueOf(newScene.equals("recipes") ? "#f4c20d" : "#ebe8bf"));

        settingsViewController.setScene(newScene.equals("settings"));
        fridgeViewController.setScene(newScene.equals("fridge"));
        recipesViewController.setScene(newScene.equals("recipes"));
    }

    void logout() {
        setVisible(false);
        recipesViewController.handleChangeToRecipes();
        handleChangeToFridge();
        fridgeViewController.setScene(false);
        appController.handleLogout();
    }

    void setVisible(Boolean visible) {
        navigationBarPane.setVisible(visible);
    }

    void setUser(User user) {
        fridgeViewController.setUser(user);
        settingsViewController.setUser(user);
        recipesViewController.setUser(user);
        appController.user = user;
    }

    void setAppController(AppController appController) {
        this.appController = appController;
    }
}
