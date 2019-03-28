package it.ltc.logica.common.controller.trasporti;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import it.ltc.logica.common.controller.ControllerReadOnly;
import it.ltc.logica.database.model.centrale.trasporti.ServizioSpedizione;

public class ControllerServizioSpedizione extends ControllerReadOnly<ServizioSpedizione> {
	
	private final static String title = "Caricamento tipi di servizio spedizioni";
	private final static String resource = "spedizione/servizio";
	
	private static ControllerServizioSpedizione instance;
	
	private final HashMap<String, ServizioSpedizione> servizi;

	private ControllerServizioSpedizione() {
		super(title, resource, ServizioSpedizione[].class);
		servizi = new HashMap<String, ServizioSpedizione>();
		caricaDati();
	}

	public static synchronized ControllerServizioSpedizione getInstance() {
		if (null == instance) {
			instance = new ControllerServizioSpedizione();
		}
		return instance;
	}

	@Override
	protected boolean aggiornaInfoTuttiDati(List<ServizioSpedizione> lista) {
		servizi.clear();
		for (ServizioSpedizione servizio : lista) {
			servizi.put(servizio.getCodice(), servizio);
		}
		return true;
	}
	
	public Collection<ServizioSpedizione> getServizi() {
		return servizi.values();
	}

	public ServizioSpedizione getServizio(String codice) {
		return servizi.get(codice);
	}

}
