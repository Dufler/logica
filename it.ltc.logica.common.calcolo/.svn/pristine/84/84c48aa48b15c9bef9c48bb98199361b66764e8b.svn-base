package it.ltc.logica.common.calcolo.algoritmi;

import java.util.LinkedList;
import java.util.List;

/**
 * Raccolta di un insieme calcoli fatti sopra un modello valutando un insieme di voci di listino.
 * @author Damiano Bellucci - damiano.bellucci@gmail.com
 *
 */
public class Calcolo {
	
	/**
	 * Indica la finalità del calcolo effettuato: costo o ricavo.
	 * @author Damiano Bellucci - damiano.bellucci@gmail.com
	 *
	 */
	public enum Tipo {
		
		COSTO,
		RICAVO;
	
	}
	
	private final List<VoceCalcolata> voci;
	private final String etichetta;
	private final int idListino;
	private final Tipo tipo;
	
	private boolean costoMinore;
	private boolean ricavoMaggiore;
	
	public Calcolo(String name, int id, Tipo type) {
		etichetta = name;
		idListino = id;
		tipo = type;
		voci = new LinkedList<VoceCalcolata>();
	}
	
	public boolean equals(Object o) {
		boolean uguali = false;
		if (o instanceof Calcolo) {
			Calcolo c = (Calcolo) o;
			uguali = tipo == c.tipo && idListino == c.idListino;
		}
		return uguali;
	}
	
	public void aggiungiVoce(VoceCalcolata voce) {
		voci.add(voce);
	}

	public List<VoceCalcolata> getVoci() {
		return voci;
	}
	
	public void pulisciVoci() {
		voci.clear();
	}

	public String getNome() {
		return etichetta;
	}
	
	public int getIdListino() {
		return idListino;
	}
	
	public Tipo getTipo() {
		return tipo;
	}
	
	public double getTotale() {
		double totale = 0;
		for (VoceCalcolata voce : voci) {
			totale += voce.getCosto();
		}
		return totale;
	}

	public boolean isCostoMinore() {
		return costoMinore;
	}

	public void setCostoMinore(boolean costoMinore) {
		this.costoMinore = costoMinore;
	}

	public boolean isRicavoMaggiore() {
		return ricavoMaggiore;
	}

	public void setRicavoMaggiore(boolean ricavoMaggiore) {
		this.ricavoMaggiore = ricavoMaggiore;
	}

}
