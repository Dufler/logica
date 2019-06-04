
package it.ltc.logica.ufficio.gui.uscite.parts;

import javax.annotation.PostConstruct;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.ufficio.gui.uscite.elements.spedizione.TabellaDatiSpedizioni;
import it.ltc.logica.ufficio.gui.uscite.elements.spedizione.ToolbarDatiSpedizione;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.SWT;

public class SpedizioniPart {
	
	public static final String PART_ID = "it.ltc.logica.ufficio.gui.uscite.part.spedizioni";

	@PostConstruct
	public void postConstruct(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		ToolbarDatiSpedizione toolbar = new ToolbarDatiSpedizione(parent);
		toolbar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		TabellaDatiSpedizioni tabella = new TabellaDatiSpedizioni(parent);
		
		toolbar.setTabella(tabella);
	}

}