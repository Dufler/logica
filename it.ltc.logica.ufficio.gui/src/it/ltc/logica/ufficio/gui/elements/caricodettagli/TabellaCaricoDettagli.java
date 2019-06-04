package it.ltc.logica.ufficio.gui.elements.caricodettagli;

import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import it.ltc.logica.common.controller.ingressi.ControllerCarichiDettagli;
import it.ltc.logica.common.controller.prodotti.ControllerProdotti;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.ingressi.CaricoDettaglio;
import it.ltc.logica.database.model.centrale.ingressi.CaricoTestata;
import it.ltc.logica.database.model.centrale.ingressi.ColloCaricoJSON;
import it.ltc.logica.database.model.centrale.ingressi.ProdottoCaricoJSON;
import it.ltc.logica.database.model.centrale.ingressi.StatiCarico;
import it.ltc.logica.gui.decoration.Immagine;
import it.ltc.logica.gui.dialog.DialogApribile;
import it.ltc.logica.gui.dialog.DialogMessaggio;
import it.ltc.logica.gui.dialog.DialogSelezioneCartella;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.TabellaCRUD;
import it.ltc.logica.ufficio.report.caricopercollo.ReportCaricoPerCollo;
import it.ltc.logica.ufficio.report.caricosemplice.ReportCaricoSemplice;
import it.ltc.logica.utilities.report.ReportExportType;
import it.ltc.logica.utilities.variabili.Permessi;

public class TabellaCaricoDettagli extends TabellaCRUD<CaricoDettaglio> {

	private final Commessa commessa;
	private final CaricoTestata carico;
	private final ControllerCarichiDettagli controller;
	private final ControllerProdotti controllerProdotti;
	
	private List<CaricoDettaglio> dettagli;
	
	protected MenuItem inserimentoMultiplo;
	
	public TabellaCaricoDettagli(Composite parent, Commessa commessa, CaricoTestata carico) {
		super(parent);
		this.commessa = commessa;
		this.carico = carico;
		this.controller = new ControllerCarichiDettagli(commessa);
		this.dettagli = null;
		
		//Creo il controller prodotti e lo passo agli etichettatori che lo condivideranno.
		this.controllerProdotti = new ControllerProdotti(commessa);
		aggiungiColonna("Riga", 100, 1);
		aggiungiColonna("Prodotto", 100, 2);
		aggiungiColonna("Descrizione", 100, 3);
		aggiungiColonna("Colore", 100, 4);
		aggiungiColonna("Taglia", 100, 5);
		aggiungiColonna("Magazzino", 100, 6);
		aggiungiColonna("Dichiarato", 100, 7);
		aggiungiColonna("Riscontrato", 100, 8);
	}
	
	@Override
	protected void aggiungiMenu(Menu menu) {
		inserimentoMultiplo = new MenuItem(menu, SWT.PUSH);
	    delete.setText("Inserimento multiplo");
	    delete.setImage(Immagine.CROCIVERDI_16X16.getImage());
	    delete.addListener(SWT.Selection, new Listener() {
	    	public void handleEvent(Event event) {
	    		apriDialogInserimentoMultiplo();
	    	}
	    });
	}

	@Override
	protected boolean eliminaElemento(CaricoDettaglio elemento) {
		boolean delete = controller.elimina(elemento);
		return delete;
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.UFFICIO_INGRESSI.getID();
	}
	
	@Override
	protected int getIDPermessoDelete() {
		return Permessi.UFFICIO_INGRESSI.getID();
	}

	@Override
	protected DialogApribile creaDialog(CaricoDettaglio elemento) {
		DialogApribile dialog;
		if (carico != null && commessa != null) {
			dialog = new DialogCaricoDettaglio(commessa, carico, dettagli, elemento, controllerProdotti);
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
		if (carico != null && commessa != null) {
			dettagli = controller.trovaDettagli(carico.getId(), commessa);
			setElementi(dettagli);
		}	
	}

	@Override
	protected Ordinatore<CaricoDettaglio> creaOrdinatore() {
		return new OrdinatoreCaricoDettagli();
	}

	public void creaReportSemplicePDF() {
		DialogSelezioneCartella dialog = new DialogSelezioneCartella();
		String path = dialog.open();
		if (path != null && !path.isEmpty()) {
			ReportCaricoSemplice report = new ReportCaricoSemplice(ReportExportType.PDF);
			report.creaReport(commessa, carico, dettagli, path);
		}		
	}
	
	public void creaReportSempliceExcel() {
		DialogSelezioneCartella dialog = new DialogSelezioneCartella();
		String path = dialog.open();
		if (path != null && !path.isEmpty()) {
			ReportCaricoSemplice report = new ReportCaricoSemplice(ReportExportType.XLS);
			report.creaReport(commessa, carico, dettagli, path);
		}		
	}
	
	public void creaReportPerCollo() {
		DialogSelezioneCartella dialog = new DialogSelezioneCartella();
		String path = dialog.open();
		if (path != null && !path.isEmpty()) {
			List<ColloCaricoJSON> colli = controller.trovaColli(carico.getId(), commessa);
			List<ProdottoCaricoJSON> prodotti = controller.trovaProdotti(carico.getId(), commessa);
			ReportCaricoPerCollo report = new ReportCaricoPerCollo();
			report.creaReport(commessa, carico, colli, prodotti, path);
		}
	}
	
	public void abilitaTastiCRUDPerStato(StatiCarico stato) {
		boolean permesso = isPermesso();
		boolean statoNuovo = false;
		boolean statoModifica = false;
		boolean statoElimina = false;
		switch (stato) {
			case INSERITO : statoNuovo = true; statoModifica = true; statoElimina = true; break;
			case ARRIVATO : statoNuovo = true; statoModifica = true; statoElimina = true; break;
			case IN_LAVORAZIONE : statoNuovo = false; statoModifica = false; statoElimina = false; break;
			case LAVORATO : statoNuovo = false; statoModifica = true; statoElimina = false; break;
			case CHIUSO : statoNuovo = false; statoModifica = false; statoElimina = false; break;
			case ANNULLATO : statoNuovo = false; statoModifica = false; statoElimina = false; break;
		}
		if (insert != null)	insert.setEnabled(permesso && statoNuovo);
		if (modify != null)	modify.setEnabled(permesso && statoModifica);
		if (delete != null)	delete.setEnabled(permesso && statoElimina);
		if (inserimentoMultiplo != null) inserimentoMultiplo.setEnabled(permesso && statoNuovo);
	}
	
	protected void apriDialogInserimentoMultiplo() {
		DialogCaricoDettagliMultipli dialog = new DialogCaricoDettagliMultipli(commessa, carico, dettagli);
		int result = dialog != null ? dialog.open() : -1;
		if (result == Dialog.OK) {
			aggiornaContenuto();
			dirty = true;
		}
	}

	@Override
	protected Etichettatore<CaricoDettaglio> creaEtichettatore() {
		return new EtichettatoreCaricoDettagli(controllerProdotti);
	}

	@Override
	protected ModificatoreValoriCelle<CaricoDettaglio> creaModificatore() {
		return null;
	}

}
