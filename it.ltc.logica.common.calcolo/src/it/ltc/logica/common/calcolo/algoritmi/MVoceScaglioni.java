package it.ltc.logica.common.calcolo.algoritmi;

import java.util.LinkedList;

public class MVoceScaglioni implements MDettaglioVoce {
	
	private final LinkedList<Scaglione> scaglioni;
	
	public MVoceScaglioni() {
		scaglioni = new LinkedList<Scaglione>();
	}
	
	public void addScaglione(Scaglione scaglione) {
		scaglioni.add(scaglione);
	}

	public LinkedList<Scaglione> getScaglioni() {
		return scaglioni;
	}
	
	public void resetScaglioni() {
		scaglioni.clear();
	}

}
