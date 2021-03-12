package pirro.gustavo.teamspeak.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import pirro.gustavo.teamspeak.utils.FXMLUtil;

public class MainController {

	@FXML
	private Button btnContact;

	@FXML
	private void openLoginScreen() throws IOException {
		Stage stage = (Stage) btnContact.getScene().getWindow();
		stage.setScene(FXMLUtil.loadScene("login"));
		stage.setTitle("Login");
		stage.setResizable(false);
	}

	@FXML
	private void openContactScreen() throws IOException {
		Stage stage = new Stage();
		stage.setScene(FXMLUtil.loadScene("contact"));
		stage.show();
		stage.setTitle("Contatos");
		stage.getScene();
	}

	@FXML
	private void openMarkerScreen() throws IOException {
		Stage stage = new Stage();
		stage.setScene(FXMLUtil.loadScene("marker"));
		stage.show();
		stage.setTitle("Marcadores");
		stage.getScene();

	}

}