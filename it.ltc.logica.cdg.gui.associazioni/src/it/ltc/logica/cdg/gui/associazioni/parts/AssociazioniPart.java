 
package it.ltc.logica.cdg.gui.associazioni.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;

import it.ltc.logica.cdg.gui.associazioni.elements.TabellaAssociazioni;
import it.ltc.logica.cdg.gui.associazioni.elements.ToolbarAssociazioni;
import it.ltc.logica.utilities.variabili.ControllerVariabiliGlobali;
import it.ltc.logica.utilities.variabili.Permessi;

public class AssociazioniPart {
	
	private TabellaAssociazioni tabellaAssociazioni;
	
	private final boolean permesso;
	
	@Inject
	public AssociazioniPart() {
		permesso = ControllerVariabiliGlobali.getInstance().haPermesso(Permessi.CDG_COSTO_PERSONALE.getID());
	}
	
	@PostConstruct
	public void postConstruct(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		Label lblAssociazioniOperatori = new Label(parent, SWT.NONE);
		lblAssociazioniOperatori.setText("Costo del personale suddiviso per sede e tipo di gruppo: ");
		
		ToolbarAssociazioni toolbar = new ToolbarAssociazioni(parent);
		toolbar.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		tabellaAssociazioni = new TabellaAssociazioni(parent);
		Table table = tabellaAssociazioni.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tabellaAssociazioni.aggiornaContenuto();
		tabellaAssociazioni.abilitaMenu(permesso);
		
		toolbar.setTabella(tabellaAssociazioni);
	}
}