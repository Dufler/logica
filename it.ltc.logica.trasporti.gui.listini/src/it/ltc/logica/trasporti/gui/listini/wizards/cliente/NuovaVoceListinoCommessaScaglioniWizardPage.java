package it.ltc.logica.trasporti.gui.listini.wizards.cliente;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.calcolo.algoritmi.Scaglione;
import it.ltc.logica.database.model.centrale.listini.VoceDiListino;
import it.ltc.logica.database.model.centrale.listini.VoceDiListinoScaglioni;
import it.ltc.logica.gui.wizard.PaginaWizard;
import it.ltc.logica.trasporti.gui.composite.CompositeVoceScaglioni;

public class NuovaVoceListinoCommessaScaglioniWizardPage extends PaginaWizard {
	
	private static final String titolo = "Nuova Voce di listino cliente - Algoritmo";
	private static final String descrizione = "Specifica i valori degli scaglioni.";
	
	private final VoceDiListino voce;	
	private final List<VoceDiListinoScaglioni> vociScaglioni;
	//private ArrayList<Scaglione> listaScaglioni;

	private CompositeVoceScaglioni compositeScaglioni;

	public NuovaVoceListinoCommessaScaglioniWizardPage(VoceDiListino voce) {
		super(titolo, descrizione);
		this.voce = voce;
		this.vociScaglioni = new ArrayList<VoceDiListinoScaglioni>();
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
			VoceDiListinoScaglioni voce = new VoceDiListinoScaglioni();
			voce.setInizio(scaglione.getInizio());
			voce.setFine(scaglione.getFine());
			voce.setValore(scaglione.getValore());
			vociScaglioni.add(voce);
		}
		voce.setScaglioni(vociScaglioni);
		voce.setStrategiaCalcolo(compositeScaglioni.getTipo());
	}

}