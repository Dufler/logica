package it.ltc.logica.cdg.gui.costiricavi.elements.generici.fasi;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.cdg.gui.costiricavi.dialogs.DialogCostiRicaviGenericiFase;
import it.ltc.logica.common.controller.cdg.ControllerCdgCostiRicaviGenericiFasi;
import it.ltc.logica.database.model.centrale.cdg.CdgCostiRicaviGenerici;
import it.ltc.logica.database.model.centrale.cdg.CdgCostiRicaviGenericiFase;
import it.ltc.logica.gui.dialog.DialogApribile;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.TabellaCRUD;
import it.ltc.logica.utilities.variabili.Permessi;

public class TabellaCostiRicaviGenericiPerFase extends TabellaCRUD<CdgCostiRicaviGenericiFase> {

	private final CdgCostiRicaviGenerici generico;
	
	public TabellaCostiRicaviGenericiPerFase(Composite parent, CdgCostiRicaviGenerici generico) {
		super(parent);
		this.generico = generico;
		
		aggiornaContenuto();
	}

	@Override
	protected boolean eliminaElemento(CdgCostiRicaviGenericiFase elemento) {
		boolean delete = ControllerCdgCostiRicaviGenericiFasi.getInstance().elimina(elemento);
		return delete;
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.CDG_COSTI_RICAVI_CUD.getID();
	}

	@Override
	protected DialogApribile creaDialog(CdgCostiRicaviGenericiFase elemento) {
		DialogCostiRicaviGenericiFase dialog = new DialogCostiRicaviGenericiFase(elemento, generico);
		return dialog;
	}

	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Fase", 100, 0);
		aggiungiColonna("Percentuale", 100, 1);
	}

	@Override
	public void aggiornaContenuto() {
		if (generico != null)
			setElementi(ControllerCdgCostiRicaviGenericiFasi.getInstance().getPercentualiPerGenerico(generico.getId()));
	}

	@Override
	protected Ordinatore<CdgCostiRicaviGenericiFase> creaOrdinatore() {
		return new OrdinatoreCostiRicaviGenericiPerFase();
	}

	@Override
	protected Etichettatore<CdgCostiRicaviGenericiFase> creaEtichettatore() {
		return new EtichettatoreCostiRicaviGenericiPerFase();
	}

	@Override
	protected ModificatoreValoriCelle<CdgCostiRicaviGenericiFase> creaModificatore() {
		return null;
	}

}
