package it.ltc.logica.ufficio.gui.elements.prodotto;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.prodotto.Modello;
import it.ltc.logica.gui.input.TextForSelection;

public class TextForModello extends TextForSelection<Modello> {
	
	private static final String HINT_SELEZIONA_MODELLO = "Doppio click per selezionare un modello...";
	
	private Commessa commessa;

	public TextForModello(Composite parent) {
		super(parent, HINT_SELEZIONA_MODELLO);
	}
	
	public void setCommessa(Commessa commessa) {
		this.commessa = commessa;
	}

	@Override
	protected Modello apriDialogSelezione() {
		DialogSelezioneModello dialog = new DialogSelezioneModello(commessa);
		return dialog.apri();
	}

	@Override
	protected void mostraSelezione(Modello selezionato) {
		setText(selezionato.getModello());
	}

}
