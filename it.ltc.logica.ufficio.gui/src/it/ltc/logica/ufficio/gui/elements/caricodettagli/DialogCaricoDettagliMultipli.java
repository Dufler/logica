package it.ltc.logica.ufficio.gui.elements.caricodettagli;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.common.controller.ingressi.ControllerCarichiDettagli;
import it.ltc.logica.common.controller.ingressi.ControllerMagazzini;
import it.ltc.logica.common.controller.prodotti.ControllerProdotti;
import it.ltc.logica.common.controller.trasporti.ControllerNazioni;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.indirizzi.Nazione;
import it.ltc.logica.database.model.centrale.ingressi.CaricoDettaglio;
import it.ltc.logica.database.model.centrale.ingressi.CaricoTestata;
import it.ltc.logica.database.model.centrale.ingressi.Magazzino;
import it.ltc.logica.database.model.prodotto.Modello;
import it.ltc.logica.database.model.prodotto.Prodotto;
import it.ltc.logica.gui.decoration.Immagine;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.dialog.DialogSempliceConValidazione;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.input.TextForInteger;
import it.ltc.logica.ufficio.gui.elements.prodotto.TextForModello;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class DialogCaricoDettagliMultipli extends DialogSempliceConValidazione {
	
	public static final String title = "Righe del carico - Inserimento multiplo per modello";
	
	private final Commessa commessa;
	private final CaricoTestata carico;
	private final ControllerCarichiDettagli controller;
	private final ControllerProdotti controllerProdotti;
	
	
	private final List<CaricoDettaglio> dettagliEsistenti;
	private final List<CaricoDettaglio> dettagli;
	
	private TextForInteger textRiga;
	private TextForModello textModello;
	private ComboBox<Magazzino> comboMagazzino;
	private ComboBox<Nazione> comboMadeIn;
	
	private TabellaCaricoDettagliMultipli tabella;

	public DialogCaricoDettagliMultipli(Commessa commessa, CaricoTestata carico, List<CaricoDettaglio> dettagliEsistenti) {
		super(title, Immagine.CROCEVERDE_16X16.getImage(), true);
		
		this.commessa = commessa;
		this.carico = carico;
		this.controller = new ControllerCarichiDettagli(commessa);
		this.controllerProdotti = new ControllerProdotti(commessa);
		
		this.dettagliEsistenti = dettagliEsistenti;
		this.dettagli = new LinkedList<>();
		
		minimumHeight = 450;
	}

	@Override
	public boolean isDirty() {
		return textRiga.isDirty() || comboMagazzino.isDirty() || comboMadeIn.isDirty() || tabella.isDirty();
	}

	@Override
	protected boolean validation() {
		validate();
		if (valid) {
			setMadeIn();
			setMagazzino();
			setRiga();
			List<String> errors = validateModel();
			if (errors == null || errors.isEmpty()) {
				insertModel();
			} else {
				valid = false;
				String errorMessage = "Errori riscontrati nella validazione dei dati:";
				StringBuilder sb = new StringBuilder(errorMessage);
				for (String error : errors) {
					sb.append("\r\n");
					sb.append(error);
				}
				mostraErrore(errorMessage, sb.toString());
			}
		}
		return valid;
	}
	
	private boolean insertModel() {
		boolean insert = true;
		for (CaricoDettaglio dettaglio : dettagli) {
			if (dettaglio.getQuantitaDichiarata() > 0) {
				boolean insertParziale = controller.inserisciSenzaProgressDialog(dettaglio);
				if (!insertParziale)
					insert = false;
			}
		}
		return insert;
	}
	
	private List<String> validateModel() {
		List<String> errors = new LinkedList<>();
		int totaleDichiarato = 0;
		for (CaricoDettaglio dettaglio : dettagli) {
			totaleDichiarato += dettaglio.getQuantitaDichiarata();
			if (dettaglio.getQuantitaDichiarata() < 0) {
				errors.add("La quantit\u00E0 dichiarata per il singolo prodotto non può essere minore di 0.");
			}
		}
		if (totaleDichiarato < 1) {
			errors.add("La quantit\u00E0 dichiarata totale non può essere minore di 1.");
		}
		return errors;
	}

	@Override
	protected void aggiungiElementiGrafici(Composite container) {		
		Composite composite = new Composite(container, SWT.NONE);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		composite.setLayout(new GridLayout(3, false));
		
		Label lblRiga = new Label(composite, SWT.NONE);
		lblRiga.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblRiga.setText("Riga: ");
		
		textRiga = new TextForInteger(composite);
		textRiga.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textRiga.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setRiga();
			}
		});
		
		new SpacerLabel(composite);
		
		Label lblMagazzino = new Label(composite, SWT.NONE);
		lblMagazzino.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblMagazzino.setText("Magazzino: ");
		
		comboMagazzino = new ComboBox<>(composite);
		comboMagazzino.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboMagazzino.setItems(ControllerMagazzini.getInstance().getMagazzini(commessa));
		comboMagazzino.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setMagazzino();
			}
		});

		new SpacerLabel(composite);
		
		Label lblMadeIn = new Label(composite, SWT.NONE);
		lblMadeIn.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblMadeIn.setText("Made In: ");
		
		comboMadeIn = new ComboBox<>(composite);
		comboMadeIn.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboMadeIn.setItems(ControllerNazioni.getInstance().getNazioni());
		comboMadeIn.setRequired(false);
		comboMadeIn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setMadeIn();
			}
		});

		new SpacerLabel(composite);
		
		Label lblModello = new Label(composite, SWT.NONE);
		lblModello.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblModello.setText("Modello: ");
		
		textModello = new TextForModello(composite);
		textModello.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textModello.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setModello();
			}
		});
		

		new SpacerLabel(composite);
		
		tabella = new TabellaCaricoDettagliMultipli(container, commessa, dettagli);
	}

	@Override
	protected void checkElementiGrafici() {
		comboMadeIn.setSelectedValue(ControllerNazioni.getInstance().getDefault());
		textRiga.setValue(trovaProgressivoRiga());
		comboMagazzino.setSelectedValue(ControllerMagazzini.getInstance().getMagazzinoDefault(commessa));
		textModello.setCommessa(commessa);
	}
	
	private int trovaProgressivoRiga() {
		int max = 0;
		if (dettagliEsistenti != null) for (CaricoDettaglio dettaglio : dettagliEsistenti) {
			if (dettaglio.getRiga() > max)
				max = dettaglio.getRiga();
		}
		return max + 1;
	}
	
	private void setModello() {
		Modello modello = textModello.getValue();
		if (modello != null) {
			//svuoto i dettagli di carico presenti
			dettagli.clear();
			//recupero tutti i dettagli necessari
			String madeIn = comboMadeIn.getSelectedValue() != null ? comboMadeIn.getSelectedValue().getCodiceIsoTre() : null;
			String magazzino = comboMagazzino.getSelectedValue() != null ? comboMagazzino.getSelectedValue().getCodiceLTC() : null;
			int riga = textRiga.getValue() != null ? textRiga.getValue() : 0;
			//vado a ricercare tutti i prodotti afferenti a quel modello
			Prodotto filtro = new Prodotto();
			filtro.setCommessa(modello.getCommessa());
			filtro.setCodiceModello(modello.getModello());
			List<Prodotto> prodotti = controllerProdotti.cercaDaModello(filtro);
			//per ogni prodotto trovato vado a creare una possibile riga di carico
			for (Prodotto prodotto : prodotti) {
				CaricoDettaglio dettaglio = new CaricoDettaglio();
				dettaglio.setArticolo(prodotto.getId());
				dettaglio.setCarico(carico.getId());
				dettaglio.setMadeIn(madeIn);
				dettaglio.setMagazzino(magazzino);
				dettaglio.setRiga(riga);
				dettagli.add(dettaglio);
			}
			//Imposto i dettagli appena creati nella tabella
			tabella.aggiornaContenuto();
		}
	}
	
	private void setMadeIn() {
		Nazione nazione = comboMadeIn.getSelectedValue();
		if (nazione != null) {
			for (CaricoDettaglio dettaglio : dettagli) {
				dettaglio.setMadeIn(nazione.getCodiceIsoTre());
			}
		}
	}
	
	private void setMagazzino() {
		Magazzino magazzino = comboMagazzino.getSelectedValue();
		if (magazzino != null) {
			for (CaricoDettaglio dettaglio : dettagli) {
				dettaglio.setMadeIn(magazzino.getCodiceLTC());
			}
		}
	}
	
	private void setRiga() {
		int riga = textRiga.getValue() != null ? textRiga.getValue() : 0;
		for (CaricoDettaglio dettaglio : dettagli) {
			dettaglio.setRiga(riga);
		}
	}

}
