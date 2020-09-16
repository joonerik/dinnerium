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
    TableColumn<Ingredient, Quantity> quantityColumn;
    @FXML
    TableColumn<Ingredient, String> itemColumn;

    @FXML
    void initialize() throws Exception {
        setup();
    }

    private void setup() {
        unitComboBox.getItems().setAll(Quantity.units);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        itemColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        try {
            IngredientContainer ingredientContainer = HandlePersistency.loadDataFromFile();
            this.ingredientContainer = ingredientContainer;
        } catch (IOException e) {
            this.ingredientContainer = new IngredientContainer();
        }
        updateTableView();
    }

    @FXML
    private void handleAddIngredient() {
        Quantity quantity = new Quantity(Double.valueOf(amountInput.getText()), unitComboBox.getSelectionModel().getSelectedItem());
        Ingredient ingredient = new Ingredient(quantity, nameInput.getText());
        this.ingredientContainer.addIngredient(ingredient);
        updateTableView();
        try {
            HandlePersistency.writeJsonToFile(ingredientContainer);
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not write to file");
        }

    }

    private void updateTableView() {
        ObservableList<Ingredient> observableList =
                FXCollections.observableArrayList((this.ingredientContainer.getIngredients()));
        ingredientTableView.setItems(observableList);
    }

}
