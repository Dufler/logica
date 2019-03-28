package it.ltc.logica.trasporti.gui.elements.cap;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import it.ltc.database.dao.locali.CriteriSelezioneCap;
import it.ltc.logica.common.controller.trasporti.ControllerCap;
import it.ltc.logica.database.model.centrale.trasporti.Cap;
import it.ltc.logica.gui.dialog.DialogApribile;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.TabellaCRUDConFiltro;
import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggioSoloTesto;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;
import it.ltc.logica.utilities.variabili.Permessi;

public class TabellaCap extends TabellaCRUDConFiltro<Cap, CriteriFiltraggioSoloTesto> {
	
	private CriteriSelezioneCap criteriSelezione;

	public TabellaCap(Composite parent) {
		super(parent, SWT.BORDER | SWT.FULL_SELECTION | SWT.VIRTUAL, false, true);
	}
	
	@Override
	protected void aggiungiColonne() {
		aggiungiColonnaVuota();
		aggiungiColonna("CAP", 70, 0, SWT.CENTER);
		aggiungiColonna("Localit\u00E0", 180, 1);
		aggiungiColonna("Ore 10", 50, 2);
		aggiungiColonna("Ore 12", 50, 3);
		aggiungiColonna("Disagiata", 80, SWT.CENTER);
		aggiungiColonna("Isola Minore", 80, 5, SWT.CENTER);
		aggiungiColonna("ZTL", 80, 6, SWT.CENTER);
	}

	@Override
	protected FiltroTabella<Cap, CriteriFiltraggioSoloTesto> creaFiltro() {
		return new FiltroCAP();
	}

	@Override
	protected boolean eliminaElemento(Cap elemento) {
		return false;
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.TRASPORTI_CAP_CUD.getID();
	}
	
	@Override
	protected int getIDPermessoDelete() {
		return Permessi.TRASPORTI_CAP_CUD.getID();
	}

	@Override
	protected DialogApribile creaDialog(Cap elemento) {
		DialogCap dialog = new DialogCap(elemento);
		return dialog;
	}

	@Override
	public void aggiornaContenuto() {
		setElementi(ControllerCap.getInstance().selezionaCap(criteriSelezione));
	}

	@Override
	protected Ordinatore<Cap> creaOrdinatore() {
		return new OrdinatoreCap();
	}
	
	public void filtra(CriteriFiltraggioSoloTesto criteri) {
		criteriSelezione = new CriteriSelezioneCap();
		criteriSelezione.setTesto(criteri.getTesto());
		aggiornaContenuto();
	}
	
	public void annullaFiltro() {
		criteriSelezione = null;
		aggiornaContenuto();
	}

	@Override
	protected Etichettatore<Cap> creaEtichettatore() {
		return new EtichettatoreCap();
	}

	@Override
	protected ModificatoreValoriCelle<Cap> creaModificatore() {
		return null;
	}

}
