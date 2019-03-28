package it.ltc.logica.common.controller.sistema;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import it.ltc.logica.common.controller.ControllerCRUD;
import it.ltc.logica.database.model.centrale.documenti.Documento;

public class ControllerDocumenti extends ControllerCRUD<Documento> {
	
	private static final String title = "Documenti";
	private static final String resource = "documento";

	private static ControllerDocumenti instance;

	private final HashMap<Integer, Documento> documenti;

	private ControllerDocumenti() {
		super(title, resource, Documento[].class);
		documenti = new HashMap<>();
		caricaDati();
	}

	public static ControllerDocumenti getInstance() {
		if (instance == null) {
			instance = new ControllerDocumenti();
		}
		return instance;
	}

	public Collection<Documento> getDocumenti() {
		return documenti.values();
	}

	public Documento getDocumento(Integer id) {
		return documenti.get(id);
	}

	@Override
	protected boolean aggiornaInfoTuttiDati(List<Documento> lista) {
		documenti.clear();
		for (Documento documento : lista) {
			documenti.put(documento.getId(), documento);
		}
		return true;
	}

	@Override
	protected void aggiornaInfoInserimento(Documento object, Documento nuovo) {
		object.setId(nuovo.getId());
		documenti.put(nuovo.getId(), nuovo);
	}

	@Override
	protected void aggiornaInfoElemento(Documento object) {
		//TODO - ricarica i dati		
	}

	@Override
	protected void aggiornaInfoEliminazione(Documento object) {
		documenti.remove(object.getId());	
	}

}
