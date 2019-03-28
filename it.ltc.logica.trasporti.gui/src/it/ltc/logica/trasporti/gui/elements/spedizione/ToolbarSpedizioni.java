package it.ltc.logica.trasporti.gui.elements.spedizione;

import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.ToolBar;

import it.ltc.logica.common.controller.sistema.ControllerCommesse;
import it.ltc.logica.common.controller.trasporti.ControllerCorrieri;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.trasporti.Corriere;
import it.ltc.logica.database.model.centrale.trasporti.Spedizione;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.elements.ToolbarCRUDConFiltro;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.input.DateField;
import it.ltc.logica.utilities.variabili.Permessi;

public class ToolbarSpedizioni extends ToolbarCRUDConFiltro<TabellaSpedizioni, Spedizione, CriteriFiltraggioSpedizione> {
	
	private DateField dataDa;
	private DateField dataA;
	private ComboBox<Corriere> comboCorriere;
	private ComboBox<Commessa> comboCommessa;
	private Button btnContrassegnoSi;
	private Button btnContrassegnoNo;
	private Button btnContrassegnoIndifferente;
	private Button btnDatiSi;
	private Button btnDatiNo;
	private Button btnDatiIndifferente;
	private Button btnDataIndifferente;
	private Text textRiferimento;

	public ToolbarSpedizioni(Composite parent) {
		super(parent);
	}

