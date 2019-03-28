package it.ltc.logica.database.model.centrale.trasporti;

public class CodiceClienteCorriere {
	
	private String codiceCliente;
	private Integer commessa;
	//private Integer idListinoCommessa;
	private Integer corriere;
	//private Integer idListinoCorriere;
	private String descrizione;
	private String stato;
	
	public String toString() {
		return codiceCliente;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codiceCliente == null) ? 0 : codiceCliente.hashCode());
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
		CodiceClienteCorriere other = (CodiceClienteCorriere) obj;
		if (codiceCliente == null) {
			if (other.codiceCliente != null)
				return false;
		} else if (!codiceCliente.equals(other.codiceCliente))
			return false;
		return true;
	}
	


	public String getCodiceCliente() {
		return codiceCliente;
	}

	public void setCodiceCliente(String codiceCliente) {
		this.codiceCliente = codiceCliente;
	}

	public Integer getCommessa() {
		return commessa;
	}

	public void setCommessa(Integer idCommessa) {
		this.commessa = idCommessa;
	}

//	public Integer getIdListinoCommessa() {
//		return idListinoCommessa;
//	}
//
//	public void setIdListinoCommessa(Integer idListinoCommessa) {
//		this.idListinoCommessa = idListinoCommessa;
//	}

	public Integer getCorriere() {
		return corriere;
	}

	public void setCorriere(Integer idCorriere) {
		this.corriere = idCorriere;
	}

//	public Integer getIdListinoCorriere() {
//		return idListinoCorriere;
//	}
//
//	public void setIdListinoCorriere(Integer idListinoCorriere) {
//		this.idListinoCorriere = idListinoCorriere;
//	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		Stato s = Stato.valueOf(stato);
		if (s == null)
			throw new IllegalArgumentException("Il valore indicato non è ammissibile.");
		this.stato = stato;
	}
	
//	@JsonInclude()
//	public Stato getEStato() {
//		Stato s = Stato.valueOf(stato);
//		return s;
//	}

	public enum Stato {
		
		DEFAULT("DEFAULT", "Default", "Il codice da abbinare normalmente a quella commessa per quel corriere."),
		ATTIVO("ATTIVO", "Attivo", "Un codice che può essere utilizzato."),
		DISATTIVO("DISATTIVO", "Disattivo", "Un codice che non può essere utilizzato."),
		DISMESSO("DISMESSO", "Dismesso", "Un codice che \u00E8 stato chiuso definitivamente."),
		PROVVISORIO("PROVVISORIO", "Provvisorio", "Un codice fittizio da usare in attesa che venga aperto quello definitivo."),
		TEST("TEST", "Test", "Un codice da inserire a fini di test.");
		
		private final String codice;
		private final String nome;
		private final String descrizione;
		
		private Stato(String codice, String nome, String descrizione) {
			this.codice = codice;
			this.nome = nome;
			this.descrizione = descrizione;
		}
		
		public String toString() {
			return nome;
		}

		public String getCodice() {
			return codice;
		}

		public String getNome() {
			return nome;
		}

		public String getDescrizione() {
			return descrizione;
		}
	}

}
