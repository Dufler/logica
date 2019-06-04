 
package it.ltc.logica.trasporti.gui.indirizzi.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;

import it.ltc.logica.trasporti.gui.elements.indirizzo.TabellaIndirizzi;
import it.ltc.logica.trasporti.gui.elements.indirizzo.ToolbarIndirizzi;

public class IndirizziPart {
	
	public static final String partID = "it.ltc.logica.trasporti.gui.indirizzi.part.indirizzi";
	
	private TabellaIndirizzi tableViewerIndirizzi;
	private Table tableIndirizzi;
	
//	private Table tableCAP;
//	private TabellaCap tableViewerCAP;
	
	@Inject
	public IndirizziPart() {}
	
	@PostConstruct
	public void postConstruct(Composite container) {
		container.setLayout(new GridLayout(1, false));
		
		Label label = new Label(container, SWT.NONE);
		label.setText("Gestione indirizzi:");
		
		ToolbarIndirizzi toolbar = new ToolbarIndirizzi(container);
		
		tableViewerIndirizzi = new TabellaIndirizzi(container, true);
		tableIndirizzi = tableViewerIndirizzi.getTable();
		tableIndirizzi.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		toolbar.setTabella(tableViewerIndirizzi);
		
//		CTabFolder tabFolder = new CTabFolder(container, SWT.BORDER | SWT.FLAT);
//		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
//		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
//		
//		CTabItem tbtmIndirizzi = new CTabItem(tabFolder, SWT.NONE);
//		tbtmIndirizzi.setText("Indirizzi");
//		
//		Composite compositeIndirizzi = new Composite(tabFolder, SWT.NONE);
//		tbtmIndirizzi.setControl(compositeIndirizzi);
//		compositeIndirizzi.setLayout(new GridLayout(1, false));
//		
//		ToolbarIndirizzi toolbar = new ToolbarIndirizzi(compositeIndirizzi);
//		
//		tableViewerIndirizzi = new TabellaIndirizzi(compositeIndirizzi, true);
//		tableIndirizzi = tableViewerIndirizzi.getTable();
//		tableIndirizzi.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
//		
//		toolbar.setTabella(tableViewerIndirizzi);
//		
//		CTabItem tbtmCap = new CTabItem(tabFolder, SWT.NONE);
//		tbtmCap.setText("CAP");
//		
//		Composite compositeCAP = new Composite(tabFolder, SWT.NONE);
//		tbtmCap.setControl(compositeCAP);
//		compositeCAP.setLayout(new GridLayout(1, false));
//		
//		ToolbarCap toolbarCap = new ToolbarCap(compositeCAP);
//		
//		tableViewerCAP = new TabellaCap(compositeCAP);
//		tableCAP = tableViewerCAP.getTable();
//		tableCAP.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
//		
//		toolbarCap.setTabella(tableViewerCAP);
//		
//		tabFolder.setSelection(0);
	}
	
}