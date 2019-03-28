package it.ltc.logica.common.controller.trasporti;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import it.ltc.logica.common.controller.ControllerReadOnly;
import it.ltc.logica.database.model.centrale.indirizzi.Provincia;

public class ControllerProvince extends ControllerReadOnly<Provincia> {
	
	private final static String title = "Province";
	private final static String resource = "provincia";
	
	private static ControllerProvince instance;
	
	private final HashMap<String, Provincia> province;

	private ControllerProvince() {
		super(title, resource, Provincia[].class);
		province = new HashMap<String, Provincia>();
		caricaDati();
	}

	public static synchronized ControllerProvince getInstance() {
		if (null == instance) {
			instance = new ControllerProvince();
		}
		return instance;
	}
	
	public Provincia getProvincia(String sigla) {
		return province.get(sigla);
	}
	
	public Collection<Provincia> getProvince() {
		return province.values();
	}

	@Override
	protected boolean aggiornaInfoTuttiDati(List<Provincia> lista) {
		province.clear();
		for (Provincia provincia : lista) {
			province.put(provincia.getSigla(), provincia);
		}
		return true;
	}

}
