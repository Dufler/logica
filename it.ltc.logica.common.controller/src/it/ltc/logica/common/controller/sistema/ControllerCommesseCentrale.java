package it.ltc.logica.common.controller.sistema;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import it.ltc.logica.common.controller.ControllerCRUD;
import it.ltc.logica.database.model.centrale.CommessaCentrale;
import it.ltc.logica.utilities.variabili.ControllerVariabiliGlobali;

public class ControllerCommesseCentrale extends ControllerCRUD<CommessaCentrale> {
	
	private static final String title = "commesse";
	private static final String resource = "commessa";
	
	private static ControllerCommesseCentrale instance;
	
	private final HashMap<Integer, CommessaCentrale> commesse;

	private ControllerCommesseCentrale() {
		super(title, resource, CommessaCentrale[].class);
		commesse = new HashMap<Integer, CommessaCentrale>();
		caricaDati();
	}

	public static ControllerCommesseCentrale getInstance() {
		if (instance == null) {
			instance = new ControllerCommesseCentrale();
		}
		return instance;
	}
	
	public CommessaCentrale getCommessa(int idCommessa) {
		return commesse.get(idCommessa);
	}
	
	public Collection<CommessaCentrale> getCommesse() {
		List<CommessaCentrale> commesseDisponibili = new LinkedList<>();
		for (CommessaCentrale commessa : commesse.values()) {
			if (ControllerVariabiliGlobali.getInstance().getBoolean("utente.commesse." + commessa.getId()))
				commesseDisponibili.add(commessa);
		}
		return commesseDisponibili;
		//return commesse.values();
	}
	
	public Collection<CommessaCentrale> getTutteCommesse() {
		return commesse.values();
	}
	
	public List<CommessaCentrale> getCommessePerSede(int idSede) {
		List<CommessaCentrale> commessePerSede = new LinkedList<>();
		for (CommessaCentrale commessa : getCommesse()) {
			if (commessa.getIdSede() == idSede) {
				commessePerSede.add(commessa);
			}
		}
		return commessePerSede;
	}

	@Override
	protected boolean aggiornaInfoTuttiDati(List<CommessaCentrale> lista) {
		commesse.clear();
		for (CommessaCentrale commessa : lista) {
			commesse.put(commessa.getId(), commessa);
		}
		return true;
	}

	@Override
	protected void aggiornaInfoInserimento(CommessaCentrale object, CommessaCentrale nuovo) {
		object.setId(nuovo.getId());
		commesse.put(nuovo.getId(), nuovo);
	}

	@Override
	protected void aggiornaInfoElemento(CommessaCentrale object) {
		commesse.put(object.getId(), object);	
	}

	@Override
	protected void aggiornaInfoEliminazione(CommessaCentrale object) {
		commesse.remove(object.getId());
	}
}
