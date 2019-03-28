package it.ltc.logica.trasporti.gui.report.wizards.destinazione;

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
import it.ltc.logica.gui.wizard.PaginaWizard;
import it.ltc.logica.trasporti.controller.ReportController;

public class SelezionaInputWizardPage extends PaginaWizard {
	
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
	
	protected SelezionaInputWizardPage() {
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
