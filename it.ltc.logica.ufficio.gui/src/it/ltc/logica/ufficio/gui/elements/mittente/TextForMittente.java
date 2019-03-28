package it.ltc.logica.ufficio.gui.elements.mittente;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.gui.input.TextForSelection;

public class TextForMittente extends TextForSelection<Indirizzo> {
	
	private static final String TOOLTIP_MITTENTE = "Doppio click per inserire il mittente.";
	
	private Commessa commessa;

	public TextForMittente(Composite parent) {
		super(parent, TOOLTIP_MITTENTE);
	}
	
	public void setCommessa(Commessa commessa) {
		this.commessa = commessa;
	}

	@Override
	protected Indirizzo apriDialogSelezione() {
		DialogSelezioneMittente dialog = new DialogSelezioneMittente(commessa);
		return dialog.apri();
	}

	@Override
	protected void mostraSelezione(Indirizzo selezionato) {
		setText(selezionato.getRagioneSociale());
	}
}
