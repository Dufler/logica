package it.ltc.logica.database.model.centrale.cdg;

import java.io.Serializable;


/**
 * The persistent class for the cdg_costi_ricavi_generici database table.
 * 
 */
public class CdgCostiRicaviGenerici implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	public enum DriverRipartizione { 
		
		COLLI("Colli"), 
		PEZZI("Pezzi"), 
		RICAVI("Ricavi"), 
		SPAZI("Spazio"), 
		SPEDIZIONI("Spedizioni"), 
		TEMPO("Tempo manodopera");
		
		private final String descrizione;
		
		private DriverRipartizione(String descrizione) {
			this.descrizione = descrizione;
		}
		
		@Override
		public String toString() {
			return descrizione;
		}
	}

	private int id;
	private DriverRipartizione driver;
	private String nome;

	public CdgCostiRicaviGenerici() {}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public DriverRipartizione getDriver() {
		return this.driver;
	}

	public void setDriver(DriverRipartizione driver) {
		this.driver = driver;
	}

	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
		CdgCostiRicaviGenerici other = (CdgCostiRicaviGenerici) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return nome;
	}

}