package pirro.gustavo.teamspeak.controllers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import pirro.gustavo.teamspeak.db.MarcadorDAO;
import pirro.gustavo.teamspeak.entidades.Marcador;
import pirro.gustavo.teamspeak.utils.AlertUtil;

public class MarkerController {

	@FXML
	private TextField txtMarkerName;

	@FXML
	private TextField txtMarkerIp;

	@FXML
	private ListView<String> markerListview;

	@FXML
	private void registerMarker() throws IOException {

		String serverName = txtMarkerName.getText();
		String ip = txtMarkerIp.getText();

		if (serverName.isBlank()) {
			Alert alert = AlertUtil.error("Erro!", "Erro!", "Digite o nome do servidor!", null);
			alert.showAndWait();
			return;
		}
		if (ip.isBlank()) {
			Alert alert = AlertUtil.error("Erro!", "Erro!", "Digite o endereço do servidor!", null);
			alert.showAndWait();
			return;
		}
		
		MarcadorDAO marcador = new MarcadorDAO();
		if(marcador.get(ip) != null) {
			Alert alert = AlertUtil.error("Erro!", "Servidor já cadastrado!", "Insira um servidor com endereço unico!", null);
			alert.showAndWait();
			return;
		}
		
		marcador.adicionar(new Marcador(serverName, ip));
		updateMarkers();

	}

	@FXML
	private void removeMarker() throws IOException {

		String selectedItem = markerListview.getSelectionModel().getSelectedItem();
		String[] selectedString = selectedItem.split("-");
		selectedString[1] = selectedString[1].trim();
		System.out.println(selectedString[1]);
		MarcadorDAO m = new MarcadorDAO();
		Marcador toRemoveUser = m.get(selectedString[1]);
		System.out.println(toRemoveUser);
		m.remover(toRemoveUser);

		updateMarkers();
	}

	@FXML
	public void initialize() throws IOException {
		updateMarkers();
	}

	@FXML
	public void updateMarkers() throws IOException {

		MarcadorDAO mDao = new MarcadorDAO();
		List<Marcador> markerList = mDao.todos();
		List<String> markerStringList = new ArrayList<>();
		for (Marcador m : markerList)
			markerStringList.add(m.getServer() + " - " + m.getIp());

		markerListview.setItems(FXCollections.observableArrayList(markerStringList));

	}

}