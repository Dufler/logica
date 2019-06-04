package it.ltc.logica.common.controller.sistema;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import it.ltc.logica.common.controller.ControllerCRUD;
import it.ltc.logica.database.model.centrale.CategoriaMerceologica;

public class ControllerCategorieMerceologiche extends ControllerCRUD<CategoriaMerceologica> {
	
	private static final String title = "Categorie Merceologiche";
	private static final String resource = "categoriamerceologica";
	
	private static ControllerCategorieMerceologiche instance;
	
//	private final HashMap<String, CategoriaMerceologica> categorie;
	private final HashSet<CategoriaMerceologica> categorie;

	private ControllerCategorieMerceologiche() {
		super(title, resource, CategoriaMerceologica[].class);
		categorie = new HashSet<CategoriaMerceologica>();
		caricaDati();
	}

	public static ControllerCategorieMerceologiche getInstance() {
		if (instance == null) {
			instance = new ControllerCategorieMerceologiche();
		}
		return instance;
	}
	
	public CategoriaMerceologica getCategoria(String nome, int commessa) {
		CategoriaMerceologica categoria = null;
		for (CategoriaMerceologica c : categorie) {
			if (c.getNome().equals(nome) && c.getCommessa() == commessa) {
				categoria = c;
				break;
			}
		}
		return categoria;
	}
	
	public Collection<CategoriaMerceologica> getCategorie() {
		return categorie;
	}

	@Override
	protected boolean aggiornaInfoTuttiDati(List<CategoriaMerceologica> lista) {
		categorie.clear();
		for (CategoriaMerceologica categoria : lista) {
			categorie.add(categoria);
		}
		return true;
	}

	@Override
	protected void aggiornaInfoInserimento(CategoriaMerceologica object, CategoriaMerceologica nuovo) {
		categorie.add(nuovo);
	}

	@Override
	protected void aggiornaInfoElemento(CategoriaMerceologica object) {
		categorie.add(object);		
	}

	@Override
	protected void aggiornaInfoEliminazione(CategoriaMerceologica object) {
		categorie.remove(object);		
	}

}
