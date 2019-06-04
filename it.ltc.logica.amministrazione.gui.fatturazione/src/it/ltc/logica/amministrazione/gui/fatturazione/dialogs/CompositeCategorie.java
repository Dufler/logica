package it.ltc.logica.amministrazione.gui.fatturazione.dialogs;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.common.controller.prodotti.ControllerBrand;
import it.ltc.logica.common.controller.sistema.ControllerCommesse;
import it.ltc.logica.database.model.centrale.CategoriaMerceologica.StatoCategoria;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.prodotto.Brand;
import it.ltc.logica.gui.composite.Gruppo;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.input.TextForDescription;
import it.ltc.logica.gui.input.TextForString;
import it.ltc.logica.gui.validation.ParentValidationHandler;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class CompositeCategorie extends Gruppo {
	
	public static final String title = "Categorie";
	
	private ComboBox<Commessa> comboCommessa;
	private ComboBox<Brand> comboBrand;
	private ComboBox<StatoCategoria> comboStato;
	private TextForString textCodice;
	private TextForDescription textDescrizione;

	public CompositeCategorie(ParentValidationHandler parentValidator, Composite parent) {
		super(parentValidator, parent, title);
	}

	@Override
	public void aggiungiElementiGrafici() {
		setLayout(new GridLayout(3, false));
		
		Label lblCommessa = new Label(this, SWT.NONE);
		lblCommessa.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCommessa.setText("Commessa: ");
		
		comboCommessa = new ComboBox<>(this);
		comboCommessa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				impostaCommessa();
			}
		});
		comboCommessa.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboCommessa.setItems(ControllerCommesse.getInstance().getTutteCommesse());
		addNonUpdatableElement(comboCommessa);
		
		new SpacerLabel(this);
		
		Label lblBrand = new Label(this, SWT.NONE);
		lblBrand.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblBrand.setText("Brand: ");
		
		comboBrand = new ComboBox<>(this);
		comboBrand.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboBrand.setRequired(false);
		comboBrand.setEnabled(false);

		new SpacerLabel(this);
		
		Label lblStato = new Label(this, SWT.NONE);
		lblStato.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblStato.setText("Stato: ");
		
		comboStato = new ComboBox<>(this);
		comboStato.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboStato.setItems(StatoCategoria.values());

		new SpacerLabel(this);
		
		Label lblCodice = new Label(this, SWT.NONE);
		lblCodice.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCodice.setText("Codice: ");
		
		textCodice = new TextForString(this);
		textCodice.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		addNonUpdatableElement(textCodice);

		new SpacerLabel(this);
		
		Label lblDescrizione = new Label(this, SWT.NONE);
		lblDescrizione.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblDescrizione.setText("Descrizione: ");
		
		textDescrizione = new TextForDescription(this, 50);
		textDescrizione.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));

		new SpacerLabel(this);
	}
	
	private void impostaCommessa() {
		Commessa commessa = comboCommessa.getSelectedValue();
		if (commessa != null) {
			ControllerBrand controller = ControllerBrand.getInstance(commessa);
			comboBrand.setItems(controller.getBrand());
			comboBrand.setEnabled(true);
		} else {
			comboBrand.setEnabled(false);
		}
	}

	public Commessa getCommessa() {
		return comboCommessa.getSelectedValue();
	}

	public void setCommessa(Commessa commessa) {
		comboCommessa.setSelectedValue(commessa);
		impostaCommessa();
	}

	public Brand getBrand() {
		return comboBrand.getSelectedValue();
	}

	public void setBrand(Brand brand) {
		comboBrand.setSelectedValue(brand);
	}

	public StatoCategoria getStato() {
		return comboStato.getSelectedValue();
	}

	public void setStato(StatoCategoria stato) {
		comboStato.setSelectedValue(stato);
	}

	public String getCodice() {
		return textCodice.getValue();
	}

	public void setCodice(String codice) {
		textCodice.setValue(codice);
	}

	public String getDescrizione() {
		return textDescrizione.getValue();
	}

	public void setDescrizione(String descrizione) {
		textDescrizione.setValue(descrizione);
	}
}
