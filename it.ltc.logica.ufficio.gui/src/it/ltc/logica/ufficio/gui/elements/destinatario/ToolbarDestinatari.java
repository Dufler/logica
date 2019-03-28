package it.ltc.logica.ufficio.gui.elements.destinatario;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.ToolItem;

import it.ltc.logica.gui.common.tables.indirizzo.ToolbarIndirizziBase;
import it.ltc.logica.gui.decoration.Immagine;
import it.ltc.logica.utilities.variabili.Permessi;

public class ToolbarDestinatari extends ToolbarIndirizziBase {

	public ToolbarDestinatari(Composite parent) {
		super(parent);
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.UFFICIO_USCITE.getID();
	}
	
	public ToolItem getToolitemNuovo() {
		return nuovo;
	}
	
	protected void aggiungiTastoNuovo(boolean testo) {
		nuovo = new ToolItem(toolbar, SWT.NONE);
		nuovo.setImage(Immagine.CROCEVERDE_16X16.getImage());
		nuovo.setDisabledImage(Immagine.CROCEVERDE_ANNULLATA_16X16.getImage());
		nuovo.setText(testo ? "Nuovo" : "");
		nuovo.setToolTipText("Nuovo");
	}

}
