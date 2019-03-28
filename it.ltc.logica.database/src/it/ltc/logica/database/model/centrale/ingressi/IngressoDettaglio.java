package it.ltc.logica.database.model.centrale.ingressi;

import java.beans.Transient;

public class IngressoDettaglio {

	private IngressoDettaglioPK id;
	
	private Integer quantitaPrevista;
	private Integer quantitaLetta;
	
	public IngressoDettaglio() {
		id = new IngressoDettaglioPK();
	}

	@Override
	public String toString() {
		return "IngressoDettaglio [id=" + id + ", quantitaPrevista=" + quantitaPrevista + ", quantitaLetta=" + quantitaLetta + "]";
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
		IngressoDettaglio other = (IngressoDettaglio) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public IngressoDettaglioPK getId() {
		return id;
	}

	public void setId(IngressoDettaglioPK id) {
		this.id = id;
	}

	@Transient
	public Integer getIdIngresso() {
		return id.getIdIngresso();
	}

	@Transient
	public void setIdIngresso(Integer idIngresso) {
		id.setIdIngresso(idIngresso);
	}

	@Transient
	public Integer getIdProdotto() {
		return id.getIdProdotto();
	}

	@Transient
	public void setIdProdotto(Integer idProdotto) {
		id.setIdProdotto(idProdotto);
	}

	public Integer getQuantitaPrevista() {
		return quantitaPrevista;
	}

	public void setQuantitaPrevista(Integer quantitaPrevista) {
		this.quantitaPrevista = quantitaPrevista;
	}

	public Integer getQuantitaLetta() {
		return quantitaLetta;
	}

	public void setQuantitaLetta(Integer quantitaLetta) {
		this.quantitaLetta = quantitaLetta;
	}

}
