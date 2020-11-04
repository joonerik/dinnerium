package dinnerium.ui;

import dinnerium.core.Ingredient;
import dinnerium.core.Quantity;
import dinnerium.core.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;

public class FridgeController {

    private User user;
    private DinneriumAccess dinneriumAccess;
    private NavbarController navbarController;
    private Pane msgPane;

    @FXML
    Pane fridgePane;
    @FXML
    TextField nameInput;
    @FXML
    TextField amountInput;
    @FXML
    ComboBox<String> unitComboBox;
    @FXML
    TableView<Ingredient> ingredientTableView;
    @FXML
    TableColumn<Ingredient, Quantity> quantityColumn;
    @FXML
    TableColumn<Ingredient, String> itemColumn;
    @FXML
    Button addButton;

    @FXML
    void initialize() {
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        itemColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
    }

    @FXML
    void handleAddIngredient() {
        try {
            String amountText = amountInput.getText();
            String unit = unitComboBox.getSelectionModel().getSelectedItem();
            String name = nameInput.getText();
            Ingredient i = new Ingredient(new Quantity(Double.parseDouble(amountText), unit), name);

            navbarController.setUser(dinneriumAccess.postIngredient(user.getUsername(), i));
            updateTableView();
            FeedbackHandler.showMessage(msgPane, "New ingredient added", 'M');
        } catch (NumberFormatException e){
            FeedbackHandler.showMessage(msgPane, "Quantityfield is empty", 'E');
        } catch (IllegalArgumentException e) {
            FeedbackHandler.showMessage(msgPane, e.getMessage(), 'E');
        }
    }

    void updateTableView() {
        ObservableList<Ingredient> observableList =
            FXCollections.observableArrayList(user.getIngredientContainer().getContainer());
        ingredientTableView.setItems(observableList);
    }

    void setScene(Boolean thisScene) {
        if (unitComboBox.getItems().size() == 0) {
            try {
                unitComboBox.getItems().setAll(dinneriumAccess.getUnits());
            } catch (IllegalArgumentException e) {
                FeedbackHandler.showMessage(msgPane, e.getMessage(), FeedbackHandler.ERROR);
            }
        }
        fridgePane.setVisible(thisScene);
    }

    void setUser(User user) {
        this.user = user;
    }

    void setNavbarController(NavbarController navbarController) {
        this.navbarController = navbarController;
    }

    void setMsgPane(Pane msgPane) {
        this.msgPane = msgPane;
    }

    void setDinneriumAccess(DinneriumAccess dinneriumAccess) {
        this.dinneriumAccess = dinneriumAccess;
    }
}
