package it.ltc.logica.trasporti.controller.preventivi;

import java.util.List;

import it.ltc.logica.trasporti.calcolo.algoritmi.SpedizioneModel;

/**
 * Oggetto che incarta l'esito di una simulazione realizzata dal controller preventivi.
 * @author Damiano Bellucci - damiano.bellucci@gmail.com
 *
 */
public class EsitoSimulazione {
	
	private final int colli;
	private final int pezzi;
	private final int spedizioni;
	private final double totaleCosti;
	private boolean costiPresenti;
	private final List<SpedizioneModel> calcoli;
	private final List<TotaleListino> totali;
	
	/**
	 * Costruttore di deafult che imposta tutti i valori dell'esito della simulazione.
	 * @param colli
	 * @param pezzi
	 * @param spedizioni
	 * @param calcoli
	 * @param totali
	 */
	public EsitoSimulazione(int colli, int pezzi, int spedizioni, double totaleCosti, List<SpedizioneModel> calcoli, List<TotaleListino> totali) {
		super();
		this.colli = colli;
		this.pezzi = pezzi;
		this.spedizioni = spedizioni;
		this.totaleCosti = totaleCosti;
		this.calcoli = calcoli;
		this.totali = totali;
	}

	public int getColli() {
		return colli;
	}

	public int getPezzi() {
		return pezzi;
	}

	public int getSpedizioni() {
		return spedizioni;
	}

	public double getTotaleCosti() {
		return totaleCosti;
	}

	public boolean isCostiPresenti() {
		return costiPresenti;
	}

	public void setCostiPresenti(boolean costiPresenti) {
		this.costiPresenti = costiPresenti;
	}

	public List<SpedizioneModel> getCalcoli() {
		return calcoli;
	}

	public List<TotaleListino> getTotali() {
		return totali;
	}

}
