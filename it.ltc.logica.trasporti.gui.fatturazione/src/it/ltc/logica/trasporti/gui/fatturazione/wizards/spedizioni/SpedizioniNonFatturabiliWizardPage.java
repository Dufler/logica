package it.ltc.logica.trasporti.gui.fatturazione.wizards.spedizioni;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;

import it.ltc.logica.database.model.centrale.trasporti.Spedizione;
import it.ltc.logica.gui.dialog.DialogMessaggio;
import it.ltc.logica.gui.wizard.PaginaWizardRisultati;
import it.ltc.logica.trasporti.controller.FatturazioneSpedizioniController;
import it.ltc.logica.trasporti.gui.fatturazione.elements.nonfatturabili.TabellaSpedizioniNonFatturabili;

public class SpedizioniNonFatturabiliWizardPage extends PaginaWizardRisultati {
	
	private static final String titolo = "Fatturazione Spedizioni";
	private static final String descrizione = "Controllo delle spedizioni non fatturabili.";
	
	private final FatturazioneSpedizioniController controllerFatturazione;
	
	private TabellaSpedizioniNonFatturabili checkBox;

	protected SpedizioniNonFatturabiliWizardPage() {
		super(titolo, descrizione, false);
		controllerFatturazione = FatturazioneSpedizioniController.getInstance();
		setPageComplete(true);
	}

	@Override
	public void mostraRisultato() {
		List<Spedizione> spedizioniNonFatturabili = controllerFatturazione.getSpedizioniNonAncoraFatturabili();
		if (spedizioniNonFatturabili.isEmpty()) {
			//Notifico l'utente che non ci sono spedizioni non ancora fatturabili
			DialogMessaggio.openInformation("Spedizioni non fatturabili", "Non ci sono spedizioni non fatturabili in sospeso, \u00E8 possibile proseguire.");
		} else {
			//Inserisco le spedizioni nella tabella e le mostro a video.
			DialogMessaggio.openInformation("Spedizioni non fatturabili", "Sono presenti alcune spedizioni non fatturabili, \u00E8 necessario eseguire un controllo.");
			checkBox.setElementi(spedizioniNonFatturabili);
		}
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(1, false));
		checkBox = new TabellaSpedizioniNonFatturabili(container);
		Table table = checkBox.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				validate();
			}
		});
		
		Button btnRendiFatturabili = new Button(container, SWT.NONE);
		btnRendiFatturabili.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				rendiSpedizioniSelezionateFatturabili();
			}
		});
		btnRendiFatturabili.setText("Rendi fatturabili");

	}
	
	@Override
	protected boolean validazioneSpecifica(boolean valid) {
		boolean selezione = checkBox != null && checkBox.getCheckedElements().length > 0 ? false : true;
		return selezione && valid;
	}
	
	private void rendiSpedizioniSelezionateFatturabili() {
		//Controllo la selezione, se non c'è nulla notifico l'utente.
		List<Spedizione> spedizioniSelezionate = checkBox.getElementiSelezionati();
		if (spedizioniSelezionate.isEmpty()) {
			DialogMessaggio.openInformation("Nessuna Spedizione Selezionata", "Non hai selezionato nessuna spedizione da rendere fatturabile!");
		} else {
			//Altrimenti chiedo conferma all'utente di voler proseguire e rendere fatturabili tutte quelle spedizioni.
			boolean risposta = DialogMessaggio.openQuestion("Cambio stato fatturazione", "Sei sicuro di voler rendere fatturabili le spedizioni selezionate?");
			if (risposta) {
				//Memorizzo le spedizioni non selezionate
				List<Spedizione> spedizioniNonSelezionate = checkBox.getElementiNonSelezionati();
				//Rendo fatturabili tutte le spedizioni selezionate.
				boolean successo = controllerFatturazione.aggiornaStatoFatturabili(spedizioniSelezionate);
				if (!successo) {
					DialogMessaggio.openError("Errore aggiornamento dati", "Ci sono stati problemi durante l'aggiornamento dei dati di fatturazione. \\u00C8 consigliabile chiudere e riaprire il wizard per verificare lo stato di fatturazione.");
				} else {
					checkBox.setElementi(spedizioniNonSelezionate);
				}
			}
			validate();
		}
	}

}
