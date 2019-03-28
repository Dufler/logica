package it.ltc.logica.database.model.centrale.trasporti;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import it.ltc.logica.database.model.centrale.trasporti.Spedizione.TipoSpedizione;

@Entity
@Table(name="spedizione_simulazione")
public class SpedizioneSimulazione implements Serializable, Comparable<Spedizione> {
	
	private static final long serialVersionUID = 1L;
		
	@Id
	@Column(unique=true, nullable=false, insertable=false)
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Basic
	private int id;

	@Column(nullable=false)
	private boolean assicurazione;

	@Column(name="codice_cliente", length=50, nullable=false)
	private String codiceCliente;

	@Column(nullable=false)
	private int colli;

	@Column(nullable=false)
	private boolean contrassegno;

	@Column(precision=10, scale=3)
	private Double costo;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_partenza")
	private Date dataPartenza;
	
//	@Temporal(TemporalType.TIMESTAMP)
//	@Column(name="data_ultima_modifica")
//	private Date dataUltimaModifica;

	@Column(name="dati_completi", nullable=false)
	private boolean datiCompleti;

	@Column(nullable=false)
	private boolean giacenza;

	@Column(name="id_commessa", nullable=false)
	private int idCommessa;

	@Column(name="id_corriere")
	private int idCorriere;

	@Column(name="id_documento", nullable=false)
	private int idDocumento;

	@Column(name="in_ritardo", nullable=false)
	private boolean inRitardo;

	@Column(name="indirizzo_destinazione", nullable=false)
	private int indirizzoDestinazione;

	@Column(name="indirizzo_partenza", nullable=false)
	private int indirizzoPartenza;

	@Column(name="lettera_di_vettura", length=45)
	private String letteraDiVettura;

	@Column(name="note", columnDefinition="TEXT")
	private String note;

	@Column(nullable=false)
	private boolean particolarita;

	@Column(precision=10, scale=3)
	private Double peso;

	private int pezzi;

	@Column(precision=10, scale=3)
	private Double ricavo;

	@Column(name="riferimento_cliente", length=45)
	private String riferimentoCliente;

	@Column(name="riferimento_mittente", length=45)
	private String riferimentoMittente;

	@Column(nullable=false, length=3, columnDefinition="CHAR")
	private String servizio;

	@Column(nullable=false, length=3, columnDefinition="CHAR")
	private String stato;

	@Column(nullable=false)
	@Enumerated(EnumType.STRING)
	private TipoSpedizione tipo;

	@Column(name="valore_merce_dichiarato", precision=10, scale=2)
	private Double valoreMerceDichiarato;

	@Column(precision=10, scale=3)
	private Double volume;
	
	@Column(name="ragione_sociale_destinatario", length=100)
	private String ragioneSocialeDestinatario;
	
	@Transient
	private ContrassegnoSimulazione contrassegnoValori;

	public SpedizioneSimulazione() {}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean getAssicurazione() {
		return this.assicurazione;
	}

	public void setAssicurazione(boolean assicurazione) {
		this.assicurazione = assicurazione;
	}

	public String getCodiceCliente() {
		return this.codiceCliente;
	}

	public void setCodiceCliente(String codiceCliente) {
		this.codiceCliente = codiceCliente;
	}

	public int getColli() {
		return this.colli;
	}

	public void setColli(int colli) {
		this.colli = colli;
	}

	public boolean getContrassegno() {
		return this.contrassegno;
	}

	public void setContrassegno(boolean contrassegno) {
		this.contrassegno = contrassegno;
	}

	public Double getCosto() {
		return this.costo;
	}

	public void setCosto(Double costo) {
		this.costo = costo;
	}

	public Date getDataPartenza() {
		return this.dataPartenza;
	}

	public void setDataPartenza(Date dataPartenza) {
		this.dataPartenza = dataPartenza;
	}
	
//	public Date getDataUltimaModifica() {
//		return dataUltimaModifica;
//	}
//
//	public void setDataUltimaModifica(Date dataUltimaModifica) {
//		this.dataUltimaModifica = dataUltimaModifica;
//	}

	public boolean getDatiCompleti() {
		return this.datiCompleti;
	}

	public void setDatiCompleti(boolean datiCompleti) {
		this.datiCompleti = datiCompleti;
	}

