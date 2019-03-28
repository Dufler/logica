package it.ltc.logica.database.model.locale;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="listino_simulazione_voce_proporzionale")
public class ListinoSimulazioneVoceProporzionale  implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id_voce", unique=true, nullable=false)
	private int id;
	@Column(columnDefinition="REAL", nullable=false, precision=10, scale=3)
	private double valore;
	@Column(name="valore_minimo", columnDefinition="REAL", precision=10, scale=3)
	private Double minimo;
	@Column(name="valore_massimo", columnDefinition="REAL", precision=10, scale=3)
	private Double massimo;
	
	public String getTableDefinition() {
		String tableDefinition = "CREATE TABLE IF NOT EXISTS listino_simulazione_voce_proporzionale ("
                + "	id_voce INTEGER NOT NULL REFERENCES listino_simulazione_voce(id) ON UPDATE CASCADE ON DELETE CASCADE, "
                + " valore REAL NOT NULL, "
                + " valore_minimo REAL, "
                + " valore_massimo REAL );";
        return tableDefinition;
	}
	
	@Override
	public String toString() {
		return "valore=" + valore + ", minimo=" + minimo + ", massimo=" + massimo;
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
		ListinoSimulazioneVoceProporzionale other = (ListinoSimulazioneVoceProporzionale) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getValore() {
		return valore;
	}

	public void setValore(double valore) {
		this.valore = valore;
	}
	
	public Double getMinimo() {
		return minimo;
	}

	public void setMinimo(Double minimo) {
		this.minimo = minimo;
	}

	public Double getMassimo() {
		return massimo;
	}

	public void setMassimo(Double massimo) {
		this.massimo = massimo;
	}

}
