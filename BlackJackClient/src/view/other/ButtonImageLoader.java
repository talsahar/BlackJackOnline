package view.other;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ButtonImageLoader {

	ImageCreator creator;

	public ButtonImageLoader() {
		creator = new ImageCreator();
	}

	private Image getImage(String path) {
		return creator.create(path);
	}

	public ButtonImageLoader loadRegisterButton(ImageView button) {
		button.setImage(getImage("/signupButton.png"));
		return this;
	}

	public ButtonImageLoader loadLoginButton(ImageView button) {
		button.setImage(getImage("/loginButton.png"));
		return this;
	}

	public ButtonImageLoader loadStandButton(ImageView button) {
		button.setImage(getImage("/standButton.png"));
		return this;

	}

	public ButtonImageLoader loadHitButton(ImageView button) {
		button.setImage(getImage("/hitButton.png"));
		return this;

	}

	public ButtonImageLoader loadExitButton(ImageView button) {
		button.setImage(getImage("/exitButton.png"));
		return this;

	}

	public ButtonImageLoader loadPlayAgainButton(ImageView button) {
		button.setImage(getImage("/playAgainButton.png"));
		return this;

	}

	public ButtonImageLoader loadLobbyButton(ImageView button) {
		button.setImage(getImage("/lobbyButton.png"));
		return this;

	}

}
