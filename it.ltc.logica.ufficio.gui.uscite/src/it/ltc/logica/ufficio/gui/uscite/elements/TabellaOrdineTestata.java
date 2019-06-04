package it.ltc.logica.ufficio.gui.uscite.elements;

import java.util.LinkedList;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import it.ltc.logica.common.controller.uscite.ControllerOrdini;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.ordini.DatiSpedizione;
import it.ltc.logica.database.model.centrale.ordini.OrdineTestata;
import it.ltc.logica.database.model.centrale.ordini.RisultatoAssegnazioneOrdine;
import it.ltc.logica.database.model.centrale.ordini.StatiOrdine;
import it.ltc.logica.database.model.centrale.ordini.RisultatoAssegnazioneOrdine.StatoAssegnazione;
import it.ltc.logica.gui.decoration.Immagine;
import it.ltc.logica.gui.dialog.DialogApribile;
import it.ltc.logica.gui.dialog.DialogMessaggio;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.TabellaCRUDConFiltro;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;
import it.ltc.logica.ufficio.gui.elements.ordinedettagli.DialogDettagliOrdine;
import it.ltc.logica.ufficio.gui.elements.ordinetestata.DialogOrdineTestata;
import it.ltc.logica.ufficio.gui.uscite.elements.assegnazione.DialogStatoAssegnazione;
import it.ltc.logica.ufficio.gui.uscite.elements.spedizione.DialogDatiSpedizione;
import it.ltc.logica.utilities.variabili.Permessi;

public class TabellaOrdineTestata extends TabellaCRUDConFiltro<OrdineTestata, CriteriFiltraggioOrdineTestata> {
	
	protected MenuItem dettagli;
	protected MenuItem statoAssegnazione;
	protected MenuItem datiSpedizione;
	
	protected Menu annullaMenu;
	protected MenuItem annulla;
	protected MenuItem annullaImballo;
	protected MenuItem annullaAssegnazioneRiposiziona;
	protected MenuItem annullaAssegnazioneNuovoCarico;
	protected MenuItem annullaImportazione;
	
	protected CriteriFiltraggioOrdineTestata criteri;
	protected Commessa commessa;

	public TabellaOrdineTestata(Composite parent) {
		super(parent);
	}
	
	public void setCommessa(Commessa commessa) {
		this.commessa = commessa;
		annullaFiltro();
	}
	
