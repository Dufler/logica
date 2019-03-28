package it.ltc.logica.trasporti.gui.listini.elements.cliente.voci;

import java.util.List;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.calcolo.algoritmi.TipoAlgoritmo;
import it.ltc.logica.common.controller.listini.ControllerListiniClienti;
import it.ltc.logica.database.model.centrale.listini.ListinoCommessa;
import it.ltc.logica.database.model.centrale.listini.VoceDiListino;
import it.ltc.logica.gui.dialog.DialogApribile;
import it.ltc.logica.gui.dialog.DialogMessaggio;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.TabellaCRUDConFiltro;
import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggioSoloTesto;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;
import it.ltc.logica.gui.wizard.DialogWizard;
import it.ltc.logica.trasporti.gui.elements.ETipoListino;
import it.ltc.logica.trasporti.gui.listini.dialogs.cliente.ProprietaVoceListinoCommessaFissa;
import it.ltc.logica.trasporti.gui.listini.dialogs.cliente.ProprietaVoceListinoCommessaPercentuale;
import it.ltc.logica.trasporti.gui.listini.dialogs.cliente.ProprietaVoceListinoCommessaProporzionale;
import it.ltc.logica.trasporti.gui.listini.dialogs.cliente.ProprietaVoceListinoCommessaScaglioni;
import it.ltc.logica.trasporti.gui.listini.dialogs.cliente.ProprietaVoceListinoCommessaScaglioniRipetuti;
import it.ltc.logica.trasporti.gui.listini.wizards.cliente.NuovaVoceListinoCommessaWizard;
import it.ltc.logica.utilities.variabili.Permessi;

public class TabellaVociListinoClienti extends TabellaCRUDConFiltro<VoceDiListino, CriteriFiltraggioSoloTesto> {
	
	private FiltroVociListinoClienti filtro;
	
	private ETipoListino tipo;
	private ListinoCommessa listinoSelezionato;

	public TabellaVociListinoClienti(Composite parent) {
		super(parent);
	}
	
	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Voce", 100, 0);
		aggiungiColonna("Ambito", 100, 1);
		aggiungiColonna("Descrizione", 100, 2);
	}
	
	private DialogApribile apriModificaVoce(VoceDiListino voce) {
		DialogApribile dialog;
		TipoAlgoritmo tipoAlgoritmo = TipoAlgoritmo.getTipo(voce.getTipoCalcolo());
		boolean permessoModifica = isPermesso();
		switch (tipoAlgoritmo) {
			case FISSO : dialog = new ProprietaVoceListinoCommessaFissa(voce, tipo, permessoModifica); break;
			case PERCENTUALE : dialog = new ProprietaVoceListinoCommessaPercentuale(voce, tipo, permessoModifica); break;
			case PROPORZIONALE : dialog = new ProprietaVoceListinoCommessaProporzionale(voce, tipo, permessoModifica); break;
			case SCAGLIONI : dialog = new ProprietaVoceListinoCommessaScaglioni(voce, tipo, permessoModifica); break;
			case SCAGLIONI_RIPETUTI : dialog = new ProprietaVoceListinoCommessaScaglioniRipetuti(voce, tipo, permessoModifica); break;
			default : dialog = null;
		}
		return dialog;
	}

	@Override
	protected Ordinatore<VoceDiListino> creaOrdinatore() {
		return new OrdinatoreVociListiniClienti();
	}

	@Override
	protected FiltroTabella<VoceDiListino, CriteriFiltraggioSoloTesto> creaFiltro() {
		filtro = new FiltroVociListinoClienti();
		return filtro;
	}

	@Override
	protected boolean eliminaElemento(VoceDiListino elemento) {
		boolean eliminazione = ControllerListiniClienti.getInstance().eliminaVoce(elemento);
		if (eliminazione)
			aggiornaContenuto();
		return eliminazione;
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.TRASPORTI_LISTINI_CLIENTI_CUD.getID();
	}

	@Override
	protected DialogApribile creaDialog(VoceDiListino elemento) {
		DialogApribile dialog;
		if (elemento == null) {
			if (listinoSelezionato != null)
				dialog = new DialogWizard(new NuovaVoceListinoCommessaWizard(listinoSelezionato, tipo), DialogWizard.WIZARD_LISTINO);
			else
				dialog = DialogMessaggio.getWarning("Seleziona un listino", "Seleziona un listino per poter inserire una voce.");
		} else {
			dialog = apriModificaVoce(elemento);
		}
		return dialog;
	}

	@Override
	public void aggiornaContenuto() {
		List<VoceDiListino> vociDiListino = ControllerListiniClienti.getInstance().getVociDiListino(listinoSelezionato != null ? listinoSelezionato.getId() : -1);
		setElementi(vociDiListino);
	}

	public void setListino(ListinoCommessa listino) {
		listinoSelezionato = listino;
		tipo = listinoSelezionato != null ? ETipoListino.getByID(listinoSelezionato.getTipo()) : null;
		aggiornaContenuto();
	}

	@Override
	protected Etichettatore<VoceDiListino> creaEtichettatore() {
		return new EtichettatoreVociListiniClienti();
	}

	@Override
	protected ModificatoreValoriCelle<VoceDiListino> creaModificatore() {
		return null;
	}

}
