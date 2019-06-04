
package it.ltc.logica.amministrazione.gui.commesse.parts;

import javax.annotation.PostConstruct;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.amministrazione.gui.commesse.elements.brand.TabellaBrand;
import it.ltc.logica.amministrazione.gui.commesse.elements.brand.ToolbarBrand;

public class BrandPart {
	
	public static final String partID = "it.ltc.logica.amministrazione.gui.commesse.part.brand";

	@PostConstruct
	public void postConstruct(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		ToolbarBrand toolbar = new ToolbarBrand(parent);
		
		TabellaBrand tabella = new TabellaBrand(parent);
		
		toolbar.setTabella(tabella);
		
	}

}