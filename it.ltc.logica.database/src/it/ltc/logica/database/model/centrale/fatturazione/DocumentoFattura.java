package it.ltc.logica.database.model.centrale.fatturazione;

import java.util.Date;

public class DocumentoFattura {
	
	public enum Stato {
		
		GENERATA("Generata"),
		APPROVATA("Approvata"),
		ARCHIVIATA("Archiviata");
		
		private final String descrizione;
		
		private Stato(String descrizione) {
			this.descrizione = descrizione;
		}
		
		@Override
		public String toString() {
			return descrizione;
		}
		
	}
	
	/**
	 * Enum che definisce le possibili aliquote IVA.
	 * @author Damiano
	 *
	 */
	public enum Iva {
		
		IVA10("IVA 10%", 10),
		IVA22("IVA 22%", 22),
		E146("Esente Art.146(1)(e)-Dir.2006/112EC", 0),
		EB("Art. 15 escluso", 0),
		ES("Art. 10 esente", 0),
		ES7("Esente art.9/IC dpr 633/72", 0),
		FVI("Fuori campo iva art.1 2 3", 0),
		NI7("Non impon.art.7-dpr633/72 26/10/72", 0),
		VLI("vendita clienti con lett. int.art.8 c.2", 0);
		
		private final String descrizione;
		private final double aliquota;
		
		private Iva(String descrizione, double aliquota) {
			this.descrizione = descrizione;
			this.aliquota = aliquota;
		}
		
		@Override
		public String toString() {
			return descrizione;
		}

		public double getAliquota() {
			return aliquota;
		}
		
	}
	
	public enum NoteFattura {
		
		SPLIT_PAYMENT("OPERAZIONE SOGGETTA A SPLIT PAYMENT CON IVA NON INCASSATA DAL CEDENTE AI SENSI DELL'EX ART.17-TER DEL DPR 633/1972"),
		ESENZIONE_ART8("ESENZIONE: ART. 8C.2 DPR633/72 VS. DICH.INTENTO NR. ___ DEL ___ NS. PROT.NR ___ DEL ___ OPERAZIONI FINO A ___"),
		ESENZIONE_ART9("ESENZIONE IVA ART.9 DPR 633/1972"),
		ESENZIONE_ART10("ESENZIONE IVA ART.10 DPR 633/1972"),
		ESCLUSO_ART15("ESCLUSO IVA ART.15 DPR 633/1972"),
		NON_IMPONIBILE("Non impon.art.7-dpr633/72 26/10/72");
		
		private final String descrizione;
		
		private NoteFattura(String descrizione) {
			this.descrizione = descrizione;
		}

		public String getDescrizione() {
			return descrizione;
		}
		
	}

	private Integer id;
	private Integer idCommessa;
	private Integer idAmbito;
	private Date dataGenerazione;
	private Date dataEmissione;
	private String utenteCreatore;
	private Stato stato;
	private String note;
	private String meseAnno;
	private ModalitaPagamentoFattura modalitaPagamento;
	private Iva iva;
	private int coordinatePagamento;
	private int numeroFattura;
	private int annoFattura;
	private String descrizioneFattura;

	@Override
	public String toString() {
		return "Documento di Fattura del: " + dataGenerazione + ", creato da: " + utenteCreatore;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		DocumentoFattura other = (DocumentoFattura) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdCommessa() {
		return idCommessa;
	}

	public void setIdCommessa(Integer idCommessa) {
		this.idCommessa = idCommessa;
	}

	public Integer getIdAmbito() {
		return idAmbito;
	}

	public void setIdAmbito(Integer idAmbito) {
		this.idAmbito = idAmbito;
	}

	public Date getDataGenerazione() {
		return dataGenerazione;
	}

	public void setDataGenerazione(Date dataGenerazione) {
		this.dataGenerazione = dataGenerazione;
	}

	public String getUtenteCreatore() {
		return utenteCreatore;
	}

	public void setUtenteCreatore(String creatore) {
		this.utenteCreatore = creatore;
	}

	public Stato getStato() {
		return stato;
	}

	public void setStato(Stato stato) {
		this.stato = stato;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getMeseAnno() {
		return meseAnno;
	}

	public void setMeseAnno(String meseAnno) {
		this.meseAnno = meseAnno;
	}

	public Date getDataEmissione() {
		return dataEmissione;
	}

	public void setDataEmissione(Date dataEmissione) {
		this.dataEmissione = dataEmissione;
	}

	public ModalitaPagamentoFattura getModalitaPagamento() {
		return modalitaPagamento;
	}

	public void setModalitaPagamento(ModalitaPagamentoFattura modalitaPagamento) {
		this.modalitaPagamento = modalitaPagamento;
	}

	public Iva getIva() {
		return iva;
	}

	public void setIva(Iva iva) {
		this.iva = iva;
	}

	public int getCoordinatePagamento() {
		return coordinatePagamento;
	}

	public void setCoordinatePagamento(int coordinatePagamento) {
		this.coordinatePagamento = coordinatePagamento;
	}

	public int getNumeroFattura() {
		return numeroFattura;
	}

	public void setNumeroFattura(int numeroFattura) {
		this.numeroFattura = numeroFattura;
	}

	public int getAnnoFattura() {
		return annoFattura;
	}

	public void setAnnoFattura(int annoFattura) {
		this.annoFattura = annoFattura;
	}

	public String getDescrizioneFattura() {
		return descrizioneFattura;
	}

	public void setDescrizioneFattura(String descrizioneFattura) {
		this.descrizioneFattura = descrizioneFattura;
	}

}
