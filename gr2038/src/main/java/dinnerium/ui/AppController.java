package dinnerium.ui;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import dinnerium.core.Ingredient;
import dinnerium.core.Quantity;
import dinnerium.json.HandlePersistency;
import javafx.fxml.FXML;

public class AppController {

    @FXML
    void initialize() throws Exception {
        System.out.println("Controller kjører");
        HandlePersistency hp = new HandlePersistency();
        Quantity quantity = new Quantity(3.0, "stk");
        Ingredient ingredient = new Ingredient(quantity, "Egg");
        hp.writeJsonToFile(ingredient);

        Ingredient x = hp.loadDataFromFile();
        System.out.println(x.getName());
        System.out.println(x.getQuantity().getAmount());

    }


    TextField nameInput;
    @FXML
    TextField quantityInput;
    @FXML
    ComboBox unitComboBox;
    @FXML
    Button addButton;
    @FXML
    TableView<String> ingredientTableView;
    @FXML
    TableColumn quantityColumn;
    @FXML
    TableColumn itemColumn;


    @FXML
    private void addIngredient() {

    }
}
