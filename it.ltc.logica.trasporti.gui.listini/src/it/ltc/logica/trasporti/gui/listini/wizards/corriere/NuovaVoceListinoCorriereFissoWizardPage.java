package it.ltc.logica.trasporti.gui.listini.wizards.corriere;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.database.model.centrale.listini.VoceDiListinoCorriere;
import it.ltc.logica.database.model.centrale.listini.VoceDiListinoCorriereFissa;
import it.ltc.logica.gui.wizard.PaginaWizard;
import it.ltc.logica.trasporti.gui.composite.CompositeVoceFissa;

public class NuovaVoceListinoCorriereFissoWizardPage extends PaginaWizard {
	
	private static final String titolo = "Nuova Voce di listino corriere - Algoritmo";
	private static final String descrizione = "Specifica il valore fisso.";
	
	private CompositeVoceFissa compositeFissa;
	
	private final VoceDiListinoCorriere voce;
	private final VoceDiListinoCorriereFissa voceFissa;	

	public NuovaVoceListinoCorriereFissoWizardPage(VoceDiListinoCorriere voce) {
		super(titolo, descrizione);
		this.voce = voce;
		this.voceFissa = new VoceDiListinoCorriereFissa();
	}	

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(1, false));
		
		compositeFissa = new CompositeVoceFissa(this, container);
		compositeFissa.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
	}

	@Override
	public void copyDataToModel() {
		voceFissa.setValore(compositeFissa.getValore());
		voce.setFissa(voceFissa);
	}

}
