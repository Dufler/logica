package it.ltc.logica.database.model.centrale.trasporti;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * I tipi di contrassegno attualmente esistenti sono:<br>
 * <ul>
 * <li>BB: Assegno Bancario intestato al Corriere con manleva</li>
 * <li>BM: Assegno Bancario intestato al Mittente</li>
 * <li>CB: Assegno Circolare intestato al Corriere con manleva</li>
 * <li>CM: Assegno Circolare intestato al Mittente</li>
 * <li>OM: Assegno intestato al Mittente originale</li>
 * <li>TM: Assegno Bancario intestato al Mittente Scadenziato</li>
 * <li>TO: Assegno CC Scadenziato (speciale per Zeis)</li>
 * <li>SC: Contante</li>
 * <li>NA: Non specificato</li>
 * </ul>
 * @author Damiano
 *
 */
@Entity
@Table(name="spedizione_contrassegno")
public class Contrassegno implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="id_spedizione", unique=true, nullable=false)
	private int idSpedizione;

	@Column(nullable=false)
	private boolean annullato;

	@Column(nullable=false, length=2, columnDefinition="CHAR")
	private String tipo;

	@Column(nullable=false, precision=10, scale=2)
	private Double valore;

	@Column(nullable=false, length=3, columnDefinition="CHAR")
	private String valuta;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_ultima_modifica")
	private Date dataUltimaModifica;

	public Contrassegno() {}

	@PrePersist
	public void prePersist() {
		if (dataUltimaModifica == null) dataUltimaModifica = new Date(0);
	}
	
	public int getIdSpedizione() {
		return this.idSpedizione;
	}

	public void setIdSpedizione(int idSpedizione) {
		this.idSpedizione = idSpedizione;
	}

	public boolean getAnnullato() {
		return this.annullato;
	}

	public void setAnnullato(boolean annullato) {
		this.annullato = annullato;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Double getValore() {
		return this.valore;
	}

	public void setValore(Double valore) {
		this.valore = valore;
	}

	public String getValuta() {
		return this.valuta;
	}

	public void setValuta(String valuta) {
		this.valuta = valuta;
	}
	
	public Date getDataUltimaModifica() {
		return dataUltimaModifica;
	}

	public void setDataUltimaModifica(Date dataUltimaModifica) {
		this.dataUltimaModifica = dataUltimaModifica;
	}
	
	@Override
	public String toString() {
		return "Contrassegno [idSpedizione=" + idSpedizione + ", valore=" + valore + ", valuta=" + valuta + ", tipo=" + tipo + ", annullato=" + annullato + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idSpedizione;
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
		Contrassegno other = (Contrassegno) obj;
		if (idSpedizione != other.idSpedizione)
			return false;
		return true;
	}

}
