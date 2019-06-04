package it.ltc.logica.ufficio.gui.elements.elementocassastandard;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.database.model.prodotto.CassaStandard;
import it.ltc.logica.database.model.prodotto.ElementoCassaStandard;
import it.ltc.logica.gui.dialog.DialogApribile;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.TabellaCRUD;
import it.ltc.logica.utilities.variabili.Permessi;

public class TabellaElementiCassaStandard extends TabellaCRUD<ElementoCassaStandard> {
	
	private final CassaStandard cassa;

	public TabellaElementiCassaStandard(Composite parent, CassaStandard cassa) {
		super(parent);
		this.cassa = cassa;
	}

	@Override
	protected boolean eliminaElemento(ElementoCassaStandard elemento) {
		boolean remove = cassa.getElementi().remove(elemento);
		return remove;
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.UFFICIO_PRODOTTI.getID();
	}

	@Override
	protected DialogApribile creaDialog(ElementoCassaStandard elemento) {
		DialogElementoCassaStandard dialog = new DialogElementoCassaStandard(elemento, cassa);
		return dialog;
	}

	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Taglia", 100, 0);
		aggiungiColonna("Quantit\u00E0", 100, 1);		
	}

	@Override
	public void aggiornaContenuto() {
		if (cassa != null)
			setElementi(cassa.getElementi());
	}

	@Override
	protected Ordinatore<ElementoCassaStandard> creaOrdinatore() {
		return new OrdinatoreElementiCassaStandard();
	}

	@Override
	protected Etichettatore<ElementoCassaStandard> creaEtichettatore() {
		return new EtichettatoreElementiCassaStandard();
	}

	@Override
	protected ModificatoreValoriCelle<ElementoCassaStandard> creaModificatore() {
		return null;
	}

}
