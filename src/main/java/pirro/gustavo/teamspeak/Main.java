package pirro.gustavo.teamspeak;

import javafx.application.Application;
public class Main {
	public static void main(String[] args) {

		Thread verificadorBD = new Thread(new VerificadorBD());
		verificadorBD.start();
		Application.launch(App.class);

	}
}