	public boolean getGiacenza() {
		return this.giacenza;
	}

	public void setGiacenza(boolean giacenza) {
		this.giacenza = giacenza;
	}

	public int getIdCommessa() {
		return this.idCommessa;
	}

	public void setIdCommessa(int idCommessa) {
		this.idCommessa = idCommessa;
	}

	public int getIdCorriere() {
		return this.idCorriere;
	}

	public void setIdCorriere(int idCorriere) {
		this.idCorriere = idCorriere;
	}

	public int getIdDocumento() {
		return this.idDocumento;
	}

	public void setIdDocumento(int idDocumento) {
		this.idDocumento = idDocumento;
	}

	public boolean getInRitardo() {
		return this.inRitardo;
	}

	public void setInRitardo(boolean inRitardo) {
		this.inRitardo = inRitardo;
	}

	public int getIndirizzoDestinazione() {
		return this.indirizzoDestinazione;
	}

	public void setIndirizzoDestinazione(int indirizzoDestinazione) {
		this.indirizzoDestinazione = indirizzoDestinazione;
	}

	public int getIndirizzoPartenza() {
		return this.indirizzoPartenza;
	}

	public void setIndirizzoPartenza(int indirizzoPartenza) {
		this.indirizzoPartenza = indirizzoPartenza;
	}

	public String getLetteraDiVettura() {
		return this.letteraDiVettura;
	}

	public void setLetteraDiVettura(String letteraDiVettura) {
		this.letteraDiVettura = letteraDiVettura;
	}

	public String getNote() {
		return this.note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public boolean getParticolarita() {
		return this.particolarita;
	}

	public void setParticolarita(boolean particolarita) {
		this.particolarita = particolarita;
	}

	public Double getPeso() {
		return this.peso;
	}

	public void setPeso(Double peso) {
		this.peso = peso;
	}

	public int getPezzi() {
		return this.pezzi;
	}

	public void setPezzi(int pezzi) {
		this.pezzi = pezzi;
	}

	public Double getRicavo() {
		return this.ricavo;
	}

	public void setRicavo(Double ricavo) {
		this.ricavo = ricavo;
	}

	public String getRiferimentoCliente() {
		return this.riferimentoCliente;
	}

	public void setRiferimentoCliente(String riferimentoCliente) {
		this.riferimentoCliente = riferimentoCliente;
	}

	public String getRiferimentoMittente() {
		return this.riferimentoMittente;
	}

	public void setRiferimentoMittente(String riferimentoMittente) {
		this.riferimentoMittente = riferimentoMittente;
	}

	public String getServizio() {
		return this.servizio;
	}

	public void setServizio(String servizio) {
		this.servizio = servizio;
	}

	public String getStato() {
		return this.stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public TipoSpedizione getTipo() {
		return this.tipo;
	}

	public void setTipo(TipoSpedizione tipo) {
		this.tipo = tipo;
	}

	public Double getValoreMerceDichiarato() {
		return this.valoreMerceDichiarato;
	}

	public void setValoreMerceDichiarato(Double valoreMerceDichiarato) {
		this.valoreMerceDichiarato = valoreMerceDichiarato;
	}

	public Double getVolume() {
		return this.volume;
	}

	public void setVolume(Double volume) {
		this.volume = volume;
	}

	public String getRagioneSocialeDestinatario() {
		return ragioneSocialeDestinatario;
	}

	public void setRagioneSocialeDestinatario(String ragioneSocialeDestinatario) {
		this.ragioneSocialeDestinatario = ragioneSocialeDestinatario;
	}

	public ContrassegnoSimulazione getContrassegnoValori() {
		return contrassegnoValori;
	}

	public void setContrassegnoValori(ContrassegnoSimulazione contrassegnoValori) {
		this.contrassegnoValori = contrassegnoValori;
	}

	@Override
	public int compareTo(Spedizione s) {
		int compare;
		if (s != null) {
			Date d1 = getDataPartenza() != null ? getDataPartenza() : new Date();
			Date d2 = s.getDataPartenza() != null ? s.getDataPartenza() : new Date();
			compare = d1.compareTo(d2);
		} else {
			compare = -1;
		}
		return compare;
	}

}
