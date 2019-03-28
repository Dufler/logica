package it.ltc.logica.database.model.centrale;

import java.io.Serializable;

public class ProdottoPK implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	protected int id;
	protected int commessa;
	
	public ProdottoPK() {}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCommessa() {
		return commessa;
	}

	public void setCommessa(int commessa) {
		this.commessa = commessa;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + commessa;
		result = prime * result + id;
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
		ProdottoPK other = (ProdottoPK) obj;
		if (commessa != other.commessa)
			return false;
		if (id != other.id)
			return false;
		return true;
	}

}
