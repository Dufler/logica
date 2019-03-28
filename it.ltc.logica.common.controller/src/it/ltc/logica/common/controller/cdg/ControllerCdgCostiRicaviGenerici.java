package it.ltc.logica.common.controller.cdg;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import it.ltc.logica.common.controller.ControllerCRUD;
import it.ltc.logica.database.model.centrale.cdg.CdgCostiRicaviGenerici;

public class ControllerCdgCostiRicaviGenerici extends ControllerCRUD<CdgCostiRicaviGenerici> {
	
	private static final String title = "Controllo di Gestione: Costi Generici";
	private static final String resource = "cdg/costiricavigenerici";
	
	private static ControllerCdgCostiRicaviGenerici instance;

	private final HashMap<Integer, CdgCostiRicaviGenerici> bilanci;
	
	private ControllerCdgCostiRicaviGenerici() {
		super(title, resource, CdgCostiRicaviGenerici[].class);
		bilanci = new HashMap<>();
		caricaDati();
	}

	public static ControllerCdgCostiRicaviGenerici getInstance() {
		if (instance == null) {
			instance = new ControllerCdgCostiRicaviGenerici();
		}
		return instance;
	}
	
	public CdgCostiRicaviGenerici getCostoRicavoGenericoDaID(int id) {
		return bilanci.get(id);
	}
	
	public Collection<CdgCostiRicaviGenerici> getCostiRicavi() {
		return bilanci.values();
	}
	
//	public List<CdgCostiRicaviGenerici> getCostiRicaviSelezionati(Sede sede) {
//		List<CdgCostiRicaviGenerici> selezionati = new LinkedList<>();
//		for (CdgCostiRicaviGenerici generico : getCostiRicavi()) {
//			//Check sulla sede
//			if (generico.getSede() == null || generico.getSede() == sede.getId()) {
//				selezionati.add(generico);
//			}
//		}
//		return selezionati;
//	}

	@Override
	protected void aggiornaInfoInserimento(CdgCostiRicaviGenerici object, CdgCostiRicaviGenerici nuovo) {
		object.setId(nuovo.getId());
		bilanci.put(nuovo.getId(), nuovo);
	}

	@Override
	protected void aggiornaInfoElemento(CdgCostiRicaviGenerici object) {
		// TODO - Refresh		
	}

	@Override
	protected void aggiornaInfoEliminazione(CdgCostiRicaviGenerici bilancio) {
		bilanci.remove(bilancio.getId());		
	}

	@Override
	protected boolean aggiornaInfoTuttiDati(List<CdgCostiRicaviGenerici> lista) {
		for (CdgCostiRicaviGenerici bilancio : lista) {
			bilanci.put(bilancio.getId(), bilancio);
		}
		return true;
	}

}
