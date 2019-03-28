package it.ltc.logica.gui.common.tables.indirizzo;

import org.eclipse.swt.widgets.Composite;

import it.ltc.database.dao.locali.CriteriSelezioneIndirizzi;
import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.TabellaCRUDConFiltro;
import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggioSoloTesto;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;

public abstract class TabellaIndirizziBase extends TabellaCRUDConFiltro<Indirizzo, CriteriFiltraggioSoloTesto> {
	
	protected CriteriSelezioneIndirizzi criteriSelezione;

	public TabellaIndirizziBase(Composite parent, boolean aperturaDoppioClick) {
		super(parent, STILE_TABELLE_GRANDI_SELEZIONE_SINGOLA, false, aperturaDoppioClick);
	}
	
	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Ragione Sociale", 200,  0);
		aggiungiColonna("Indirizzo", 300, 1);
	}

	@Override
	protected Ordinatore<Indirizzo> creaOrdinatore() {
		return new OrdinatoreIndirizzi();
	}

	@Override
	protected FiltroTabella<Indirizzo, CriteriFiltraggioSoloTesto> creaFiltro() {
		return new FiltroIndirizzi();
	}

	@Override
	protected boolean eliminaElemento(Indirizzo elemento) {
		return false;
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
	protected Etichettatore<Indirizzo> creaEtichettatore() {
		return new EtichettatoreIndirizzi();
	}

	@Override
	protected ModificatoreValoriCelle<Indirizzo> creaModificatore() {
		return null;
	}
}
