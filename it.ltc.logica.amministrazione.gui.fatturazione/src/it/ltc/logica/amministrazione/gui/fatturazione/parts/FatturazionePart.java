 
package it.ltc.logica.amministrazione.gui.fatturazione.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;

public class FatturazionePart {
	
	//private TabellaCoordinate tabellaCoordinate;
	
//	private final ControllerCoordinateBancarie controllerCoordinateBancarie;
//	private final ControllerPreferenzeFatturazione controllerPreferenze;
	
	@Inject
	public FatturazionePart() {
		//Caricamento dei controller
		//ControllerIngressi.getInstance();
//		ControllerListiniClienti.getInstance();
		//ControllerProdotti.getInstance();
//		controllerCoordinateBancarie = ControllerCoordinateBancarie.getInstance();
//		controllerPreferenze = ControllerPreferenzeFatturazione.getInstance();
	}
	
	@PostConstruct
	public void postConstruct(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		Label lblInCostruzione = new Label(parent, SWT.NONE);
		lblInCostruzione.setText("In costruzione...");
		
//		CTabFolder tabFolder = new CTabFolder(parent, SWT.BORDER);
//		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
//		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
//		
//		CTabItem tbtmCoordinateBancarie = new CTabItem(tabFolder, SWT.NONE);
//		tbtmCoordinateBancarie.setText("Coordinate Bancarie");
//		
//		Composite compositeCoordinate = new Composite(tabFolder, SWT.NONE);
//		tbtmCoordinateBancarie.setControl(compositeCoordinate);
//		compositeCoordinate.setLayout(new GridLayout(1, false));
//		
//		ToolbarCoordinate toolbarCoordinate = new ToolbarCoordinate(compositeCoordinate);
//		
//		TabellaCoordinate tabellaCoordinate = new TabellaCoordinate(compositeCoordinate);
//		Table tableCoordinate = tabellaCoordinate.getTable();
//		tableCoordinate.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
//		tabellaCoordinate.setElementi(controllerCoordinateBancarie.getCoordinate());
//		
//		toolbarCoordinate.setTabella(tabellaCoordinate);
//		
//		CTabItem tbtmPreferenze = new CTabItem(tabFolder, SWT.NONE);
//		tbtmPreferenze.setText("Preferenze");
//		
//		Composite compositePreferenze = new Composite(tabFolder, SWT.NONE);
//		tbtmPreferenze.setControl(compositePreferenze);
//		compositePreferenze.setLayout(new GridLayout(1, false));
//		
//		ToolbarPreferenze toolbarPreferenze = new ToolbarPreferenze(compositePreferenze);
//		
//		TabellaPreferenzeFatturazione tabellaPreferenze = new TabellaPreferenzeFatturazione(compositePreferenze);
//		Table tablePreferenze = tabellaPreferenze.getTable();
//		tablePreferenze.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
//		tabellaPreferenze.setElementi(controllerPreferenze.getPreferenze());
//		
//		toolbarPreferenze.setTabella(tabellaPreferenze);
//		
//		//Imposto la prima tab come selezionata e quindi subito visibile.
//		tabFolder.setSelection(0);		
	}	
}