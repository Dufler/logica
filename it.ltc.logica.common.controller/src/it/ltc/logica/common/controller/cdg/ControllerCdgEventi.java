package it.ltc.logica.common.controller.cdg;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import it.ltc.logica.common.controller.ControllerCRUD;
import it.ltc.logica.database.model.centrale.cdg.CdgEvento;
import it.ltc.logica.database.model.centrale.cdg.CdgFase;

public class ControllerCdgEventi extends ControllerCRUD<CdgEvento> {
	
	private static final String title = "Controllo di Gestione: Eventi";
	private static final String resource = "cdg/evento";
	
	private static ControllerCdgEventi instance;
	
	private final HashMap<Integer, CdgEvento> eventi;

	private ControllerCdgEventi() {
		super(title, resource, CdgEvento[].class);
		eventi = new HashMap<>();
		caricaDati();
	}

	public static ControllerCdgEventi getInstance() {
		if (instance == null) {
			instance = new ControllerCdgEventi();
		}
		return instance;
	}
	
	public CdgEvento getEvento(int id) {
		return eventi.get(id);
	}
	
	public Collection<CdgEvento> getEventi() {
		return eventi.values();
	}
	
	/**
	 * Restituisce tutti gli eventi appartenenti alla fase specificata nell'argomento.<br>
	 * Nel caso in cui venga passato <code>null</code> o non ci siano eventi viene restituita una lista vuota.
	 * @param faseSelezionata La fase di interesse.
	 * @return Una lista di eventi, eventualmente vuota, che appartengono alla fase specificata.
	 */
	public List<CdgEvento> getEventiPerFase(CdgFase faseSelezionata) {
		List<CdgEvento> eventiPerFase = new LinkedList<>();
		if (faseSelezionata != null)
		for (CdgEvento evento : eventi.values()) {
			if (evento.getFase() == faseSelezionata.getId())
				eventiPerFase.add(evento);
		}
		return eventiPerFase;
	}

	@Override
	protected boolean aggiornaInfoTuttiDati(List<CdgEvento> lista) {
		eventi.clear();
		for (CdgEvento evento : lista) {
			eventi.put(evento.getId(), evento);
		}
		return true;
	}

	@Override
	protected void aggiornaInfoInserimento(CdgEvento cdgEvento, CdgEvento evento) {
		cdgEvento.setId(evento.getId());
		eventi.put(evento.getId(), evento);
	}

	@Override
	protected void aggiornaInfoElemento(CdgEvento object) {
		//TODO - ricarica i dati		
	}

	@Override
	protected void aggiornaInfoEliminazione(CdgEvento cdgEvento) {
		eventi.remove(cdgEvento.getId());
	}

}
