package it.ltc.logica.trasporti.gui.report.wizards.semplice;

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

import it.ltc.logica.common.controller.sistema.ControllerCommesse;
import it.ltc.logica.common.controller.trasporti.ControllerCodiciClienteCorriere;
import it.ltc.logica.common.controller.trasporti.ControllerCorrieri;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.trasporti.CodiceClienteCorriere;
import it.ltc.logica.database.model.centrale.trasporti.Corriere;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.input.DateField;
import it.ltc.logica.gui.input.TextForDouble;
import it.ltc.logica.gui.input.TextForInteger;
import it.ltc.logica.gui.wizard.PaginaWizard;
import it.ltc.logica.trasporti.controller.ReportController;

public class StatisticheSempliciSpedizioniSelezionaInputWizardPage extends PaginaWizard {
	
	private static final String titolo = "Statistiche sulle spedizioni";
	private static final String descrizione = "Seleziona i dati di interesse su cui elaborare le statistiche.";
	
	private ReportController controller;
	
	private DateField dataDa;
	private DateField dataA;
	private ComboBox<Corriere> comboCorriere;
	private ComboBox<Commessa> comboCommessa;
	private ComboBox<CodiceClienteCorriere> comboCodice;
	private Button btnSi;
	private Button btnNo;
	private Button btnIndifferente;
	private Button btnDataIndifferente;
	private TextForInteger textColliDa;
	private TextForInteger textColliA;
	private TextForInteger textPezziDa;
	private TextForInteger textPezziA;
	private TextForDouble textPesoDa;
	private TextForDouble textPesoA;
	private TextForDouble textVolumeDa;
	private TextForDouble textVolumeA;
	
	protected StatisticheSempliciSpedizioniSelezionaInputWizardPage() {
		super(titolo, descrizione);
		controller = ReportController.getInstance();
		controller.resetFiltri();
		setPageComplete(true);
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(1, false));
		
		Label lblEPossibileRiempire = new Label(container, SWT.NONE);
		lblEPossibileRiempire.setText("Non \u00E8 necessario compilare tutti i campi, inserire solo i filtri di interesse.");
		
		Composite compositeFiltro = new Composite(container, SWT.NONE);
		compositeFiltro.setLayout(new GridLayout(2, false));
		compositeFiltro.setLayoutData(new GridData(SWT.FILL, SWT.TOP, true, true, 1, 1));
		
		Label lblData = new Label(compositeFiltro, SWT.NONE);
		lblData.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblData.setText("Data: ");
		
		Composite compositeData = new Composite(compositeFiltro, SWT.NONE);
		compositeData.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		compositeData.setLayout(new GridLayout(6, false));
		
		btnDataIndifferente = new Button(compositeData, SWT.CHECK);
		btnDataIndifferente.setBounds(0, 0, 93, 16);
		btnDataIndifferente.setText("Indifferente");
		btnDataIndifferente.setSelection(true);
		
		Label lblDa = new Label(compositeData, SWT.NONE);
		lblDa.setText("Da:");
		
