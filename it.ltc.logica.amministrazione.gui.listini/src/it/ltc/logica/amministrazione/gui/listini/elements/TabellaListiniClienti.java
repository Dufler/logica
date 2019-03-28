package it.ltc.logica.amministrazione.gui.listini.elements;

import java.util.List;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;

import it.ltc.logica.amministrazione.gui.listini.dialogs.ProprietaListinoCommessa;
import it.ltc.logica.amministrazione.gui.listini.wizards.NuovoListinoCommessaWizard;
import it.ltc.logica.common.controller.listini.ControllerListiniClienti;
import it.ltc.logica.database.model.centrale.listini.ListinoCommessa;
import it.ltc.logica.gui.dialog.DialogApribile;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.Tabella;
import it.ltc.logica.gui.elements.TabellaCRUDConFiltro;
import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggioSoloTesto;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;
import it.ltc.logica.gui.wizard.DialogWizard;
import it.ltc.logica.utilities.variabili.ControllerVariabiliGlobali;
import it.ltc.logica.utilities.variabili.Permessi;


public class TabellaListiniClienti extends TabellaCRUDConFiltro<ListinoCommessa, CriteriFiltraggioSoloTesto> {

	private final static int idPermesso = Permessi.AMMINISTRAZIONE_LISTINI_CUD.getID();
	
	private TabellaVociListinoClienti tabellaVoci;
	
	private FiltroListiniClienti filtro;
	
	public TabellaListiniClienti(Composite parent) {
		super(parent, Tabella.STILE_SELEZIONE_SINGOLA);
		copy.dispose();
	}
	
	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Listino", 100, 0);
		aggiungiColonna("Tipo", 100, 1);
		aggiungiColonna("Descrizione", 100, 2);
	}
	
	public void setTabellaVoci(TabellaVociListinoClienti tabella) {
		tabellaVoci = tabella;
	}

	@Override
	protected void aggiungiMenu(Menu menu) {
		if (modify != null)
			modify.setText("Propriet\u00E0");
	}

	@Override
	protected Ordinatore<ListinoCommessa> creaOrdinatore() {
		return new OrdinatoreListiniClienti();
	}

	@Override
	protected FiltroTabella<ListinoCommessa, CriteriFiltraggioSoloTesto> creaFiltro() {
		filtro = new FiltroListiniClienti();
		return filtro;
	}

	@Override
	protected boolean eliminaElemento(ListinoCommessa listino) {
		boolean eliminazione = ControllerListiniClienti.getInstance().eliminaListino(listino);
		if (eliminazione && tabellaVoci != null) {
			tabellaVoci.setListino(null);
		}
		return eliminazione;
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.AMMINISTRAZIONE_LISTINI_CUD.getID();
	}
	
	@Override
	public void aggiornaContenuto() {
		List<ListinoCommessa> listini = ControllerListiniClienti.getInstance().getListiniClientiPerLogisitica();
		setElementi(listini);
	}

	@Override
	protected DialogApribile creaDialog(ListinoCommessa elemento) {
		DialogApribile dialog;
		if (elemento == null) {
			dialog = new DialogWizard(new NuovoListinoCommessaWizard(), DialogWizard.WIZARD_LISTINO);
		} else {
			boolean permessoGestione = ControllerVariabiliGlobali.getInstance().haPermesso(idPermesso);
			dialog = new ProprietaListinoCommessa(elemento, permessoGestione);
		}
		return dialog;
	}

	@Override
	protected Etichettatore<ListinoCommessa> creaEtichettatore() {
		return new EtichettatoreListiniClienti();
	}

	@Override
	protected ModificatoreValoriCelle<ListinoCommessa> creaModificatore() {
		return null;
	}

}
