package it.ltc.logica.magazzino.gui.ingressi.elements.riscontro;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.ingressi.CaricoTestata;
import it.ltc.logica.gui.dialog.DialogSemplice;

public class DialogRiscontroCarico extends DialogSemplice {
	
	public static final String title = "Riscontro carico";

	public DialogRiscontroCarico(Commessa commessa, CaricoTestata carico, boolean abilita) {
		super(title, null, abilita);
	}

	@Override
	protected void aggiungiElementiGrafici(Composite container) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void checkElementiGrafici() {
		// TODO Auto-generated method stub
		
	}

}
