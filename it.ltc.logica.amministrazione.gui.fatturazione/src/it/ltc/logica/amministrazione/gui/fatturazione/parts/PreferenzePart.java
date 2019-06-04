
package it.ltc.logica.amministrazione.gui.fatturazione.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

import it.ltc.logica.amministrazione.gui.fatturazione.elements.preferenze.TabellaPreferenzeFatturazione;
import it.ltc.logica.amministrazione.gui.fatturazione.elements.preferenze.ToolbarPreferenze;
import it.ltc.logica.common.controller.fatturazione.ControllerPreferenzeFatturazione;

public class PreferenzePart {
	
	public static final String partID = "it.ltc.logica.amministrazione.gui.fatturazione.part.preferenze";
	
	private final ControllerPreferenzeFatturazione controllerPreferenze;
	
	@Inject
	public PreferenzePart() {
		controllerPreferenze = ControllerPreferenzeFatturazione.getInstance();
	}

	@PostConstruct
	public void postConstruct(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		ToolbarPreferenze toolbarPreferenze = new ToolbarPreferenze(parent);
		
		TabellaPreferenzeFatturazione tabellaPreferenze = new TabellaPreferenzeFatturazione(parent);
		Table tablePreferenze = tabellaPreferenze.getTable();
		tablePreferenze.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tabellaPreferenze.setElementi(controllerPreferenze.getPreferenze());
		
		toolbarPreferenze.setTabella(tabellaPreferenze);
	}

}