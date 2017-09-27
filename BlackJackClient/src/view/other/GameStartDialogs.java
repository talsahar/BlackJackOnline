package view.other;

import java.util.Optional;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextInputDialog;

public class GameStartDialogs {

	private boolean commonDialog(String title, String header, String content) {
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle(title);
		alert.setHeaderText(header);
		alert.setContentText(content);
		Optional<ButtonType> result = alert.showAndWait();
		return (result.get() == ButtonType.OK) ? true : false;

	}

	public boolean inviteDialog(String username) {
		return commonDialog("Invite", "Game invitation to " + username,
				"Would you like to send a game invitation to " + username + "?");

	}

	public boolean incomingInvitationDialog(String username) {
		return commonDialog("Incoming Invitation", "Game invitation from " + username,
				"Would you like to start a game with " + username + "?");
	}

	public double enterBetDialog(double maxCash,String msg) {
		TextInputDialog dialog = new TextInputDialog("500");
		dialog.setTitle("Enter Bet (1-"+maxCash+")");
		dialog.setHeaderText(msg);
		dialog.setContentText("Please enter your bet:");
		dialog.getDialogPane().lookupButton(ButtonType.CANCEL).setDisable(true);

		// Traditional way to get the response value.
		String result = dialog.showAndWait().get();
		if(isLegalBet(result, maxCash))
			return Double.parseDouble(result);
		return enterBetDialog(maxCash,"illegal bet");
		
	}

	public boolean isLegalBet(String cash, double max) {
		try {
			Double d = Double.parseDouble(cash);
			return d <= max&&d>0;

		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public boolean rematchOffer() {
		
		return commonDialog("Rematch Offer", "Rematch Offer ",
				"Would you like to play again?");
		
		
	}

}
