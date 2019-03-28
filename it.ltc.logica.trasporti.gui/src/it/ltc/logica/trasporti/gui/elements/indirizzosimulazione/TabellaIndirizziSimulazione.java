package it.ltc.logica.trasporti.gui.elements.indirizzosimulazione;

import org.eclipse.swt.widgets.Composite;

import it.ltc.database.dao.locali.CriteriSelezioneIndirizzi;
import it.ltc.logica.common.controller.trasporti.ControllerIndirizziSimulazione;
import it.ltc.logica.database.model.centrale.indirizzi.IndirizzoSimulazione;
import it.ltc.logica.gui.dialog.DialogApribile;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.TabellaCRUDConFiltro;
import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggioSoloTesto;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;
import it.ltc.logica.utilities.variabili.Permessi;

public class TabellaIndirizziSimulazione extends TabellaCRUDConFiltro<IndirizzoSimulazione, CriteriFiltraggioSoloTesto> {
	
	protected CriteriSelezioneIndirizzi criteriSelezione;

	public TabellaIndirizziSimulazione(Composite parent) {
		super(parent);
	}
	
	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Ragione Sociale", 200, 0);
		aggiungiColonna("Indirizzo", 300, 1);
	}
	
	@Override
	public void aggiornaContenuto() {
		System.out.println();
		setElementi(ControllerIndirizziSimulazione.getInstance().selezionaIndirizzi(criteriSelezione));
	}
	
	@Override
	protected int getIDPermesso() {
		return Permessi.TRASPORTI_PREVENTIVI.getID();
	}
	
	@Override
	protected DialogApribile creaDialog(IndirizzoSimulazione elemento) {
		DialogIndirizzoSimulazione dialog = new DialogIndirizzoSimulazione(elemento);
		return dialog;
	}
	
	@Override
	protected Ordinatore<IndirizzoSimulazione> creaOrdinatore() {
		return new OrdinatoreIndirizziSimulazione();
	}

	@Override
	protected boolean eliminaElemento(IndirizzoSimulazione elemento) {
		boolean delete = ControllerIndirizziSimulazione.getInstance().elimina(elemento);
		return delete;
	}
	
	public void filtra(CriteriFiltraggioSoloTesto criteri) {
		criteriSelezione = new CriteriSelezioneIndirizzi();
		criteriSelezione.setTesto(criteri.getTesto());
		aggiornaContenuto();
	}
	
	public void annullaFiltro() {
		criteriSelezione = null;
		aggiornaContenuto();
	}
	
	@Override
	protected FiltroTabella<IndirizzoSimulazione, CriteriFiltraggioSoloTesto> creaFiltro() {
		return new FiltroIndirizziSimulazione();
	}

	@Override
	protected Etichettatore<IndirizzoSimulazione> creaEtichettatore() {
		return new EtichettatoreIndirizziSimulazione();
	}

	@Override
	protected ModificatoreValoriCelle<IndirizzoSimulazione> creaModificatore() {
		return null;
	}

}
