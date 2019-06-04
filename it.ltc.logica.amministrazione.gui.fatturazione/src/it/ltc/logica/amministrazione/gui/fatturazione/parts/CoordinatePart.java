
package it.ltc.logica.amministrazione.gui.fatturazione.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

import it.ltc.logica.amministrazione.gui.fatturazione.elements.coordinate.TabellaCoordinate;
import it.ltc.logica.amministrazione.gui.fatturazione.elements.coordinate.ToolbarCoordinate;
import it.ltc.logica.common.controller.fatturazione.ControllerCoordinateBancarie;

public class CoordinatePart {
	
	public static final String partID = "it.ltc.logica.amministrazione.gui.fatturazione.part.coordinate";
	
	private final ControllerCoordinateBancarie controllerCoordinateBancarie;
	
	@Inject
	public CoordinatePart() {
		controllerCoordinateBancarie = ControllerCoordinateBancarie.getInstance();
	}

	@PostConstruct
	public void postConstruct(Composite parent) {
		parent.setLayout(new GridLayout(1, false));
		
		ToolbarCoordinate toolbarCoordinate = new ToolbarCoordinate(parent);
		
		TabellaCoordinate tabellaCoordinate = new TabellaCoordinate(parent);
		Table tableCoordinate = tabellaCoordinate.getTable();
		tableCoordinate.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tabellaCoordinate.setElementi(controllerCoordinateBancarie.getCoordinate());
		
		toolbarCoordinate.setTabella(tabellaCoordinate);
	}

}