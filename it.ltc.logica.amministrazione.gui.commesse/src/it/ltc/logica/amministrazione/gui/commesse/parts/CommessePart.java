 
package it.ltc.logica.amministrazione.gui.commesse.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;

import it.ltc.logica.amministrazione.gui.commesse.elements.TabellaCommesse;
import it.ltc.logica.amministrazione.gui.commesse.elements.ToolbarCommesse;
import it.ltc.logica.common.controller.sistema.ControllerCommesseCentrale;

public class CommessePart {
	
	public static final String partID = "it.ltc.logica.amministrazione.gui.commesse.part.commesse";
	
//	private final ControllerClienti controllerClienti;
	private final ControllerCommesseCentrale controllerCommesse;
	
	@Inject
	public CommessePart() {
//		controllerClienti = ControllerClienti.getInstance();
		controllerCommesse = ControllerCommesseCentrale.getInstance();
	}
	
	@PostConstruct
	public void postConstruct(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
//		CTabFolder tabFolder = new CTabFolder(parent, SWT.BORDER);
//		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
//		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
//		
//		CTabItem tbtmClienti = new CTabItem(tabFolder, SWT.NONE);
//		tbtmClienti.setText("Clienti");
//		
//		Composite compositeClienti = new Composite(tabFolder, SWT.NONE);
//		tbtmClienti.setControl(compositeClienti);
//		compositeClienti.setLayout(new GridLayout(1, false));
//		
//		Label lblCclienti = new Label(compositeClienti, SWT.NONE);
//		lblCclienti.setText("Clienti");
//		
//		ToolbarClienti toolbarClienti = new ToolbarClienti(compositeClienti);
//		
//		TabellaClienti tabellaClienti = new TabellaClienti(compositeClienti);
//		Table tableClienti = tabellaClienti.getTable();
//		tableClienti.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
//		tabellaClienti.setElementi(controllerClienti.getClienti());
//		
//		toolbarClienti.setTabella(tabellaClienti);
//		
//		CTabItem tbtmCommesse = new CTabItem(tabFolder, SWT.NONE);
//		tbtmCommesse.setText("Commesse");
//		
//		Composite compositeCommesse = new Composite(tabFolder, SWT.NONE);
//		tbtmCommesse.setControl(compositeCommesse);
//		compositeCommesse.setLayout(new GridLayout(1, false));
//		
//		Label lblCommesse = new Label(compositeCommesse, SWT.NONE);
//		lblCommesse.setText("Commesse");
//		
//		ToolbarCommesse toolbarCommesse = new ToolbarCommesse(compositeCommesse);
//		
//		TabellaCommesse tabellaCommesse = new TabellaCommesse(compositeCommesse);
//		Table tableCommesse = tabellaCommesse.getTable();
//		tableCommesse.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
//		tabellaCommesse.setElementi(controllerCommesse.getTutteCommesse());
//		
//		toolbarCommesse.setTabella(tabellaCommesse);
//		
//		//Imposto la prima tab come selezionata e quindi subito visibile.
//		tabFolder.setSelection(0);
		
		Label lblCommesse = new Label(parent, SWT.NONE);
		lblCommesse.setText("Commesse");
		
		ToolbarCommesse toolbarCommesse = new ToolbarCommesse(parent);
		
		TabellaCommesse tabellaCommesse = new TabellaCommesse(parent);
		Table tableCommesse = tabellaCommesse.getTable();
		tableCommesse.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tabellaCommesse.setElementi(controllerCommesse.getTutteCommesse());
		
		toolbarCommesse.setTabella(tabellaCommesse);
	}
	
}