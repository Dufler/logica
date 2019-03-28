package it.ltc.logica.trasporti.gui.listini.wizards.cliente;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.database.model.centrale.listini.VoceDiListino;
import it.ltc.logica.database.model.centrale.listini.VoceDiListinoScaglioniRipetuti;
import it.ltc.logica.gui.wizard.PaginaWizard;
import it.ltc.logica.trasporti.gui.composite.CompositeVoceScaglioniRipetuti;

public class NuovaVoceListinoCommessaScaglioniRipetutiWizardPage extends PaginaWizard {
	
	private static final String titolo = "Nuova Voce di listino cliente - Algoritmo";
	private static final String descrizione = "Specifica i valori degli scaglioni ripetuti.";
	
	private final VoceDiListino voce;	
	private final VoceDiListinoScaglioniRipetuti voceScaglioniRipetuti;
	
	private CompositeVoceScaglioniRipetuti compositeScaglioni;

	public NuovaVoceListinoCommessaScaglioniRipetutiWizardPage(VoceDiListino voce) {
		super(titolo, descrizione);
		this.voce = voce;
		this.voceScaglioniRipetuti = new VoceDiListinoScaglioniRipetuti();
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(1, false));
		
		compositeScaglioni = new CompositeVoceScaglioniRipetuti(this, container, true);
		compositeScaglioni.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
	}

	@Override
	public void copyDataToModel() {
		voceScaglioniRipetuti.setValore(compositeScaglioni.getValore());
		voceScaglioniRipetuti.setIntervallo(compositeScaglioni.getIntervallo());
		voceScaglioniRipetuti.setMassimo(compositeScaglioni.getMassimo());
		voceScaglioniRipetuti.setMinimo(compositeScaglioni.getMinimo());
		voce.setStrategiaCalcolo(compositeScaglioni.getTipo());
		voce.setRipetuti(voceScaglioniRipetuti);
	}

}
