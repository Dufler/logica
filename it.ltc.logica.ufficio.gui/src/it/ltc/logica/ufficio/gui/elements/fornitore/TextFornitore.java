package it.ltc.logica.ufficio.gui.elements.fornitore;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.ingressi.Fornitore;
import it.ltc.logica.gui.input.TextForSelection;

public class TextFornitore extends TextForSelection<Fornitore> {
	
	private static final String TOOLTIP_FORNITORE = "Doppio click per inserire il fornitore.";

	private Commessa commessa;
	
	public TextFornitore(Composite parent) {
		super(parent, TOOLTIP_FORNITORE);
	}
	
	public void setCommessa(Commessa commessa) {
		this.commessa = commessa;
	}

	@Override
	protected Fornitore apriDialogSelezione() {
		DialogSelezioneFornitore dialog = new DialogSelezioneFornitore(commessa);
		return dialog.apri();
	}

	@Override
	protected void mostraSelezione(Fornitore selezionato) {
		setText(selezionato.getNome());
	}

}
