package it.ltc.logica.trasporti.gui.dialog;

import java.util.HashSet;
import java.util.Set;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.common.controller.trasporti.ControllerNazioni;
import it.ltc.logica.database.model.centrale.indirizzi.Nazione;
import it.ltc.logica.gui.common.tables.nazioni.TabellaNazioni;
import it.ltc.logica.gui.dialog.DialogSemplice;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.layout.GridData;

public class DialogSelezioneNazioni extends DialogSemplice {

	private static final String title = "Selezione Nazioni";
	private static final String BASE_LABEL = "Nazioni selezionate: ";
	private static final int MAX_NAZIONI_SELEZIONABILI = 250;
	
	public static final String SEPARATORE = "-";
	public static final char SEPARATORE_C = '-';
	
	private Label lblNazioni;
	private TabellaNazioni tabellaNazioni;
	
	private final Set<Nazione> nazioniSelezionate;
	
	private String selezionePrecedente;

	public DialogSelezioneNazioni(String selezionePrecedente) {
		super(title, null, true);
		nazioniSelezionate = new HashSet<Nazione>();
		this.selezionePrecedente = selezionePrecedente;
	}
	
	@Override
	public void aggiungiAltriBottoni(Composite parent) {
		checkSelezione();
	}
	
	/**
	 * Abilita o disabilita il bottone di 'Ok' in base alla selezione dell'utente.
	 * E' necessario inserire almeno una regione per poter confermare la selezione.
	 */
	private void checkSelezione() {
		nazioniSelezionate.clear();
		nazioniSelezionate.addAll(tabellaNazioni.getElementiSelezionati());
		String testoLabel = BASE_LABEL + nazioniSelezionate.size();
		int selezionate = nazioniSelezionate.size();
		if (selezionate == 0) {
			testoLabel += " \u00E8 necessario selezionare almeno una nazione.";
		} else if (selezionate > MAX_NAZIONI_SELEZIONABILI) {
			testoLabel += " \u00E8 possibile selezionare al più " + MAX_NAZIONI_SELEZIONABILI + " nazioni.";
		}
		lblNazioni.setText(testoLabel);
		boolean selezioneCorretta = selezionate > 0 && selezionate < MAX_NAZIONI_SELEZIONABILI;
		okButton.setEnabled(selezioneCorretta);
	}
	
	public String apri() {
		StringBuilder nazioni = new StringBuilder();
		if (open() == Dialog.OK) {
			for (Nazione n : nazioniSelezionate) {
				nazioni.append(n.getCodiceIsoTre());
				nazioni.append(SEPARATORE);
			}
			if (nazioni.length() > 0) 
				nazioni.deleteCharAt(nazioni.length() - 1);
		} else {
			nazioni.append(selezionePrecedente);
		}
		return nazioni.toString();
	}
	
	public void aggiungiElementiGrafici(Composite container) {
		lblNazioni = new Label(container, SWT.NONE);
		lblNazioni.setText(BASE_LABEL);
		
		tabellaNazioni = new TabellaNazioni(container);
		Table table = tabellaNazioni.getTable();
		GridData layout = new GridData(SWT.FILL, SWT.TOP, true, true, 1, 1);
		layout.heightHint = 400;
		table.setLayoutData(layout);
		tabellaNazioni.getTable().addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent e) {
				checkSelezione();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) {
				//DO NOTHING!				
			}
		});
	}
	
	@Override
	public void checkElementiGrafici() {
		//Se avevo già delle regioni precedentemente inserite le riporto.
		if (selezionePrecedente != null && !selezionePrecedente.isEmpty()) {
			String[] nazioniPrecedenti = selezionePrecedente.split(SEPARATORE);
			Nazione[] nazioniSelezionate = new Nazione[nazioniPrecedenti.length];
			for (int index = 0; index < nazioniPrecedenti.length; index++) {
				Nazione nazione = ControllerNazioni.getInstance().getNazione(nazioniPrecedenti[index]);
				if (nazione != null) {
					nazioniSelezionate[index] = nazione;
				}
			}
			tabellaNazioni.setCheckedElements(nazioniSelezionate);
			lblNazioni.setText(BASE_LABEL + nazioniPrecedenti.length);
		}
	}

}
