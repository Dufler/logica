package it.ltc.logica.database.model.centrale;

public class Sede {
	
	private int id;
	private String sede;
//	private int indirizzo;
	private String prefissoCollo;
	private String indirizzoWeb;

	public Sede() {}
	
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
		Sede other = (Sede) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public String toString() {
		return sede;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSede() {
		return sede;
	}

	public void setSede(String nome) {
		this.sede = nome;
	}

//	public int getIndirizzo() {
//		return indirizzo;
//	}
//
//	public void setIndirizzo(int indirizzo) {
//		this.indirizzo = indirizzo;
//	}

	public String getPrefissoCollo() {
		return prefissoCollo;
	}

	public void setPrefissoCollo(String prefissoCollo) {
		this.prefissoCollo = prefissoCollo;
	}

	public String getIndirizzoWeb() {
		return indirizzoWeb;
	}

	public void setIndirizzoWeb(String indirizzoWeb) {
		this.indirizzoWeb = indirizzoWeb;
	}

}
