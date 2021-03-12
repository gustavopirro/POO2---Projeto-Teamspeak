package pirro.gustavo.teamspeak.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import pirro.gustavo.teamspeak.db.UsuarioDAO;
import pirro.gustavo.teamspeak.entidades.Usuario;
import pirro.gustavo.teamspeak.utils.AlertUtil;

public class ContactController {

	public static String staticLogin;
	private String login;

	@FXML
	private ListView<String> contactListview;

	@FXML
	private TextField txtContact;

	public ContactController() {
		if (staticLogin == null)
			return;
		this.login = staticLogin;
	}

	@FXML
	public void initialize() throws IOException {
		showUsers();
	}

	@FXML
	private void registerContact() throws IOException {

		String nomeContato = txtContact.getText();

		if (nomeContato.isBlank()) {
			Alert alert = AlertUtil.error("Erro!", "Erro!", "Digite o nome do contato", null);
			alert.showAndWait();
			return;
		}

		UsuarioDAO uDAO = new UsuarioDAO();
		Usuario contato = new Usuario();

		contato = uDAO.get(nomeContato);

		if (contato == null) {
			Alert alert = AlertUtil.error("Erro!", "Erro!", "Usuário não existe!", null);
			alert.showAndWait();
			return;
		}
		
		if(login == null) {
			Alert alert = AlertUtil.error("Erro!", "Erro!", "É necessario fazer o login para registrar contatos!", null);
			alert.showAndWait();
			return;
		}

		Usuario u = uDAO.get(login);
		u.getContatos().add(contato);
		uDAO.adicionar(u);

		showUsers();

	}

	@FXML
	public void removeContact() throws IOException {
		String selectedItem = contactListview.getSelectionModel().getSelectedItem();

		UsuarioDAO uDAO = new UsuarioDAO();

		Usuario usuario = uDAO.get(login);

		for (int indiceContato = 0; indiceContato < usuario.getContatos().size(); indiceContato++) {
			if (usuario.getContatos().get(indiceContato).getLogin().contentEquals(selectedItem)) {
				usuario.getContatos().remove(indiceContato);
			}
		}
		uDAO.adicionar(usuario);
		System.out.println("teste");
		showUsers();
	}

	@FXML
	public void showUsers() throws IOException {
		if (login == null) {
			return;
		}

		UsuarioDAO uDao = new UsuarioDAO();
		List<Usuario> userList = uDao.get(login).getContatos();
		List<String> userStringList = new ArrayList<>();

		for (Usuario u : userList)
			userStringList.add(u.getLogin());

		contactListview.setItems(FXCollections.observableArrayList(userStringList));

	}

}