	@Override
	protected void aggiungiListener() {
		table.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				OrdineTestata selezionato = getRigaSelezionata();
				if (selezionato != null) {
					annulla.setEnabled(true);
					boolean importazione = false;
					boolean assegnazione = false;
					boolean imballo = false;
					switch (selezionato.getStato()) {
						case IMPO : importazione = true; assegnazione = true; break;
						case ASSE : assegnazione = true; break;
						case INIB : case DIIB : case COIB : case INSP : case SPED : imballo = true;
						default : break;
					}
					annullaImportazione.setEnabled(importazione);
					annullaImballo.setEnabled(imballo);
					annullaAssegnazioneRiposiziona.setEnabled(assegnazione);
					annullaAssegnazioneNuovoCarico.setEnabled(assegnazione);
				} else {
					annulla.setEnabled(false);
				}
			}
		});
	}
	
	

	@Override
	protected void aggiungiMenu(Menu menu) {
		
		dettagli = new MenuItem(menuPopup, SWT.PUSH);
		dettagli.setText("Dettagli");
		dettagli.addListener(SWT.Selection, new Listener() {
	    	public void handleEvent(Event event) {
	    		visualizzaDettagli();
	    	}
	    });
		
		statoAssegnazione = new MenuItem(menuPopup, SWT.PUSH);
		statoAssegnazione.setText("Stato Assegnazione");
		statoAssegnazione.addListener(SWT.Selection, new Listener() {
	    	public void handleEvent(Event event) {
	    		checkStatoAssegnazione();
	    	}
	    });
		
		datiSpedizione = new MenuItem(menuPopup, SWT.PUSH);
		datiSpedizione.setText("Dati Spedizione");
		datiSpedizione.addListener(SWT.Selection, new Listener() {
	    	public void handleEvent(Event event) {
	    		checkDatiSpedizione();
	    	}
	    });
		
		annulla = new MenuItem(menu, SWT.CASCADE);
		annullaMenu = new Menu(annulla);
		
		annulla.setText("Annulla");
		annulla.setMenu(annullaMenu);
		annulla.setImage(Immagine.CROCEROSSA_16X16.getImage());
		
		annullaImballo = new MenuItem(annullaMenu, SWT.PUSH);
		annullaImballo.setText("Imballo");
		annullaImballo.addListener(SWT.Selection, new Listener() {
	    	public void handleEvent(Event event) {
	    		annullaImballo();
	    	}
	    });
		
		annullaAssegnazioneRiposiziona = new MenuItem(annullaMenu, SWT.PUSH);
		annullaAssegnazioneRiposiziona.setText("Assegnazione (Riposiziona merce)");
		annullaAssegnazioneRiposiziona.addListener(SWT.Selection, new Listener() {
	    	public void handleEvent(Event event) {
	    		annullaAssegnazioneERiposiziona();
	    	}
	    });
		
		annullaAssegnazioneNuovoCarico = new MenuItem(annullaMenu, SWT.PUSH);
		annullaAssegnazioneNuovoCarico.setText("Assegnazione (Nuovo carico)");
		annullaAssegnazioneNuovoCarico.addListener(SWT.Selection, new Listener() {
	    	public void handleEvent(Event event) {
	    		annullaAssegnazioneNuovoCarico();
	    	}
	    });
		
		annullaImportazione = new MenuItem(annullaMenu, SWT.PUSH);
		annullaImportazione.setText("Importazione");
		annullaImportazione.addListener(SWT.Selection, new Listener() {
	    	public void handleEvent(Event event) {
	    		annullaImportazione();
	    	}
	    });
		
	}

	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Data", 100, 1);
		aggiungiColonna("Riferimento", 100, 2);
		aggiungiColonna("Destinatario", 100, 3);
		aggiungiColonna("Ordinati", 100, 4);
		aggiungiColonna("Assegnati", 100, 5);
		aggiungiColonna("Imballati", 100, 6);
		aggiungiColonna("Tipo", 100, 7);
		aggiungiColonna("Stato", 100, 8);
	}

	@Override
	public void aggiornaContenuto() {
		if (criteri != null && commessa != null) {
			ControllerOrdini controller = new ControllerOrdini(commessa);
			String tipo = criteri.getTipo() != null ? criteri.getTipo().getCodice() : null;
			setElementi(controller.trovaOrdini(criteri.getRiferimento(), criteri.getStato(), tipo, criteri.getDa(), criteri.getA()));
		} else {
			//Imposto una lista vuota se non posso andare a filtrare per qualche ragione.
			setElementi(new LinkedList<>());
		}
	}

	@Override
	protected Ordinatore<OrdineTestata> creaOrdinatore() {
		return new OrdinatoreOrdineTestata();
	}

	@Override
	protected FiltroTabella<OrdineTestata, CriteriFiltraggioOrdineTestata> creaFiltro() {
		//In realtà non serve.
		return null;
	}
	
	@Override
	public void filtra(CriteriFiltraggioOrdineTestata criteri) {
		this.criteri = criteri;
		aggiornaContenuto();
	}
	
	@Override
	public void annullaFiltro() {
		this.criteri = null;
		aggiornaContenuto();
	}

	@Override
	protected boolean eliminaElemento(OrdineTestata elemento) {
		boolean delete = false;
		if (commessa != null) {
			ControllerOrdini controller = new ControllerOrdini(commessa);
			delete = controller.elimina(elemento);
		}
		return delete;
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
	protected DialogApribile creaDialog(OrdineTestata elemento) {
		DialogApribile dialog;
		if (commessa != null) {
			boolean abilita = elemento == null || elemento.getStato() != StatiOrdine.SPED;
			DialogOrdineTestata dialogCarico = new DialogOrdineTestata(commessa, elemento, abilita);
			dialog = dialogCarico;
		} else {
			dialog = DialogMessaggio.getWarning("Selezione commessa", "Va selezionata una commessa");
		}
		return dialog;
	}
	
	private void visualizzaDettagli() {
		OrdineTestata elemento = getRigaSelezionata();
		if (elemento != null && commessa != null) {
			DialogDettagliOrdine dialog = new DialogDettagliOrdine(commessa, elemento);
			int code = dialog.open();
			if (code == Window.OK) {
				aggiornaContenuto();
			}
		}
	}
	
	private void checkStatoAssegnazione() {
		OrdineTestata elemento = getRigaSelezionata();
		if (elemento != null && commessa != null) {
			ControllerOrdini controller = new ControllerOrdini(commessa);
			RisultatoAssegnazioneOrdine assegnazione = controller.recuperaAssegnazione(elemento);
			if (assegnazione != null && assegnazione.getStato() != StatoAssegnazione.NONDEFINITA) {
				DialogStatoAssegnazione dialog = new DialogStatoAssegnazione(commessa, assegnazione);
				dialog.open();
			} else {
				DialogMessaggio.openWarning("Assegnazione Ordine", "L'ordine " + elemento.getNumeroLista() + " non \u00E8 stato assegnato, utilizzare l'apposito wizard Assegna.");
			}
		}
	}
	
	private void checkDatiSpedizione() {
		OrdineTestata elemento = getRigaSelezionata();
		if (elemento != null && commessa != null) {
			ControllerOrdini controller = new ControllerOrdini(commessa);
			DatiSpedizione dati = controller.trovaDatiSpedizione(elemento);
			DialogDatiSpedizione dialog = new DialogDatiSpedizione(commessa, dati);
			dialog.open();
		}
	}
	
	protected void annullaImportazione() {
		OrdineTestata elemento = getRigaSelezionata();
		if (elemento != null && commessa != null) {
			boolean conferma = DialogMessaggio.openQuestion("Annullamento Importazione Ordine", "Stai per annullare i movimenti d'impegno dell'ordine e riportarlo allo stato d'inserimento.\r\nVuoi procedere?");
			if (conferma) {
				ControllerOrdini controller = new ControllerOrdini(commessa);
				boolean esito = controller.annullaImportazione(elemento);
				if (esito) {
					aggiornaContenuto();
					DialogMessaggio.openInformation("Annullamento Importazione Ordine", "L'annullamento \u00E8 stato completato con successo.");
				} else {
					DialogMessaggio.openError("Annullamento Importazione Ordine", "Sono stati riscontrati errori durante l'annullamento, contattare il CED.");
				}
			}			
		}		
	}

	protected void annullaAssegnazioneNuovoCarico() {
		OrdineTestata elemento = getRigaSelezionata();
		if (elemento != null && commessa != null) {
			boolean conferma = DialogMessaggio.openQuestion("Annullamento Assegnazione Ordine", "Stai per annullare l'assegnazione dell'ordine, verr\u00E0 generato un nuovo carico per contare la merce precedentemente assegnata.\r\nVuoi procedere?");
			if (conferma) {
				ControllerOrdini controller = new ControllerOrdini(commessa);
				boolean esito = controller.annullaAssegnazioneERicarica(elemento);
				if (esito) {
					aggiornaContenuto();
					DialogMessaggio.openInformation("Annullamento Assegnazione Ordine", "L'annullamento \u00E8 stato completato con successo. La merce andr\u00E0 ricontata nell'apposito carico appena creato.");
				} else {
					DialogMessaggio.openError("Annullamento Assegnazione Ordine", "Sono stati riscontrati errori durante l'annullamento, contattare il CED.");
				}
			}			
		}
	}

	protected void annullaAssegnazioneERiposiziona() {
		OrdineTestata elemento = getRigaSelezionata();
		if (elemento != null && commessa != null) {
			boolean conferma = DialogMessaggio.openQuestion("Annullamento Assegnazione Ordine", "Stai per annullare l'assegnazione dell'ordine, la merce andr\u00E0 riposizionata nei colli da cui è stata prelevata.\r\nVuoi procedere?");
			if (conferma) {
				ControllerOrdini controller = new ControllerOrdini(commessa);
				boolean esito = controller.annullaAssegnazioneERiposiziona(elemento);
				if (esito) {
					aggiornaContenuto();
					DialogMessaggio.openInformation("Annullamento ", "L'annullamento \u00E8 stato completato con successo. La merce andr\u00E0 riposizionata nei colli da dove \u00E8 stata prelevata.");
				} else {
					DialogMessaggio.openError("Annullamento ", "Sono stati riscontrati errori durante l'annullamento, contattare il CED.");
				}
			}			
		}
	}

	protected void annullaImballo() {
		OrdineTestata elemento = getRigaSelezionata();
		if (elemento != null && commessa != null) {
			boolean conferma = DialogMessaggio.openQuestion("Annullamento Imballo Ordine", "Stai per eliminare l'imballo di un'ordine, la merce andr\u00E0 re-imballata di nuovo.\r\nVuoi procedere?");
			if (conferma) {
				ControllerOrdini controller = new ControllerOrdini(commessa);
				boolean esito = controller.annullaImballo(elemento);
				if (esito) {
					aggiornaContenuto();
					DialogMessaggio.openInformation("Annullamento Imballo Ordine", "L'annullamento \u00E8 stato completato con successo. Re-imballare l'ordine.");
				} else {
					DialogMessaggio.openError("Annullamento Imballo Ordine", "Sono stati riscontrati errori durante l'annullamento, contattare il CED.");
				}
			}			
		}
	}

	@Override
	protected Etichettatore<OrdineTestata> creaEtichettatore() {
		return new EtichettatoreOrdineTestata();
	}

	@Override
	protected ModificatoreValoriCelle<OrdineTestata> creaModificatore() {
		return null;
	}

}
