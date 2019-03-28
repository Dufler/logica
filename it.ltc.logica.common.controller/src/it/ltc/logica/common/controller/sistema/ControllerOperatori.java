package it.ltc.logica.common.controller.sistema;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import it.ltc.logica.common.controller.ControllerCRUD;
import it.ltc.logica.database.model.centrale.utenti.Operatore;

public class ControllerOperatori extends ControllerCRUD<Operatore> {
	
	private static final String title = "Operatori";
	private static final String resource = "operatore";
	
	private final HashMap<String, Operatore> operatori;
	
	private static ControllerOperatori instance;

	private ControllerOperatori() {
		super(title, resource, Operatore[].class);
		operatori = new HashMap<>();
		caricaDati();
	}

	public static ControllerOperatori getInstance() {
		if (instance == null) {
			instance = new ControllerOperatori();
		}
		return instance;
	}
	
	public Operatore getOperatore(String username) {
		return operatori.get(username);
	}
	
	public Collection<Operatore> getOperatori() {
		return operatori.values();
	}

	@Override
	protected void aggiornaInfoInserimento(Operatore object, Operatore nuovo) {
		operatori.put(nuovo.getUsername(), nuovo);
	}

	@Override
	protected void aggiornaInfoElemento(Operatore object) {
		// TODO - Refresh		
	}

	@Override
	protected void aggiornaInfoEliminazione(Operatore operatore) {
		operatori.remove(operatore.getUsername());
	}

	@Override
	protected boolean aggiornaInfoTuttiDati(List<Operatore> lista) {
		for (Operatore operatore : lista) {
			operatori.put(operatore.getUsername(), operatore);
		}
		return true;
	}

}
