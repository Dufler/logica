package it.ltc.logica.common.controller.cdg;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import it.ltc.logica.common.controller.ControllerCRUD;
import it.ltc.logica.database.model.centrale.cdg.CdgCostoAssociazione;

public class ControllerCdgAssociazioni extends ControllerCRUD<CdgCostoAssociazione>{
	
	private static final String title = "Controllo di Gestione: Associazioni Operatori";
	private static final String resource = "cdg/costoassociazione";
	
	private static ControllerCdgAssociazioni instance;
	
	private final HashMap<Integer, CdgCostoAssociazione> associazioni;

	private ControllerCdgAssociazioni() {
		super(title, resource, CdgCostoAssociazione[].class);
		associazioni = new HashMap<>();
		caricaDati();
	}

	public static ControllerCdgAssociazioni getInstance() {
		if (instance == null) {
			instance = new ControllerCdgAssociazioni();
		}
		return instance;
	}
	
	public CdgCostoAssociazione getAssociazione(int id) {
		return associazioni.get(id);
	}
	
	public Collection<CdgCostoAssociazione> getAssociazioni() {
		return associazioni.values();
	}

	@Override
	protected boolean aggiornaInfoTuttiDati(List<CdgCostoAssociazione> lista) {
		associazioni.clear();
		for (CdgCostoAssociazione associazione : lista) {
			associazioni.put(associazione.getId(), associazione);
		}
		return true;
	}

	@Override
	protected void aggiornaInfoInserimento(CdgCostoAssociazione associazione, CdgCostoAssociazione nuova) {
		associazione.setId(nuova.getId());
		associazioni.put(nuova.getId(), nuova);
	}

	@Override
	protected void aggiornaInfoElemento(CdgCostoAssociazione object) {
		//TODO - ricarica i dati
		
	}

	@Override
	protected void aggiornaInfoEliminazione(CdgCostoAssociazione associazione) {
		associazioni.remove(associazione.getId());
	}

}
