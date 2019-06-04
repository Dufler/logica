
package it.ltc.logica.ufficio.gui.prodotti.parts;

import javax.annotation.PostConstruct;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.ufficio.gui.elements.cassastandard.TabellaCasseStandard;
import it.ltc.logica.ufficio.gui.elements.cassastandard.ToolbarCasseStandard;

public class CasseStandardPart {

	@PostConstruct
	public void postConstruct(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		ToolbarCasseStandard toolbar = new ToolbarCasseStandard(parent);
		
		TabellaCasseStandard tabella = new TabellaCasseStandard(parent);
		
		toolbar.setTabella(tabella);
	}

}