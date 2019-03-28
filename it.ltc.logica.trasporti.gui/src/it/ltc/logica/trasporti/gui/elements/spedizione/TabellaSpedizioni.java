package it.ltc.logica.trasporti.gui.elements.spedizione;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MenuAdapter;
import org.eclipse.swt.events.MenuEvent;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import it.ltc.database.dao.locali.CriteriSelezioneSpedizioni;
import it.ltc.logica.common.controller.trasporti.ControllerSpedizioni;
import it.ltc.logica.database.model.centrale.trasporti.Spedizione;
import it.ltc.logica.gui.dialog.DialogApribile;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.Tabella;
import it.ltc.logica.gui.elements.TabellaCRUDConFiltro;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;
import it.ltc.logica.gui.wizard.DialogWizard;
import it.ltc.logica.trasporti.controller.SincronizzazioneDatiTrasportiController;
import it.ltc.logica.trasporti.gui.dialog.DialogContrassegno;
import it.ltc.logica.trasporti.gui.dialog.DialogGiacenza;
import it.ltc.logica.trasporti.gui.wizard.spedizione.NuovaSpedizioneWizard;
import it.ltc.logica.utilities.variabili.Permessi;

public class TabellaSpedizioni extends TabellaCRUDConFiltro<Spedizione, CriteriFiltraggioSpedizione> {

	/**
	 * Contiene tutti i campi possibili da visualizzare
	 */
	public static final int TIPO_COMPLETO = 0;
	
	/**
	 * Contiene solo i campi: Data, Destinatario e Riferimento
	 */
	public static final int TIPO_SEMPLICE = 1;
	
	protected MenuItem menuModifica;
	protected MenuItem menuGiacenza;
	protected MenuItem menuContrassegno;
	
	private FiltroSpedizioni filtro;
	private CriteriSelezioneSpedizioni criteriSelezione;
	
	/**
	 * Costruisce una tabella atta a visualizzare entities <code>Spedizione</code>
	 * Vanno specificati il <code>Composite</code> genitore e la tipologia di dati da visualizzare.
	 * I possibili tipi di tabella sono: completo e semplice.
	 * @param parent Il composite che conterra' la tabella.
	 * @param type Il tipo di tabella.
	 */
	public TabellaSpedizioni(Composite parent, int type) {
		super(parent, Tabella.STILE_TABELLE_GRANDI, false, true);

		//Colonne fondamentali, sono presenti in tutti i tipi
		aggiungiColonna("Data", 120, 0);
		aggiungiColonna("Destinatario", 200, 1);
		aggiungiColonna("Riferimento", 150, 2);
		
		//Versione completa
		if (type == TIPO_COMPLETO) {
			aggiungiColonna("Colli", 40, 3);
			aggiungiColonna("Pezzi", 40, 4);
			aggiungiColonna("Contrassegno", 85, 5);
			aggiungiColonna("Giacenza", 80, 6);
			aggiungiColonna("Ritardo", 80, 7);
		}
		
	}
	
	@Override
	protected void aggiungiColonne() {
		//Viene fatto in base al tipo passato al costruttore.
	}

	@Override
	protected void aggiungiMenu(Menu menu) {
		
		boolean permesso = isPermesso();
		if (permesso) {
			menu.addMenuListener(new MenuAdapter(){
				@Override
				public void menuShown(MenuEvent e) {
					Spedizione selezione = getRigaSelezionata();
					if (selezione != null) {
						menuContrassegno.setEnabled(!selezione.getContrassegno());
					}
				}
			});
		    
		    menuGiacenza = new MenuItem(menu, SWT.PUSH);
		    menuGiacenza.setText("Aggiungi Giacenza");
		    menuGiacenza.addListener(SWT.Selection, new Listener() {
		    	public void handleEvent(Event event) {
		    		aggiungiGiacenza();
		    	}
		    });
		    
		    menuContrassegno = new MenuItem(menu, SWT.PUSH);
		    menuContrassegno.setText("Aggiungi Contrassegno");
		    menuContrassegno.addListener(SWT.Selection, new Listener() {
		    	public void handleEvent(Event event) {
		    		aggiungiContrassegno();
		    	}
		    });
		    
		    if (insert != null) insert.dispose();
		}		

	}
	
	protected void aggiungiGiacenza() {
		Spedizione spedizione = getRigaSelezionata();
		if (spedizione != null) {
			DialogGiacenza dialog = new DialogGiacenza(spedizione, null);
			int esito = dialog.open();
			if (esito == Dialog.OK) {
				refresh();
			}
		}
		
	}
	
	protected void aggiungiContrassegno() {
		Spedizione spedizione = getRigaSelezionata();
		if (spedizione != null) {
			DialogContrassegno dialog = new DialogContrassegno(spedizione, null);
			int esito = dialog.open();
			if (esito == Dialog.OK) {
				refresh();
			}
		}
	}

	@Override
	protected Ordinatore<Spedizione> creaOrdinatore() {
		return new OrdinatoreSpedizioni();
	}

	@Override
	protected FiltroTabella<Spedizione, CriteriFiltraggioSpedizione> creaFiltro() {
		filtro = new FiltroSpedizioni();
		return filtro;
	}

	@Override
	protected boolean eliminaElemento(Spedizione elemento) {
		return false;
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.TRASPORTI_SPEDIZIONI_CUD.getID();
	}

	@Override
	protected DialogApribile creaDialog(Spedizione elemento) {
		DialogApribile dialog;
		if (elemento == null) {
			dialog = new DialogWizard(new NuovaSpedizioneWizard(), DialogWizard.WIZARD_SPEDIZIONE);
		} else {
			dialog = new DialogSpedizione(elemento);
		}
		return dialog;
	}

	@Override
	public void aggiornaContenuto() {
		setElementi(ControllerSpedizioni.getInstance().selezionaSpedizioni(criteriSelezione));
		/**
		 * Per far comparire un tooltip che gli spieghi di andare ad usare i filtri per trovare le spedizioni.
		 * */
//		if (criteriSelezione == null) {
//			ToolTip tip = new ToolTip(table.getParent().getShell(), SWT.BALLOON | SWT.ICON_INFORMATION);
//			tip.setAutoHide(false);
//			tip.setMessage("Usa i filtri per trovare le spedizioni!");
//			Point point = table.toDisplay(15, 20);
//			tip.setLocation(point.x, point.y);
//          tip.setVisible(true);
//		}
	}
	
	public void filtra(CriteriFiltraggioSpedizione criteri) {
		criteriSelezione = criteri.getCriteriDiSelezione();
		aggiornaContenuto();
	}
	
	public void annullaFiltro() {
		criteriSelezione = null;
		aggiornaContenuto();
	}

	public void sincronizzaEAggiornaContenuto() {
		SincronizzazioneDatiTrasportiController.getInstance().sincronizzaDati();
		aggiornaContenuto();
	}

	@Override
	protected Etichettatore<Spedizione> creaEtichettatore() {
		return new EtichettatoreSpedizioni();
	}

	@Override
	protected ModificatoreValoriCelle<Spedizione> creaModificatore() {
		return null;
	}

}
