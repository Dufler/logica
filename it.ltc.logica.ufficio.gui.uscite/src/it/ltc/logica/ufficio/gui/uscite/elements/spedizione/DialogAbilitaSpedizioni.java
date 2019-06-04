package it.ltc.logica.ufficio.gui.uscite.elements.spedizione;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;

import it.ltc.logica.common.controller.sistema.ControllerCommesse;
import it.ltc.logica.common.controller.uscite.ControllerOrdini;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.ordini.DatiSpedizione;
import it.ltc.logica.database.model.centrale.ordini.StatiSpedizione;
import it.ltc.logica.gui.decoration.Immagine;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.dialog.DialogMessaggio;
import it.ltc.logica.gui.dialog.DialogSemplice;
import it.ltc.logica.gui.input.ComboBox;

public class DialogAbilitaSpedizioni extends DialogSemplice {

public static final String title = "Stampa Delivery";
	
	private ComboBox<Commessa> comboCommessa;
	private Button btnAbilita;
	private TabellaDatiSpedizioneCheckBox tabella;
	
	protected DialogAbilitaSpedizioni() {
		super(title, Immagine.REPORT_16X16.getImage(), true);
		minimumHeight = 600;
	}

	@Override
	protected void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(1, false));
		
		Label lblSelezionaICriteri = new Label(container, SWT.NONE);
		lblSelezionaICriteri.setText("Seleziona la commessa: ");
		
		Composite compositeFiltri = new Composite(container, SWT.NONE);
		compositeFiltri.setLayout(new GridLayout(5, false));
		compositeFiltri.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		Label lblCommessa = new Label(compositeFiltri, SWT.NONE);
		lblCommessa.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCommessa.setText("Commessa: ");
		
		comboCommessa = new ComboBox<>(compositeFiltri);
		comboCommessa.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		comboCommessa.setItems(ControllerCommesse.getInstance().getCommesse());
		
		new SpacerLabel(compositeFiltri);
		
		Composite compositeTasti = new Composite(container, SWT.NONE);
		compositeTasti.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		compositeTasti.setLayout(new GridLayout(3, false));
		
		Button btnFiltra = new Button(compositeTasti, SWT.NONE);
		btnFiltra.setText("Filtra");
		btnFiltra.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				filtraSpedizioni();
			}
		});
		
		
		Button btnSelezionaTutte = new Button(compositeTasti, SWT.NONE);
		btnSelezionaTutte.setText("Seleziona tutte");
		btnSelezionaTutte.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				selezionaTutteLeSpedizioni();
			}
		});
		
		btnAbilita = new Button(compositeTasti, SWT.NONE);
		btnAbilita.setText("Abilita Spedizioni");
		btnAbilita.setEnabled(false);
		btnAbilita.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				abilitaSpedizioni();
			}
		});
		
		tabella = new TabellaDatiSpedizioneCheckBox(container);
		Table table = tabella.getTable();
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean enabled = tabella.getElementiSelezionati().size() > 0;
				btnAbilita.setEnabled(enabled);
			}
		});
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
	}

	@Override
	protected void checkElementiGrafici() {}
	
	private void filtraSpedizioni() {
		//Eseguo dei controlli
		Commessa commessaSelezionata = comboCommessa.getSelectedValue(); 
		if (commessaSelezionata != null) {
			//Recupero le spedizioni delle commessa selezionata
			ControllerOrdini controller = new ControllerOrdini(commessaSelezionata);
			List<DatiSpedizione> spedizioni = controller.trovaSpedizioni(null, null, StatiSpedizione.INSERITA, null, null);
			tabella.setElementi(spedizioni);
		} else {
			DialogMessaggio.openWarning("Filtri non completi", "Va selezionata una commessa per la ricerca delle spedizioni.");
		}
	}
	
	private void selezionaTutteLeSpedizioni() {
		tabella.setAllChecked(true);
		boolean enabled = tabella.getElementiSelezionati().size() > 0;
		btnAbilita.setEnabled(enabled);
	}
	
	private void abilitaSpedizioni() {
		Commessa commessaSelezionata = comboCommessa.getSelectedValue();
		List<DatiSpedizione> spedizioniConProblemi = new LinkedList<>();
		List<DatiSpedizione> spedizioniSelezionate = tabella.getElementiSelezionati();
		ControllerOrdini controller = new ControllerOrdini(commessaSelezionata);
		for (DatiSpedizione spedizione : spedizioniSelezionate) {
			DatiSpedizione risultato = controller.abilitaSpedizione(spedizione, true);
			if (risultato == null) {
				spedizioniConProblemi.add(spedizione);
			}
		}
		tabella.setElementi(spedizioniConProblemi);
		if (!spedizioniConProblemi.isEmpty()) {
			String title = "Spedizione non abilitata";
			StringBuilder message = new StringBuilder("Alcune spedizioni non sono state abilitate a causa di problemi:\r\n");
			for (DatiSpedizione spedizione : spedizioniConProblemi) {
				message.append("- ");
				message.append(spedizione.getRiferimento());
				message.append(" per ");
				message.append(spedizione.getRagioneSociale());
				message.append("\r\n");
			}
			DialogMessaggio.openWarning(title, message.toString());
		}
	}

}
