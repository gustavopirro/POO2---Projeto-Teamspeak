package pirro.gustavo.teamspeak.controllers;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import pirro.gustavo.teamspeak.db.UsuarioDAO;
import pirro.gustavo.teamspeak.entidades.Usuario;
import pirro.gustavo.teamspeak.utils.AlertUtil;
import pirro.gustavo.teamspeak.utils.FXMLUtil;

public class LoginController {

	@FXML
	private TextField txtLogin;

	@FXML
	private PasswordField passwordLogin;

	public LoginController() {

	}

	@FXML
	private void openMainScreen() throws IOException {
		Stage stage = (Stage) txtLogin.getScene().getWindow();
		stage.setScene(FXMLUtil.loadScene("main"));
    	stage.setResizable(true);
	}

	@FXML
	public void openRegisterScreen() throws IOException {
		Stage stage = (Stage) txtLogin.getScene().getWindow();
		stage.setScene(FXMLUtil.loadScene("register"));
		stage.setTitle("Cadastro");

	}

	@FXML
	private void logUser() throws IOException {
		String login = txtLogin.getText();
		String password = passwordLogin.getText();

		if (login.isBlank()) {
			Alert alert = AlertUtil.error("Erro!", "Erro!", "Digite o login", null);
			alert.showAndWait();
			return;
		}
		if (password.isBlank()) {
			Alert alert = AlertUtil.error("Erro!", "Erro!", "Digite a senha!", null);
			alert.showAndWait();
			return;
		}
		
		Usuario u = new UsuarioDAO().get(login);
		
		if (u == null) {
			Alert alert = AlertUtil.error("Erro!", "Erro!", "Login ou senha inválido(s)!", null);
			alert.showAndWait();
			return;
		}
		if (!u.getSenha().contentEquals(password)) {
			Alert alert = AlertUtil.error("Erro!", "Erro!", "Login ou senha inválido(s)!", null);
			alert.showAndWait();
			return;
		}

		ContactController.staticLogin = login;
		openMainScreen();
		
	
		
	}

}
