package it.ltc.logica.ufficio.gui.elements.ordinedettagli;

import java.util.List;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.controller.prodotti.ControllerProdotti;
import it.ltc.logica.common.controller.uscite.ControllerOrdiniDettagli;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.ordini.OrdineDettaglio;
import it.ltc.logica.database.model.centrale.ordini.OrdineTestata;
import it.ltc.logica.database.model.centrale.ordini.StatiOrdine;
import it.ltc.logica.gui.dialog.DialogApribile;
import it.ltc.logica.gui.dialog.DialogMessaggio;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.TabellaCRUD;
import it.ltc.logica.utilities.variabili.Permessi;

public class TabellaOrdineDettagli extends TabellaCRUD<OrdineDettaglio> {

	private final Commessa commessa;
	private final OrdineTestata ordine;
	private final ControllerOrdiniDettagli controller;
	private final ControllerProdotti controllerProdotti;
	
	private List<OrdineDettaglio> dettagli;
	
	public TabellaOrdineDettagli(Composite parent, Commessa commessa, OrdineTestata ordine) {
		super(parent);
		this.commessa = commessa;
		this.ordine = ordine;
		this.controller = new ControllerOrdiniDettagli(commessa);
		this.dettagli = null;
		
		//Creo il controller prodotti e lo passo agli etichettatori che lo condivideranno.
		this.controllerProdotti = new ControllerProdotti(commessa);
		aggiungiColonna("Riga", 100, 1);
		aggiungiColonna("Prodotto", 100, 2);
		aggiungiColonna("Descrizione", 100, 3);
		aggiungiColonna("Colore", 100, 4);
		aggiungiColonna("Taglia", 100, 5);
		aggiungiColonna("Magazzino", 100, 6);
		aggiungiColonna("Ordinato", 100, 7);
		aggiungiColonna("Assegnato", 100, 8);
		aggiungiColonna("Imballato", 100, 9);
	}

	@Override
	protected boolean eliminaElemento(OrdineDettaglio elemento) {
		boolean delete = controller.elimina(elemento);
		return delete;
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.UFFICIO_INGRESSI.getID();
	}

	@Override
	protected DialogApribile creaDialog(OrdineDettaglio elemento) {
		DialogApribile dialog;
		if (ordine != null && commessa != null) {
			dialog = new DialogOrdineDettaglio(commessa, ordine, dettagli, elemento, controllerProdotti);
		} else {
			dialog = DialogMessaggio.getWarning("Selezione commessa e carico", "Vanno selezionati una commessa e un carico.");
		}
		return dialog;
	}

	@Override
	protected void aggiungiColonne() {
		// DO NOTHING! - Lo faccio nel construttore per passargli il controller prodotti.
	}

	@Override
	public void aggiornaContenuto() {
		if (ordine != null && commessa != null) {
			dettagli = controller.trovaDettagli(ordine.getId(), commessa);
			setElementi(dettagli);
		}	
	}

	@Override
	protected Ordinatore<OrdineDettaglio> creaOrdinatore() {
		return new OrdinatoreOrdineDettagli();
	}

//	public void creaReportSemplicePDF() {
//		DialogSelezioneCartella dialog = new DialogSelezioneCartella();
//		String path = dialog.open();
//		if (path != null && !path.isEmpty()) {
//			ReportCaricoSemplice report = new ReportCaricoSemplice(ReportExportType.PDF);
//			report.creaReport(commessa, ordine, dettagli, path);
//		}		
//	}
//	
//	public void creaReportSempliceExcel() {
//		DialogSelezioneCartella dialog = new DialogSelezioneCartella();
//		String path = dialog.open();
//		if (path != null && !path.isEmpty()) {
//			ReportCaricoSemplice report = new ReportCaricoSemplice(ReportExportType.XLS);
//			report.creaReport(commessa, ordine, dettagli, path);
//		}		
//	}
//	
//	public void creaReportPerCollo() {
//		DialogSelezioneCartella dialog = new DialogSelezioneCartella();
//		String path = dialog.open();
//		if (path != null && !path.isEmpty()) {
//			List<ColloCaricoJSON> colli = controller.trovaColli(ordine.getId(), commessa);
//			List<ProdottoCaricoJSON> prodotti = controller.trovaProdotti(ordine.getId(), commessa);
//			ReportCaricoPerCollo report = new ReportCaricoPerCollo();
//			report.creaReport(commessa, ordine, colli, prodotti, path);
//		}
//	}
	
	public void abilitaTastiCRUDPerStato(StatiOrdine stato) {
		boolean permesso = isPermesso();
		boolean statoNuovo = false;
		boolean statoModifica = false;
		boolean statoElimina = false;
		switch (stato) {
			case INSE : statoNuovo = true; statoModifica = true; statoElimina = true; break;
			default : statoNuovo = false; statoModifica = false; statoElimina = false; break;
		}
		if (insert != null)	insert.setEnabled(permesso && statoNuovo);
		if (modify != null)	modify.setEnabled(permesso && statoModifica);
		if (delete != null)	delete.setEnabled(permesso && statoElimina);
	}

	@Override
	protected Etichettatore<OrdineDettaglio> creaEtichettatore() {
		return new EtichettatoreOrdineDettagli(controllerProdotti);
	}

	@Override
	protected ModificatoreValoriCelle<OrdineDettaglio> creaModificatore() {
		return null;
	}
}
