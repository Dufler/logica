package it.ltc.logica.database.model.locale;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="listino_simulazione_voce_fissa")
public class ListinoSimulazioneVoceFissa implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id_voce", unique=true, nullable=false)
	private int id;
	
	@Column(columnDefinition="REAL", nullable=false , precision=10, scale=3)
	private double valore;
	
	public String getTableDefinition() {
		String tableDefinition = "CREATE TABLE IF NOT EXISTS listino_simulazione_voce_fissa ("
                + "	id_voce INTEGER NOT NULL REFERENCES listino_simulazione_voce(id) ON UPDATE CASCADE ON DELETE CASCADE, "
                + " valore REAL NOT NULL );";
        return tableDefinition;
	}

	@Override
	public String toString() {
		return "\u20AC " + valore;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		ListinoSimulazioneVoceFissa other = (ListinoSimulazioneVoceFissa) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public double getValore() {
		return valore;
	}

	public void setValore(double valore) {
		this.valore = valore;
	}

}
