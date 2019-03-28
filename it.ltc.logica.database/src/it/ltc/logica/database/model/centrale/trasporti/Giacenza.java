package it.ltc.logica.database.model.centrale.trasporti;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="spedizione_giacenza")
public class Giacenza implements Serializable, Comparable<Giacenza> {
	
	private static final long serialVersionUID = 1L;
	
	/**
	 * Definisce i possibili stati di fatturazione.
	 * Lo stato di default, <code>NON_FATTURABILE</code>, è uno stato pozzo per gestire i casi limite, come le simulazioni, che non dovranno mai essere fatturate.
	 * @author Damiano
	 *
	 */
	public enum Fatturazione { 
		
		NON_FATTURABILE("Non fatturabile"),
		IN_DEFINIZIONE("Non ancora fatturabile"),
		FATTURABILE("Fatturabile"),
		FATTURATA("Gia' fatturata");
		
		private final String descrizione;
		
		private Fatturazione(String descrizione) {
			this.descrizione = descrizione;
		}
		
		@Override
		public String toString() {
			return descrizione;
		}
	}
	
	@Id
	@Column(unique=true, nullable=false)
	private int id;

	@Column(precision=10, scale=3)
	private Double costo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_apertura")
	private Date dataApertura;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_chiusura")
	private Date dataChiusura;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_ultima_modifica")
	private Date dataUltimaModifica;

	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	private Fatturazione fatturazione;

	@Column(name="id_commessa", nullable=false)
	private int idCommessa;

	@Column(name="id_destinatario", nullable=false)
	private int idDestinatario;

	@Column(name="id_mittente", nullable=false)
	private int idMittente;

	@Column(name="id_documento", nullable=false)
	private int idDocumento;

	@Column(name="id_spedizione", nullable=false)
	private int idSpedizione;

	@Column(name="lettera_di_vettura", nullable=false, length=45)
	private String letteraDiVettura;

	@Column(name="lettera_di_vettura_originale", nullable=false, length=45)
	private String letteraDiVetturaOriginale;

	@Column(length=200)
	private String note;

	@Column(precision=10, scale=3)
	private Double ricavo;

	public Giacenza() {}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Double getCosto() {
		return this.costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

	public Date getDataApertura() {
		return this.dataApertura;
	}

	public void setDataApertura(Date dataApertura) {
		this.dataApertura = dataApertura;
	}

	public Date getDataChiusura() {
		return this.dataChiusura;
	}

	public void setDataChiusura(Date dataChiusura) {
		this.dataChiusura = dataChiusura;
	}
	
	public Date getDataUltimaModifica() {
		return dataUltimaModifica;
	}

	public void setDataUltimaModifica(Date dataUltimaModifica) {
		this.dataUltimaModifica = dataUltimaModifica;
	}

	public Fatturazione getFatturazione() {
		return this.fatturazione;
	}

	public void setFatturazione(Fatturazione fatturazione) {
		this.fatturazione = fatturazione;
	}

	public int getIdCommessa() {
		return this.idCommessa;
	}

	public void setIdCommessa(int idCommessa) {
		this.idCommessa = idCommessa;
	}

	public int getIdDestinatario() {
		return this.idDestinatario;
	}

	public void setIdDestinatario(int idDestinatario) {
		this.idDestinatario = idDestinatario;
	}

	public int getIdMittente() {
		return this.idMittente;
	}

	public void setIdMittente(int idMittente) {
		this.idMittente = idMittente;
	}

	public int getIdDocumento() {
		return this.idDocumento;
	}

	public void setIdDocumento(int idDocumento) {
		this.idDocumento = idDocumento;
	}

	public int getIdSpedizione() {
		return this.idSpedizione;
	}

	public void setIdSpedizione(int idSpedizione) {
		this.idSpedizione = idSpedizione;
	}

	public String getLetteraDiVettura() {
		return this.letteraDiVettura;
	}

	public void setLetteraDiVettura(String letteraDiVettura) {
		this.letteraDiVettura = letteraDiVettura;
	}

	public String getLetteraDiVetturaOriginale() {
		return this.letteraDiVetturaOriginale;
	}

	public void setLetteraDiVetturaOriginale(String letteraDiVetturaOriginale) {
		this.letteraDiVetturaOriginale = letteraDiVetturaOriginale;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public Double getRicavo() {
		return this.ricavo;
	}

	public void setRicavo(Double ricavo) {
		this.ricavo = ricavo;
	}

	public Integer calcolaDurata() {
		Integer durataInGiorni = null;
		if (dataChiusura != null) {
			GregorianCalendar inizio = new GregorianCalendar();
			inizio.setTime(dataApertura);
			GregorianCalendar fine = new GregorianCalendar();
			fine.setTime(dataChiusura);
			int inizioGiorno = inizio.get(Calendar.DAY_OF_YEAR);
			int fineGiorno = fine.get(Calendar.DAY_OF_YEAR);
			//Nel caso in cui la fine preceda l'inizio vuol dire che la giacenza è a cavallo fra 2 anni.
			if (fineGiorno < inizioGiorno) {
				//Controllo se l'anno era bisestile.
				int offset = inizio.isLeapYear(inizio.get(Calendar.YEAR)) ? 366 : 365;
				fineGiorno += offset;
			}
			durataInGiorni = fineGiorno - inizioGiorno;
		}
		return durataInGiorni;
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
		Giacenza other = (Giacenza) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Giacenza [id=" + id + ", apertura=" + dataApertura + ", chiusura=" + dataChiusura + ", letteraDiVettura="
				+ letteraDiVettura + "]";
	}

	@Override
	public int compareTo(Giacenza giacenza) {
		Date a1 = dataApertura != null ? dataApertura : new Date();
		Date a2 = giacenza != null && giacenza.getDataApertura() != null ? giacenza.getDataApertura() : new Date();
		int compare = a1.compareTo(a2);
		return compare;
	}

}
