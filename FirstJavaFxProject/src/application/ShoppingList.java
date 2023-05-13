package application;

import java.util.ArrayList;
import java.util.List;

public class ShoppingList {
    private int shoppingListId;
    private List<String> itemList;
    private List<Product> productList; // Added productList variable
    private Budget budget;
	private String name;

    public ShoppingList(int shoppingListId) {
        this.shoppingListId = shoppingListId;
        this.itemList = new ArrayList<>();
        this.productList = new ArrayList<>(); // Initialized productList
        this.budget = new Budget(0.0);
    }

    public void addProduct(Product product) {
        productList.add(product);
        budget.setRemainingBalance(budget.calculateRemainingBalance() - product.getPrice() * product.getQuantity());
    }

    public void removeProduct(Product product) {
        productList.remove(product);
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void addItem(String item) {
        itemList.add(item);
    }

    public void removeItem(String item) {
        itemList.remove(item);
    }

    public double calculateTotalCost() {
        // not relevant for the new implementation
        return 0.0;
    }

    public double calculateRemainingBalance() {
        // not relevant for the new implementation
        return 0.0;
    }

    public void createShoppingList() {
        // Code to create the shopping list goes here
    }
    public void setName(String name) {
        this.name = name;
    }
    public void updateShoppingList() {
        // Code to update the shopping list goes here
    }

    public void deleteShoppingList() {
        // Code to delete the shopping list goes here
    }

    public void setBudget(Budget budget2) {
        Budget budget1 = null;
		this.budget = budget1;
    }

    public Budget getBudget() {
        return budget;
    }

    public int getShoppingListId() {
        return shoppingListId;
    }

    public List<String> getItemList() {
        return itemList;
    }

    @Override
    public String toString() {
        return "ShoppingList{" +
                "shoppingListId=" + shoppingListId +
                ", itemList=" + itemList +
                ", productList=" + productList +
                ", budget=" + budget +
                '}';
    }
}




