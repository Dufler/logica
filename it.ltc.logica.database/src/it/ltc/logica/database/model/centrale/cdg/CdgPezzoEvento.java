package it.ltc.logica.database.model.centrale.cdg;

public class CdgPezzoEvento {
	
	private int pezzo;
	private int evento;
	private double costo;
	private double ricavo;
	
	public CdgPezzoEvento() {}

	public int getPezzo() {
		return pezzo;
	}

	public void setPezzo(int pezzo) {
		this.pezzo = pezzo;
	}

	public int getEvento() {
		return evento;
	}

	public void setEvento(int evento) {
		this.evento = evento;
	}

	public double getCosto() {
		return costo;
	}

	public void setCosto(double costo) {
		this.costo = costo;
	}

	public double getRicavo() {
		return ricavo;
	}

	public void setRicavo(double ricavo) {
		this.ricavo = ricavo;
	}

	@Override
	public String toString() {
		return "CdgPezzoEvento [pezzo=" + pezzo + ", evento=" + evento + ", costo=" + costo + ", ricavo=" + ricavo + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + evento;
		result = prime * result + pezzo;
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
		CdgPezzoEvento other = (CdgPezzoEvento) obj;
		if (evento != other.evento)
			return false;
		if (pezzo != other.pezzo)
			return false;
		return true;
	}

}
