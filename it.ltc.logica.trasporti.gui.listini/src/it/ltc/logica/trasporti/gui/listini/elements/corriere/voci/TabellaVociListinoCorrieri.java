package it.ltc.logica.trasporti.gui.listini.elements.corriere.voci;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.calcolo.algoritmi.TipoAlgoritmo;
import it.ltc.logica.common.controller.listini.ControllerListiniCorrieri;
import it.ltc.logica.database.model.centrale.listini.ListinoCorriere;
import it.ltc.logica.database.model.centrale.listini.VoceDiListinoCorriere;
import it.ltc.logica.gui.dialog.DialogApribile;
import it.ltc.logica.gui.dialog.DialogMessaggio;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.TabellaCRUDConFiltro;
import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggioSoloTesto;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;
import it.ltc.logica.gui.wizard.DialogWizard;
import it.ltc.logica.trasporti.gui.listini.dialogs.corriere.ProprietaVoceListinoCorriereFissa;
import it.ltc.logica.trasporti.gui.listini.dialogs.corriere.ProprietaVoceListinoCorrierePercentuale;
import it.ltc.logica.trasporti.gui.listini.dialogs.corriere.ProprietaVoceListinoCorriereProporzionale;
import it.ltc.logica.trasporti.gui.listini.dialogs.corriere.ProprietaVoceListinoCorriereScaglioni;
import it.ltc.logica.trasporti.gui.listini.dialogs.corriere.ProprietaVoceListinoCorriereScaglioniRipetuti;
import it.ltc.logica.trasporti.gui.listini.wizards.corriere.NuovaVoceListinoCorriereWizard;
import it.ltc.logica.utilities.variabili.Permessi;

public class TabellaVociListinoCorrieri extends TabellaCRUDConFiltro<VoceDiListinoCorriere, CriteriFiltraggioSoloTesto> {

	private FiltroVociListinoCorrieri filtro;
	private ListinoCorriere listinoSelezionato;
	
	public TabellaVociListinoCorrieri(Composite parent) {
		super(parent);
	}
	
	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Voce", 100, 0);
		aggiungiColonna("Ambito", 100, 1);
		aggiungiColonna("Descrizione", 100, 2);
	}
	
	private DialogApribile apriModificaVoce(VoceDiListinoCorriere voce) {
		DialogApribile dialog;
		TipoAlgoritmo tipoAlgoritmo = TipoAlgoritmo.getTipo(voce.getTipoCalcolo());
		boolean permessoModifica = isPermesso();
		switch (tipoAlgoritmo) {
			case FISSO : dialog = new ProprietaVoceListinoCorriereFissa(voce, permessoModifica); break;
			case PERCENTUALE : dialog = new ProprietaVoceListinoCorrierePercentuale(voce, permessoModifica); break;
			case PROPORZIONALE : dialog = new ProprietaVoceListinoCorriereProporzionale(voce, permessoModifica); break;
			case SCAGLIONI : dialog = new ProprietaVoceListinoCorriereScaglioni(voce, permessoModifica); break;
			case SCAGLIONI_RIPETUTI : dialog = new ProprietaVoceListinoCorriereScaglioniRipetuti(voce, permessoModifica); break;
			default : dialog = null;
		}
		return dialog;
	}

	@Override
	protected Ordinatore<VoceDiListinoCorriere> creaOrdinatore() {
		return new OrdinatoreVociListinoCorrieri();
	}

	@Override
	protected FiltroTabella<VoceDiListinoCorriere, CriteriFiltraggioSoloTesto> creaFiltro() {
		filtro = new FiltroVociListinoCorrieri();
		return filtro;
	}

	@Override
	protected boolean eliminaElemento(VoceDiListinoCorriere elemento) {
		boolean eliminazione = ControllerListiniCorrieri.getInstance().eliminaVoce(elemento);
		if (eliminazione)
			aggiornaContenuto();
		return eliminazione;
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.TRASPORTI_LISTINI_CORRIERI_CUD.getID();
	}

	@Override
	protected DialogApribile creaDialog(VoceDiListinoCorriere elemento) {
		DialogApribile dialog;
		if (elemento == null) {
			if (listinoSelezionato != null)
				dialog = new DialogWizard(new NuovaVoceListinoCorriereWizard(listinoSelezionato), DialogWizard.WIZARD_LISTINO);
			else
				dialog = DialogMessaggio.getWarning("Seleziona un listino", "Seleziona un listino per poter inserire una voce.");
		} else {
			dialog = apriModificaVoce(elemento);
		}
		return dialog;
	}

	@Override
	public void aggiornaContenuto() {
		setElementi(ControllerListiniCorrieri.getInstance().getVociDiListino(listinoSelezionato != null ? listinoSelezionato.getId() : -1));		
	}

	public void setListino(ListinoCorriere listino) {
		listinoSelezionato = listino;
		aggiornaContenuto();
	}

	@Override
	protected Etichettatore<VoceDiListinoCorriere> creaEtichettatore() {
		return new EtichettatoreVociListiniCorriere();
	}

	@Override
	protected ModificatoreValoriCelle<VoceDiListinoCorriere> creaModificatore() {
		return null;
	}

}
