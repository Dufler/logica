package it.ltc.logica.trasporti.gui.listini.wizards.corriere;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.database.model.centrale.listini.VoceDiListinoCorriere;
import it.ltc.logica.database.model.centrale.listini.VoceDiListinoCorrierePercentuale;
import it.ltc.logica.gui.wizard.PaginaWizard;
import it.ltc.logica.trasporti.gui.composite.CompositeVocePercentuale;

public class NuovaVoceListinoCorrierePercentualeWizardPage extends PaginaWizard {
	
	private static final String titolo = "Nuova Voce di listino corriere - Algoritmo";
	private static final String descrizione = "Specifica il valore percentuale.";
	
	private CompositeVocePercentuale compositePercentuale;
	
	private final VoceDiListinoCorriere voce;
	private final VoceDiListinoCorrierePercentuale vocePercentuale;
	
	public NuovaVoceListinoCorrierePercentualeWizardPage(VoceDiListinoCorriere voce) {
		super(titolo, descrizione);
		this.voce = voce;
		this.vocePercentuale = new VoceDiListinoCorrierePercentuale();
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
		voce.setPercentuale(vocePercentuale);
		voce.setStrategiaCalcolo(compositePercentuale.getTipo());		
	}
	
}