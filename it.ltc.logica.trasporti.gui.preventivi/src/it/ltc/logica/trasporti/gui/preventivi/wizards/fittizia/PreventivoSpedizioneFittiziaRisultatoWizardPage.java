package it.ltc.logica.trasporti.gui.preventivi.wizards.fittizia;

import java.util.List;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.CTabItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;

import it.ltc.logica.gui.dialog.DialogMessaggio;
import it.ltc.logica.gui.wizard.PaginaWizardRisultati;
import it.ltc.logica.trasporti.calcolo.algoritmi.SpedizioneModel;
import it.ltc.logica.trasporti.controller.preventivi.PreventivoFittiziaController;
import it.ltc.logica.trasporti.gui.composite.CompositeComparazioneCostiRicaviSpedizione;

public class PreventivoSpedizioneFittiziaRisultatoWizardPage extends PaginaWizardRisultati {
	
	private static final String title = "Preventivo di costo e fatturazione per una spedizione";
	private static final String description = "Il risultato del preventivo."; 
	
	private PreventivoFittiziaController controllerPreventivo;
	
	private CTabFolder tabFolder;

	public PreventivoSpedizioneFittiziaRisultatoWizardPage() {
		super(title, description, true);
		controllerPreventivo = PreventivoFittiziaController.getInstance();
	}
	
	@Override
	public IWizardPage getPreviousPage() {
		return null;
	}
	
	@Override
	public void mostraRisultato() {
		List<SpedizioneModel> preventivi = controllerPreventivo.calcolaSpedizioniFittizie();
		for (SpedizioneModel spedizione : preventivi) {
			CTabItem tab = new CTabItem(tabFolder, SWT.NONE);
			tab.setText(spedizione.getLabel());
			CompositeComparazioneCostiRicaviSpedizione composite = new CompositeComparazioneCostiRicaviSpedizione(tabFolder, SWT.NONE, spedizione);
			tab.setControl(composite);
		}
		tabFolder.setSelection(0);
	}
	
	private void esportaDati() {
		DialogMessaggio.openInformation("Funzione da implementare", "La funzione di esportazione dati deve ancora essere implementata.");
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(1, false));
		
		tabFolder = new CTabFolder(container, SWT.BORDER);
		tabFolder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tabFolder.setSelectionBackground(Display.getCurrent().getSystemColor(SWT.COLOR_TITLE_INACTIVE_BACKGROUND_GRADIENT));
		
		Composite compositeControlli = new Composite(container, SWT.NONE);
		compositeControlli.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, false, 1, 1));
		compositeControlli.setBounds(0, 0, 84, 35);
		compositeControlli.setLayout(new GridLayout(1, false));
		
		Button btnEsportaDati = new Button(compositeControlli, SWT.NONE);
		btnEsportaDati.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				esportaDati();
			}
		});
		btnEsportaDati.setBounds(0, 0, 75, 25);
		btnEsportaDati.setText("Esporta dati");
	}
	
	@Override
	public void copyDataToModel() {
		//DO NOTHING!		
	}
}
