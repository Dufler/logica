package it.ltc.logica.trasporti.gui.listini.wizards.simulazione.voce;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.database.model.locale.ListinoSimulazioneVoce;
import it.ltc.logica.database.model.locale.ListinoSimulazioneVoceProporzionale;
import it.ltc.logica.gui.wizard.PaginaWizard;
import it.ltc.logica.trasporti.gui.composite.CompositeVoceProporzionale;

public class NuovaVoceListinoSimulazioneProporzionaleWizardPage extends PaginaWizard {
	
	private static final String titolo = "Nuova Voce di listino di simulazione - Algoritmo";
	private static final String descrizione = "Specifica il valore proporzionale.";
	
	private final ListinoSimulazioneVoce voce;
	private final ListinoSimulazioneVoceProporzionale voceProporzionale;
	
	private CompositeVoceProporzionale compositeProporzionale;

	public NuovaVoceListinoSimulazioneProporzionaleWizardPage(ListinoSimulazioneVoce voce) {
		super(titolo, descrizione);
		this.voce = voce;
		this.voceProporzionale = new ListinoSimulazioneVoceProporzionale();
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
		voce.setStrategia(compositeProporzionale.getTipo());
		voce.setProporzionale(voceProporzionale);
	}

}
