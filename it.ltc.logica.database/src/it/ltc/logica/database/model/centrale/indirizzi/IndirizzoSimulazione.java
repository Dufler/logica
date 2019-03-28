package it.ltc.logica.database.model.centrale.indirizzi;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="indirizzo_simulazione")
public class IndirizzoSimulazione implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(unique=true, nullable=false, insertable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Basic
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
	
	@Column(name="inserimento_manuale", nullable=false)
	private boolean inserimentoManuale;

	@Override
	public String toString() {
		return "Indirizzo [ragioneSociale=" + ragioneSociale + ", indirizzo=" + indirizzo + ", cap=" + cap + ", localita=" + localita + ", provincia=" + provincia + "]";
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
		IndirizzoSimulazione other = (IndirizzoSimulazione) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	public Indirizzo toIndirizzo() {
		Indirizzo oldEntity = new Indirizzo();
		oldEntity.setCap(cap);
		oldEntity.setConsegnaAlPiano(consegnaAlPiano);
		oldEntity.setConsegnaAppuntamento(consegnaAppuntamento);
		oldEntity.setConsegnaGdo(consegnaGdo);
		oldEntity.setConsegnaPrivato(consegnaPrivato);
		oldEntity.setEmail(email);
		oldEntity.setIndirizzo(indirizzo);
		oldEntity.setLocalita(localita);
		oldEntity.setNazione(nazione);
		oldEntity.setProvincia(provincia);
		oldEntity.setRagioneSociale(ragioneSociale);
		oldEntity.setTelefono(telefono);
		return oldEntity;
	}
	
	public IndirizzoSimulazione() {}

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

	public boolean isInserimentoManuale() {
		return inserimentoManuale;
	}

	public void setInserimentoManuale(boolean inserimentoManuale) {
		this.inserimentoManuale = inserimentoManuale;
	}

}
