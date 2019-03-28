package it.ltc.logica.ufficio.gui.elements.prodotto;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.Prodotto;
import it.ltc.logica.gui.input.TextForSelection;

public class TextForProdotto extends TextForSelection<Prodotto> {
	
	private static final String HINT_SELEZIONA_PRODOTTO = "Doppio click per selezionare un prodotto...";
	
	private Commessa commessa;

	public TextForProdotto(Composite parent) {
		super(parent, HINT_SELEZIONA_PRODOTTO);
	}
	
	public void setCommessa(Commessa commessa) {
		this.commessa = commessa;
	}

	@Override
	protected Prodotto apriDialogSelezione() {
		DialogSelezioneProdotto dialog = new DialogSelezioneProdotto(commessa);
		return dialog.apri();
	}

	@Override
	protected void mostraSelezione(Prodotto selezionato) {
		setText(selezionato.getChiaveCliente());
	}

}
