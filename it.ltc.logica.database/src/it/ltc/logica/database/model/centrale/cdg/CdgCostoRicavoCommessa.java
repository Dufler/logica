package it.ltc.logica.database.model.centrale.cdg;

import java.io.Serializable;
import java.util.Date;


/**
 * The persistent class for the cdg_bilancio database table.
 * 
 */
public class CdgCostoRicavoCommessa implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public enum TipoBilancioCdg { 
		
		INGRESSO("Ingresso"),
		USCITA("Uscita");
		
		private final String descrizione;
		
		private TipoBilancioCdg(String descrizione) {
			this.descrizione = descrizione;
		}
		
		public String toString() {
			return descrizione;
		}
	}
	
	private int id;

	private int commessa;

	private Date dataEmissione;

	private TipoBilancioCdg tipo;

	private double valore;
	
	private int fase;
	
	private String descrizione;

	public CdgCostoRicavoCommessa() {}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCommessa() {
		return this.commessa;
	}

	public void setCommessa(int commessa) {
		this.commessa = commessa;
	}

	public Date getDataEmissione() {
		return this.dataEmissione;
	}

	public void setDataEmissione(Date dataEmissione) {
		this.dataEmissione = dataEmissione;
	}

	public TipoBilancioCdg getTipo() {
		return this.tipo;
	}

	public void setTipo(TipoBilancioCdg tipo) {
		this.tipo = tipo;
	}

	public double getValore() {
		return this.valore;
	}

	public void setValore(double valore) {
		this.valore = valore;
	}
	
	public int getFase() {
		return fase;
	}

	public void setFase(int fase) {
		this.fase = fase;
	}
	
	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	@Override
	public String toString() {
		return "CdgBilancio [id=" + id + ", commessa=" + commessa + ", dataEmissione=" + dataEmissione + ", tipo=" + tipo + ", valore=" + valore + "]";
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
		CdgCostoRicavoCommessa other = (CdgCostoRicavoCommessa) obj;
		if (id != other.id)
			return false;
		return true;
	}

}