	@Override
	protected void setupFiltri(Composite compositeFiltro) {
		compositeFiltro.setLayout(new GridLayout(2, false));
		compositeFiltro.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
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
		
		dataDa = new DateField(compositeData, false);
		dataDa.setRequired(false);
		dataDa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				btnDataIndifferente.setSelection(false);
			}
		});
		
		new SpacerLabel(compositeData);
		
		Label lblA = new Label(compositeData, SWT.NONE);
		lblA.setText("A:");
		
		dataA = new DateField(compositeData, false);
		dataA.setRequired(false);
		dataA.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				btnDataIndifferente.setSelection(false);
			}
		});
		
		Label lblCliente = new Label(compositeFiltro, SWT.NONE);
		lblCliente.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCliente.setText("Cliente: ");
		
		comboCommessa = new ComboBox<Commessa>(compositeFiltro);
		comboCommessa.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboCommessa.setItems(ControllerCommesse.getInstance().getTutteCommesse());
		comboCommessa.setRequired(false);
		
		Label lblCorriere = new Label(compositeFiltro, SWT.NONE);
		lblCorriere.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCorriere.setText("Corriere: ");
		
		comboCorriere = new ComboBox<Corriere>(compositeFiltro);
		comboCorriere.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboCorriere.setItems(ControllerCorrieri.getInstance().getCorrieri());
		comboCorriere.setRequired(false);
		
		Label lblContrassegno = new Label(compositeFiltro, SWT.NONE);
		lblContrassegno.setText("Contrassegno: ");
		
		Composite compositeContrassegno = new Composite(compositeFiltro, SWT.NONE);
		compositeContrassegno.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		compositeContrassegno.setLayout(new GridLayout(3, false));
		
		btnContrassegnoSi = new Button(compositeContrassegno, SWT.RADIO);
		btnContrassegnoSi.setBounds(0, 0, 90, 16);
		btnContrassegnoSi.setText("Si");
		btnContrassegnoSi.setSelection(false);
		
		btnContrassegnoNo = new Button(compositeContrassegno, SWT.RADIO);
		btnContrassegnoNo.setBounds(0, 0, 90, 16);
		btnContrassegnoNo.setText("No");
		btnContrassegnoNo.setSelection(false);
		
		btnContrassegnoIndifferente = new Button(compositeContrassegno, SWT.RADIO);
		btnContrassegnoIndifferente.setBounds(0, 0, 90, 16);
		btnContrassegnoIndifferente.setText("Indifferente");
		btnContrassegnoIndifferente.setSelection(true);
		
		Label lblDatiCompleti = new Label(compositeFiltro, SWT.NONE);
		lblDatiCompleti.setText("Dati Completi: ");
		
		Composite compositeDatiCompleti = new Composite(compositeFiltro, SWT.NONE);
		compositeDatiCompleti.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		compositeDatiCompleti.setLayout(new GridLayout(3, false));
		
		btnDatiSi = new Button(compositeDatiCompleti, SWT.RADIO);
		btnDatiSi.setBounds(0, 0, 90, 16);
		btnDatiSi.setText("Si");
		btnDatiSi.setSelection(false);
		
		btnDatiNo = new Button(compositeDatiCompleti, SWT.RADIO);
		btnDatiNo.setBounds(0, 0, 90, 16);
		btnDatiNo.setText("No");
		btnDatiNo.setSelection(false);
		
		btnDatiIndifferente = new Button(compositeDatiCompleti, SWT.RADIO);
		btnDatiIndifferente.setBounds(0, 0, 90, 16);
		btnDatiIndifferente.setText("Indifferente");
		btnDatiIndifferente.setSelection(true);
		
		Label lblRiferimento = new Label(compositeFiltro, SWT.NONE);
		lblRiferimento.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblRiferimento.setText("Ricerca testo: ");
		
		textRiferimento = new Text(compositeFiltro, SWT.BORDER);
		textRiferimento.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.character == SWT.CR || e.character == SWT.LF) {
					filtra();
				}
			}
		});
		textRiferimento.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textRiferimento.setMessage("DDT, Ordine cliente, Lettera di vettura, Destinatario ...");		
	}

	@Override
	protected CriteriFiltraggioSpedizione getCriteriDiFiltraggio() {
		Date da = null;
		Date a = null;
		if (!btnDataIndifferente.getSelection()) {
			da = dataDa.getSimpleStartValue();
			a = dataA.getSimpleEndValue();
		}
		Integer idCorriere = null;
		Corriere corriere = comboCorriere.getSelectedValue();
		if (corriere != null) {
			idCorriere = corriere.getId();
		}
		Integer idCommessa = null;
		Commessa commessa = comboCommessa.getSelectedValue();
		if (commessa != null) {
			idCommessa = commessa.getId();
		}
		Boolean contrassegno;
		if (btnContrassegnoSi.getSelection()) {
			contrassegno = true;
		} else if (btnContrassegnoNo.getSelection()) {
			contrassegno = false;
		} else {
			contrassegno = null;
		}
		Boolean datiCompleti;
		if (btnDatiSi.getSelection()) {
			datiCompleti = true;
		} else if (btnDatiNo.getSelection()) {
			datiCompleti = false;
		} else {
			datiCompleti = null;
		}
		String riferimento = null;
		if (!textRiferimento.getText().isEmpty())
			riferimento = textRiferimento.getText();
		CriteriFiltraggioSpedizione criteri = new CriteriFiltraggioSpedizione();
		criteri.setA(a);
		criteri.setDa(da);
		criteri.setContrassegno(contrassegno);
		criteri.setDatiCompleti(datiCompleti);
		criteri.setIdCommessa(idCommessa);
		criteri.setIdCorriere(idCorriere);
		criteri.setRiferimento(riferimento);
		return criteri;
	}

	@Override
	protected void resetCampiFiltri() {
		btnDataIndifferente.setSelection(true);
		comboCorriere.setSelectedValue(null);
		comboCommessa.setSelectedValue(null);
		btnContrassegnoSi.setSelection(false);
		btnContrassegnoNo.setSelection(false);
		btnContrassegnoIndifferente.setSelection(true);
		textRiferimento.setText("");
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.TRASPORTI_SPEDIZIONI_CUD.getID();
	}
	
	@Override
	protected void refreshTabella() {
		tabella.sincronizzaEAggiornaContenuto();
	}
	
	@Override
	protected void creaCompositeDx() {
		compositeDx = new Composite(this, SWT.NONE);
		compositeDx.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false, 1, 1));
		compositeDx.setLayout(new GridLayout(1, false));
		
//		SpacerLabel emptyLabel = new SpacerLabel(compositeDx);
//		emptyLabel.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		new SpacerLabel(compositeDx);
		new SpacerLabel(compositeDx);
		new SpacerLabel(compositeDx);
		new SpacerLabel(compositeDx);
		new SpacerLabel(compositeDx);
		new SpacerLabel(compositeDx);
		new SpacerLabel(compositeDx);
		new SpacerLabel(compositeDx);
		new SpacerLabel(compositeDx);
		
		toolbar = new ToolBar(compositeDx, SWT.FLAT | SWT.RIGHT);
		toolbar.setLayoutData(new GridData(SWT.LEFT, SWT.BOTTOM, true, false, 1, 1));
	}

	@Override
	protected void setupTastiToolbar() {
		aggiungiTastoNuovo();
		aggiungiTastoModifica();
		aggiungiTastoRefresh();
		aggiungiTastoFiltra();
		aggiungiTastoAnnullaFiltra();
	}

}
