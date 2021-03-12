package pirro.gustavo.teamspeak.db;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pirro.gustavo.teamspeak.entidades.Usuario;
import pirro.gustavo.teamspeak.utils.APIUtil;

public class UsersInFiles {

	public void checkFiles() {
		String fileLocation = "./local-users.json";
		List<String> fileLines = new ArrayList<>();
		try {
			File file = new File(fileLocation);
			if (file.exists()) {
				Scanner scanner = new Scanner(file);
				while (scanner.hasNextLine())
					fileLines.add(scanner.nextLine());
				scanner.close();
			} else {
				System.err.println("File \"" + fileLocation + "\" is missing.");
			}
		} catch (FileNotFoundException e) {
			System.err.println("Error while opening file \"" + fileLocation + "\".");
		}

		List<Usuario> userList = APIUtil.consumeAPI(fileLines);

		for (Usuario u : userList) {

			UsuarioDAO uDAO = new UsuarioDAO();
			String login = u.getLogin();

			Boolean existsInDB = checkIfExistsInDB(u);

			if (!existsInDB) {
				uDAO.adicionar(u);
				System.out.println("Novo usuario adicionado no banco de usuários: " + login);
			} else {
				Boolean passwordIsTheSame = checkIfPasswordIsTheSame(u, uDAO.get(login));
				if (!passwordIsTheSame) {
					uDAO.adicionar(u);
					System.out.println("Usuario atualizado no banco de usuários: " + login);
				}
			}
		}
	}

	public Boolean checkIfExistsInDB(Usuario u) {

		String login = u.getLogin();
		UsuarioDAO uDAO = new UsuarioDAO();

		if (uDAO.get(login) == null)
			return false;

		return true;
	}

	public Boolean checkIfPasswordIsTheSame(Usuario usuarioNovo, Usuario usuarioAntigo) {

		Boolean isPasswordTheSame = usuarioNovo.getSenha().equals(usuarioAntigo.getSenha());
		return isPasswordTheSame;

	}
}
