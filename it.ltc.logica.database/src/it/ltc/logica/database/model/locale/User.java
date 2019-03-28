package it.ltc.logica.database.model.locale;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="utente")
public class User implements Serializable, Comparable<User> {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="name", unique=true, nullable=false)
	private String username;
	private String password;
	
	@Column(name="ultimo_login")
	private Date ultimoLogin;
	
	
	public String getTableDefinition() {
		String tableDefinition = "CREATE TABLE IF NOT EXISTS utente ("
                + "	name text PRIMARY KEY, "
                + "	password text NOT NULL,"
                + " ultimo_login datetime NOT NULL );";
        return tableDefinition;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		User other = (User) obj;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return username;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getUltimoLogin() {
		return ultimoLogin;
	}

	public void setUltimoLogin(Date ultimoLogin) {
		this.ultimoLogin = ultimoLogin;
	}

	@Override
	public int compareTo(User anotherUser) {
		Date ul = anotherUser.getUltimoLogin();
		int compare = 0;
		if (ultimoLogin != null && ul != null) {
			compare = -1 * ultimoLogin.compareTo(ul);
		}
		return compare;
	}

}
