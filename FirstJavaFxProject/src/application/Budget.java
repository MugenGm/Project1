package application;

public class Budget {
private double totalBudget;
private double amountSpent; 
public Budget(double totalBudget) {
    this.totalBudget = totalBudget;
    this.amountSpent = 0.0;
}

public double calculateRemainingBalance() {
    return totalBudget - amountSpent;
}

public void setTotalBudget(double budget) {
    this.totalBudget = budget;
}

public double getTotalBudget() {
    return totalBudget;
}

public void setAmountSpent(double amount) {
    this.amountSpent = amount;
}

public double getAmountSpent() {
    return amountSpent;
}

@Override
public String toString() {
    return "Budget{" +
            "totalBudget=" + totalBudget +
            ", amountSpent=" + amountSpent +
            '}';
}

public void setRemainingBalance(double d) {
	// TODO Auto-generated method stub
	
}
}