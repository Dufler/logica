package it.ltc.logica.common.controller.sistema;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import it.ltc.logica.common.controller.ControllerCRUD;
import it.ltc.logica.common.controller.trasporti.ControllerIndirizzi;
import it.ltc.logica.database.model.centrale.Cliente;

public class ControllerClienti extends ControllerCRUD<Cliente> {
	
	private static final String title = "Clienti";
	private static final String resource = "cliente";
	
	private static ControllerClienti instance;
	
	private final HashMap<Integer, Cliente> clienti;

	private ControllerClienti() {
		super(title, resource, Cliente[].class);
		clienti = new HashMap<Integer, Cliente>();
		caricaDati();
	}

	public static ControllerClienti getInstance() {
		if (instance == null) {
			instance = new ControllerClienti();
		}
		return instance;
	}
	
	public Collection<Cliente> getClienti() {
		return clienti.values();
	}
	
	public Cliente getCliente(int id) {
		return clienti.get(id);
	}
	
	@Override
	protected boolean aggiornaInfoTuttiDati(List<Cliente> lista) {
		clienti.clear();
		for (Cliente cliente : lista) {
			clienti.put(cliente.getId(), cliente);
		}
		return true;
	}

	@Override
	protected void aggiornaInfoInserimento(Cliente object, Cliente nuovo) {
		object.setId(nuovo.getId());
		clienti.put(nuovo.getId(), nuovo);
		ControllerIndirizzi.getInstance().caricaDati(); //Eseguo la sincronizzazione degli indirizzi.
	}

	@Override
	protected void aggiornaInfoElemento(Cliente object) {
		//TODO - ricarica i dati		
	}

	@Override
	protected void aggiornaInfoEliminazione(Cliente object) {
		clienti.remove(object.getId());		
	}

}
