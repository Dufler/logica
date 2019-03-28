package it.ltc.logica.trasporti.gui.listini.wizards.corriere;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.database.model.centrale.listini.VoceDiListinoCorriere;
import it.ltc.logica.database.model.centrale.listini.VoceDiListinoCorriereProporzionale;
import it.ltc.logica.gui.wizard.PaginaWizard;
import it.ltc.logica.trasporti.gui.composite.CompositeVoceProporzionale;

public class NuovaVoceListinoCorriereProporzionaleWizardPage extends PaginaWizard {
	
	private static final String titolo = "Nuova Voce di listino corriere - Algoritmo";
	private static final String descrizione = "Specifica il valore proporzionale.";
	
	private CompositeVoceProporzionale compositeProporzionale;

	private final VoceDiListinoCorriere voce;
	private final VoceDiListinoCorriereProporzionale voceProporzionale;
	
	public NuovaVoceListinoCorriereProporzionaleWizardPage(VoceDiListinoCorriere voce) {
		super(titolo, descrizione);
		this.voce = voce;
		this.voceProporzionale = new VoceDiListinoCorriereProporzionale();
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
