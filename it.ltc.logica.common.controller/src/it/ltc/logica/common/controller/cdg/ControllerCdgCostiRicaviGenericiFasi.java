package it.ltc.logica.common.controller.cdg;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import it.ltc.logica.common.controller.ControllerCRUD;
import it.ltc.logica.database.model.centrale.cdg.CdgCostiRicaviGenericiFase;

public class ControllerCdgCostiRicaviGenericiFasi extends ControllerCRUD<CdgCostiRicaviGenericiFase> {
	
	private static final String title = "Controllo di Gestione: Percentuale Fase per Costi Generici";
	private static final String resource = "cdg/costiricavigenericifase";
	
	private static ControllerCdgCostiRicaviGenericiFasi instance;
	
	private final Set<CdgCostiRicaviGenericiFase> percentuali;

	private ControllerCdgCostiRicaviGenericiFasi() {
		super(title, resource, CdgCostiRicaviGenericiFase[].class);
		percentuali = new HashSet<>();
		caricaDati();
	}

	public static synchronized ControllerCdgCostiRicaviGenericiFasi getInstance() {
		if (null == instance) {
			instance = new ControllerCdgCostiRicaviGenericiFasi();
		}
		return instance;
	}
	
	public Collection<CdgCostiRicaviGenericiFase> getPercentuali() {
		return percentuali;
	}
	
	public List<CdgCostiRicaviGenericiFase> getPercentualiPerGenerico(int idGenerico) {
		List<CdgCostiRicaviGenericiFase> lista = new LinkedList<>();
		for (CdgCostiRicaviGenericiFase elemento : percentuali) {
			if (elemento.getGenerico() == idGenerico)
				lista.add(elemento);
		}
		return lista;
	}

	@Override
	protected void aggiornaInfoInserimento(CdgCostiRicaviGenericiFase object, CdgCostiRicaviGenericiFase nuovo) {
		percentuali.add(nuovo);		
	}

	@Override
	protected void aggiornaInfoElemento(CdgCostiRicaviGenericiFase object) {
		// TODO Auto-generated method stub
	}

	@Override
	protected void aggiornaInfoEliminazione(CdgCostiRicaviGenericiFase object) {
		percentuali.remove(object);		
	}

	@Override
	protected boolean aggiornaInfoTuttiDati(List<CdgCostiRicaviGenericiFase> lista) {
		percentuali.clear();
		percentuali.addAll(lista);
		return true;
	}

}
