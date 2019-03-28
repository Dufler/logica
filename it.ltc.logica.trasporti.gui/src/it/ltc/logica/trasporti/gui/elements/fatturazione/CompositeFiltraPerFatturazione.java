package it.ltc.logica.trasporti.gui.elements.fatturazione;

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

import it.ltc.logica.gui.composite.GruppoSemplice;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.input.DateField;
import it.ltc.logica.gui.validation.ParentValidationHandler;

public class CompositeFiltraPerFatturazione extends GruppoSemplice {
	
	private DateField dataDa;
	private DateField dataA;
	private Text textRiferimento;
	private Button btnContrassegnoSi;
	private Button btnContrassegnoNo;
	private Button btnContrassegnoIndifferente;
	private Button btnDatiSi;
	private Button btnDatiNo;
	private Button btnDatiIndifferente;
	private Button btnDataIndifferente;
	
	private Button btnFiltra;
	
	private TabellaFatturazione tabella;

	public CompositeFiltraPerFatturazione(ParentValidationHandler parentValidator, Composite parent) {
		super(parentValidator, parent);
	}
	
	public void setTabellaFatturazione(TabellaFatturazione tabella) {
		this.tabella = tabella;
	}

	@Override
	public void aggiungiElementiGrafici() {
		setLayout(new GridLayout(1, false));
		
		Composite compositeFiltro = new Composite(this, SWT.NONE);
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
		
		dataDa = new DateField(compositeData);
		dataDa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				btnDataIndifferente.setSelection(false);
			}
		});
		
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
					filtraSpedizioni();
				}
			}
		});
		textRiferimento.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textRiferimento.setMessage("DDT, Ordine cliente, Lettera di vettura, Destinatario ...");
		
		new Label(compositeFiltro, SWT.NONE);
		
		Composite compositeControlli = new Composite(compositeFiltro, SWT.NONE);
		compositeControlli.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		compositeControlli.setLayout(new GridLayout(2, false));
		
		btnFiltra = new Button(compositeControlli, SWT.NONE);
		btnFiltra.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				filtraSpedizioni();
			}
		});
		btnFiltra.setText("Filtra");
		
		Button btnAnnullaFiltro = new Button(compositeControlli, SWT.NONE);
		btnAnnullaFiltro.setSize(83, 25);
		btnAnnullaFiltro.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				annullaFiltro();
			}
		});
		btnAnnullaFiltro.setText("Annulla Filtro");
	}
	
	private void filtraSpedizioni() {
		Date da = null;
		Date a = null;
		if (!btnDataIndifferente.getSelection()) {
			da = dataDa.getSimpleStartValue();
			a = dataA.getSimpleEndValue();
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
		CriteriFiltraggioSpedizioniFatturazione criteri = new CriteriFiltraggioSpedizioniFatturazione();
		criteri.setA(a);
		criteri.setDa(da);
		criteri.setContrassegno(contrassegno);
		criteri.setDatiCompleti(datiCompleti);
		criteri.setRiferimento(riferimento);
		tabella.filtra(criteri);
	}
	
	private void annullaFiltro() {
		tabella.annullaFiltro();
	}

}
