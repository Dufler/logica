package it.ltc.logica.amministrazione.gui.controllodigestione.controller;

import java.util.LinkedList;
import java.util.List;

import it.ltc.logica.database.model.centrale.trasporti.Spedizione;

public class SpedizioniCDGController {
	
	private static SpedizioniCDGController instance;
	
	private List<Spedizione> spedizioni;
	
	private SpedizioniCDGController() {
		caricaDati();
	}
	
	public static SpedizioniCDGController getInstance() {
		if (instance == null) {
			instance = new SpedizioniCDGController();
		}
		return instance;
	}
	
	private void caricaDati() {
		spedizioni = new LinkedList<>();
		//TODO
	}

	public List<Spedizione> getSpedizioni() {
		return spedizioni;
	}

}
