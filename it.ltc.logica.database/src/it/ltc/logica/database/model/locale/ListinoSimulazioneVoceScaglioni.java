package it.ltc.logica.database.model.locale;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

@Entity
@Table(name="listino_simulazione_voce_scaglioni")
@IdClass(ListinoSimulazioneVoceScaglioniPK.class)
public class ListinoSimulazioneVoceScaglioni implements Serializable, Comparable<ListinoSimulazioneVoceScaglioni> {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="id_voce", nullable=false)
	private int id;
	@Id
	@Column(columnDefinition="REAL", nullable=false, precision=10, scale=3)
	private double inizio;
	@Id
	@Column(columnDefinition="REAL", nullable=false, precision=10, scale=3)
	private double fine;
	@Column(columnDefinition="REAL", nullable=false, precision=10, scale=3)
	private double valore;

	public String getTableDefinition() {
		String tableDefinition = "CREATE TABLE IF NOT EXISTS listino_simulazione_voce_scaglioni ("
                + "	id_voce INTEGER NOT NULL REFERENCES listino_simulazione_voce(id) ON UPDATE CASCADE ON DELETE CASCADE, "
                + " valore REAL NOT NULL, "
                + " inizio REAL NOT NULL, "
                + " fine REAL NOT NULL, PRIMARY KEY (id_voce, inizio, fine) );";
        return tableDefinition;
	}

	@Override
	public String toString() {
		return "inizio=" + inizio + ", fine=" + fine + ", valore=" + valore;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getInizio() {
		return inizio;
	}

	public void setInizio(double inizio) {
		this.inizio = inizio;
	}

	public double getFine() {
		return fine;
	}

	public void setFine(double fine) {
		this.fine = fine;
	}

	public double getValore() {
		return valore;
	}

	public void setValore(double valore) {
		this.valore = valore;
	}

	@Override
	public int compareTo(ListinoSimulazioneVoceScaglioni o) {
		Double i1 = inizio;
		Double i2 = o.getInizio();
		int order = i1.compareTo(i2);
		return order;
	}

}
