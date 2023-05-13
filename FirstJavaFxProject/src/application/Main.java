package application;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.collections.FXCollections;

public class Main extends Application {
    private ShoppingList shoppingList;
    private Label remainingBalanceAmountLabel;

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        // Initialize the shopping list
        shoppingList = new ShoppingList(1);

        // Create the budget controls
        Label budgetLabel = new Label("Budget:");
        TextField budgetField = new TextField();
        budgetField.setPrefColumnCount(4);
        Button setBudgetButton = new Button("Set Budget");
        Label budgetAmountLabel = new Label(String.format("$%.2f", shoppingList.getBudget().getTotalBudget()));

        // Create the remaining balance controls
        Label remainingBalanceLabel = new Label("Remaining Balance:");
        remainingBalanceAmountLabel = new Label(String.format("$%.2f", shoppingList.calculateRemainingBalance()));

        // Create the product form controls
        TextField nameField = new TextField();
        Label nameLabel = new Label("Name:");
        Label priceLabel = new Label("Price:");
        TextField priceField = new TextField();
        Label quantityLabel = new Label("Quantity:");
        TextField quantityField = new TextField();
        Button addProductButton = new Button("Add Product");
        Button removeProductButton = new Button("Remove Product");
        HBox productForm = new HBox(5, nameLabel, nameField, priceLabel, priceField, quantityLabel, quantityField);

        // Create the product table view
        TableView<Product> productTableView = new TableView<>();

        TableColumn<Product, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("productName"));

        TableColumn<Product, Double> priceColumn = new TableColumn<>("Price");
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));

        TableColumn<Product, Integer> quantityColumn = new TableColumn<>("Quantity");
        quantityColumn.setCellValueFactory(new PropertyValueFactory<>("quantity"));

        productTableView.getColumns().addAll(nameColumn, priceColumn, quantityColumn);

        HBox bottomControls = new HBox(5, addProductButton, removeProductButton);
        VBox remainingBalanceControls = new VBox(5, remainingBalanceLabel, remainingBalanceAmountLabel);
        VBox budgetControls = new VBox(5, budgetLabel, budgetField, setBudgetButton, budgetAmountLabel);

        // Create the main UI layout
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
        root.setTop(productForm);
        root.setCenter(productTableView);
        root.setBottom(bottomControls);
        root.setRight(remainingBalanceControls);
        root.setLeft(budgetControls);



        // Add functionality to the budget controls
        setBudgetButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String budgetInput = budgetField.getText();
                if (!budgetInput.isEmpty()) {
                    double budget = Double.parseDouble(budgetInput);
                    shoppingList.getBudget().setTotalBudget(budget);
                    budgetAmountLabel.setText(String.format("$%.2f", shoppingList.getBudget().getTotalBudget()));
                    double remainingBalance = shoppingList.calculateRemainingBalance();
                    remainingBalanceAmountLabel.setText(String.format("$%.2f", remainingBalance));

                    // Subtract the total price of products from the budget
                    if (remainingBalance < 0) {
                        double totalProductPrice = shoppingList.calculateTotalCost();
                        shoppingList.getBudget().setTotalBudget(budget - totalProductPrice);
                        budgetAmountLabel.setText(String.format("$%.2f", shoppingList.getBudget().getTotalBudget()));
                        remainingBalanceAmountLabel.setText(String.format("$%.2f", shoppingList.calculateRemainingBalance()));
                    }
                }
            }
        });

        // Add functionality to the add product button
        addProductButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String name = nameField.getText();
                String priceInput = priceField.getText();
                String quantityInput = quantityField.getText();
                if (!name.isEmpty() && !priceInput.isEmpty() && !quantityInput.isEmpty()) {
                    double price = Double.parseDouble(priceInput);
                    int quantity = Integer.parseInt(quantityInput);
                    Product product = new Product(name, price, quantity);
                    shoppingList.addProduct(product);
                    productTableView.setItems(FXCollections.observableArrayList(shoppingList.getProductList()));
                    budgetAmountLabel.setText(String.format("$%.2f", shoppingList.getBudget().getTotalBudget()));
                    remainingBalanceAmountLabel.setText(String.format("$%.2f", shoppingList.calculateRemainingBalance()));
                    nameField.clear();
                    priceField.clear();
                    quantityField.clear();
                }
            }
        });

        // Add functionality to the remove product button
        removeProductButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Product selectedProduct = productTableView.getSelectionModel().getSelectedItem();
                if (selectedProduct != null) {
                    shoppingList.removeProduct(selectedProduct);
                    productTableView.setItems(FXCollections.observableArrayList(shoppingList.getProductList()));
                    remainingBalanceAmountLabel.setText(String.format("$%.2f", shoppingList.calculateRemainingBalance()));
                }
            }
        });

        // Set up the primary stage
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Shopping List");
        primaryStage.show();
    }}