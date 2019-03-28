package it.ltc.logica.trasporti.gui.listini.elements.simulazione.voci;

import java.util.List;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.calcolo.algoritmi.TipoAlgoritmo;
import it.ltc.logica.common.controller.listini.ListiniSimulazioneController;
import it.ltc.logica.database.model.locale.ListinoSimulazione;
import it.ltc.logica.database.model.locale.ListinoSimulazioneVoce;
import it.ltc.logica.gui.dialog.DialogApribile;
import it.ltc.logica.gui.dialog.DialogMessaggio;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.TabellaCRUDConFiltro;
import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggioSoloTesto;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;
import it.ltc.logica.gui.wizard.DialogWizard;
import it.ltc.logica.trasporti.gui.listini.dialogs.simulazione.ProprietaVoceListinoSimulazioneFissa;
import it.ltc.logica.trasporti.gui.listini.dialogs.simulazione.ProprietaVoceListinoSimulazionePercentuale;
import it.ltc.logica.trasporti.gui.listini.dialogs.simulazione.ProprietaVoceListinoSimulazioneProporzionale;
import it.ltc.logica.trasporti.gui.listini.dialogs.simulazione.ProprietaVoceListinoSimulazioneScaglioni;
import it.ltc.logica.trasporti.gui.listini.dialogs.simulazione.ProprietaVoceListinoSimulazioneScaglioniRipetuti;
import it.ltc.logica.trasporti.gui.listini.wizards.simulazione.voce.NuovaVoceListinoSimulazioneWizard;
import it.ltc.logica.utilities.variabili.Permessi;

public class TabellaVociListinoSimulazione extends TabellaCRUDConFiltro<ListinoSimulazioneVoce, CriteriFiltraggioSoloTesto> {
	
	private FiltroVociListinoSimulazione filtro;
	private ListinoSimulazione listinoSelezionato;

	public TabellaVociListinoSimulazione(Composite parent) {
		super(parent);
	}
	
	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Voce", 100, 0);
		aggiungiColonna("Ambito", 100, 1);
		aggiungiColonna("Descrizione", 100, 2);
	}
	
	private DialogApribile apriModificaVoce(ListinoSimulazioneVoce voce) {
		DialogApribile dialog;
		TipoAlgoritmo tipoAlgoritmo = TipoAlgoritmo.getTipo(voce.getTipo());
		boolean permessoModifica = isPermesso();
		switch (tipoAlgoritmo) {
			case FISSO : dialog = new ProprietaVoceListinoSimulazioneFissa(voce, permessoModifica); break;
			case PERCENTUALE : dialog = new ProprietaVoceListinoSimulazionePercentuale(voce, permessoModifica); break;
			case PROPORZIONALE : dialog = new ProprietaVoceListinoSimulazioneProporzionale(voce, permessoModifica); break;
			case SCAGLIONI : dialog = new ProprietaVoceListinoSimulazioneScaglioni(voce, permessoModifica); break;
			case SCAGLIONI_RIPETUTI : dialog = new ProprietaVoceListinoSimulazioneScaglioniRipetuti(voce, permessoModifica); break;
			default : dialog = null;
		}
		return dialog;
	}

	@Override
	protected Ordinatore<ListinoSimulazioneVoce> creaOrdinatore() {
		return new OrdinatoreVociListiniSimulazione();
	}

	@Override
	protected FiltroTabella<ListinoSimulazioneVoce, CriteriFiltraggioSoloTesto> creaFiltro() {
		filtro = new FiltroVociListinoSimulazione();
		return filtro;
	}

	@Override
	protected boolean eliminaElemento(ListinoSimulazioneVoce elemento) {
		boolean eliminazione = ListiniSimulazioneController.getInstance().eliminaVoce(elemento);
		if (eliminazione)
			aggiornaContenuto();
		return eliminazione;
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.TRASPORTI_LISTINI_SIMULAZIONE.getID();
	}
	
	@Override
	protected DialogApribile creaDialog(ListinoSimulazioneVoce elemento) {
		DialogApribile dialog;
		if (elemento == null) {
			if (listinoSelezionato != null)
				dialog = new DialogWizard(new NuovaVoceListinoSimulazioneWizard(listinoSelezionato), DialogWizard.WIZARD_LISTINO);
			else
				dialog = DialogMessaggio.getWarning("Seleziona un listino", "Seleziona un listino per poter inserire una voce.");
		} else {
			dialog = apriModificaVoce(elemento);
		}
		return dialog;
	}

	@Override
	public void aggiornaContenuto() {
		List<ListinoSimulazioneVoce> vociDiListino = ListiniSimulazioneController.getInstance().getVociDiListino(listinoSelezionato != null ? listinoSelezionato.getId() : -1);
		setElementi(vociDiListino);
	}

	public void setListino(ListinoSimulazione listino) {
		listinoSelezionato = listino;
		aggiornaContenuto();
	}

	@Override
	protected Etichettatore<ListinoSimulazioneVoce> creaEtichettatore() {
		return new EtichettatoreVociListiniSimulazione();
	}

	@Override
	protected ModificatoreValoriCelle<ListinoSimulazioneVoce> creaModificatore() {
		return null;
	}

}
