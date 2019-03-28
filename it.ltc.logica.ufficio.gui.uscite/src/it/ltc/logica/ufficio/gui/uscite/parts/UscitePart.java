 
package it.ltc.logica.ufficio.gui.uscite.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;

import it.ltc.logica.ufficio.gui.uscite.elements.TabellaOrdineTestata;
import it.ltc.logica.ufficio.gui.uscite.elements.ToolbarOrdineTestata;

public class UscitePart {
	
	public static final String partID = "it.ltc.logica.ufficio.gui.ordini.part.ordini";
	
	private TabellaOrdineTestata viewerOrdini;
	
	@Inject
	public UscitePart() {}
	
	@PostConstruct
	public void postConstruct(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		ToolbarOrdineTestata toolbar = new ToolbarOrdineTestata(parent);
		
		Label lblOrdini = new Label(parent, SWT.NONE);
		lblOrdini.setText("Ordini: ");
		
		viewerOrdini = new TabellaOrdineTestata(parent);
		Table table = viewerOrdini.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		toolbar.setTabella(viewerOrdini);
	}
	
}