package it.ltc.logica.trasporti.gui.preventivi.wizards.fittizia;

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

import it.ltc.logica.common.controller.trasporti.ControllerIndirizziSimulazione;
import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.database.model.centrale.indirizzi.IndirizzoSimulazione;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.input.TextForDouble;
import it.ltc.logica.gui.input.TextForInteger;
import it.ltc.logica.gui.input.TextForMoney;
import it.ltc.logica.gui.wizard.PaginaWizard;
import it.ltc.logica.trasporti.controller.preventivi.PreventivoFittiziaController;
import it.ltc.logica.trasporti.gui.preventivi.elements.indirizzi.TabellaCheckboxIndirizzi;

public class PreventivoSpedizioneFittiziaDatiWizardPage extends PaginaWizard {
	
	private static final String titolo = "Preventivo di costo e fatturazione per una spedizione";
	private static final String descrizione = "Inserisci i dati sulla spedizione tipo.";
	
	private PreventivoFittiziaController controllerPreventivo;
	
	private TextForInteger textPezzi;
	private TextForInteger textColli;
	private TextForDouble textPeso;
	private TextForDouble textVolume;
	private TextForMoney textValoreContrassegno;
	private Button btnSi;
	private Button btnNo;
	
	private TabellaCheckboxIndirizzi tabellaIndirizzi;

	public PreventivoSpedizioneFittiziaDatiWizardPage() {
		super(titolo, descrizione);
		controllerPreventivo = PreventivoFittiziaController.getInstance();
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(1, false));
		
		Label lblInserisciIDati = new Label(container, SWT.NONE);
		lblInserisciIDati.setText("Inserisci i dati sulla spedizione: ");
		
		Composite compositeDati = new Composite(container, SWT.NONE);
		compositeDati.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		compositeDati.setLayout(new GridLayout(3, false));
		
		Label lblMediaPezzi = new Label(compositeDati, SWT.NONE);
		lblMediaPezzi.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblMediaPezzi.setText("Media Pezzi per Collo: ");
		
		textPezzi = new TextForInteger(compositeDati);
		textPezzi.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		addChild(textPezzi);
		
		new SpacerLabel(compositeDati);
		
		Label lblMediaColli = new Label(compositeDati, SWT.NONE);
		lblMediaColli.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblMediaColli.setText("Media Colli per Spedizione: ");
		
		textColli = new TextForInteger(compositeDati);
		textColli.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		addChild(textColli);
		
		new SpacerLabel(compositeDati);
		
		Label lblMediaPesoCollo = new Label(compositeDati, SWT.NONE);
		lblMediaPesoCollo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblMediaPesoCollo.setText("Media Peso Collo: ");
		
		textPeso = new TextForDouble(compositeDati);
		textPeso.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		addChild(textPeso);
		
		new SpacerLabel(compositeDati);
		
		Label lblMediaVolumeCollo = new Label(compositeDati, SWT.NONE);
		lblMediaVolumeCollo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblMediaVolumeCollo.setText("Media Volume Collo: ");
		
		textVolume = new TextForDouble(compositeDati);
		textVolume.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		addChild(textVolume);
		
		new SpacerLabel(compositeDati);
		
		Label lblContrassegno = new Label(compositeDati, SWT.NONE);
		lblContrassegno.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblContrassegno.setText("Contrassegno: ");
		
		Composite compositeContrassegno = new Composite(compositeDati, SWT.NONE);
		compositeContrassegno.setLayout(new GridLayout(2, false));
		
		btnSi = new Button(compositeContrassegno, SWT.RADIO);
		btnSi.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean contrassegno = btnSi.getSelection();
				textValoreContrassegno.setRequired(contrassegno);
				textValoreContrassegno.setEnabled(contrassegno);
			}
		});
		btnSi.setText("Si");
		btnSi.setSelection(false);
		
		btnNo = new Button(compositeContrassegno, SWT.RADIO);
		btnNo.setText("No");
		btnNo.setSelection(true);
		
		new Label(compositeDati, SWT.NONE);
		
		
		Label lblMediaValoreContrassegno = new Label(compositeDati, SWT.NONE);
		lblMediaValoreContrassegno.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblMediaValoreContrassegno.setText("Media Valore Contrassegno: ");
		
		textValoreContrassegno = new TextForMoney(compositeDati);
		textValoreContrassegno.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textValoreContrassegno.setRequired(false);
		textValoreContrassegno.setEnabled(false);
		addChild(textValoreContrassegno);
		
		new SpacerLabel(compositeDati);
		
		Label lblSelezionaGliIndirizzi = new Label(container, SWT.NONE);
		lblSelezionaGliIndirizzi.setText("Seleziona gli indirizzi a cui spedire: ");
		
		Composite compositeIndirizzi = new Composite(container, SWT.NONE);
		compositeIndirizzi.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, true, 1, 1));
		compositeIndirizzi.setLayout(new GridLayout(1, false));
		
		tabellaIndirizzi = new TabellaCheckboxIndirizzi(compositeIndirizzi, TabellaCheckboxIndirizzi.STILE_SEMPLICE);
		
		Table table = tabellaIndirizzi.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				validate();
			}
		});
		
		tabellaIndirizzi.setElementi(ControllerIndirizziSimulazione.getInstance().trovaTuttiInserimentoManuale());
	}
	
	@Override
	protected boolean validazioneSpecifica(boolean valid) {
		boolean valido = valid && tabellaIndirizzi.getCheckedElements().length > 0; 
		return valido;
	}

	@Override
	public void copyDataToModel() {
		//Dati sulla spedizione
		controllerPreventivo.setPezzi(textPezzi.getValue());
		controllerPreventivo.setColli(textColli.getValue());
		controllerPreventivo.setPeso(textPeso.getValue());
		controllerPreventivo.setVolume(textVolume.getValue());
		boolean contrassegno = btnSi.getSelection();
		controllerPreventivo.setContrassegno(contrassegno);
		if (contrassegno)
			controllerPreventivo.setValoreContrassegno(textValoreContrassegno.getValue());
		//Indirizzi
		List<Indirizzo> indirizzi = new LinkedList<>();
		Object[] arrayIndirizziSelezionati = tabellaIndirizzi.getCheckedElements();
		for (Object selezione : arrayIndirizziSelezionati) {
			IndirizzoSimulazione indirizzoSimulazione = (IndirizzoSimulazione) selezione;
			Indirizzo indirizzoSelezionato = indirizzoSimulazione.toIndirizzo();
			indirizzi.add(indirizzoSelezionato);
		}
		controllerPreventivo.setIndirizzi(indirizzi);
	}
}
