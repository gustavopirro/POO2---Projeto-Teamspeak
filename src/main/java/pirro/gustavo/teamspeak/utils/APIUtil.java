package pirro.gustavo.teamspeak.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.control.Alert;
import pirro.gustavo.teamspeak.entidades.Usuario;

public class APIUtil {

	public static List<String> consultAPI() {
		List<String> result = new ArrayList<>();
		try {
			URL url = new URL("http://lucasbueno.com.br/steam.json");
			URLConnection uc = url.openConnection();
			InputStreamReader input = new InputStreamReader(uc.getInputStream());
			BufferedReader in = new BufferedReader(input);
			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				result.add(inputLine);
			}

			in.close();
		} catch (Exception e) {
			Alert alert = AlertUtil.error("Erro", "Erro ao tentar consumir API", "Exception:", null);
			alert.showAndWait();

		}
		return result;
	}

	public static List<Usuario> consumeAPI(List<String> users) {
		List<Usuario> result = new ArrayList<Usuario>();
		for (int lineIndex = 0; lineIndex < users.size(); lineIndex++) {
			String line = users.get(lineIndex);
			if (line.contains("username")) {
				String username = processJSONLine(line);
				lineIndex++;
				line = users.get(lineIndex);
				String password = processJSONLine(line);
				Usuario user = new Usuario(username, password);
				result.add(user);
			}
		}
		return result;
	}

	private static String processJSONLine(String line) {
		String[] dividedLine = line.split(":");
		String input = dividedLine[1];
		input = input.replace(',', ' ');
		input = input.replace("\"", " ");
		input = input.trim();
		return input;
	}
}
