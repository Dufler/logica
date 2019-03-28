package it.ltc.logica.database.model.locale;

import java.io.Serializable;

public class ListinoSimulazioneVoceScaglioniPK implements Serializable {

	private static final long serialVersionUID = 1L;
	
	protected int id;
	protected double inizio;
	protected double fine;
	
	public ListinoSimulazioneVoceScaglioniPK() {}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(fine);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + id;
		temp = Double.doubleToLongBits(inizio);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		ListinoSimulazioneVoceScaglioniPK other = (ListinoSimulazioneVoceScaglioniPK) obj;
		if (Double.doubleToLongBits(fine) != Double.doubleToLongBits(other.fine))
			return false;
		if (id != other.id)
			return false;
		if (Double.doubleToLongBits(inizio) != Double.doubleToLongBits(other.inizio))
			return false;
		return true;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public double getInizio() {
		return inizio;
	}
	
	public void setInizio(double inizio) {
		this.inizio = inizio;
	}
	
	public double getFine() {
		return fine;
	}
	
	public void setFine(double fine) {
		this.fine = fine;
	}
	
}
