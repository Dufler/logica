package it.ltc.logica.common.controller.sistema;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import it.ltc.logica.common.controller.ControllerCRUD;
import it.ltc.logica.database.model.centrale.Feature;
import it.ltc.logica.database.model.centrale.Funzione;

public class ControllerFunzioni extends ControllerCRUD<Funzione> {
	
	private static final String title = "Caricamento funzioni";
	private static final String resource = "funzione";
	
	private static ControllerFunzioni instance;
	
	private final HashMap<String, Funzione> funzioni;

	private ControllerFunzioni() {
		super(title, resource, Funzione[].class);
		funzioni = new HashMap<String, Funzione>();
		caricaDati();
	}

	public static ControllerFunzioni getInstance() {
		if (instance == null) {
			instance = new ControllerFunzioni();
		}
		return instance;
	}
	
	public Funzione getFunzione(String nome) {
		return funzioni.get(nome);
	}
	
	public Collection<Funzione> getFunzioni() {
		return funzioni.values();
	}
	
	public List<Funzione> getFunzioniPerFeature(Feature feature) {
		List<Funzione> funzioniFeature = new LinkedList<>();
		for (Funzione funzione : funzioni.values()) {
			if (funzione.getFeature().equals(feature.getFeatureid())) {
				funzioniFeature.add(funzione);
			}
		}
		return funzioniFeature;
	}

	@Override
	protected boolean aggiornaInfoTuttiDati(List<Funzione> lista) {
		funzioni.clear();
		for (Funzione Funzione : lista) {
			funzioni.put(Funzione.getPartid(), Funzione);
		}
		return true;
	}

	@Override
	protected void aggiornaInfoInserimento(Funzione object, Funzione nuovo) {
		funzioni.put(nuovo.getPartid(), nuovo);	
	}

	@Override
	protected void aggiornaInfoElemento(Funzione object) {
		funzioni.put(object.getPartid(), object);			
	}

	@Override
	protected void aggiornaInfoEliminazione(Funzione object) {
		funzioni.remove(object.getPartid());
	}

}
