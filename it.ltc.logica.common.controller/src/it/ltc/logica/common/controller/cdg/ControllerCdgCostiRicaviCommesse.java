package it.ltc.logica.common.controller.cdg;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import it.ltc.logica.common.controller.ControllerCRUD;
import it.ltc.logica.database.model.centrale.cdg.CdgCostoRicavoCommessa;

public class ControllerCdgCostiRicaviCommesse extends ControllerCRUD<CdgCostoRicavoCommessa> {
	
	private static final String title = "Controllo di Gestione: Costi e Ricavo per Commesse";
	private static final String resource = "cdg/costiricavicommesse";
	
	private static ControllerCdgCostiRicaviCommesse instance;

	private final HashMap<Integer, CdgCostoRicavoCommessa> bilanci;
	
	private ControllerCdgCostiRicaviCommesse() {
		super(title, resource, CdgCostoRicavoCommessa[].class);
		bilanci = new HashMap<>();
		caricaDati();
	}

	public static ControllerCdgCostiRicaviCommesse getInstance() {
		if (instance == null) {
			instance = new ControllerCdgCostiRicaviCommesse();
		}
		return instance;
	}
	
	public Collection<CdgCostoRicavoCommessa> getCostiRicavi() {
		return bilanci.values();
	}
	
	public List<CdgCostoRicavoCommessa> getRiepilogo(FiltroRiepilogo filtro) {
		List<CdgCostoRicavoCommessa> lista = new LinkedList<>();
		for (CdgCostoRicavoCommessa bilancio : bilanci.values()) {
			if (bilancio.getCommessa() == filtro.getCommessa() && bilancio.getDataEmissione().after(filtro.getInizio()) && bilancio.getDataEmissione().before(filtro.getFine()))
				lista.add(bilancio);
		}
		return lista;
	}

	@Override
	protected void aggiornaInfoInserimento(CdgCostoRicavoCommessa object, CdgCostoRicavoCommessa nuovo) {
		bilanci.put(nuovo.getId(), nuovo);
	}

	@Override
	protected void aggiornaInfoElemento(CdgCostoRicavoCommessa object) {
		// TODO - Refresh		
	}

	@Override
	protected void aggiornaInfoEliminazione(CdgCostoRicavoCommessa bilancio) {
		bilanci.remove(bilancio.getId());		
	}

	@Override
	protected boolean aggiornaInfoTuttiDati(List<CdgCostoRicavoCommessa> lista) {
		for (CdgCostoRicavoCommessa bilancio : lista) {
			bilanci.put(bilancio.getId(), bilancio);
		}
		return true;
	}

}
