package it.ltc.logica.database.model.centrale;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Tracking implements Comparable<Tracking> {
	
	private Integer idSpedizione;
	private String stato;
	private String descrizione;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm")
	private Date data;
	
	@Override
	public String toString() {
		return "Tracking [idOrdine=" + idSpedizione + ", stato=" + stato + ", descrizione=" + descrizione + ", data=" + data
				+ "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((descrizione == null) ? 0 : descrizione.hashCode());
		result = prime * result + ((idSpedizione == null) ? 0 : idSpedizione.hashCode());
		result = prime * result + ((stato == null) ? 0 : stato.hashCode());
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
		Tracking other = (Tracking) obj;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (descrizione == null) {
			if (other.descrizione != null)
				return false;
		} else if (!descrizione.equals(other.descrizione))
			return false;
		if (idSpedizione == null) {
			if (other.idSpedizione != null)
				return false;
		} else if (!idSpedizione.equals(other.idSpedizione))
			return false;
		if (stato == null) {
			if (other.stato != null)
				return false;
		} else if (!stato.equals(other.stato))
			return false;
		return true;
	}

	public boolean isFinale() {
		boolean consegnato = "S03".equals(stato);
		boolean consegnatoInRitardo = "S04".equals(stato);
		boolean annullato = "C06".equals(stato);
		boolean finale = consegnato || consegnatoInRitardo || annullato;
		return finale;
	}

	public Integer getIdSpedizione() {
		return idSpedizione;
	}

	public void setIdSpedizione(Integer idSpedizione) {
		this.idSpedizione = idSpedizione;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		if (data != null)
			this.data = new Date(data.getTime());
		else
			this.data = null;
	}
	
	@Override
	public int compareTo(Tracking o) {
		int compare = 0;
		if (data != null && o.getData() != null)
			compare = data.compareTo(o.getData());
		return compare;
	}

}