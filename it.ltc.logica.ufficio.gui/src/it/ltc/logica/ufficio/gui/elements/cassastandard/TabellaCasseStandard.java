package it.ltc.logica.ufficio.gui.elements.cassastandard;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.controller.prodotti.ControllerCasseStandard;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.prodotto.CassaStandard;
import it.ltc.logica.gui.dialog.DialogApribile;
import it.ltc.logica.gui.dialog.DialogMessaggio;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.TabellaCRUDConFiltro;
import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggioSoloTesto;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;
import it.ltc.logica.utilities.variabili.Permessi;

public class TabellaCasseStandard extends TabellaCRUDConFiltro<CassaStandard, CriteriFiltraggioSoloTesto> {
	
	private Commessa commessa;
	private ControllerCasseStandard controller;

	public TabellaCasseStandard(Composite parent) {
		super(parent);
	}
	
	public void setCommessa(Commessa commessa) {
		this.commessa = commessa;
		this.controller = new ControllerCasseStandard(commessa);
		annullaFiltro();
	}

	@Override
	protected FiltroTabella<CassaStandard, CriteriFiltraggioSoloTesto> creaFiltro() {
		return new FiltroCasseStandard();
	}

	@Override
	protected boolean eliminaElemento(CassaStandard elemento) {
		boolean delete = controller.elimina(elemento);
		return delete;
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.UFFICIO_PRODOTTI.getID();
	}
	
	@Override
	protected int getIDPermessoDelete() {
		return Permessi.UFFICIO_PRODOTTI_ELIMINA.getID();
	}

	@Override
	protected DialogApribile creaDialog(CassaStandard elemento) {
		DialogApribile dialog;
		if (commessa != null) {
			dialog = new DialogCassaStandard(elemento, commessa);
		} else {
			dialog = DialogMessaggio.getWarning("Selezione commessa", "Va selezionata una commessa");
		}
		return dialog;
	}

	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Cassa", 100, 0);
		aggiungiColonna("Pezzi", 100, 1);
		aggiungiColonna("Composizione", 100, 2);
	}

	@Override
	public void aggiornaContenuto() {
		if (commessa != null && controller != null) {
			setElementi(controller.getCasse());
		}		
	}

	@Override
	protected Ordinatore<CassaStandard> creaOrdinatore() {
		return new OrdinatoreCasseStandard();
	}

	@Override
	protected Etichettatore<CassaStandard> creaEtichettatore() {
		return new EtichettatoreCasseStandard();
	}

	@Override
	protected ModificatoreValoriCelle<CassaStandard> creaModificatore() {
		return null;
	}

}
