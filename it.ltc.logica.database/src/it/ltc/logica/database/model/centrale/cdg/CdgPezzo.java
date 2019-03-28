package it.ltc.logica.database.model.centrale.cdg;

import java.io.Serializable;
import java.util.List;


/**
 * The persistent class for the cdg_pezzo database table.
 * 
 */
public class CdgPezzo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private int id;
	private String categoriaMerceologica;
	private int commessa;
	private double costo;
	private double ricavo;
	
	private List<CdgPezzoEvento> spacchettamenti;

	public List<CdgPezzoEvento> getSpacchettamenti() {
		return spacchettamenti;
	}

	public void setSpacchettamenti(List<CdgPezzoEvento> spacchettamenti) {
		this.spacchettamenti = spacchettamenti;
	}
	//Fine

	public CdgPezzo() {}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoriaMerceologica() {
		return this.categoriaMerceologica;
	}

	public void setCategoriaMerceologica(String categoriaMerceologica) {
		this.categoriaMerceologica = categoriaMerceologica;
	}

	public int getCommessa() {
		return this.commessa;
	}

	public void setCommessa(int commessa) {
		this.commessa = commessa;
	}

	public double getCosto() {
		return this.costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public double getRicavo() {
		return this.ricavo;
	}

	public void setRicavo(double ricavo) {
		this.ricavo = ricavo;
	}

	@Override
	public String toString() {
		return "CdgPezzo [id=" + id + ", categoriaMerceologica=" + categoriaMerceologica + ", commessa=" + commessa + ", costo=" + costo + ", ricavo=" + ricavo + "]";
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
		CdgPezzo other = (CdgPezzo) obj;
		if (id != other.id)
			return false;
		return true;
	}

}