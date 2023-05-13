package application;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ShoppingListGUI {
    private GridPane gridPane;
    private ListView<String> itemList;
    private TextField itemField;

    public ShoppingListGUI(ShoppingList shoppingList) {
        this.gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(8);
        gridPane.setHgap(10);

        itemList = new ListView<>();
        for (String item : shoppingList.getItemList()) {
            itemList.getItems().add(item);
        }
        GridPane.setConstraints(itemList, 0, 0, 1, 3);

        Label itemLabel = new Label("New Item:");
        GridPane.setConstraints(itemLabel, 1, 0);

        itemField = new TextField();
        GridPane.setConstraints(itemField, 2, 0);

        Button addButton = new Button("Add");
        GridPane.setConstraints(addButton, 2, 1);
        addButton.setOnAction(event -> {
            String item = itemField.getText();
            if (!item.isEmpty()) {
                shoppingList.addItem(item);
                itemList.getItems().add(item);
                itemField.setText("");
            }
        });

        Button removeButton = new Button("Remove");
        GridPane.setConstraints(removeButton, 2, 2);
        removeButton.setOnAction(event -> {
            String selectedItem = itemList.getSelectionModel().getSelectedItem();
            if (selectedItem != null) {
                shoppingList.removeItem(selectedItem);
                itemList.getItems().remove(selectedItem);
            }
        });

        HBox buttonBox = new HBox(10, addButton, removeButton);
        VBox addBox = new VBox(10, itemLabel, itemField, buttonBox);
        GridPane.setConstraints(addBox, 1, 1);

        gridPane.getChildren().addAll(itemList, addBox);
    }

    public GridPane getGridPane() {
        return gridPane;
    }
}
