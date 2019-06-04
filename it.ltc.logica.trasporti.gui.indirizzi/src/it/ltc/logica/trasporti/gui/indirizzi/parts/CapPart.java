
package it.ltc.logica.trasporti.gui.indirizzi.parts;

import javax.annotation.PostConstruct;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;

import it.ltc.logica.trasporti.gui.elements.cap.TabellaCap;
import it.ltc.logica.trasporti.gui.elements.cap.ToolbarCap;

public class CapPart {
	
	public static final String partID = "it.ltc.logica.trasporti.gui.indirizzi.part.cap";
	
	private Table tableCAP;
	private TabellaCap tableViewerCAP;

	@PostConstruct
	public void postConstruct(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		Label label = new Label(parent, SWT.NONE);
		label.setText("Gestione cap:");
		
		ToolbarCap toolbarCap = new ToolbarCap(parent);
		
		tableViewerCAP = new TabellaCap(parent);
		tableCAP = tableViewerCAP.getTable();
		tableCAP.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		toolbarCap.setTabella(tableViewerCAP);
	}

}