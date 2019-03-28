package it.ltc.logica.trasporti.gui.listini.wizards.simulazione.voce;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.calcolo.algoritmi.Scaglione;
import it.ltc.logica.database.model.locale.ListinoSimulazioneVoce;
import it.ltc.logica.database.model.locale.ListinoSimulazioneVoceScaglioni;
import it.ltc.logica.gui.wizard.PaginaWizard;
import it.ltc.logica.trasporti.gui.composite.CompositeVoceScaglioni;

public class NuovaVoceListinoSimulazioneScaglioniWizardPage extends PaginaWizard {
	
	private static final String titolo = "Nuova Voce di listino di simulazione - Algoritmo";
	private static final String descrizione = "Specifica i valori degli scaglioni.";
	
	private final ListinoSimulazioneVoce voce;
	private final List<ListinoSimulazioneVoceScaglioni> vociScaglioni;

	private CompositeVoceScaglioni compositeScaglioni;

	public NuovaVoceListinoSimulazioneScaglioniWizardPage(ListinoSimulazioneVoce voce) {
		super(titolo, descrizione);
		this.voce = voce;
		this.vociScaglioni = new ArrayList<ListinoSimulazioneVoceScaglioni>();
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(1, false));
		
		compositeScaglioni = new CompositeVoceScaglioni(this, container);
		compositeScaglioni.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
	}

	@Override
	public void copyDataToModel() {
		vociScaglioni.clear();
		for (Scaglione scaglione : compositeScaglioni.getScaglioni()) {
			ListinoSimulazioneVoceScaglioni voce = new ListinoSimulazioneVoceScaglioni();
			voce.setInizio(scaglione.getInizio());
			voce.setFine(scaglione.getFine());
			voce.setValore(scaglione.getValore());
			vociScaglioni.add(voce);
		}
		voce.setScaglioni(vociScaglioni);
		voce.setStrategia(compositeScaglioni.getTipo());
	}

}
