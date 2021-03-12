package pirro.gustavo.teamspeak.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pirro.gustavo.teamspeak.db.UsuarioDAO;
import pirro.gustavo.teamspeak.entidades.Usuario;
import pirro.gustavo.teamspeak.utils.AlertUtil;
import pirro.gustavo.teamspeak.utils.FXMLUtil;

public class RegisterController {
	@FXML
	private Button btnRegister;

	@FXML
	private TextField txtLogin;

	@FXML
	private PasswordField txtPassword;

	@FXML
	private void openLoginScreen() throws IOException {
		Stage stage = (Stage) btnRegister.getScene().getWindow();
		stage.setScene(FXMLUtil.loadScene("Login"));
		stage.setTitle("Login");
	}

	@FXML
	private void registerNewUser() {
		String login = txtLogin.getText();
		String password = txtPassword.getText();

		if (login.isBlank()) {
			Alert alert = AlertUtil.error("Erro!", "Erro!", "Digite a login!", null);
			alert.showAndWait();
			return;
		}
		if (password.isBlank()) {
			Alert alert = AlertUtil.error("Erro!", "Erro!", "Digite a senha!", null);
			alert.showAndWait();
			return;
		}

		UsuarioDAO usuario = new UsuarioDAO();
		if (usuario.get(login) != null) {
			Alert alert = AlertUtil.error("Erro!", "Usuário já cadastrado!", "Login já em uso.", null);
			alert.showAndWait();
			return;
		}
		usuario.adicionar(new Usuario(login, password));

		Alert alert = AlertUtil.info("Sucesso!", "Usuário cadastrado com sucesso!", "");
		alert.showAndWait();
		Stage stage = (Stage) btnRegister.getScene().getWindow();
		stage.setScene(FXMLUtil.loadScene("Login"));
		stage.setTitle("Login");

	}

}
