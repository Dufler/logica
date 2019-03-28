package it.ltc.logica.database.model.centrale.ingressi;

import java.util.Date;

public class Ingresso {
	
	/**
	 * Enum che definisce i possibili stati dell'ingresso.
	 * @author Damiano Bellucci - damiano.bellucci@gmail.com
	 *
	 */
//	public enum Stato {
//		
//		INSERITO("Inserito"),
//		ARRIVATO("Arrivato"),
//		IN_LAVORAZIONE("In lavorazione"), 
//		LAVORATO("Lavorato"), 
//		CHIUSO("Chiuso");
//		
//		private final String descrizione;
//		
//		private Stato(String descrizione) {
//			this.descrizione = descrizione;
//		}
//		
//		@Override
//		public String toString() {
//			return descrizione;
//		}
//		
//	}
	
//	public enum TipoIngresso {
//		
//		PRODUZIONE("Produzione"),
//		RESO("Reso");
//		
//		private final String descrizione;
//		
//		private TipoIngresso(String descrizione) {
//			this.descrizione = descrizione;
//		}
//		
//		@Override
//		public String toString() {
//			return descrizione;
//		}
//	}
	
	private Integer id;
	private Integer idCommessa;
	private Integer idDocumento;
	private Integer idFornitore;
	private String riferimentoCliente;
	private String riferimentoInterno;
	private Integer pezziStimati;
	private Integer pezziLetti;
	private String stato;
	private String tipo;
	private Date dataArrivo;
	private Integer colli;

	@Override
	public String toString() {
		return "Ingresso [riferimentoCliente=" + riferimentoCliente + ", riferimentoInterno=" + riferimentoInterno + ", pezziStimati=" + pezziStimati + ", pezziLetti=" + pezziLetti + ", stato="
				+ stato + ", tipo=" + tipo + "]";
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
		Ingresso other = (Ingresso) obj;
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

	public Integer getIdDocumento() {
		return idDocumento;
	}

	public void setIdDocumento(Integer idDocumento) {
		this.idDocumento = idDocumento;
	}

	public Integer getIdFornitore() {
		return idFornitore;
	}

	public void setIdFornitore(Integer idFornitore) {
		this.idFornitore = idFornitore;
	}

	public String getRiferimentoCliente() {
		return riferimentoCliente;
	}

	public void setRiferimentoCliente(String riferimentoCliente) {
		this.riferimentoCliente = riferimentoCliente;
	}

	public String getRiferimentoInterno() {
		return riferimentoInterno;
	}

	public void setRiferimentoInterno(String riferimentoInterno) {
		this.riferimentoInterno = riferimentoInterno;
	}

	public Integer getPezziStimati() {
		return pezziStimati;
	}

	public void setPezziStimati(Integer pezziStimati) {
		this.pezziStimati = pezziStimati;
	}

	public Integer getPezziLetti() {
		return pezziLetti;
	}

	public void setPezziLetti(Integer pezziLetti) {
		this.pezziLetti = pezziLetti;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public Date getDataArrivo() {
		return dataArrivo;
	}

	public void setDataArrivo(Date dataArrivo) {
		this.dataArrivo = dataArrivo;
	}

	public Integer getColli() {
		return colli;
	}

	public void setColli(Integer colli) {
		this.colli = colli;
	}

}
