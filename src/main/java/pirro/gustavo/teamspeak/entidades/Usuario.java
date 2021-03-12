package pirro.gustavo.teamspeak.entidades;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class Usuario {
	@Id
	private String login;
	private String senha;

	@ManyToMany
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<Usuario> contatos;

	public Usuario() {
		this.contatos = new ArrayList<>();
	}

	public Usuario(String login, String senha) {
		super();
		this.login = login;
		this.senha = senha;
		this.contatos = new ArrayList<>();
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<Usuario> getContatos() {
		return contatos;
	}

	public void setContatos(List<Usuario> contatos) {
		this.contatos = contatos;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((login == null) ? 0 : login.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (login == null) {
			if (other.login != null)
				return false;
		} else if (!login.equals(other.login))
			return false;
		return true;
	}

}
