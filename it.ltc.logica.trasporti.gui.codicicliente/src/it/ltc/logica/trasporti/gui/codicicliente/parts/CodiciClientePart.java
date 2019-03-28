 
package it.ltc.logica.trasporti.gui.codicicliente.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

import it.ltc.logica.trasporti.gui.codicicliente.elements.TabellaCodiciCliente;
import it.ltc.logica.trasporti.gui.codicicliente.elements.ToolbarCodiciCliente;
import org.eclipse.swt.widgets.Label;

public class CodiciClientePart {
	
	public static final String partID = "it.ltc.logica.trasporti.gui.codicicliente.part.codicicliente";
	
	private TabellaCodiciCliente tableViewer;
	private Table table;
	
	@Inject
	public CodiciClientePart() {}
	
	@PostConstruct
	public void postConstruct(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		Label lblGestioneDeiCodici = new Label(parent, SWT.NONE);
		lblGestioneDeiCodici.setText("Gestione dei codici cliente presso i corrieri:");
		
		ToolbarCodiciCliente toolbar = new ToolbarCodiciCliente(parent);
		
		tableViewer = new TabellaCodiciCliente(parent);
		
		table = tableViewer.getTable();
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		toolbar.setTabella(tableViewer);
	}
	
}