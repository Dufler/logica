package it.ltc.logica.magazzino.gui.ingressi.elements.carico;

import java.util.List;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;

import it.ltc.logica.common.controller.ingressi.ControllerCarichi;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.ingressi.CaricoTestata;
import it.ltc.logica.database.model.centrale.ingressi.StatiCarico;
import it.ltc.logica.gui.dialog.DialogApribile;
import it.ltc.logica.gui.dialog.DialogMessaggio;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.TabellaCRUDConFiltro;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;
import it.ltc.logica.magazzino.gui.ingressi.elements.riscontro.DialogRiscontroCarico;
import it.ltc.logica.utilities.variabili.Permessi;

public class TabellaCaricoTestata extends TabellaCRUDConFiltro<CaricoTestata, CriteriFiltraggioCaricoTestata> {
	
	protected CriteriFiltraggioCaricoTestata criteri;
	protected Commessa commessa;
	
	public TabellaCaricoTestata(Composite parent) {
		super(parent);		
	}
	
	public void setCommessa(Commessa commessa) {
		this.commessa = commessa;
		annullaFiltro();
	}
	
	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Data", 100, 0);
		aggiungiColonna("Riferimento Cliente", 100, 1);
		aggiungiColonna("Riferimento Interno", 100, 2);
		aggiungiColonna("Pezzi", 100, 3);
		aggiungiColonna("Colli", 100, 4);
		aggiungiColonna("Stato", 100, 5);
		aggiungiColonna("Tipo", 100, 6);
	}
	
	@Override
	protected void aggiungiMenu(Menu menu) {
		//DO NOTHING! (for now...)
	}

	@Override
	protected Ordinatore<CaricoTestata> creaOrdinatore() {
		return new OrdinatoreCaricoTestata();
	}

	@Override
	public void aggiornaContenuto() {
		if (criteri != null && commessa != null) {
			ControllerCarichi controller = new ControllerCarichi(commessa);
			int idFornitore = criteri.getFornitore() != null ? criteri.getFornitore().getId() : 0;
			String tipo = criteri.getTipo() != null ? criteri.getTipo().getCodice() : null;
			setElementi(controller.trovaCarichi(criteri.getRiferimento(), criteri.getStato(), idFornitore, tipo, criteri.getDa(), criteri.getA()));
		} else {
			List<CaricoTestata> listaVuota = null;
			setElementi(listaVuota);
		}
	}

	@Override
	protected boolean eliminaElemento(CaricoTestata elemento) {
		boolean delete = false;
		if (commessa != null) {
			if (elemento.getStato() == StatiCarico.INSERITO) {
				ControllerCarichi controller = new ControllerCarichi(commessa);
				delete = controller.elimina(elemento);
			} else {
				DialogMessaggio.getWarning("Impossibile eliminare", "Non \u00E8 possibile eliminare un carico gi\u00E0 arrivato.").open();
				delete = false;
			}
		}
		return delete;
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.UFFICIO_INGRESSI.getID();
	}
	
	@Override
	protected int getIDPermessoDelete() {
		return Permessi.UFFICIO_INGRESSI_ELIMINA.getID();
	}
	

	@Override
	protected DialogApribile creaDialog(CaricoTestata elemento) {
		DialogApribile dialog;
		if (commessa != null && elemento != null) {
			boolean abilita = elemento.getStato() == StatiCarico.ARRIVATO || elemento.getStato() == StatiCarico.IN_LAVORAZIONE;
			DialogRiscontroCarico dialogCarico = new DialogRiscontroCarico(commessa, elemento, abilita);
			dialog = dialogCarico;
		} else {
			dialog = DialogMessaggio.getWarning("Selezione commessa", "Va selezionata una commessa");
		}
		return dialog;
	}

	@Override
	protected FiltroTabella<CaricoTestata, CriteriFiltraggioCaricoTestata> creaFiltro() {
		//In realtà non serve.
		return null;
	}
	
	@Override
	public void filtra(CriteriFiltraggioCaricoTestata criteri) {
		this.criteri = criteri;
		aggiornaContenuto();
	}
	
	@Override
	public void annullaFiltro() {
		this.criteri = null;
		aggiornaContenuto();
	}

	@Override
	protected Etichettatore<CaricoTestata> creaEtichettatore() {
		return new EtichettatoreCaricoTestata();
	}

	@Override
	protected ModificatoreValoriCelle<CaricoTestata> creaModificatore() {
		return null;
	}

}
