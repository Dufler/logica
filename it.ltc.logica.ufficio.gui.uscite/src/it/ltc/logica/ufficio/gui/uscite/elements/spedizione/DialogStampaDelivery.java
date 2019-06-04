package it.ltc.logica.ufficio.gui.uscite.elements.spedizione;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.controller.sistema.ControllerCommesse;
import it.ltc.logica.common.controller.trasporti.ControllerCorrieri;
import it.ltc.logica.common.controller.uscite.ControllerDelivery;
import it.ltc.logica.common.controller.uscite.ControllerOrdini;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.ordini.DatiSpedizione;
import it.ltc.logica.database.model.centrale.ordini.Delivery;
import it.ltc.logica.database.model.centrale.ordini.StatiSpedizione;
import it.ltc.logica.database.model.centrale.trasporti.Corriere;
import it.ltc.logica.gui.decoration.Immagine;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.dialog.DialogMessaggio;
import it.ltc.logica.gui.dialog.DialogSemplice;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.input.DateField;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Label;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Table;

public class DialogStampaDelivery extends DialogSemplice {

	public static final String title = "Stampa Delivery";
	
	private ComboBox<Commessa> comboCommessa;
	private ComboBox<Corriere> comboCorriere;
	private DateField dataDa;
	private DateField dataA;
	private Button btnStampaDelivery;
	private TabellaDatiSpedizioneCheckBox tabella;
	
	protected DialogStampaDelivery() {
		super(title, Immagine.REPORT_16X16.getImage(), true);
		minimumHeight = 600;
	}

	@Override
	protected void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(1, false));
		
		Label lblSelezionaICriteri = new Label(container, SWT.NONE);
		lblSelezionaICriteri.setText("Seleziona i criteri di filtraggio: ");
		
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
		
		Label lblCorriere = new Label(compositeFiltri, SWT.NONE);
		lblCorriere.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCorriere.setText("Corriere: ");
		
		comboCorriere = new ComboBox<>(compositeFiltri);
		comboCorriere.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 3, 1));
		comboCorriere.setItems(ControllerCorrieri.getInstance().getCorrieri());
		
		new SpacerLabel(compositeFiltri);
		
		Label lblDa = new Label(compositeFiltri, SWT.NONE);
		lblDa.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDa.setText("Da:");
		
		dataDa = new DateField(compositeFiltri);
		
		Label lblA = new Label(compositeFiltri, SWT.NONE);
		lblA.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblA.setText(" A: ");
		
		dataA = new DateField(compositeFiltri);
		
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
		
		btnStampaDelivery = new Button(compositeTasti, SWT.NONE);
		btnStampaDelivery.setText("Stampa Delivery");
		btnStampaDelivery.setEnabled(false);
		btnStampaDelivery.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				stampaDelivery();
			}
		});
		
		tabella = new TabellaDatiSpedizioneCheckBox(container);
		Table table = tabella.getTable();
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean enabled = tabella.getElementiSelezionati().size() > 0;
				btnStampaDelivery.setEnabled(enabled);
			}
		});
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
	}

	@Override
	protected void checkElementiGrafici() {
		dataDa.setValue(new Date());
		dataA.setValue(new Date());
	}
	
	private void filtraSpedizioni() {
		//Eseguo dei controlli
		boolean commessaValida = comboCommessa.validate();
		boolean corriereValido = comboCorriere.validate();
		boolean daValida = dataDa.validate();
		boolean aValida = dataA.validate();
		if (commessaValida && corriereValido && daValida && aValida) {
			//Recupero i valori selezionati
			Commessa commessaSelezionata = comboCommessa.getSelectedValue(); 
			Corriere corriereSelezionato = comboCorriere.getSelectedValue();
			Date da = dataDa.getSimpleStartValue();
			Date a = dataA.getSimpleEndValue();
			ControllerOrdini controller = new ControllerOrdini(commessaSelezionata);
			List<DatiSpedizione> spedizioni = controller.trovaSpedizioni(null, corriereSelezionato.getCodifica(), StatiSpedizione.ABILITATA, da, a);
			tabella.setElementi(spedizioni);
		} else {
			DialogMessaggio.openWarning("Filtri non completi", "Controllare i campi di selezione per la ricerca delle spedizioni.");
		}
	}
	
	private void selezionaTutteLeSpedizioni() {
		tabella.setAllChecked(true);
		boolean enabled = tabella.getElementiSelezionati().size() > 0;
		btnStampaDelivery.setEnabled(enabled);
	}
	
	private void stampaDelivery() {
		//Recupero gli elementi selezionati
		Commessa commessaSelezionata = comboCommessa.getSelectedValue();
		Corriere corriereSelezionato = comboCorriere.getSelectedValue();
		List<DatiSpedizione> spedizioniSelezionate = tabella.getElementiSelezionati();
		//Creo la delivery
		Delivery delivery = new Delivery();
		delivery.setCorriere(corriereSelezionato.getCodifica());
		delivery.setDataGenerazione(new Date());
		Set<Integer> idSpedizioni = new HashSet<>();
		int totaleColli = 0;
		double totalePeso = 0;
		double totaleVolume = 0;
		for (DatiSpedizione spedizione : spedizioniSelezionate) {
			idSpedizioni.add(spedizione.getId());
			totaleColli += spedizione.getColli();
			totalePeso += spedizione.getPeso();
			totaleVolume += spedizione.getVolume();
		}
		delivery.setSpedizioni(idSpedizioni);
		delivery.setTotaleSpedizioni(idSpedizioni.size());
		delivery.setTotaleColli(totaleColli);
		delivery.setTotalePeso(totalePeso);
		delivery.setTotaleVolume(totaleVolume);
		//vado a segnarle come stampate
		ControllerDelivery controller = new ControllerDelivery(commessaSelezionata);
		boolean insert = controller.inserisci(delivery);
		if (insert) {
			ReportDelivery report = new ReportDelivery();
			report.creaReport(spedizioniSelezionate, commessaSelezionata, corriereSelezionato);
			//rimuovo le spedizioni usate e lascio solo quelle rimaste.
			List<DatiSpedizione> spedizioniNonSelezionate = tabella.getElementiNonSelezionati();
			tabella.setElementi(spedizioniNonSelezionate);
		} else {
			DialogMessaggio.openError("Errore Delivery", "Impossibile creare la nuova delivery.");
		}		
	}
	
}
