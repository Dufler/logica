package it.ltc.logica.common.controller.sistema;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import it.ltc.logica.common.controller.ControllerReadOnly;
import it.ltc.logica.database.model.centrale.StatoOrdine;

public class ControllerStatoTracking extends ControllerReadOnly<StatoOrdine> {
	
	private static final String title = "Stati Tracking";
	private static final String resource = "trackingstato";
	
	private static ControllerStatoTracking instance;
	
	private final HashMap<String, StatoOrdine> stati;

	private ControllerStatoTracking() {
		super(title, resource, StatoOrdine[].class);
		stati = new HashMap<String, StatoOrdine>();
		caricaDati();
	}

	public static ControllerStatoTracking getInstance() {
		if (instance == null) {
			instance = new ControllerStatoTracking();
		}
		return instance;
	}
	
	public StatoOrdine getStato(String nome) {
		return stati.get(nome);
	}
	
	public Collection<StatoOrdine> getStati() {
		return stati.values();
	}

	@Override
	protected boolean aggiornaInfoTuttiDati(List<StatoOrdine> lista) {
		stati.clear();
		for (StatoOrdine stato : lista) {
			stati.put(stato.getCodice(), stato);
		}
		return true;
	}

}
