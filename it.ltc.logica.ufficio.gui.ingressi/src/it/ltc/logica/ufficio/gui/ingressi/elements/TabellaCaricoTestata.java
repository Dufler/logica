package it.ltc.logica.ufficio.gui.ingressi.elements;

import java.util.List;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.events.MenuListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import it.ltc.logica.common.controller.ingressi.ControllerCarichi;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.ingressi.CaricoTestata;
import it.ltc.logica.database.model.centrale.ingressi.StatiCarico;
import it.ltc.logica.gui.dialog.DialogApribile;
import it.ltc.logica.gui.dialog.DialogMessaggio;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.TabellaCRUDConFiltro;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;
import it.ltc.logica.ufficio.gui.elements.caricodettagli.DialogDettagli;
import it.ltc.logica.ufficio.gui.elements.caricotestata.DialogCaricoTestata;
import it.ltc.logica.utilities.variabili.Permessi;

public class TabellaCaricoTestata extends TabellaCRUDConFiltro<CaricoTestata, CriteriFiltraggioCaricoTestata> {
	
	protected CriteriFiltraggioCaricoTestata criteri;
	protected Commessa commessa;
	
	protected MenuItem dettagli;
	protected MenuItem annulla;
	protected MenuItem inserito;
	protected MenuItem arrivato;
	protected MenuItem inLavorazione;
	protected MenuItem lavorato;
	protected MenuItem chiuso;
	
	public TabellaCaricoTestata(Composite parent) {
		super(parent);		
	}
	
