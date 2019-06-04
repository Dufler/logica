
package it.ltc.logica.amministrazione.gui.commesse.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;

import it.ltc.logica.amministrazione.gui.commesse.elements.TabellaClienti;
import it.ltc.logica.amministrazione.gui.commesse.elements.ToolbarClienti;
import it.ltc.logica.common.controller.sistema.ControllerClienti;

public class ClientiPart {
	
	public static final String partID = "it.ltc.logica.amministrazione.gui.commesse.part.clienti";
	
	private final ControllerClienti controllerClienti;
	
	@Inject
	public ClientiPart() {
		controllerClienti = ControllerClienti.getInstance();
	}

	@PostConstruct
	public void postConstruct(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		Label lblCclienti = new Label(parent, SWT.NONE);
		lblCclienti.setText("Clienti");
		
		ToolbarClienti toolbarClienti = new ToolbarClienti(parent);
		
		TabellaClienti tabellaClienti = new TabellaClienti(parent);
		Table tableClienti = tabellaClienti.getTable();
		tableClienti.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tabellaClienti.setElementi(controllerClienti.getClienti());
		
		toolbarClienti.setTabella(tabellaClienti);
	}

}