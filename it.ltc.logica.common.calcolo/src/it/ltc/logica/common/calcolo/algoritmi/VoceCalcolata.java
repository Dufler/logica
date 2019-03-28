package it.ltc.logica.common.calcolo.algoritmi;

/**
 * Rappresenta l'esito del calcolo della voce di listino applicata all'evento di costo.
 * @author Damiano Bellucci - damiano.bellucci@gmail.com
 *
 */
public class VoceCalcolata {
	
	private final Integer idVoce;
	private final String nomeVoce;
	private final Integer idAmbito;
	private final String nomeAmbito;
	private final String categoriaAmbito;
	private final String descrizioneAmbito;
	private double costo;
	
	private int idVoceFatturazione;
	
	/**
	 * Vanno indicati l'ID e il nome della voce di listino, lo specifico ambito di fatturazione.
	 * @param idVoce
	 * @param nomeVoce
	 * @param idAmbito
	 * @param nomeAmbito
	 * @param categoriaAmbito
	 * @param descrizioneAmbito
	 */
	public VoceCalcolata(Integer idVoce, String nomeVoce, Integer idAmbito, String nomeAmbito, String categoriaAmbito, String descrizioneAmbito) {
		this.idVoce = idVoce;
		this.nomeVoce = nomeVoce;
		this.idAmbito = idAmbito;
		this.nomeAmbito = nomeAmbito;
		this.categoriaAmbito = categoriaAmbito;
		this.descrizioneAmbito = descrizioneAmbito;
	}
	
	public Integer getIdVoce() {
		return idVoce;
	}

	public String getNomeVoce() {
		return nomeVoce;
	}

	public Integer getIdAmbito() {
		return idAmbito;
	}

	public String getNomeAmbito() {
		return nomeAmbito;
	}

	public String getCategoriaAmbito() {
		return categoriaAmbito;
	}

	public String getDescrizioneAmbito() {
		return descrizioneAmbito;
	}

	public double getCosto() {
		return costo;
	}
	
	public void setCosto(double costo) {
		this.costo = costo;
	}

	public int getIdVoceFatturazione() {
		return idVoceFatturazione;
	}

	public void setIdVoceFatturazione(int idVoceFatturazione) {
		this.idVoceFatturazione = idVoceFatturazione;
	}

	@Override
	public String toString() {
		return nomeAmbito + ", " + costo + " \u20AC";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(costo);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((idVoce == null) ? 0 : idVoce.hashCode());
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
		VoceCalcolata other = (VoceCalcolata) obj;
		if (Double.doubleToLongBits(costo) != Double.doubleToLongBits(other.costo))
			return false;
		if (idVoce == null) {
			if (other.idVoce != null)
				return false;
		} else if (!idVoce.equals(other.idVoce))
			return false;
		return true;
	}
	
}
