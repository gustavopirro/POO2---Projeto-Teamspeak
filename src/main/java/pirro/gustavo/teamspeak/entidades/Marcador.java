package pirro.gustavo.teamspeak.entidades;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Marcador {

	@Id
	private String ip;
	private String serverName;

	public Marcador(String serverName, String ip) {
		super();
		this.serverName = serverName;
		this.ip = ip;

	}

	public Marcador() {

	}

	public String getServer() {
		return serverName;
	}

	public String getIp() {
		return ip;
	}

	public void setServer(String server) {
		this.serverName = server;
	}

	public void setIp(String ip) {
		this.serverName = ip;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ip == null) ? 0 : ip.hashCode());
		result = prime * result + ((serverName == null) ? 0 : serverName.hashCode());
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
		Marcador other = (Marcador) obj;
		if (ip == null) {
			if (other.ip != null)
				return false;
		} else if (!ip.equals(other.ip))
			return false;
		if (serverName == null) {
			if (other.serverName != null)
				return false;
		} else if (!serverName.equals(other.serverName))
			return false;
		return true;
	}
}
