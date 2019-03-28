package it.ltc.logica.ufficio.gui.elements.destinatario;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.gui.input.TextForSelection;

public class TextForDestinatario extends TextForSelection<Indirizzo> {
	
	private static final String TOOLTIP_DESTINATARIO = "Doppio click per inserire il destinatario.";
	
	private Commessa commessa;

	public TextForDestinatario(Composite parent) {
		super(parent, TOOLTIP_DESTINATARIO);
	}
	
	public void setCommessa(Commessa commessa) {
		this.commessa = commessa;
	}

	@Override
	protected Indirizzo apriDialogSelezione() {
		DialogSelezioneDestinatario dialog = new DialogSelezioneDestinatario(commessa);
		return dialog.apri();
	}

	@Override
	protected void mostraSelezione(Indirizzo selezionato) {
		setText(selezionato.getRagioneSociale());
	}

}
