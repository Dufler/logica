package it.ltc.logica.common.controller.cdg;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import it.ltc.logica.common.controller.ControllerCRUD;
import it.ltc.logica.database.model.centrale.cdg.CdgPezzo;

public class ControllerCdgPezzi extends ControllerCRUD<CdgPezzo> {

	private static final String title = "Controllo di Gestione: Costi e Ricavi per pezzo.";
	private static final String resource = "cdg/pezzo";
	
	private static ControllerCdgPezzi instance;
	
	private final HashMap<Integer, CdgPezzo> pezzi;

	private ControllerCdgPezzi() {
		super(title, resource, CdgPezzo[].class);
		
		pezzi = new HashMap<>();
		caricaDati();
	}

	public static ControllerCdgPezzi getInstance() {
		if (instance == null) {
			instance = new ControllerCdgPezzi();
		}
		return instance;
	}
	
	public CdgPezzo getPezzo(int id) {
		return pezzi.get(id);
	}
	
	public Collection<CdgPezzo> getPezzi() {
		return pezzi.values();
	}
	
	public List<CdgPezzo> getPezzoDaCommessa(int commessa) {
		List<CdgPezzo> pezziCommessa = new LinkedList<CdgPezzo>();
		for (CdgPezzo pezzo : pezzi.values()) {
			if (pezzo.getCommessa() == commessa) {
				pezziCommessa.add(pezzo);
			}
		}
		return pezziCommessa;
	}
	
	public CdgPezzo getPezzoDaCategoriaECommessa(String categoria, int commessa) {
		CdgPezzo pezzoEventoCommessa = null;
		for (CdgPezzo pezzo : pezzi.values()) {
			if (pezzo.getCommessa() == commessa && pezzo.getCategoriaMerceologica().equals(categoria)) {
				pezzoEventoCommessa = pezzo;
				break;
			}
		}
		return pezzoEventoCommessa;
	}

	@Override
	protected void aggiornaInfoInserimento(CdgPezzo object, CdgPezzo nuovo) {
		object.setId(nuovo.getId());
		pezzi.put(nuovo.getId(), nuovo);
	}

	@Override
	protected void aggiornaInfoElemento(CdgPezzo object) {
		// TODO - Refresh		
	}

	@Override
	protected void aggiornaInfoEliminazione(CdgPezzo pezzo) {
		pezzi.remove(pezzo.getId());
	}

	@Override
	protected boolean aggiornaInfoTuttiDati(List<CdgPezzo> lista) {
		pezzi.clear();
		for (CdgPezzo pezzo : lista) {
			pezzi.put(pezzo.getId(), pezzo);
		}
		return true;
	}
	
}
