package it.ltc.logica.common.controller.sistema;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import it.ltc.logica.common.controller.ControllerReadOnly;
import it.ltc.logica.common.controller.processi.ProcessoCaricamentoDati;
import it.ltc.logica.database.model.centrale.CategoriaMerceologica;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.Sede;
import it.ltc.logica.gui.task.DialogProgresso;

public class ControllerCategorieMerceologichePerCliente extends ControllerReadOnly<CategoriaMerceologica> {
	
	private static final String title = "Categorie Merceologiche";
	private static final String resource = "categoriamerceologica";
	
	private static ControllerCategorieMerceologichePerCliente instance;
	
	private final HashMap<Integer, List<CategoriaMerceologica>> categorie;

	private ControllerCategorieMerceologichePerCliente() {
		super(title, resource, CategoriaMerceologica[].class);
		categorie = new HashMap<>();
		caricaDati();
	}

	public static ControllerCategorieMerceologichePerCliente getInstance() {
		if (instance == null) {
			instance = new ControllerCategorieMerceologichePerCliente();
		}
		return instance;
	}
	
	protected List<CategoriaMerceologica> scaricaCategorie(Commessa commessa) {
		Sede sede = ControllerSedi.getInstance().getSede(commessa.getIdSede());
		ProcessoCaricamentoDati<CategoriaMerceologica> processo = new ProcessoCaricamentoDati<CategoriaMerceologica>(title, resource, c, sede, commessa);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		List<CategoriaMerceologica> categorie = processo.getEsito() ? processo.getLista() : null;
		return categorie;
	}
	
//	public CategoriaMerceologica getCategoria(String nome) {
//		return categorie.get(nome);
//	}
//	
//	public Collection<CategoriaMerceologica> getCategorie() {
//		return categorie.values();
//	}
	
	public CategoriaMerceologica getCategoria(Commessa commessa, String nome) {
		CategoriaMerceologica categoria = null;
		for (CategoriaMerceologica cm : getCategorie(commessa)) {
			if (cm.getNome().equals(nome)) {
				categoria = cm;
				break;
			}
		}
		return categoria;
	}
	
	public Collection<CategoriaMerceologica> getCategorie(Commessa commessa) {
		if (!categorie.containsKey(commessa.getId())) {
			List<CategoriaMerceologica> lista = scaricaCategorie(commessa);
			if (lista != null) {
				categorie.put(commessa.getId(), lista);
			}
		}
		return categorie.get(commessa.getId());
	}

	@Override
	protected boolean aggiornaInfoTuttiDati(List<CategoriaMerceologica> lista) {
//		categorie.clear();
//		for (CategoriaMerceologica categoria : lista) {
//			categorie.put(categoria.getNome(), categoria);
//		}
//		return true;
		//DO NOTHING!
		return false;
	}

}
