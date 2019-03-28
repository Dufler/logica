 
package it.ltc.logica.amministrazione.gui.controllodigestione.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;

import it.ltc.logica.amministrazione.gui.controllodigestione.elements.TabellaCDGSpedizioni;

public class ControlloDiGestionePart {
	
	public static final String partID = "it.ltc.logica.amministrazione.gui.controllodigestione.part.controllo";
	
	private TabellaCDGSpedizioni viewerSpedizioni;
	
	@Inject
	public ControlloDiGestionePart() {
		
	}
	
	@PostConstruct
	public void postConstruct(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		CTabFolder tabFolder = new CTabFolder(parent, SWT.BORDER);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		CTabItem tbtmTrasporti = new CTabItem(tabFolder, SWT.NONE);
		tbtmTrasporti.setText("Trasporti");
		
		Composite compositeTrasporti = new Composite(tabFolder, SWT.NONE);
		tbtmTrasporti.setControl(compositeTrasporti);
		compositeTrasporti.setLayout(new GridLayout(1, false));
		
		Label lblSpedizioni = new Label(compositeTrasporti, SWT.NONE);
		lblSpedizioni.setText("Spedizioni");
		
		viewerSpedizioni = new TabellaCDGSpedizioni(compositeTrasporti);
		Table table = viewerSpedizioni.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		viewerSpedizioni.aggiustaLarghezzaColonne();
		
		CTabItem tbtmLogistica = new CTabItem(tabFolder, SWT.NONE);
		tbtmLogistica.setText("Logistica");
		
		CTabItem tbtmExtra = new CTabItem(tabFolder, SWT.NONE);
		tbtmExtra.setText("Extra");
		
		tabFolder.setSelection(0);
		
	}
	
}