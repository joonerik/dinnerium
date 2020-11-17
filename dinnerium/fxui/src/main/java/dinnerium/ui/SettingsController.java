package dinnerium.ui;

import dinnerium.core.User;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

/**
 * Controller for the settings-page of the application.
 */
public class SettingsController {

    private User user;
    private NavbarController navbarController;

    @FXML
    Text greetUserText;
    @FXML
    Pane settingsPane;

    @FXML
    void handleLogout() {
        navbarController.logout();
    }

    void setScene(Boolean thisScene) {
        greetUserText.setText("Hi " + user.getUsername());
        settingsPane.setVisible(thisScene);
    }

    void setUser(User user) {
        this.user = user;
    }

    void setNavbarController(NavbarController navbarController) {
        this.navbarController = navbarController;
    }
}
