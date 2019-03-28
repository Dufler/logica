package it.ltc.logica.amministrazione.gui.listini.elements;

import java.util.List;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.amministrazione.gui.listini.dialogs.ProprietaVoceListinoCommessaFissa;
import it.ltc.logica.amministrazione.gui.listini.dialogs.ProprietaVoceListinoCommessaPercentuale;
import it.ltc.logica.amministrazione.gui.listini.dialogs.ProprietaVoceListinoCommessaProporzionale;
import it.ltc.logica.amministrazione.gui.listini.dialogs.ProprietaVoceListinoCommessaScaglioni;
import it.ltc.logica.amministrazione.gui.listini.dialogs.ProprietaVoceListinoCommessaScaglioniRipetuti;
import it.ltc.logica.amministrazione.gui.listini.wizards.NuovaVoceListinoCommessaWizard;
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
import it.ltc.logica.utilities.variabili.Permessi;


public class TabellaVociListinoClienti extends TabellaCRUDConFiltro<VoceDiListino, CriteriFiltraggioSoloTesto> {
	
	private FiltroVociListinoClienti filtro;
	
	private ListinoCommessa listinoSelezionato;
	
	private final ControllerListiniClienti controller;

	public TabellaVociListinoClienti(Composite parent) {
		super(parent);
		
		controller = ControllerListiniClienti.getInstance();
	}
	
	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Voce", 100, 0);
		aggiungiColonna("Ambito", 100, 1);
		aggiungiColonna("Descrizione", 100, 2);
	}
	
	public void setListino(ListinoCommessa listino) {
		listinoSelezionato = listino;
		aggiornaContenuto();
	}
	
	private DialogApribile apriModificaVoce(VoceDiListino voce) {
		DialogApribile dialog;
		TipoAlgoritmo tipoAlgoritmo = TipoAlgoritmo.getTipo(voce.getTipoCalcolo());
		boolean permessoModifica = isPermesso();
		switch (tipoAlgoritmo) {
			case FISSO : dialog = new ProprietaVoceListinoCommessaFissa(voce, permessoModifica); break;
			case PERCENTUALE : dialog = new ProprietaVoceListinoCommessaPercentuale(voce, permessoModifica); break;
			case PROPORZIONALE : dialog = new ProprietaVoceListinoCommessaProporzionale(voce, permessoModifica); break;
			case SCAGLIONI : dialog = new ProprietaVoceListinoCommessaScaglioni(voce, permessoModifica); break;
			case SCAGLIONI_RIPETUTI : dialog = new ProprietaVoceListinoCommessaScaglioniRipetuti(voce, permessoModifica); break;
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
		boolean eliminazione = controller.eliminaVoce(elemento);
		if (eliminazione)
			aggiornaContenuto();
		return eliminazione;
	}
	
	@Override
	public void aggiornaContenuto() {
		List<VoceDiListino> vociDiListino = ControllerListiniClienti.getInstance().getVociDiListino(listinoSelezionato != null ? listinoSelezionato.getId() : -1);
		setElementi(vociDiListino);
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.AMMINISTRAZIONE_LISTINI_CUD.getID();
	}

	@Override
	protected DialogApribile creaDialog(VoceDiListino elemento) {
		DialogApribile dialog;
		if (elemento == null) {
			if (listinoSelezionato != null)
				dialog = new DialogWizard(new NuovaVoceListinoCommessaWizard(listinoSelezionato), DialogWizard.WIZARD_LISTINO);
			else
				dialog = DialogMessaggio.getWarning("Seleziona un listino", "Seleziona un listino per poter inserire una voce.");
		} else {
			dialog = apriModificaVoce(elemento);
		}
		return dialog;
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
