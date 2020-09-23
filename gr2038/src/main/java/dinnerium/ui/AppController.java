package dinnerium.ui;

import dinnerium.core.Ingredient;
import dinnerium.core.IngredientContainer;
import dinnerium.core.Quantity;
import dinnerium.json.HandlePersistency;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

public class AppController {

    // contains our current stock of ingredients/groceries
    private IngredientContainer ingredientContainer;

    @FXML
    TextField nameInput;
    @FXML
    TextField amountInput;

    @FXML
    Text errorOutput;
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

    // need to handle throws from init!!
    private void setup() {
        // sets up our tableview with correct rows and columns
        unitComboBox.getItems().setAll(Quantity.units);
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        itemColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        // loading our stock of ingredients (populating (?) our ingredientContainer)
        try {
            this.ingredientContainer = HandlePersistency.loadDataFromFile();
        } catch (IOException e) {
            this.ingredientContainer = new IngredientContainer();
        }
        updateTableView();
    }


    @FXML
    private void handleAddIngredient() {

        try {
            Quantity quantity =
                    new Quantity(Double.valueOf(amountInput.getText()),
                                 unitComboBox.getSelectionModel().getSelectedItem());
            Ingredient ingredient = new Ingredient(quantity, nameInput.getText());
            this.ingredientContainer.addIngredient(ingredient);
        } catch (IllegalArgumentException e) {
            // write error output in app
            errorOutput.setVisible(true);
            errorOutput.setText(e.getMessage());
            CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS).execute(() -> {
                errorOutput.setVisible(false);
            });
            System.err.println(e.getMessage());
            System.out.println(e.getMessage());
        }
        updateTableView();
        // writes our new ingredient to the file
        try {
            HandlePersistency.writeJsonToFile(ingredientContainer);
        } catch (Exception e) {
            throw new IllegalArgumentException("Could not write to file");
        }

    }

    // updates our tableView with an observable list
    private void updateTableView() {
        ObservableList<Ingredient> observableList =
                FXCollections.observableArrayList((this.ingredientContainer.getIngredients()));
        ingredientTableView.setItems(observableList);
    }

}
