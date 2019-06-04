package it.ltc.logica.ufficio.gui.elements.cassa;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.prodotto.Prodotto;
import it.ltc.logica.database.model.prodotto.TipoCassa;
import it.ltc.logica.gui.composite.Gruppo;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.dialog.DialogMessaggio;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.input.TextForString;
import it.ltc.logica.gui.validation.ParentValidationHandler;
import it.ltc.logica.ufficio.gui.elements.prodotto.TextForProdotto;

public class CompositeCassa extends Gruppo {
	
	public static final String title = "Cassa";
	
	private TextForProdotto textProdotto;
	private TextForString textCodiceCassa;
	private TextForString textModello;
	private ComboBox<TipoCassa> comboCassa;

	public CompositeCassa(ParentValidationHandler parentValidator, Composite parent) {
		super(parentValidator, parent, title);
	}
	
	public void setCommessa(Commessa commessa) {
		textProdotto.setCommessa(commessa);
	}

	@Override
	public void aggiungiElementiGrafici() {
		setLayout(new GridLayout(3, false));
		
		Label lblProdotto = new Label(this, SWT.NONE);
		lblProdotto.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblProdotto.setText("Prodotto a cassa: ");
		
		textProdotto = new TextForProdotto(this);
		textProdotto.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textProdotto.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				Prodotto prodottoSelezionato = textProdotto.getValue();
				if (prodottoSelezionato != null) {
					if (prodottoSelezionato.getCassa() == TipoCassa.NO) {
						textProdotto.resetValue();
						DialogMessaggio.openWarning("Prodotto selezionato non valido", "Il prodotto selezionato non \u00E8 valido perch\u00E8 non \u00E8 un prodotto a cassa.");
					} else {
						if (textCodiceCassa.getValue() == null || textCodiceCassa.getValue().isEmpty()) {
							textCodiceCassa.setValue(prodottoSelezionato.getChiaveCliente());
						}
						if (textModello.getValue() == null || textModello.getValue().isEmpty()) {
							textModello.setValue(prodottoSelezionato.getCodiceModello());
						}
						comboCassa.setSelectedValue(prodottoSelezionato.getCassa());
						comboCassa.setEnabled(false);
					}
					
				}
			}
		});
		
		new SpacerLabel(this);
		
		Label lblSku = new Label(this, SWT.NONE);
		lblSku.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblSku.setText("Codice cassa: ");
		
		textCodiceCassa = new TextForString(this);
		textCodiceCassa.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		nonUpdatableElements.add(textCodiceCassa);
		
		new SpacerLabel(this);
		
		Label lblModello = new Label(this, SWT.NONE);
		lblModello.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblModello.setText("Modello: ");
		
		textModello = new TextForString(this);
		textModello.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textModello.setRequired(false);

		new SpacerLabel(this);
		
		Label lblCassa = new Label(this, SWT.NONE);
		lblCassa.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCassa.setText("Cassa: ");
		
		comboCassa = new ComboBox<>(this);
		comboCassa.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboCassa.setItems(TipoCassa.values());

		new SpacerLabel(this);

	}
	
	public Prodotto getArticolo() {
		return textProdotto.getValue();
	}

	public void setArticolo(Prodotto articolo) {
		textProdotto.setValue(articolo);
	}
	
	public String getCodiceCassa() {
		return textCodiceCassa.getText();
	}

	public void setCodiceCassa(String value) {
		textCodiceCassa.setText(value);
	}

	public String getModello() {
		return textModello.getText();
	}

	public void setModello(String modello) {
		textModello.setText(modello);
	}
	
	public TipoCassa getTipoCassa() {
		return comboCassa.getSelectedValue();
	}

	public void setTipoCassa(TipoCassa cassa) {
		comboCassa.setSelectedValue(cassa);
	}

}
