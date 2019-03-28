package it.ltc.logica.common.calcolo.algoritmi;

/**
 * Rappresenta una voce di listino in maniera astratta, viene usata per fare calcoli.
 * 
 * @author Damiano Bellucci - damiano.bellucci@gmail.com
 *
 */
public abstract class MVoceDiListino<T extends ModelDaCalcolare> implements Comparable<MVoceDiListino<T>> {
	
	/**
	 * Indica lo scopo del calcolo per una determinata spedizione: ricavo oppure costo. 
	 * @author Damiano Bellucci - damiano.bellucci@gmail.com
	 *
	 */
	public enum Scopo {
		
		RICAVO,
		COSTO,
		NESSUNO;
	
	}

	protected final Integer idListino;
	protected final Integer idVoce;
	protected final String nomeVoce;
	protected final IAmbito<T> ambito;
	protected final IAlgoritmo algoritmo;
	
	protected final MDettaglioVoce dettagli;
	
	protected final Scopo scopo;
	
	/**
	 * Crea una nuova voce di listino da utilizzare per i calcoli utilizzando i valori passati come argomento.
	 * @param idListino l'ID del listino che contiene la voce.
	 * @param idVoce l'ID della voce di listino.
	 * @param nomeVoce il nome della voce di listino.
	 * @param ambito l'ambito di applicazione della voce di listino.
	 * @param algoritmo l'algoritmo da utilizzare per calcolare la voce di listino.
	 * @param minimo il minimo fatturabile per la voce di listino.
	 * @param massimo il massimo fatturabile per la voce di listino.
	 */
	public MVoceDiListino(Integer idListino, Integer idVoce, String nomeVoce, IAmbito<T> ambito, IAlgoritmo algoritmo, MDettaglioVoce dettagli, /*Double minimo, Double massimo,*/ Scopo scopo) {
		this.idListino = idListino;
		this.idVoce = idVoce;
		this.nomeVoce = nomeVoce;
		this.ambito = ambito;
		this.algoritmo = algoritmo;
		this.dettagli = dettagli;
		this.scopo = scopo;
	}

	public Integer getIdListino() {
		return idListino;
	}

	public Integer getIdVoce() {
		return idVoce;
	}

	public String getNome() {
		return nomeVoce;
	}

	public IAmbito<T> getAmbito() {
		return ambito;
	}

	public IAlgoritmo getAlgoritmo() {
		return algoritmo;
	}
	
	public MDettaglioVoce getDettagli() {
		return dettagli;
	}
	
	public Scopo getScopo() {
		return scopo;
	}

	public void calcola(T model) {
		double costo = ambito.calcolaValore(model, this);
		if (costo > 0) {
			Calcolo calcolo = model.getCalcolo(idListino);
			VoceCalcolata voce = new VoceCalcolata(idVoce, nomeVoce, ambito.getId(), ambito.getNome(), ambito.getCategoria(), ambito.getDescrizione());
			voce.setCosto(costo);
			calcolo.aggiungiVoce(voce);
		}
	}
	
	@Override
	public String toString() {
		return "MVoceDiListino [nomeVoce=" + nomeVoce + ", ambito=" + ambito + ", algoritmo=" + algoritmo + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		MVoceDiListino<?> other = (MVoceDiListino<?>) obj;
		if (idVoce == null) {
			if (other.idVoce != null)
				return false;
		} else if (!idVoce.equals(other.idVoce))
			return false;
		return true;
	}

	@Override
	public int compareTo(MVoceDiListino<T> o) {
		TipoAlgoritmo t1 = algoritmo.getTipoAlgoritmo();
		TipoAlgoritmo t2 = o.getAlgoritmo().getTipoAlgoritmo();
		int compare = t1.compareTo(t2);
		return compare;
	}

}
