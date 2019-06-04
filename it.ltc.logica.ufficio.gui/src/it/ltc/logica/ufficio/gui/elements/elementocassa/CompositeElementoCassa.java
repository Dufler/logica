package it.ltc.logica.ufficio.gui.elements.elementocassa;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.prodotto.Prodotto;
import it.ltc.logica.gui.composite.Gruppo;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.input.TextForInteger;
import it.ltc.logica.gui.validation.ParentValidationHandler;
import it.ltc.logica.ufficio.gui.elements.prodotto.TextForProdotto;

public class CompositeElementoCassa extends Gruppo {

	public static final String title = "Prodotto Cassa";
	
	private TextForProdotto textProdotto;
	private TextForInteger textQuantita;
	
	public CompositeElementoCassa(ParentValidationHandler parentValidator, Composite parent, Commessa commessa) {
		super(parentValidator, parent, title);
		if (commessa != null) {
			textProdotto.setCommessa(commessa);
		}
	}

	@Override
	public void aggiungiElementiGrafici() {
		setLayout(new GridLayout(3, false));
		
		Label lblProdotto = new Label(this, SWT.NONE);
		lblProdotto.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblProdotto.setText("Prodotto: ");
		
		textProdotto = new TextForProdotto(this);
		textProdotto.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		new SpacerLabel(this);
		
		Label lblQuantit = new Label(this, SWT.NONE);
		lblQuantit.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblQuantit.setText("Quantit\u00E0: ");
		
		textQuantita = new TextForInteger(this);
		textQuantita.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		new SpacerLabel(this);
	}
	
	public Prodotto getArticolo() {
		return textProdotto.getValue();
	}

	public void setArticolo(Prodotto articolo) {
		textProdotto.setValue(articolo);
	}
	
	public int getQuantita() {
		return textQuantita.getValue();
	}
	
	public void setQuantita(int value) {
		textQuantita.setValue(value);
	}

}