		dataDa = new DateField(compositeData);
		dataDa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				btnDataIndifferente.setSelection(false);
			}
		});
		dataDa.setRequired(false);
		addChild(dataDa);
		
		new SpacerLabel(compositeData);
		
		Label lblA = new Label(compositeData, SWT.NONE);
		lblA.setText("A:");
		
		dataA = new DateField(compositeData);
		dataA.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				btnDataIndifferente.setSelection(false);
			}
		});
		dataA.setRequired(false);
		addChild(dataA);
		
		Label lblCliente = new Label(compositeFiltro, SWT.NONE);
		lblCliente.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCliente.setText("Cliente: ");
		
		comboCommessa = new ComboBox<Commessa>(compositeFiltro);
		comboCommessa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setCodiciCliente();
			}
		});
		comboCommessa.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboCommessa.setItems(ControllerCommesse.getInstance().getTutteCommesse());
		comboCommessa.setRequired(false);
		addChild(comboCommessa);
		
		Label lblCorriere = new Label(compositeFiltro, SWT.NONE);
		lblCorriere.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCorriere.setText("Corriere: ");
		
		comboCorriere = new ComboBox<Corriere>(compositeFiltro);
		comboCorriere.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setCodiciCliente();
			}
		});
		comboCorriere.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboCorriere.setItems(ControllerCorrieri.getInstance().getCorrieri());
		comboCorriere.setRequired(false);
		addChild(comboCorriere);
		
		Label lblCodice = new Label(compositeFiltro, SWT.NONE);
		lblCodice.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCodice.setText("Codice: ");
		
		comboCodice = new ComboBox<CodiceClienteCorriere>(compositeFiltro);
		comboCodice.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboCodice.setRequired(false);
		comboCodice.setEnabled(false);
		addChild(comboCodice);
		
		Label lblContrassegno = new Label(compositeFiltro, SWT.NONE);
		lblContrassegno.setText("Contrassegno: ");
		
		Composite compositeContrassegno = new Composite(compositeFiltro, SWT.NONE);
		compositeContrassegno.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		compositeContrassegno.setLayout(new GridLayout(3, false));
		
		btnSi = new Button(compositeContrassegno, SWT.RADIO);
		btnSi.setBounds(0, 0, 90, 16);
		btnSi.setText("Si");
		btnSi.setSelection(false);
		
		btnNo = new Button(compositeContrassegno, SWT.RADIO);
		btnNo.setBounds(0, 0, 90, 16);
		btnNo.setText("No");
		btnNo.setSelection(false);
		
		btnIndifferente = new Button(compositeContrassegno, SWT.RADIO);
		btnIndifferente.setBounds(0, 0, 90, 16);
		btnIndifferente.setText("Indifferente");
		btnIndifferente.setSelection(true);
		
		Label lblColli = new Label(compositeFiltro, SWT.NONE);
		lblColli.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblColli.setText("Colli: ");
		
		Composite compositeColli = new Composite(compositeFiltro, SWT.NONE);
		compositeColli.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		compositeColli.setLayout(new GridLayout(6, false));
		
		Label lblColliDa = new Label(compositeColli, SWT.NONE);
		lblColliDa.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblColliDa.setBounds(0, 0, 55, 15);
		lblColliDa.setText("Da: ");
		
		textColliDa = new TextForInteger(compositeColli);
		textColliDa.setRequired(false);
		addChild(textColliDa);
		
		new SpacerLabel(compositeColli);
		
		Label lblColliA = new Label(compositeColli, SWT.NONE);
		lblColliA.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblColliA.setText("A: ");
		
		textColliA = new TextForInteger(compositeColli);
		textColliA.setRequired(false);
		addChild(textColliA);
		
		new SpacerLabel(compositeColli);
		
		Label lblPezzi = new Label(compositeFiltro, SWT.NONE);
		lblPezzi.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPezzi.setText("Pezzi:");
		
		Composite compositePezzi = new Composite(compositeFiltro, SWT.NONE);
		compositePezzi.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		compositePezzi.setLayout(new GridLayout(6, false));
		
		Label lblPezziDa = new Label(compositePezzi, SWT.NONE);
		lblPezziDa.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPezziDa.setBounds(0, 0, 55, 15);
		lblPezziDa.setText("Da: ");
		
		textPezziDa = new TextForInteger(compositePezzi);
		textPezziDa.setRequired(false);
		addChild(textPezziDa);
		
		new SpacerLabel(compositePezzi);
		
		Label lblPezziA = new Label(compositePezzi, SWT.NONE);
		lblPezziA.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPezziA.setText("A: ");
		
		textPezziA = new TextForInteger(compositePezzi);
		textPezziA.setRequired(false);
		addChild(textPezziA);
		
		new SpacerLabel(compositePezzi);
		
		Label lblPeso = new Label(compositeFiltro, SWT.NONE);
		lblPeso.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPeso.setText("Peso: ");
		
		Composite compositePeso = new Composite(compositeFiltro, SWT.NONE);
		compositePeso.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		compositePeso.setLayout(new GridLayout(6, false));
		
		Label lblPesoDa = new Label(compositePeso, SWT.NONE);
		lblPesoDa.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPesoDa.setBounds(0, 0, 55, 15);
		lblPesoDa.setText("Da: ");
		
		textPesoDa = new TextForDouble(compositePeso);
		textPesoDa.setRequired(false);
		addChild(textPesoDa);
		
		new SpacerLabel(compositePeso);
		
		Label lblPesoA = new Label(compositePeso, SWT.NONE);
		lblPesoA.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPesoA.setText("A: ");
		
		textPesoA = new TextForDouble(compositePeso);
		textPesoA.setRequired(false);
		addChild(textPesoA);
		
		new SpacerLabel(compositePeso);
		
		Label lblVolume = new Label(compositeFiltro, SWT.NONE);
		lblVolume.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblVolume.setText("Volume: ");
		
		Composite compositeVolume = new Composite(compositeFiltro, SWT.NONE);
		compositeVolume.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		compositeVolume.setLayout(new GridLayout(6, false));
		
		Label lblVolumeDa = new Label(compositeVolume, SWT.NONE);
		lblVolumeDa.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblVolumeDa.setBounds(0, 0, 55, 15);
		lblVolumeDa.setText("Da: ");
		
		textVolumeDa = new TextForDouble(compositeVolume);
		textVolumeDa.setRequired(false);
		addChild(textVolumeDa);
		
		new SpacerLabel(compositeVolume);
		
		Label lblVolumeA = new Label(compositeVolume, SWT.NONE);
		lblVolumeA.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblVolumeA.setText("A: ");
		
		textVolumeA = new TextForDouble(compositeVolume);
		textVolumeA.setRequired(false);
		addChild(textVolumeA);
		
		new SpacerLabel(compositeVolume);
	}
	
	private void setCodiciCliente() {
		Commessa commessaSelezionata = comboCommessa.getSelectedValue();
		Corriere corriereSelezionato = comboCorriere.getSelectedValue();
		List<CodiceClienteCorriere> listaCodici = new LinkedList<CodiceClienteCorriere>();
		for (CodiceClienteCorriere codice : ControllerCodiciClienteCorriere.getInstance().getCodiciCliente()) {
			boolean commessaOk = commessaSelezionata != null ? codice.getCommessa() == commessaSelezionata.getId() : true;
			boolean corriereOk = corriereSelezionato != null ? codice.getCorriere() == corriereSelezionato.getId() : true;
			if (commessaOk && corriereOk)
				listaCodici.add(codice);
		}
		boolean hasElements = listaCodici.size() > 0;
		comboCodice.setEnabled(hasElements);
		comboCodice.setItems(listaCodici);
	}

	@Override
	public void copyDataToModel() {
		controller.resetFiltri();
		//Date
		if (!btnDataIndifferente.getSelection()) {
			controller.setDataDa(dataDa.getSimpleStartValue());
			controller.setDataA(dataA.getSimpleEndValue());
		} else {
			controller.setDataDa(null);
			controller.setDataA(null);
		}
		//Commessa
		controller.setCommessa(comboCommessa.getSelectedValue());
		//Corriere
		controller.setCorriere(comboCorriere.getSelectedValue());
		//Codice cliente
		if (comboCodice.isEnabled())
			controller.setCodice(comboCodice.getSelectedValue());
		else
			controller.setCodice(null);
		//Colli
		controller.setMinimoColli(textColliDa.getValue());
		controller.setMassimoColli(textColliA.getValue());
		//Pezzi
		controller.setMinimoPezzi(textPezziDa.getValue());
		controller.setMassimoPezzi(textPezziA.getValue());
		//Peso
		controller.setMinimoPeso(textPesoDa.getValue());
		controller.setMassimoPeso(textPesoA.getValue());
		//Volume
		controller.setMinimoVolume(textVolumeDa.getValue());
		controller.setMassimoVolume(textVolumeA.getValue());
		//Contrassegno
		Boolean contrassegno;
		if (btnSi.getSelection()) {
			contrassegno = true;
		} else if (btnNo.getSelection()) {
			contrassegno = false;
		} else {
			contrassegno = null;
		}
		controller.setContrassegno(contrassegno);
	}
}