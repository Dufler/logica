package it.ltc.logica.trasporti.gui.elements.documentisimulazionespedizioni;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.controller.trasporti.ControllerDocumentiSpedizioniSimulazione;
import it.ltc.logica.database.model.centrale.trasporti.DocumentoSpedizioniSimulazione;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.TabellaCheckBox;

public class TabellaDocumentiSimulazioneSpedizioni extends TabellaCheckBox<DocumentoSpedizioniSimulazione> {

	public TabellaDocumentiSimulazioneSpedizioni(Composite parent) {
		super(parent, STILE_SELEZIONE_SINGOLA);
	}

	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Documento", 100, 1);
		aggiungiColonna("Tipo", 100, 2);
		aggiungiColonna("Data Importazione", 100, 3);
		aggiungiColonna("Descrizione", 100, 4);
	}

	@Override
	public void aggiornaContenuto() {
		setElementi(ControllerDocumentiSpedizioniSimulazione.getInstance().trovaTutti());		
	}

	@Override
	protected Ordinatore<DocumentoSpedizioniSimulazione> creaOrdinatore() {
		return new OrdinatoreDocumentiSimulazioneSpedizioni();
	}

	@Override
	protected Etichettatore<DocumentoSpedizioniSimulazione> creaEtichettatore() {
		return new EtichettatoreDocumentiSimulazioneSpedizioni();
	}

	@Override
	protected ModificatoreValoriCelle<DocumentoSpedizioniSimulazione> creaModificatore() {
		return null;
	}

}
