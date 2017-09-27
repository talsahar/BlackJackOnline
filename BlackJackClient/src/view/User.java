package view;

import javafx.beans.binding.DoubleExpression;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;

public class User {

	private String name;
	private double amount;

	public User(String name, double d) {
		this.name =name;
		this.amount = d;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public boolean equals(Object obj) {
	User other=(User) obj;
	return name.equals(other.name);
	}

public boolean collusion(User other) {
	return name.equals(other.name)&&amount!=other.amount;
}
}
