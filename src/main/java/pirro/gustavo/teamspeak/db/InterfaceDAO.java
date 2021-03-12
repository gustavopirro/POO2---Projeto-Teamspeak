package pirro.gustavo.teamspeak.db;

import java.util.List;

public interface InterfaceDAO<T> {

	public void adicionar(T referencia);

	public void remover(T referencia);

	public T get(Object pk);

	public List<T> todos();

}
