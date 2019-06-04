package it.ltc.logica.ufficio.gui.elements.prodottopermodello;

import java.util.HashMap;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.controller.prodotti.ProdottiPerModello;
import it.ltc.logica.common.controller.prodotti.ProdottoConQuantita;
import it.ltc.logica.database.model.prodotto.Prodotto;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.TabellaCRUDConFiltro;
import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggioSoloTesto;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;
import it.ltc.logica.utilities.variabili.Permessi;

public class TabellaProdottiPerModello extends TabellaCRUDConFiltro<ProdottiPerModello, CriteriFiltraggioSoloTesto> {
	
	private FiltroProdottiPerModello filtro;
	private final HashMap<Prodotto, Integer> mappaProdottiQuantita;

	public TabellaProdottiPerModello(Composite parent) {
		super(parent, STILE_SELEZIONE_SINGOLA);
		mappaProdottiQuantita = new HashMap<Prodotto, Integer>();
	}
	
	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Brand", 100, 0);
		aggiungiColonna("Categoria Merceologica", 100, 1);
		aggiungiColonna("Modello", 100, 2);
		aggiungiColonna("Taglie", 100, 3);
		aggiungiColonna("Totale", 100, 4);
	}
	
	/**
	 * Restituisce la lista dei prodotti con le corrispettive quantita'.
	 * Alcuni prodotti potrebbero avere quantita' a 0 e vanno scartati.
	 * @return un oggetto HashMap che ha per chiave il prodotto e contiene la sua quantita' selezionata.
	 */
	public HashMap<Prodotto, Integer> getProdottiSelezionati() {
		return mappaProdottiQuantita;
	}

	@Override
	protected Ordinatore<ProdottiPerModello> creaOrdinatore() {
		return new OrdinatoreProdottiPerModello();
	}

	@Override
	protected FiltroTabella<ProdottiPerModello, CriteriFiltraggioSoloTesto> creaFiltro() {
		filtro = new FiltroProdottiPerModello();
		return filtro;
	}

	@Override
	protected boolean eliminaElemento(ProdottiPerModello elemento) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void apriDialog(ProdottiPerModello ppm) {
		DialogProdottiPerModello dialog = new DialogProdottiPerModello(ppm);
		dialog.apri();
		//Importo la selezione.
		for (ProdottoConQuantita pcq : ppm.getProdotti()) {
			mappaProdottiQuantita.put(pcq.getProdotto(), pcq.getQuantita());
		}
		aggiustaLarghezzaColonne();
		refresh();
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.UFFICIO.getID(); //FIXME - Aggiungere il permesso e riportarlo qui.
	}

	@Override
	protected DialogProdottiPerModello creaDialog(ProdottiPerModello elemento) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void aggiornaContenuto() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Etichettatore<ProdottiPerModello> creaEtichettatore() {
		return new EtichettatoreProdottiPerModello();
	}

	@Override
	protected ModificatoreValoriCelle<ProdottiPerModello> creaModificatore() {
		return null;
	}

}
