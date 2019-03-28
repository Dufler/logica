package it.ltc.logica.common.controller.sistema;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import it.ltc.logica.common.controller.ControllerReadOnly;
import it.ltc.logica.common.ws.RestClient;
import it.ltc.logica.database.model.centrale.Sede;

public class ControllerSedi extends ControllerReadOnly<Sede> {
	
	private static final String title = "Sedi";
	private static final String resource = "sede";
	
	private static ControllerSedi instance;
	
	private final HashMap<Integer, Sede> sedi;

	private ControllerSedi() {
		super(title, resource, Sede[].class);
		sedi = new HashMap<Integer, Sede>();
		caricaDati();
	}

	public static ControllerSedi getInstance() {
		if (instance == null) {
			instance = new ControllerSedi();
		}
		return instance;
	}
	
	public Sede getSede(int idSede) {
		return sedi.get(idSede);
	}
	
	public Collection<Sede> getSedi() {
		return sedi.values();
	}

	@Override
	protected boolean aggiornaInfoTuttiDati(List<Sede> lista) {
		sedi.clear();
		for (Sede sede : lista) {
			sedi.put(sede.getId(), sede);
		}
		return true;
	}
	
	@Override
	public String getContextPath() {
		return RestClient.CONTEXT_PATH_SEDE;
	}
	
	public String getDomain() {
		return RestClient.DOMAIN_SEDE;
	}

}
