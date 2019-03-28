package it.ltc.logica.ufficio.gui.uscite.wizard.assegna;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.ordini.OrdineTestata;
import it.ltc.logica.gui.wizard.PaginaWizard;
import it.ltc.logica.ufficio.gui.uscite.elements.TabellaOrdiniDaLavorare;
import it.ltc.logica.ufficio.gui.uscite.elements.TabellaOrdiniDaLavorare.TipoOrdiniLavorabili;

public class SelezioneOrdiniWizardPage extends PaginaWizard {
	
	private final static String title = "Assegnazione Ordini";
	private final static String description = "Seleziona gli ordini da assegnare.";
	
	private TabellaOrdiniDaLavorare tabella;
	
	private final Commessa commessa;
	private final List<OrdineTestata> ordiniDaAssegnare;

	protected SelezioneOrdiniWizardPage(Commessa commessa, List<OrdineTestata> ordiniDaAssegnare) {
		super(title, description);
		this.commessa = commessa;
		this.ordiniDaAssegnare = ordiniDaAssegnare;
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(1, false));
		tabella = new TabellaOrdiniDaLavorare(container, commessa, TipoOrdiniLavorabili.ASSEGNARE);
		Table table = tabella.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tabella.aggiornaContenuto();
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				validate();
			}
		});
	}

	@Override
	public void copyDataToModel() {
		ordiniDaAssegnare.clear();
		ordiniDaAssegnare.addAll(tabella.getElementiSelezionati());
	}
	
	@Override
	protected boolean validazioneSpecifica(boolean valid) {
		boolean valido = valid && !tabella.getElementiSelezionati().isEmpty();
		return valido;
	}

}
