
package it.ltc.logica.magazzino.gui.ingressi.parts;

import javax.annotation.PostConstruct;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;

import it.ltc.logica.magazzino.gui.ingressi.elements.carico.TabellaCaricoTestata;
import it.ltc.logica.magazzino.gui.ingressi.elements.carico.ToolbarCaricoTestata;

import org.eclipse.swt.SWT;

public class IngressiPart {
	
	private TabellaCaricoTestata viewerIngressi;

	@PostConstruct
	public void postConstruct(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		ToolbarCaricoTestata toolbar = new ToolbarCaricoTestata(parent);
		
		Label lblCarichi = new Label(parent, SWT.NONE);
		lblCarichi.setText("Carichi aperti: ");
		
		viewerIngressi = new TabellaCaricoTestata(parent);
		Table table = viewerIngressi.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
				
		toolbar.setTabella(viewerIngressi);
		
	}

}