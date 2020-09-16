package dinnerium.ui;

import dinnerium.core.IngredientContainer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import dinnerium.core.Ingredient;
import dinnerium.core.Quantity;
import dinnerium.json.HandlePersistency;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppController {

    private IngredientContainer ingredientContainer;

    @FXML
    TextField nameInput;
    @FXML
    TextField amountInput;
    @FXML
    ComboBox<String> unitComboBox;
    @FXML
    Button addButton;
    @FXML
    TableView<Ingredient> ingredientTableView;
    @FXML
    TableColumn<Quantity, String> quantityColumn;
    @FXML
    TableColumn<Ingredient, String> itemColumn;

    @FXML
    void initialize() throws Exception {
        setup();
    }

    private void setup() {

        unitComboBox.getItems().setAll(Quantity.units);
        try {
            IngredientContainer ingredientContainer = HandlePersistency.loadDataFromFile();
            this.ingredientContainer = ingredientContainer;
        } catch (IOException e) {
            throw new IllegalArgumentException("error in setup");
        }
        ObservableList<Ingredient> observableList =
                FXCollections.observableArrayList((this.ingredientContainer.getIngredients()));
        ingredientTableView.setItems(observableList);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("unit"));
        itemColumn.setCellValueFactory(new PropertyValueFactory<>("ingredient"));

    }

    @FXML
    private void handleAddIngredient() {
        Quantity quantity = new Quantity(Double.valueOf(amountInput.getText()), unitComboBox.getSelectionModel().getSelectedItem());
        Ingredient ingredient = new Ingredient(quantity, nameInput.getText());
        this.ingredientContainer.addIngredient(ingredient);
        try {
            HandlePersistency.writeJsonToFile(ingredientContainer);
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not write to file");
        }

    }

}
