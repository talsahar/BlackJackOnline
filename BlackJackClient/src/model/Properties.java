package model;

import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.text.Text;
import javafx.stage.Popup;

public class Properties {

	private SimpleStringProperty myCash;
	private SimpleStringProperty rivalCash;
	private SimpleStringProperty currentBet;
	private SimpleStringProperty myName;
	private SimpleStringProperty rivalName;
	private SimpleStringProperty bigMessage;
	private SimpleStringProperty label;

	public Properties() {
		myCash = new SimpleStringProperty();
		rivalCash = new SimpleStringProperty();
		currentBet = new SimpleStringProperty();
		myName = new SimpleStringProperty();
		rivalName = new SimpleStringProperty();
		bigMessage = new SimpleStringProperty();
		label=new SimpleStringProperty();

	}

	public SimpleStringProperty getBigMessage() {
		return bigMessage;
	}

	public void setBigMessage(String bigMessage) {
		this.bigMessage.set(bigMessage);
	}

	public SimpleStringProperty getMyCash() {
		return myCash;
	}

	public void setMyCash(double myCash) {
		this.myCash.set("$" + myCash);
	}

	public void updateMyCash(double addIt) {
		updateMoneyField(myCash, addIt);
	}

	public void setRivalCash(double rivalCash) {
		this.rivalCash.setValue("$" + rivalCash);
	}

	public void updateRivalCash(double addIt) {
		updateMoneyField(rivalCash, addIt);

	}

	private void updateMoneyField(SimpleStringProperty prop, double amount) {
		double before = getDouble(prop);
		before += amount;
		prop.set("$" + before);

	}

	public void setCurrentBet(double currentBet) {
		this.currentBet.set("$" + currentBet);
	}

	public void updateCurrentBet(double addIt) {
		updateMoneyField(currentBet, addIt);

	}

	public SimpleStringProperty getRivalCash() {
		return rivalCash;
	}

	public SimpleStringProperty getCurrentBet() {
		return currentBet;
	}

	public SimpleStringProperty getMyName() {
		return myName;
	}

	public void setMyName(String myName) {
		this.myName.set(myName);
	}

	public SimpleStringProperty getRivalName() {
		return rivalName;
	}

	public void setRivalName(String rivalName) {
		this.rivalName.set(rivalName);
	}

	public void clear() {
		rivalCash.set("");
		currentBet.set("");
		rivalName.set("");
		Platform.runLater(()->label.set(""));
	}

	public double getMinCash() {
		return getDouble(myCash) <= getDouble(rivalCash) ? getDouble(myCash) : getDouble(rivalCash);

	}

	public double getDouble(SimpleStringProperty prop) {
		return Double.parseDouble((prop.get().substring(1)));
	}

	public SimpleStringProperty getLabel() {
		return label;
	}

	public void setLabel(String string) {
Platform.runLater(()->label.set(string));		
	}

}
