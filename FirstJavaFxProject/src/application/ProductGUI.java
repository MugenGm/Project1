package application;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class ProductGUI {
    private Product product;
    private TextField idTextField;
    private TextField nameTextField;
    private TextField categoryTextField;
    private TextField priceTextField;
    private TextField quantityTextField;
    private TextField brandTextField;

    public ProductGUI(Product product) {
        this.product = product;
        
        // Create the GUI elements
        Label idLabel = new Label("Product ID:");
        idTextField = new TextField(Integer.toString(product.getProductId()));
        idTextField.setEditable(false);

        Label nameLabel = new Label("Product Name:");
        nameTextField = new TextField(product.getProductName());
        nameTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            product.setProductName(newValue);
        });

        Label categoryLabel = new Label("Category:");
        categoryTextField = new TextField(product.getCategory());

        Label priceLabel = new Label("Price:");
        priceTextField = new TextField(Double.toString(product.getPrice()));

        Label quantityLabel = new Label("Quantity:");
        quantityTextField = new TextField(Integer.toString(product.getQuantity()));

        Label brandLabel = new Label("Brand:");
        brandTextField = new TextField(product.getBrand());

        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> save());

        // Create the grid pane and add the GUI elements to it
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.add(idLabel, 0, 0);
        gridPane.add(idTextField, 1, 0);
        gridPane.add(nameLabel, 0, 1);
        gridPane.add(nameTextField, 1, 1);
        gridPane.add(categoryLabel, 0, 2);
        gridPane.add(categoryTextField, 1, 2);
        gridPane.add(priceLabel, 0, 3);
        gridPane.add(priceTextField, 1, 3);
        gridPane.add(quantityLabel, 0, 4);
        gridPane.add(quantityTextField, 1, 4);
        gridPane.add(brandLabel, 0, 5);
        gridPane.add(brandTextField, 1, 5);
        gridPane.add(saveButton, 1, 6);

        // Set the spacing and background color of the grid pane
        gridPane.setHgap(10);
        gridPane.setVgap(10);
        gridPane.setStyle("-fx-background-color: #E0FFFF;");
    }

    public GridPane getGridPane() {
        return getGridPane();
    }

    private void save() {
        // Update the product object with the new values from the text fields
        product.setProductName(nameTextField.getText());
        product.setCategory(categoryTextField.getText());
        product.setPrice(Double.parseDouble(priceTextField.getText()));
        product.setQuantity(Integer.parseInt(quantityTextField.getText()));
        product.setBrand(brandTextField.getText());
    }
}

