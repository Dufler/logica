
package it.ltc.logica.ufficio.gui.prodotti.parts;

import it.ltc.logica.ufficio.gui.elements.prodotto.TabellaProdotti;
import it.ltc.logica.ufficio.gui.elements.prodotto.ToolbarProdotti;
import javax.annotation.PostConstruct;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Table;

public class ProdottiPart {

	@PostConstruct
	public void postConstruct(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		ToolbarProdotti toolbar = new ToolbarProdotti(parent);
		toolbar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		TabellaProdotti tabella = new TabellaProdotti(parent, true);
		Table table = tabella.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		toolbar.setTabella(tabella);
		
	}

}