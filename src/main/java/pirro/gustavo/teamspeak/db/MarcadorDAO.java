package pirro.gustavo.teamspeak.db;

import java.util.List;
import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.RollbackException;

import javafx.scene.control.Alert;
import pirro.gustavo.teamspeak.entidades.*;
import pirro.gustavo.teamspeak.utils.AlertUtil;
import pirro.gustavo.teamspeak.utils.UtilDB;

public class MarcadorDAO implements InterfaceDAO<Marcador> {

	public void adicionar(Marcador t) {
		EntityManager em = UtilDB.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(t);
			em.getTransaction().commit();
		} catch (EntityExistsException | RollbackException e) {
			em.getTransaction().rollback();
			Marcador original = get(t.getIp());
			em.getTransaction().begin();
			original.setIp(t.getServer());
			em.getTransaction().commit();
			Alert alert = AlertUtil.error("Erro!", "Ip já cadastrado", "Não foi possivel realizar o cadastro!", null);
			alert.showAndWait();

		}
	}

	public void remover(Marcador t) {
		EntityManager em = UtilDB.getEntityManager();
		em.getTransaction().begin();
		em.remove(t);
		em.getTransaction().commit();
	}

	@Override
	public Marcador get(Object primaryKey) {
		return UtilDB.getEntityManager().find(Marcador.class, primaryKey);
	}

	@Override
	public List<Marcador> todos() {
		return UtilDB.getEntityManager().createQuery("SELECT u FROM Marcador u", Marcador.class).getResultList();
	}

}
