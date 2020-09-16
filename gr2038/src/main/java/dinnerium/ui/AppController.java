package dinnerium.ui;

//import javafx.fxml.FXML;
//import javafx.scene.control.Button;

import dinnerium.core.Ingredient;
import dinnerium.core.Quantity;
import dinnerium.json.HandlePersistency;
import javafx.fxml.FXML;

public class AppController {

    @FXML
    void initialize() throws Exception {
        System.out.println("Controller kjører");
        HandlePersistency hp = new HandlePersistency();
        Quantity quantity = new Quantity(2.0, "stk");
        Ingredient ingredient = new Ingredient(quantity, "Egg");
        hp.writeJsonToFile(ingredient);
    }

}
