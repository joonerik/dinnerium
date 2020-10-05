package dinnerium.ui;

import dinnerium.json.HandlePersistency;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

public class AppTest extends ApplicationTest {

    private AppController controller;

    @Override
    public void start(Stage stage) throws Exception {
        final FXMLLoader loader = new FXMLLoader(getClass().getResource("dinnerium/ui/appTest.fxml"));
        final Parent parent = loader.load();
        controller = loader.getController();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    @BeforeEach
    public void setupUser() {
        try (Reader reader = new InputStreamReader(
            getClass().getResourceAsStream("dinnerium/ui/testUser.json"))) {
            controller.setUser(HandlePersistency.readUserFromReader(reader));
        } catch (IOException e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void testControllerInitializing() {
        assertNotNull(controller);
    }

    @Test
    public void testSetupUser() {
        assertNotNull(controller.getUser());
    }
}
