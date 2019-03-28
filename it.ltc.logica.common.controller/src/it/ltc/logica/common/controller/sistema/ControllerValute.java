package it.ltc.logica.common.controller.sistema;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import it.ltc.logica.common.controller.ControllerReadOnly;
import it.ltc.logica.database.model.centrale.Valuta;

public class ControllerValute extends ControllerReadOnly<Valuta> {
	
	private static final String title = "Caricamento feature";
	private static final String resource = "valuta";

	private static ControllerValute instance;
	
	private final HashMap<String, Valuta> valute;

	private ControllerValute() {
		super(title, resource, Valuta[].class);
		valute = new HashMap<String, Valuta>();
		caricaDati();
	}

	public static ControllerValute getInstance() {
		if (instance == null) {
			instance = new ControllerValute();
		}
		return instance;
	}
	
	public Valuta getValuta(String nome) {
		return valute.get(nome);
	}
	
	public Collection<Valuta> getValute() {
		return valute.values();
	}

	@Override
	protected boolean aggiornaInfoTuttiDati(List<Valuta> lista) {
		valute.clear();
		for (Valuta valuta : lista) {
			valute.put(valuta.getCodice(), valuta);
		}
		return true;
	}
	
}
