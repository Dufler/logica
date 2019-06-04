package it.ltc.logica.ufficio.gui.elements.prodotto;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.common.controller.trasporti.ControllerNazioni;
import it.ltc.logica.database.model.centrale.CategoriaMerceologica;
import it.ltc.logica.database.model.centrale.indirizzi.Nazione;
import it.ltc.logica.database.model.prodotto.TipoCassa;
import it.ltc.logica.gui.composite.Gruppo;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.input.TextForString;
import it.ltc.logica.gui.validation.ParentValidationHandler;

public class CompositeProdotto extends Gruppo {
	
	private static final String title = "Prodotto";
	
	private TextForString textSKU;
	private TextForString textModello;
	private TextForString textBarcode;
	private TextForString textTaglia;
	private TextForString textColore;
	private TextForString textDescrizione;
	private TextForString textComposizione;
	private TextForString textBrand;
	private TextForString textStagione;
	private ComboBox<CategoriaMerceologica> comboCategoria;
	private ComboBox<Nazione> comboMadeIn;
	private ComboBox<TipoCassa> comboCassa;

	public CompositeProdotto(ParentValidationHandler parentValidator, Composite parent) {
		super(parentValidator, parent, title);
	}

	@Override
	public void aggiungiElementiGrafici() {
		setLayout(new GridLayout(3, false));
		
		Label lblSku = new Label(this, SWT.NONE);
		lblSku.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblSku.setText("SKU: ");
		
		textSKU = new TextForString(this);
		textSKU.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		nonUpdatableElements.add(textSKU);
		
		new SpacerLabel(this);
		
		Label lblModello = new Label(this, SWT.NONE);
		lblModello.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblModello.setText("Modello: ");
		
		textModello = new TextForString(this);
		textModello.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		new SpacerLabel(this);
		
		Label lblBarcode = new Label(this, SWT.NONE);
		lblBarcode.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblBarcode.setText("Barcode: ");
		
		textBarcode = new TextForString(this);
		textBarcode.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		nonUpdatableElements.add(textBarcode);

		new SpacerLabel(this);
		
		Label lblTaglia = new Label(this, SWT.NONE);
		lblTaglia.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblTaglia.setText("Taglia: ");
		
		textTaglia = new TextForString(this);
		textTaglia.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		new SpacerLabel(this);
		
		Label lblColore = new Label(this, SWT.NONE);
		lblColore.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblColore.setText("Colore: ");
		
		textColore = new TextForString(this);
		textColore.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textColore.setRequired(false);

		new SpacerLabel(this);
		
		Label lblDescrizione = new Label(this, SWT.NONE);
		lblDescrizione.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblDescrizione.setText("Descrizione: ");
		
		textDescrizione = new TextForString(this);
		textDescrizione.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		new SpacerLabel(this);
		
		Label lblComposizione = new Label(this, SWT.NONE);
		lblComposizione.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblComposizione.setText("Composizione: ");
		
		textComposizione = new TextForString(this);
		textComposizione.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textComposizione.setRequired(false);

		new SpacerLabel(this);
		
		Label lblBrand = new Label(this, SWT.NONE);
		lblBrand.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblBrand.setText("Brand: ");
		
		textBrand = new TextForString(this);
		textBrand.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		new SpacerLabel(this);
		
		Label lblStagione = new Label(this, SWT.NONE);
		lblStagione.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblStagione.setText("Stagione: ");
		
		textStagione = new TextForString(this);
		textStagione.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		new SpacerLabel(this);
		
		Label lblCategoria = new Label(this, SWT.NONE);
		lblCategoria.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCategoria.setText("Categoria: ");
		
		comboCategoria = new ComboBox<>(this);
		comboCategoria.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		new SpacerLabel(this);
		
		Label lblMadeIn = new Label(this, SWT.NONE);
		lblMadeIn.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblMadeIn.setText("Made In: ");
		
		comboMadeIn = new ComboBox<>(this);
		comboMadeIn.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboMadeIn.setItems(ControllerNazioni.getInstance().getNazioni());
		comboMadeIn.setRequired(false);

		new SpacerLabel(this);
		
		Label lblCassa = new Label(this, SWT.NONE);
		lblCassa.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCassa.setText("Cassa: ");
		
		comboCassa = new ComboBox<>(this);
		comboCassa.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboCassa.setItems(TipoCassa.values());

		new SpacerLabel(this);
	}
	
	public ComboBox<CategoriaMerceologica> getComboCategoria() {
		return comboCategoria;
	}

	public String getSKU() {
		return textSKU.getText();
	}

	public void setSKU(String sku) {
		textSKU.setText(sku);
	}

	public String getModello() {
		return textModello.getText();
	}

	public void setModello(String modello) {
		textModello.setText(modello);
	}

	public String getBarcode() {
		return textBarcode.getText();
	}

	public void setBarcode(String barcode) {
		textBarcode.setText(barcode);
	}

	public String getTaglia() {
		return textTaglia.getText();
	}

	public void setTaglia(String taglia) {
		textTaglia.setText(taglia);
	}

	public String getColore() {
		return textColore.getText();
	}

	public void setColore(String colore) {
		textColore.setText(colore);
	}

	public String getDescrizione() {
		return textDescrizione.getText();
	}

	public void setDescrizione(String descrizione) {
		textDescrizione.setText(descrizione);
	}

	public String getComposizione() {
		return textComposizione.getText();
	}

	public void setComposizione(String composizione) {
		textComposizione.setText(composizione);
	}

	public String getBrand() {
		return textBrand.getText();
	}

	public void setBrand(String brand) {
		textBrand.setText(brand);
	}
	
	public String getStagione() {
		return textStagione.getText();
	}

	public void setStagione(String stagione) {
		textStagione.setText(stagione);
	}

	public CategoriaMerceologica getCategoria() {
		return comboCategoria.getSelectedValue();
	}

	public void setCategoria(CategoriaMerceologica categoria) {
		comboCategoria.setSelectedValue(categoria);
	}

	public Nazione getMadeIn() {
		return comboMadeIn.getSelectedValue();
	}

	public void setMadeIn(Nazione madeIn) {
		comboMadeIn.setSelectedValue(madeIn);
	}

	public TipoCassa getCassa() {
		return comboCassa.getSelectedValue();
	}

	public void setCassa(TipoCassa cassa) {
		comboCassa.setSelectedValue(cassa);
	}

}
