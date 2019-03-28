package it.ltc.logica.common.controller.trasporti;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import it.ltc.logica.common.controller.ControllerReadOnly;
import it.ltc.logica.database.model.centrale.trasporti.TipoContrassegno;

public class ControllerTipoContrassegno extends ControllerReadOnly<TipoContrassegno> {

	private static final String title = "Caricamento dei tipi di contrassegno";
	private static final String resource = "contrassegno/tipi";
	
	private final HashMap<String, TipoContrassegno> tipi;
	
	private static ControllerTipoContrassegno instance;

	private ControllerTipoContrassegno() {
		super(title, resource, TipoContrassegno[].class);
		tipi = new HashMap<>();
	}

	public static synchronized ControllerTipoContrassegno getInstance() {
		if (null == instance) {
			instance = new ControllerTipoContrassegno();
		}
		return instance;
	}

	@Override
	protected boolean aggiornaInfoTuttiDati(List<TipoContrassegno> lista) {
		tipi.clear();
		for (TipoContrassegno tipo : lista) {
			tipi.put(tipo.getCodice(), tipo);
		}
		return true;
	}
	
	public Collection<TipoContrassegno> getTipiContrassegno() {
		return tipi.values();
	}

	public TipoContrassegno getTipoContrassegno(String codice) {
		return tipi.get(codice);
	}

}
