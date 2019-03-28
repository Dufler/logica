package it.ltc.logica.database.model.locale;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="resource_to_delete")
public class ResourceToDelete implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(unique=true, nullable=false)
	private String name;
	
	public String getTableDefinition() {
		String tableDefinition = "CREATE TABLE IF NOT EXISTS resource_to_delete ("
                + "	name text PRIMARY KEY );";
        return tableDefinition;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		ResourceToDelete other = (ResourceToDelete) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
