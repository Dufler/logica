 
package it.ltc.logica.trasporti.gui.spedizioni.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;

import it.ltc.logica.trasporti.gui.elements.spedizione.TabellaSpedizioni;
import it.ltc.logica.trasporti.gui.elements.spedizione.ToolbarSpedizioni;

public class SpedizioniPart {
	
	public static final String partID = "it.ltc.logica.trasporti.gui.spedizioni.part.spedizioni";
	
	private TabellaSpedizioni tableViewer;
	private Table table;
	
	private ToolbarSpedizioni toolbar;
	
	@Inject
	public SpedizioniPart() {}
	
	@PostConstruct
	public void postConstruct(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		Label lblGestioneSpedizioni = new Label(parent, SWT.NONE);
		lblGestioneSpedizioni.setText("Gestione Spedizioni");
		
		toolbar = new ToolbarSpedizioni(parent);
		toolbar.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false, 1, 1));
		
		tableViewer = new TabellaSpedizioni(parent, TabellaSpedizioni.TIPO_COMPLETO);
		table = tableViewer.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		toolbar.setTabella(tableViewer);
	}
	
}