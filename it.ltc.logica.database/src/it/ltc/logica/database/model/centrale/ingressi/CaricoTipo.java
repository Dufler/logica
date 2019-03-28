package it.ltc.logica.database.model.centrale.ingressi;

import java.util.List;

public class CaricoTipo {
	
	private String codice;
	private String descrizione;
	private List<Integer> particolarita;
	
	public CaricoTipo() {}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codice == null) ? 0 : codice.hashCode());
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
		CaricoTipo other = (CaricoTipo) obj;
		if (codice == null) {
			if (other.codice != null)
				return false;
		} else if (!codice.equals(other.codice))
			return false;
		return true;
	}

	public String getCodice() {
		return codice;
	}

	public void setCodice(String codice) {
		this.codice = codice;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	
	public List<Integer> getParticolarita() {
		return particolarita;
	}

	public void setParticolarita(List<Integer> particolarita) {
		this.particolarita = particolarita;
	}

	@Override
	public String toString() {
		String testo = codice.equalsIgnoreCase(descrizione) ? codice : codice + " - " + descrizione;
		return testo;
	}

}
