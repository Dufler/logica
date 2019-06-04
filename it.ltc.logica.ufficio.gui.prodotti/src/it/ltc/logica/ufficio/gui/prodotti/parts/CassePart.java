
package it.ltc.logica.ufficio.gui.prodotti.parts;

import javax.annotation.PostConstruct;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.ufficio.gui.elements.cassa.TabellaCasse;
import it.ltc.logica.ufficio.gui.elements.cassa.ToolbarCasse;

public class CassePart {

	@PostConstruct
	public void postConstruct(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		ToolbarCasse toolbar = new ToolbarCasse(parent);
		
		TabellaCasse tabella = new TabellaCasse(parent);
		
		toolbar.setTabella(tabella);
	}

}