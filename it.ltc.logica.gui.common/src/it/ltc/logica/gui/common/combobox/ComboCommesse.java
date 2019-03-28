package it.ltc.logica.gui.common.combobox;

import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.controller.sistema.ControllerCommesse;
import it.ltc.logica.container.controller.ControllerUtente;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.gui.input.ComboBox;

public class ComboCommesse extends ComboBox<Commessa> {

	public ComboCommesse(Composite parent, boolean tutte) {
		super(parent);
		
		setItems(tutte ? ControllerCommesse.getInstance().getCommesse() : ControllerCommesse.getInstance().getTutteCommesse());
		
		//Listener per andare a cambiare la commessa selezionata, reminder per gli utenti distratti.
		addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				aggiornaTestoCommessa();
			}
		});
	}
	
	public void aggiornaTestoCommessa() {
		Commessa commessaSelezionata = getSelectedValue();
		if (commessaSelezionata != null) {
			ControllerUtente.getInstance().setCommessaSelezionata(commessaSelezionata);
		}
	}

}
