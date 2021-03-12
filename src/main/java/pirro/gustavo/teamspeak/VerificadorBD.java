package pirro.gustavo.teamspeak;

import pirro.gustavo.teamspeak.db.UsersInFiles;

public class VerificadorBD implements Runnable {
	@Override
	public void run() {
		try {
			while (true) {
				System.out.println("Verificando por novos usuários no arquivo json");
				new UsersInFiles().checkFiles();

				Thread.sleep(4200);
			}
		} catch (InterruptedException e) {
			System.out.println(e);
		}
	}
}
