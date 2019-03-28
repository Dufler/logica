package it.ltc.logica.common.controller.trasporti;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import it.ltc.logica.common.controller.ControllerReadOnly;
import it.ltc.logica.database.model.centrale.indirizzi.Nazione;

public class ControllerNazioni extends ControllerReadOnly<Nazione> {
	
	private static final String title = "Nazioni";
	private static final String resource = "nazione";
	
	private static ControllerNazioni instance;
	
	private final HashMap<String, Nazione> nazioni;

	private ControllerNazioni() {
		super(title, resource, Nazione[].class);
		nazioni = new HashMap<String, Nazione>();
		caricaDati();
	}

	public static synchronized ControllerNazioni getInstance() {
		if (null == instance) {
			instance = new ControllerNazioni();
		}
		return instance;
	}
	
	public Nazione getDefault() {
		return getNazione("ITA");
	}
	
	public Nazione getNazione(String codice) {
		return nazioni.get(codice);
	}
	
	public Collection<Nazione> getNazioni() {
		return nazioni.values();
	}

	@Override
	protected boolean aggiornaInfoTuttiDati(List<Nazione> lista) {
		nazioni.clear();
		for (Nazione nazione : lista) {
			nazioni.put(nazione.getCodiceIsoTre(), nazione);
		}
		return true;
	}

}
