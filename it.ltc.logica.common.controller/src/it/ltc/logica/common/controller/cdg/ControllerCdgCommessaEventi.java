package it.ltc.logica.common.controller.cdg;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import it.ltc.logica.common.controller.ControllerCRUD;
import it.ltc.logica.database.model.centrale.cdg.CdgCommessaEvento;

public class ControllerCdgCommessaEventi extends ControllerCRUD<CdgCommessaEvento> {
	
	private static final String title = "Controllo di Gestione: Abbinamento Commessa-Eventi";
	private static final String resource = "cdg/commessaevento";

	private static ControllerCdgCommessaEventi instance;

	private final HashMap<CdgCommessaEvento, CdgCommessaEvento> elementi;
	
	private ControllerCdgCommessaEventi() {
		super(title, resource, CdgCommessaEvento[].class);
		elementi = new HashMap<>();
		caricaDati();
	}

	public static ControllerCdgCommessaEventi getInstance() {
		if (instance == null) {
			instance = new ControllerCdgCommessaEventi();
		}
		return instance;
	}
	
	public CdgCommessaEvento getEvento(int commessa, int evento) {
		CdgCommessaEvento chiaveDiRicerca = new CdgCommessaEvento();
		chiaveDiRicerca.setCommessa(commessa);
		chiaveDiRicerca.setEvento(evento);
		return elementi.get(chiaveDiRicerca);
	}
	
	public Collection<CdgCommessaEvento> getEventi() {
		return elementi.values();
	}

	@Override
	protected boolean aggiornaInfoTuttiDati(List<CdgCommessaEvento> lista) {
		elementi.clear();
		for (CdgCommessaEvento abbinamento : lista) {
			elementi.put(abbinamento, abbinamento);
		}
		return true;
	}

	@Override
	protected void aggiornaInfoInserimento(CdgCommessaEvento object, CdgCommessaEvento abbinamento) {
		elementi.put(abbinamento, abbinamento);		
	}

	@Override
	protected void aggiornaInfoElemento(CdgCommessaEvento object) {
		//TODO - ricarica i dati		
	}

	@Override
	protected void aggiornaInfoEliminazione(CdgCommessaEvento cdgCommessaEvento) {
		elementi.remove(cdgCommessaEvento);
	}
	
}
