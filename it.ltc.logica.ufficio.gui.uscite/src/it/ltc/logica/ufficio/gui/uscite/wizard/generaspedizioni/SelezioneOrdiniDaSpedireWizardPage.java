package it.ltc.logica.ufficio.gui.uscite.wizard.generaspedizioni;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.ordini.OrdineTestata;
import it.ltc.logica.gui.wizard.PaginaWizard;
import it.ltc.logica.ufficio.gui.uscite.elements.TabellaOrdiniDaLavorare;
import it.ltc.logica.ufficio.gui.uscite.elements.TabellaOrdiniDaLavorare.TipoOrdiniLavorabili;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.layout.GridData;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class SelezioneOrdiniDaSpedireWizardPage extends PaginaWizard {
	
	private final static String title = "Generazione Dati Spedizioni";
	private final static String description = "Seleziona gli ordini per cui generare i dati per la spedizione.";
	
	private final List<OrdineTestata> ordiniDaFinalizzare;
	
	private TabellaOrdiniDaLavorare tabella;
	
	private final Commessa commessa;

	protected SelezioneOrdiniDaSpedireWizardPage(Commessa commessa, List<OrdineTestata> ordiniDaFinalizzare) {
		super(title, description);
		this.commessa = commessa;
		this.ordiniDaFinalizzare = ordiniDaFinalizzare;
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(1, false));
		tabella = new TabellaOrdiniDaLavorare(container, commessa, TipoOrdiniLavorabili.SPEDIRE);
		Table table = tabella.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				validate();
			}
		});
		tabella.aggiornaContenuto();
	}

	@Override
	public void copyDataToModel() {
		ordiniDaFinalizzare.clear();
		ordiniDaFinalizzare.addAll(tabella.getElementiSelezionati());
	}
	
	@Override
	protected boolean validazioneSpecifica(boolean valid) {
		boolean valido = valid && !tabella.getElementiSelezionati().isEmpty();
		return valido;
	}

}
