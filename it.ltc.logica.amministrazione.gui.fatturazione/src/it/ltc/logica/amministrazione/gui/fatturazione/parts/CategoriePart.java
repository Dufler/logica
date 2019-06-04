
package it.ltc.logica.amministrazione.gui.fatturazione.parts;

import javax.annotation.PostConstruct;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.amministrazione.gui.fatturazione.elements.categorie.TabellaCategorie;
import it.ltc.logica.amministrazione.gui.fatturazione.elements.categorie.ToolbarCategorie;

public class CategoriePart {
	
	public static final String partID = "it.ltc.logica.amministrazione.gui.fatturazione.part.categorie";

	@PostConstruct
	public void postConstruct(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		ToolbarCategorie toolbar = new ToolbarCategorie(parent);
		
		TabellaCategorie tabella = new TabellaCategorie(parent);
		
		toolbar.setTabella(tabella);		
	}

}