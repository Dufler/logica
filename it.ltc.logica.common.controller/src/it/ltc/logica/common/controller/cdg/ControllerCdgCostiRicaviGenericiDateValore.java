package it.ltc.logica.common.controller.cdg;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import it.ltc.logica.common.controller.ControllerCRUD;
import it.ltc.logica.database.model.centrale.Sede;
import it.ltc.logica.database.model.centrale.cdg.CdgCostiRicaviGenericiDateValore;

public class ControllerCdgCostiRicaviGenericiDateValore extends ControllerCRUD<CdgCostiRicaviGenericiDateValore> {
	
	private static final String title = "Controllo di Gestione: Date e Valori per Costi Generici";
	private static final String resource = "cdg/costiricavigenericidatevalore";
	
	private static ControllerCdgCostiRicaviGenericiDateValore instance;
	
	private final HashMap<Integer, CdgCostiRicaviGenericiDateValore> dateValoriGenerici;

	private ControllerCdgCostiRicaviGenericiDateValore() {
		super(title, resource, CdgCostiRicaviGenericiDateValore[].class);
		dateValoriGenerici = new HashMap<>();
		caricaDati();
	}

	public static synchronized ControllerCdgCostiRicaviGenericiDateValore getInstance() {
		if (null == instance) {
			instance = new ControllerCdgCostiRicaviGenericiDateValore();
		}
		return instance;
	}
	
	public CdgCostiRicaviGenericiDateValore getDettaglio(int id) {
		return dateValoriGenerici.get(id);
	}
	
	public Collection<CdgCostiRicaviGenericiDateValore> getDettagli() {
		return dateValoriGenerici.values();
	}
	
	public List<CdgCostiRicaviGenericiDateValore> getDettagliPerGenerico(int idGenerico) {
		List<CdgCostiRicaviGenericiDateValore> lista = new LinkedList<>();
		for (CdgCostiRicaviGenericiDateValore elemento : getDettagli()) {
			if (elemento.getGenerico() == idGenerico)
				lista.add(elemento);
		}
		return lista;
	}
	
	public List<CdgCostiRicaviGenericiDateValore> getDettagliPerGenerico(int idGenerico, Date inizio, Date fine, Sede sede) {
		List<CdgCostiRicaviGenericiDateValore> lista = new LinkedList<>();
		for (CdgCostiRicaviGenericiDateValore elemento : getDettagli()) {
			if (elemento.getGenerico() == idGenerico)
				if (elemento.getSede() == null || elemento.getSede() == sede.getId())
					if (elemento.getDataInizio().after(inizio) || elemento.getDataInizio().before(fine) || elemento.getDataFine().after(inizio) || elemento.getDataFine().before(fine))
						lista.add(elemento);
		}
		return lista;
	}
	

	@Override
	protected void aggiornaInfoInserimento(CdgCostiRicaviGenericiDateValore object,	CdgCostiRicaviGenericiDateValore nuovo) {
		object.setId(nuovo.getId());
		dateValoriGenerici.put(object.getId(), object);
	}

	@Override
	protected void aggiornaInfoElemento(CdgCostiRicaviGenericiDateValore object) {
		// TODO Auto-generated method stub
	}

	@Override
	protected void aggiornaInfoEliminazione(CdgCostiRicaviGenericiDateValore object) {
		dateValoriGenerici.remove(object.getId());		
	}

	@Override
	protected boolean aggiornaInfoTuttiDati(List<CdgCostiRicaviGenericiDateValore> lista) {
		for (CdgCostiRicaviGenericiDateValore elemento : lista) {
			dateValoriGenerici.put(elemento.getId(), elemento);
		}
		return true;
	}

}
