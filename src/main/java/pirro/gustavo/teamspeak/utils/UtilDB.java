package pirro.gustavo.teamspeak.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import pirro.gustavo.teamspeak.db.MarcadorDAO;
import pirro.gustavo.teamspeak.db.UsuarioDAO;
import pirro.gustavo.teamspeak.entidades.Marcador;
import pirro.gustavo.teamspeak.entidades.Usuario;

public class UtilDB {

	private static EntityManagerFactory entityManagerFactory;
	private static EntityManager entityManager;

	private static EntityManagerFactory getEntityManagerFactory() {
		if (entityManagerFactory == null)
			entityManagerFactory = Persistence.createEntityManagerFactory("teste");
		return entityManagerFactory;
	}

	public static EntityManager getEntityManager() {
		if (entityManager == null)
			entityManager = getEntityManagerFactory().createEntityManager();
		return entityManager;
	}

	public static void closeConn() {
		if (entityManager != null)
			entityManager.close();
		if (entityManagerFactory != null)
			entityManagerFactory.close();
	}

	public static void initDB() {
		try {
			MarcadorDAO mDao = new MarcadorDAO();
			Marcador m = new Marcador("Server teste", "192.168.0.1");

			for (Usuario u : APIUtil.consumeAPI(APIUtil.consultAPI())) {
				new UsuarioDAO().adicionar(u);
			}

			mDao.adicionar(m);

		} catch (Exception e) {
			System.out.println("Usuário ja existe no banco de dados. -" + " Erro: " + e);
		}

	}

}