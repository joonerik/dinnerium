package dinnerium.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent parent = FXMLLoader.load(getClass().getResource("app.fxml"));
        Scene scene = new Scene(parent);
        scene.getStylesheets().addAll(
                getClass().getResource("table-view-style.css").toExternalForm(),
                getClass().getResource("recipe-pane.css").toExternalForm()
        );
        stage.setScene(scene);
        stage.setTitle("Dinnerium");
        stage.getIcons().add(new Image("https://folk.ntnu.no/anderobs/images/dinnerium.png"));
        stage.show();
    }

    public static void main(String[] args) {
        launch(App.class, args);
    }
}