	public void setCommessa(Commessa commessa) {
		this.commessa = commessa;
		annullaFiltro();
	}
	
	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Data", 100, 0);
		aggiungiColonna("Riferimento Cliente", 100, 1);
		aggiungiColonna("Riferimento Interno", 100, 2);
		aggiungiColonna("Dichiarati", 100, 3);
		aggiungiColonna("Riscontrati", 100, 4);
		//aggiungiColonna("Colli", 100, 4); //Attualmente questo valore non è valorizzato correttamente sui sitemi legacy quindi non lo riporto.
		aggiungiColonna("Stato", 100, 5);
		aggiungiColonna("Tipo", 100, 6);
	}
	
	protected void abilitaVociInserito() {
		inserito.setEnabled(false);
		arrivato.setEnabled(true);
		inLavorazione.setEnabled(false);
		lavorato.setEnabled(false);
		chiuso.setEnabled(false);
	}
	
	protected void abilitaVociArrivato() {
		inserito.setEnabled(true);
		arrivato.setEnabled(false);
		inLavorazione.setEnabled(true);
		lavorato.setEnabled(false);
		chiuso.setEnabled(false);
	}
	
	protected void abilitaVociInLavorazione() {
		inserito.setEnabled(false);
		arrivato.setEnabled(true);
		inLavorazione.setEnabled(false);
		lavorato.setEnabled(true);
		chiuso.setEnabled(false);
	}
	
	protected void abilitaVociLavorato() {
		inserito.setEnabled(false);
		arrivato.setEnabled(false);
		inLavorazione.setEnabled(true);
		lavorato.setEnabled(false);
		chiuso.setEnabled(true);
	}
	
	protected void abilitaVociChiuso() {
		inserito.setEnabled(true);
		arrivato.setEnabled(true);
		inLavorazione.setEnabled(true);
		lavorato.setEnabled(true);
		chiuso.setEnabled(false);
	}
	
	protected void disabilitaVociMenu() {
		inserito.setEnabled(false);
		arrivato.setEnabled(false);
		inLavorazione.setEnabled(false);
		lavorato.setEnabled(false);
		chiuso.setEnabled(false);
	}
	
	@Override
	protected void aggiungiMenu(Menu menu) {
		
		menuPopup.addMenuListener(new MenuListener() {

			@Override
			public void menuHidden(MenuEvent e) {
				//DO NOTHING!
			}

			@Override
			public void menuShown(MenuEvent e) {
				CaricoTestata carico = getRigaSelezionata();
				if (carico != null) {
					StatiCarico stato = carico.getStato();
					switch (stato) {
						case INSERITO : abilitaVociInserito(); break;
						case ARRIVATO : abilitaVociArrivato(); break;
						case IN_LAVORAZIONE : abilitaVociInLavorazione(); break;
						case LAVORATO : abilitaVociLavorato(); break;
						case CHIUSO : abilitaVociChiuso(); break;
						case ANNULLATO : disabilitaVociMenu(); annulla.setEnabled(false); break;
						default : disabilitaVociMenu(); break;
					}
				}
			}
		});
		
		dettagli = new MenuItem(menuPopup, SWT.PUSH);
		dettagli.setText("Dettagli");
		dettagli.addListener(SWT.Selection, new Listener() {
	    	public void handleEvent(Event event) {
	    		int selectionIndex = table.getSelectionIndex();
	    		if (selectionIndex != -1) {
	    			visualizzaDettagli();
	    		}
	    	}
	    });
		
		MenuItem stato = new MenuItem(menu, SWT.CASCADE);
		stato.setText("Modifica stato...");
		
		Menu menuStato = new Menu(stato);
		stato.setMenu(menuStato);
		stato.setEnabled(isPermesso());
		
		inserito = new MenuItem(menuStato, SWT.PUSH);
		inserito.setText("Inserito");
		inserito.addListener(SWT.Selection, new Listener() {
	    	public void handleEvent(Event event) {
	    		CaricoTestata carico = getRigaSelezionata();
	    		if (carico != null)
	    			modificaStato(carico, StatiCarico.INSERITO);
	    	}
	    });
		
		arrivato = new MenuItem(menuStato, SWT.PUSH);
		arrivato.setText("Arrivato");
		arrivato.addListener(SWT.Selection, new Listener() {
	    	public void handleEvent(Event event) {
	    		CaricoTestata carico = getRigaSelezionata();
	    		if (carico != null)
	    			modificaStato(carico, StatiCarico.ARRIVATO);
	    	}
	    });
		
		inLavorazione = new MenuItem(menuStato, SWT.PUSH);
		inLavorazione.setText("In Lavorazione");
		inLavorazione.addListener(SWT.Selection, new Listener() {
	    	public void handleEvent(Event event) {
	    		CaricoTestata carico = getRigaSelezionata();
	    		if (carico != null)
	    			modificaStato(carico, StatiCarico.IN_LAVORAZIONE);
	    	}
	    });
		
		lavorato = new MenuItem(menuStato, SWT.PUSH);
		lavorato.setText("Lavorato");
		lavorato.addListener(SWT.Selection, new Listener() {
	    	public void handleEvent(Event event) {
	    		CaricoTestata carico = getRigaSelezionata();
	    		boolean checkTotali = true;
	    		boolean checkAnnullaChiuso = true;
	    		if (carico.getStato() == StatiCarico.CHIUSO) {
	    			String messaggio = "Il carico \u00E8 gi\u00E0 stato chiuso, i movimenti sono stati generati e i prodotti sono stati caricati a magazzino\r\nSei sicuro di volerlo riportare a lavorato ed annulare i movimenti?\r\nCos\u00EC facendo i prodotti non saranno pi\u00F9 disponibili."; 
	    			checkAnnullaChiuso = DialogMessaggio.openQuestion("Annullamento movimenti", messaggio);
	    		} else if (carico.getQuantitaRiscontrataTotale() < carico.getQuantitaDichiarataTotale()) {
	    			String messaggio = "Il carico \u00E8 stato riscontrato solo parzialmente, sei sicuro di voler aggiornare lo stato a lavorato?\r\nNon sar\u00E0 pi\u00F9 possibile leggerci altri pezzi.";
	    			checkTotali = DialogMessaggio.openQuestion("Riscontro carico parziale", messaggio);
	    		}
	    		if (checkAnnullaChiuso && checkTotali && carico != null)
	    			modificaStato(carico, StatiCarico.LAVORATO);
	    	}
	    });
		
		chiuso = new MenuItem(menuStato, SWT.PUSH);
		chiuso.setText("Chiuso");
		chiuso.addListener(SWT.Selection, new Listener() {
	    	public void handleEvent(Event event) {
	    		CaricoTestata carico = getRigaSelezionata();
	    		if (carico != null)
	    			modificaStato(carico, StatiCarico.CHIUSO);
	    	}
	    });
	}
	
	protected void modificaStato(CaricoTestata carico, StatiCarico stato) {
		if (commessa != null) {
			ControllerCarichi controller = new ControllerCarichi(commessa);
			boolean modifica = controller.modificaStato(carico, stato);
			if (modifica) {
				aggiornaContenuto();
			} else {
				DialogMessaggio.openError("Aggiornamento fallito", "Impossibile aggiornare lo stato del carico.");
			}
		}
	}

	@Override
	protected Ordinatore<CaricoTestata> creaOrdinatore() {
		return new OrdinatoreCaricoTestata();
	}

	@Override
	public void aggiornaContenuto() {
		if (criteri != null && commessa != null) {
			ControllerCarichi controller = new ControllerCarichi(commessa);
			int idFornitore = criteri.getFornitore() != null ? criteri.getFornitore().getId() : 0;
			String tipo = criteri.getTipo() != null ? criteri.getTipo().getCodice() : null;
			setElementi(controller.trovaCarichi(criteri.getRiferimento(), criteri.getStato(), idFornitore, tipo, criteri.getDa(), criteri.getA()));
		} else {
			List<CaricoTestata> listaVuota = null;
			setElementi(listaVuota);
		}
	}

	@Override
	protected boolean eliminaElemento(CaricoTestata elemento) {
		boolean delete = false;
		if (commessa != null) {
			if (elemento.getStato() == StatiCarico.INSERITO) {
				ControllerCarichi controller = new ControllerCarichi(commessa);
				delete = controller.elimina(elemento);
			} else {
				DialogMessaggio.getWarning("Impossibile eliminare", "Non \u00E8 possibile eliminare un carico gi\u00E0 arrivato.").open();
				delete = false;
			}
		}
		return delete;
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.UFFICIO_INGRESSI.getID();
	}
	
	@Override
	protected int getIDPermessoDelete() {
		return Permessi.UFFICIO_INGRESSI_ELIMINA.getID();
	}

	@Override
	protected DialogApribile creaDialog(CaricoTestata elemento) {
		DialogApribile dialog;
		if (commessa != null) {
			boolean abilita = elemento == null || elemento.getStato() != StatiCarico.CHIUSO;
			DialogCaricoTestata dialogCarico = new DialogCaricoTestata(commessa, elemento, abilita);
			dialog = dialogCarico;
		} else {
			dialog = DialogMessaggio.getWarning("Selezione commessa", "Va selezionata una commessa");
		}
		return dialog;
	}
	
	private void visualizzaDettagli() {
		CaricoTestata elemento = getRigaSelezionata();
		if (elemento != null && commessa != null) {
			DialogDettagli dialog = new DialogDettagli(commessa, elemento);
			int code = dialog.open();
			if (code == Window.OK) {
				aggiornaContenuto();
			}
		}
		
	}

	@Override
	protected FiltroTabella<CaricoTestata, CriteriFiltraggioCaricoTestata> creaFiltro() {
		//In realtà non serve.
		return null;
	}
	
	@Override
	public void filtra(CriteriFiltraggioCaricoTestata criteri) {
		this.criteri = criteri;
		aggiornaContenuto();
	}
	
	@Override
	public void annullaFiltro() {
		this.criteri = null;
		aggiornaContenuto();
	}

	@Override
	protected Etichettatore<CaricoTestata> creaEtichettatore() {
		return new EtichettatoreCaricoTestata();
	}

	@Override
	protected ModificatoreValoriCelle<CaricoTestata> creaModificatore() {
		return null;
	}

}
