package it.ltc.logica.common.controller.cdg;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import it.ltc.logica.common.controller.ControllerCRUD;
import it.ltc.logica.database.model.centrale.cdg.CdgFase;

public class ControllerCdgFasi extends ControllerCRUD<CdgFase> {
	
	private static final String title = "Controllo di Gestione: Fasi Logistiche";
	private static final String resource = "cdg/fase";
	
	private static ControllerCdgFasi instance;
	
	private final HashMap<Integer, CdgFase> fasi;

	private ControllerCdgFasi() {
		super(title, resource, CdgFase[].class);
		fasi = new HashMap<>();
		caricaDati();
	}

	public static ControllerCdgFasi getInstance() {
		if (instance == null) {
			instance = new ControllerCdgFasi();
		}
		return instance;
	}
	
	public CdgFase getFase(int id) {
		return fasi.get(id);
	}
	
	public Collection<CdgFase> getFasi() {
		return fasi.values();
	}

	@Override
	protected boolean aggiornaInfoTuttiDati(List<CdgFase> lista) {
		fasi.clear();
		for (CdgFase categoria : lista) {
			fasi.put(categoria.getId(), categoria);
		}
		return true;
	}

	@Override
	protected void aggiornaInfoInserimento(CdgFase object, CdgFase nuovo) {
		object.setId(nuovo.getId());
		fasi.put(nuovo.getId(), nuovo);	
	}

	@Override
	protected void aggiornaInfoElemento(CdgFase object) {
		// TODO - Ricarica		
	}

	@Override
	protected void aggiornaInfoEliminazione(CdgFase object) {
		fasi.remove(object.getId());		
	}

}
