package it.ltc.logica.common.controller.sistema;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import it.ltc.logica.common.controller.ControllerReadOnly;
import it.ltc.logica.database.model.centrale.CategoriaMerceologica;

public class ControllerCategorieMerceologiche extends ControllerReadOnly<CategoriaMerceologica> {
	
	private static final String title = "Categorie Merceologiche";
	private static final String resource = "categoriamerceologica";
	
	private static ControllerCategorieMerceologiche instance;
	
	private final HashMap<String, CategoriaMerceologica> categorie;

	private ControllerCategorieMerceologiche() {
		super(title, resource, CategoriaMerceologica[].class);
		categorie = new HashMap<String, CategoriaMerceologica>();
		caricaDati();
	}

	public static ControllerCategorieMerceologiche getInstance() {
		if (instance == null) {
			instance = new ControllerCategorieMerceologiche();
		}
		return instance;
	}
	
	public CategoriaMerceologica getCategoria(String nome) {
		return categorie.get(nome);
	}
	
	public Collection<CategoriaMerceologica> getCategorie() {
		return categorie.values();
	}

	@Override
	protected boolean aggiornaInfoTuttiDati(List<CategoriaMerceologica> lista) {
		categorie.clear();
		for (CategoriaMerceologica categoria : lista) {
			categorie.put(categoria.getNome(), categoria);
		}
		return true;
	}

}
