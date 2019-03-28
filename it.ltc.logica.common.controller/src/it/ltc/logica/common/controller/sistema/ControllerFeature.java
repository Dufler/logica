package it.ltc.logica.common.controller.sistema;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import it.ltc.logica.common.controller.ControllerCRUD;
import it.ltc.logica.database.model.centrale.Feature;

public class ControllerFeature extends ControllerCRUD<Feature> {
	
	private static final String title = "Caricamento feature";
	private static final String resource = "feature";
	
	private static ControllerFeature instance;
	
	private final HashMap<String, Feature> features;

	private ControllerFeature() {
		super(title, resource, Feature[].class);
		features = new HashMap<String, Feature>();
		caricaDati();
	}

	public static ControllerFeature getInstance() {
		if (instance == null) {
			instance = new ControllerFeature();
		}
		return instance;
	}
	
	public Feature getFeature(String nome) {
		return features.get(nome);
	}
	
	public Feature getFeatureDaID(String idFeature) {
		Feature feature = null;
		for (Feature f : features.values()) {
			if (f.getFeatureid().equals(idFeature)) {
				feature = f;
				break;
			}
		}
		return feature;
	}
	
	public Collection<Feature> getFeatures() {
		return features.values();
	}

	@Override
	protected boolean aggiornaInfoTuttiDati(List<Feature> lista) {
		features.clear();
		for (Feature feature : lista) {
			features.put(feature.getFeatureid(), feature);
		}
		return true;
	}

	@Override
	protected void aggiornaInfoInserimento(Feature object, Feature feature) {
		features.put(feature.getFeatureid(), feature);	
	}

	@Override
	protected void aggiornaInfoElemento(Feature feature) {
		features.put(feature.getFeatureid(), feature);	
	}

	@Override
	protected void aggiornaInfoEliminazione(Feature feature) {
		features.remove(feature.getFeatureid());		
	}

}
