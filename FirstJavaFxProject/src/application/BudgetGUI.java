package application;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class BudgetGUI {
    private GridPane gridPane;

    public BudgetGUI(Budget budget) {
        this.gridPane = new GridPane();
        gridPane.setPadding(new Insets(10, 10, 10, 10));
        gridPane.setVgap(8);
        gridPane.setHgap(10);

        Label totalBudgetLabel = new Label("Total Budget:");
        GridPane.setConstraints(totalBudgetLabel, 0, 0);

        TextField totalBudgetField = new TextField(String.valueOf(budget.getTotalBudget()));
        GridPane.setConstraints(totalBudgetField, 1, 0);

        Label amountSpentLabel = new Label("Amount Spent:");
        GridPane.setConstraints(amountSpentLabel, 0, 1);

        TextField amountSpentField = new TextField(String.valueOf(budget.getAmountSpent()));
        GridPane.setConstraints(amountSpentField, 1, 1);

        Button updateButton = new Button("Update");
        GridPane.setConstraints(updateButton, 1, 2);
        updateButton.setOnAction(event -> {
            budget.setTotalBudget(Double.parseDouble(totalBudgetField.getText()));
            budget.setAmountSpent(Double.parseDouble(amountSpentField.getText()));
            totalBudgetField.setText(String.valueOf(budget.getTotalBudget()));
            amountSpentField.setText(String.valueOf(budget.getAmountSpent()));
        });

        gridPane.getChildren().addAll(totalBudgetLabel, totalBudgetField, amountSpentLabel, amountSpentField, updateButton);
    }

    public GridPane getGridPane() {
        return gridPane;
    }
}