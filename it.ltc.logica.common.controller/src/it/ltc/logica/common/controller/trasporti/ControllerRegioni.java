package it.ltc.logica.common.controller.trasporti;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import it.ltc.logica.common.controller.ControllerReadOnly;
import it.ltc.logica.database.model.centrale.indirizzi.Regione;

public class ControllerRegioni extends ControllerReadOnly<Regione> {
	
	private final static String title = "Regioni";
	private final static String resource = "regione";
	
	private final HashMap<String, Regione> regioni;
	
	private static ControllerRegioni instance;

	private ControllerRegioni() {
		super(title, resource, Regione[].class);
		regioni = new HashMap<String, Regione>();
		caricaDati();
	}

	public static synchronized ControllerRegioni getInstance() {
		if (null == instance) {
			instance = new ControllerRegioni();
		}
		return instance;
	}
	
	public Regione getRegione(String sigla) {
		return regioni.get(sigla);
	}
	
	public Collection<Regione> getRegioni() {
		return regioni.values();
	}

	@Override
	protected boolean aggiornaInfoTuttiDati(List<Regione> lista) {
		regioni.clear();
		for (Regione regione : lista) {
			regioni.put(regione.getCodice(), regione);
		}
		return true;
	}

}
