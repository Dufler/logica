package it.ltc.logica.ufficio.gui.elements.elementocassa;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.prodotto.Cassa;
import it.ltc.logica.database.model.prodotto.ElementoCassa;
import it.ltc.logica.gui.dialog.DialogApribile;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.TabellaCRUD;
import it.ltc.logica.utilities.variabili.Permessi;

public class TabellaElementiCassa extends TabellaCRUD<ElementoCassa> {
	
	private final Cassa cassa;
	private final Commessa commessa;

	public TabellaElementiCassa(Composite parent, Cassa cassa, Commessa commessa) {
		super(parent);
		this.cassa = cassa;
		this.commessa = commessa;
	}

	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("SKU", 100, 0);
		aggiungiColonna("Modello", 100, 1);
		aggiungiColonna("Taglia", 100, 2);
		aggiungiColonna("Quantit\u00E0", 100, 3);
	}

	@Override
	public void aggiornaContenuto() {
		if (cassa != null)
			setElementi(cassa.getProdotti());
	}

	@Override
	protected Ordinatore<ElementoCassa> creaOrdinatore() {
		return new OrdinatoreElementiCassa();
	}

	@Override
	protected Etichettatore<ElementoCassa> creaEtichettatore() {
		return new EtichettatoreElementiCassa();
	}

	@Override
	protected ModificatoreValoriCelle<ElementoCassa> creaModificatore() {
		return null;
	}

	@Override
	protected boolean eliminaElemento(ElementoCassa elemento) {
		boolean remove = cassa.getProdotti().remove(elemento);
		return remove;
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.UFFICIO_PRODOTTI.getID();
	}

	@Override
	protected DialogApribile creaDialog(ElementoCassa elemento) {
		DialogElementoCassa dialog = new DialogElementoCassa(elemento, cassa, commessa);
		return dialog;
	}

}
