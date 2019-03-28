package it.ltc.logica.trasporti.gui.listini.wizards.corriere;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.calcolo.algoritmi.Scaglione;
import it.ltc.logica.database.model.centrale.listini.VoceDiListinoCorriere;
import it.ltc.logica.database.model.centrale.listini.VoceDiListinoCorriereScaglioni;
import it.ltc.logica.gui.wizard.PaginaWizard;
import it.ltc.logica.trasporti.gui.composite.CompositeVoceScaglioni;

public class NuovaVoceListinoCorriereScaglioniWizardPage extends PaginaWizard {
	
	private static final String titolo = "Nuova Voce di listino corriere - Algoritmo";
	private static final String descrizione = "Specifica i valori degli scaglioni.";
	
	private CompositeVoceScaglioni compositeScaglioni;
	
	private final VoceDiListinoCorriere voce;
	private final List<VoceDiListinoCorriereScaglioni> listaScaglioni;
	
	public NuovaVoceListinoCorriereScaglioniWizardPage(VoceDiListinoCorriere voce) {
		super(titolo, descrizione);
		this.voce = voce;
		this.listaScaglioni = new ArrayList<VoceDiListinoCorriereScaglioni>();
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(1, false));
		
		compositeScaglioni = new CompositeVoceScaglioni(this, container);
		compositeScaglioni.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
	}

	@Override
	public void copyDataToModel() {
		listaScaglioni.clear();
		for (Scaglione scaglione : compositeScaglioni.getScaglioni()) {
			VoceDiListinoCorriereScaglioni nuovoScaglione = new VoceDiListinoCorriereScaglioni();
			nuovoScaglione.setInizio(scaglione.getInizio());
			nuovoScaglione.setFine(scaglione.getFine());
			nuovoScaglione.setValore(scaglione.getValore());
			listaScaglioni.add(nuovoScaglione);
		}
		voce.setScaglioni(listaScaglioni);
		voce.setStrategiaCalcolo(compositeScaglioni.getTipo());
	}
	
}
