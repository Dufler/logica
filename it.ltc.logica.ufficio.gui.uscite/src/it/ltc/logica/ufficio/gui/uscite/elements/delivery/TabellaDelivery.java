package it.ltc.logica.ufficio.gui.uscite.elements.delivery;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import it.ltc.logica.common.controller.trasporti.ControllerCorrieri;
import it.ltc.logica.common.controller.uscite.ControllerDelivery;
import it.ltc.logica.common.controller.uscite.ControllerOrdini;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.ordini.DatiSpedizione;
import it.ltc.logica.database.model.centrale.ordini.Delivery;
import it.ltc.logica.database.model.centrale.trasporti.Corriere;
import it.ltc.logica.gui.decoration.Immagine;
import it.ltc.logica.gui.dialog.DialogApribile;
import it.ltc.logica.gui.dialog.DialogMessaggio;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.TabellaCRUDConFiltro;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;
import it.ltc.logica.ufficio.gui.uscite.elements.spedizione.ReportDelivery;
import it.ltc.logica.utilities.variabili.Permessi;

public class TabellaDelivery extends TabellaCRUDConFiltro<Delivery, FiltroDelivery> {
	
	protected MenuItem stampaDelivery;
	
	protected FiltroDelivery criteri;
	protected Commessa commessa;

	public TabellaDelivery(Composite parent) {
		super(parent);
		
		//Disabilito temporaneamente alcuni tasti.
		insert.dispose();
		modify.dispose();
		delete.dispose();
	}
	
	public void setCommessa(Commessa commessa) {
		this.commessa = commessa;
		annullaFiltro();
	}
	
	@Override
	protected void aggiungiMenu(Menu menu) {
		stampaDelivery = new MenuItem(menuPopup, SWT.PUSH);
		stampaDelivery.setText("Ristampa delivery");
		stampaDelivery.setImage(Immagine.REPORT_16X16.getImage());
		stampaDelivery.addListener(SWT.Selection, new Listener() {
	    	public void handleEvent(Event event) {
	    		stampaDelivery();
	    	}
	    });
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.UFFICIO_USCITE.getID();
	}
	
	@Override
	protected int getIDPermessoDelete() {
		return Permessi.UFFICIO_USCITE_ELIMINA.getID();
	}

	@Override
	protected DialogApribile creaDialog(Delivery elemento) {
		//Nessuna dialog, per ora.
		return null;
	}

	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Data", 100, 0);
		aggiungiColonna("Corriere", 100, 1);
		aggiungiColonna("Spedizioni", 100, 2);
		aggiungiColonna("Colli", 100, 3);
		aggiungiColonna("Peso", 100, 4);
		aggiungiColonna("Volume", 100, 5);
		aggiungiColonna("Utente", 100, 6);
	}

	@Override
	public void filtra(FiltroDelivery criteri) {
		this.criteri = criteri;
		aggiornaContenuto();
	}
	
	@Override
	public void annullaFiltro() {
		this.criteri = null;
		aggiornaContenuto();
	}

	@Override
	public void aggiornaContenuto() {
		if (criteri != null && commessa != null) {
			ControllerDelivery controller = new ControllerDelivery(commessa);
			setElementi(controller.trovaDelivery(criteri.getCorriere(), criteri.getDa(), criteri.getA()));
		} else {
			//Imposto una lista vuota se non posso andare a filtrare per qualche ragione.
			setElementi(new LinkedList<>());
		}		
	}

	@Override
	protected Ordinatore<Delivery> creaOrdinatore() {
		return new OrdinatoreDelivery();
	}

	@Override
	protected Etichettatore<Delivery> creaEtichettatore() {
		return new EtichettatoreDelivery();
	}

	@Override
	protected ModificatoreValoriCelle<Delivery> creaModificatore() {
		return null;
	}

	@Override
	protected FiltroTabella<Delivery, FiltroDelivery> creaFiltro() {
		//In realtà non serve.
		return null;
	}

	@Override
	protected boolean eliminaElemento(Delivery elemento) {
		boolean delete = false;
		if (commessa != null) {
			ControllerDelivery controller = new ControllerDelivery(commessa);
			delete = controller.elimina(elemento);
		}
		return delete;
	}
	
	protected void stampaDelivery() {
		Delivery delivery = getRigaSelezionata();
		if (delivery != null && commessa != null) {
			//Cerco le spedizioni appartenenti alla delivery
			ControllerDelivery controller = new ControllerDelivery(commessa);
			delivery = controller.trovaDettagliDelivery(delivery);
			if (delivery != null) {
				//Recupero i dati necessari
				Corriere corriere = ControllerCorrieri.getInstance().getCorriere(delivery.getCorriere());
				ControllerOrdini controllerSpedizioni = new ControllerOrdini(commessa);
				List<DatiSpedizione> spedizioni = new LinkedList<>();
				for (Integer idSpedizione : delivery.getSpedizioni()) {
					DatiSpedizione spedizione = controllerSpedizioni.trovaDatiSpedizioneDaID(idSpedizione);
					spedizioni.add(spedizione);
				}
				ReportDelivery report = new ReportDelivery();
				report.creaReport(spedizioni, commessa, corriere);
			} else {
				DialogMessaggio.openError("Errore Delivery", "Impossibile trovare i dettagli della delivery.");
			}
			
		}		
	}

}
