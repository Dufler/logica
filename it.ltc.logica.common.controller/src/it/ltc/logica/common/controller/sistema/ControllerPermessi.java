package it.ltc.logica.common.controller.sistema;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import it.ltc.logica.common.controller.ControllerCRUD;
import it.ltc.logica.database.model.centrale.utenti.Permesso;

/**
 * TODO Ancora non viene usato, quando anche la parte admin utilizzerà i Web Services allora ingloberà tutti i metodi statici della classe Permesso. (Gestione CRUD)
 * @author Damiano Bellucci - damiano.bellucci@gmail.com
 *
 */
public class ControllerPermessi extends ControllerCRUD<Permesso> {
	
	private static final String title = "Permessi";
	private static final String resource = "permesso";
	
	private static ControllerPermessi instance;
	
	private final HashMap<Integer, Permesso> permessi;
	private final LinkedList<Permesso> permessiAdAlbero;

	private ControllerPermessi() {
		super(title, resource, Permesso[].class);
		permessi = new HashMap<Integer, Permesso>();
		permessiAdAlbero = new LinkedList<Permesso>();
		caricaDati();
	}

	public static ControllerPermessi getInstance() {
		if (instance == null) {
			instance = new ControllerPermessi();
		}
		return instance;
	}
	
	public Permesso getPermesso(int idPermesso) {
		return permessi.get(idPermesso);
	}
	
	public Collection<Permesso> getPermessi() {
		return permessi.values();
	}
	
	public List<Permesso> getPermessiAdAlbero() {
		//LinkedList<Permesso> permessiAdAlbero = new LinkedList<>();
		if (permessiAdAlbero.isEmpty()) {
			for (Permesso permesso : getPermessi()) {
				if (permesso.getIdPadre() != null && permesso.getIdPadre() > 0) {
					Permesso padre = getPermesso(permesso.getIdPadre());
					padre.aggiungiFiglio(permesso);
				} else {
					permessiAdAlbero.add(permesso);
				}
			}
		}
		return permessiAdAlbero;
	}
	
	public boolean aggiungiPermesso(Permesso padre, String nomePermesso) {
		Permesso nuovoPermesso = new Permesso();
		nuovoPermesso.setNome(nomePermesso);
		nuovoPermesso.setCategoria(padre.getCategoria());
		nuovoPermesso.setIdPadre(padre.getId());
		inserisci(nuovoPermesso);
		int id = nuovoPermesso.getId();
		boolean inserimento = id > 0;
		return inserimento;
	}

	@Override
	protected void aggiornaInfoInserimento(Permesso object, Permesso permesso) {
		object.setId(permesso.getId());
		permessi.put(permesso.getId(), permesso);
		//Aggiorno anche il padre, se ne ha uno.
		if (permesso.getIdPadre() != null && permesso.getIdPadre() > 0) {
			Permesso padre = getPermesso(permesso.getIdPadre());
			padre.aggiungiFiglio(permesso);
		}
	}

	@Override
	protected void aggiornaInfoElemento(Permesso permesso) {
		permessi.put(permesso.getId(), permesso);
	}

	@Override
	protected void aggiornaInfoEliminazione(Permesso permesso) {
		permessi.remove(permesso.getId());
	}

	@Override
	protected boolean aggiornaInfoTuttiDati(List<Permesso> lista) {
		permessi.clear();
		for (Permesso permesso : lista) {
			permessi.put(permesso.getId(), permesso);
		}
		return true;
	}

}
