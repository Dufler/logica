package it.ltc.logica.database.model.centrale.indirizzi;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
@Table(name="indirizzo")
public class Indirizzo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(unique=true, nullable=false)
	private int id;

	@Column(nullable=false, length=10)
	private String cap;

	@Column(name="consegna_al_piano", nullable=false)
	private boolean consegnaAlPiano;

	@Column(name="consegna_appuntamento", nullable=false)
	private boolean consegnaAppuntamento;

	@Column(name="consegna_gdo", nullable=false)
	private boolean consegnaGdo;

	@Column(name="consegna_privato", nullable=false)
	private boolean consegnaPrivato;

	@Column(length=100)
	private String email;

	@Column(nullable=false, length=100)
	private String indirizzo;

	@Column(nullable=false, length=45)
	private String localita;

	@Column(nullable=false, length=3, columnDefinition="CHAR")
	private String nazione;

	@Column(nullable=false, length=2, columnDefinition="CHAR")
	private String provincia;

	@Column(name="ragione_sociale", nullable=false, length=200)
	private String ragioneSociale;

	@Column(length=30)
	private String telefono;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_ultima_modifica")
	private Date dataUltimaModifica;
	
	@Transient
	private String codice;

	@Override
	public String toString() {
		return "Indirizzo [ragioneSociale=" + ragioneSociale + ", indirizzo=" + indirizzo + ", cap=" + cap + ", localita=" + localita + ", provincia=" + provincia + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cap == null) ? 0 : cap.hashCode());
		result = prime * result + ((indirizzo == null) ? 0 : indirizzo.hashCode());
		result = prime * result + ((localita == null) ? 0 : localita.hashCode());
		result = prime * result + ((provincia == null) ? 0 : provincia.hashCode());
		result = prime * result + ((ragioneSociale == null) ? 0 : ragioneSociale.hashCode());
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
		Indirizzo other = (Indirizzo) obj;
		if (cap == null) {
			if (other.cap != null)
				return false;
		} else if (!cap.equals(other.cap))
			return false;
		if (indirizzo == null) {
			if (other.indirizzo != null)
				return false;
		} else if (!indirizzo.equals(other.indirizzo))
			return false;
		if (localita == null) {
			if (other.localita != null)
				return false;
		} else if (!localita.equals(other.localita))
			return false;
		if (provincia == null) {
			if (other.provincia != null)
				return false;
		} else if (!provincia.equals(other.provincia))
			return false;
		if (ragioneSociale == null) {
			if (other.ragioneSociale != null)
				return false;
		} else if (!ragioneSociale.equals(other.ragioneSociale))
			return false;
		return true;
	}
	
	public boolean quasiUguali(Indirizzo indirizzo) {
		String capIndirizzo = indirizzo.getCap();
		String localitaIndirizzo = indirizzo.getLocalita();
		boolean capUguali = cap.equals(capIndirizzo);
		boolean localitaUguali = localita.equals(localitaIndirizzo);
		boolean uguali = capUguali && localitaUguali;
		return uguali;
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCap() {
		return this.cap;
	}

	public void setCap(String cap) {
		this.cap = cap;
	}

	public boolean getConsegnaAlPiano() {
		return this.consegnaAlPiano;
	}

	public void setConsegnaAlPiano(boolean consegnaAlPiano) {
		this.consegnaAlPiano = consegnaAlPiano;
	}

	public boolean getConsegnaAppuntamento() {
		return this.consegnaAppuntamento;
	}

	public void setConsegnaAppuntamento(boolean consegnaAppuntamento) {
		this.consegnaAppuntamento = consegnaAppuntamento;
	}

	public boolean getConsegnaGdo() {
		return this.consegnaGdo;
	}

	public void setConsegnaGdo(boolean consegnaGdo) {
		this.consegnaGdo = consegnaGdo;
	}

	public boolean getConsegnaPrivato() {
		return this.consegnaPrivato;
	}

	public void setConsegnaPrivato(boolean consegnaPrivato) {
		this.consegnaPrivato = consegnaPrivato;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIndirizzo() {
		return this.indirizzo;
	}

	public void setIndirizzo(String indirizzo) {
		this.indirizzo = indirizzo;
	}

	public String getLocalita() {
		return this.localita;
	}

	public void setLocalita(String localita) {
		this.localita = localita;
	}

	public String getNazione() {
		return this.nazione;
	}

	public void setNazione(String nazione) {
		this.nazione = nazione;
	}

	public String getProvincia() {
		return this.provincia;
	}

	public void setProvincia(String provincia) {
		this.provincia = provincia;
	}

	public String getRagioneSociale() {
		return this.ragioneSociale;
	}

	public void setRagioneSociale(String ragioneSociale) {
		this.ragioneSociale = ragioneSociale;
	}

	public String getTelefono() {
		return this.telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public Date getDataUltimaModifica() {
		return dataUltimaModifica;
	}

	public void setDataUltimaModifica(Date dataUltimaModifica) {
		this.dataUltimaModifica = dataUltimaModifica;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

}
