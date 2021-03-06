package it.ltc.logica.trasporti.gui.listini.wizards.cliente;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.database.model.centrale.listini.VoceDiListino;
import it.ltc.logica.database.model.centrale.listini.VoceDiListinoProporzionale;
import it.ltc.logica.gui.wizard.PaginaWizard;
import it.ltc.logica.trasporti.gui.composite.CompositeVoceProporzionale;

public class NuovaVoceListinoCommessaProporzionaleWizardPage extends PaginaWizard {
	
	private static final String titolo = "Nuova Voce di listino cliente - Algoritmo";
	private static final String descrizione = "Specifica il valore proporzionale.";
	
	private final VoceDiListino voce;	
	private final VoceDiListinoProporzionale voceProporzionale;
	
	private CompositeVoceProporzionale compositeProporzionale;

	public NuovaVoceListinoCommessaProporzionaleWizardPage(VoceDiListino voce) {
		super(titolo, descrizione);
		this.voce = voce;
		this.voceProporzionale = new VoceDiListinoProporzionale();
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(1, false));
		
		compositeProporzionale = new CompositeVoceProporzionale(this, container);
		compositeProporzionale.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
	}

	@Override
	public void copyDataToModel() {
		voceProporzionale.setValore(compositeProporzionale.getValore());
		voceProporzionale.setMinimo(compositeProporzionale.getMinimo());
		voceProporzionale.setMassimo(compositeProporzionale.getMassimo());
		voce.setStrategiaCalcolo(compositeProporzionale.getTipo());
		voce.setProporzionale(voceProporzionale);
	}

}
