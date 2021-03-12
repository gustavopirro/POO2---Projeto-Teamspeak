package pirro.gustavo.teamspeak.db;

import java.util.List;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.RollbackException;

import pirro.gustavo.teamspeak.entidades.*;
import pirro.gustavo.teamspeak.utils.UtilDB;

public class UsuarioDAO implements InterfaceDAO<Usuario> {

	@Override
	public void adicionar(Usuario t) {
		EntityManager em = UtilDB.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(t);
			em.getTransaction().commit();
		} catch (EntityExistsException | RollbackException e) {
			em.getTransaction().rollback();
			Usuario original = get(t.getLogin());
			em.getTransaction().begin();
			original.setSenha(t.getSenha());
			original.getContatos().clear();
			for (Usuario u : t.getContatos())
				original.getContatos().add(u);
			em.getTransaction().commit();

		}
	}

	@Override
	public void remover(Usuario t) {
		EntityManager em = UtilDB.getEntityManager();
		em.getTransaction().begin();
		em.remove(t);
		em.getTransaction().commit();
	}

	@Override
	public Usuario get(Object primaryKey) {
		return UtilDB.getEntityManager().find(Usuario.class, primaryKey);
	}

	@Override
	public List<Usuario> todos() {
		return UtilDB.getEntityManager().createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
	}
}
