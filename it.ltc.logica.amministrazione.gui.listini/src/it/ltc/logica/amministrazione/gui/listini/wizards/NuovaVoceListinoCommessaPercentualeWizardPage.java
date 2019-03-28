package it.ltc.logica.amministrazione.gui.listini.wizards;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.amministrazione.gui.composite.CompositeVocePercentuale;
import it.ltc.logica.database.model.centrale.listini.VoceDiListino;
import it.ltc.logica.database.model.centrale.listini.VoceDiListinoPercentuale;
import it.ltc.logica.gui.wizard.PaginaWizard;

public class NuovaVoceListinoCommessaPercentualeWizardPage extends PaginaWizard {
	
	private static final String titolo = "Nuova Voce di listino cliente - Algoritmo";
	private static final String descrizione = "Specifica il valore percentuale.";

	private final VoceDiListino voce;
	private final VoceDiListinoPercentuale vocePercentuale;
	
	private CompositeVocePercentuale compositePercentuale;
	
	public NuovaVoceListinoCommessaPercentualeWizardPage(VoceDiListino voce) {
		super(titolo, descrizione);
		this.voce = voce;
		this.vocePercentuale = new VoceDiListinoPercentuale();
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(1, false));
		
		compositePercentuale = new CompositeVocePercentuale(this, container);
		compositePercentuale.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
	}

	@Override
	public void copyDataToModel() {
		vocePercentuale.setValore(compositePercentuale.getValore());
		vocePercentuale.setValoreMassimo(compositePercentuale.getMassimo());
		vocePercentuale.setValoreMinimo(compositePercentuale.getMinimo());
		voce.setStrategiaCalcolo(compositePercentuale.getTipo());
		voce.setPercentuale(vocePercentuale);
	}

}