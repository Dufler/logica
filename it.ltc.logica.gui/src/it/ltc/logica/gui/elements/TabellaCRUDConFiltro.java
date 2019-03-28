package it.ltc.logica.gui.elements;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.gui.elements.table.filter.CriteriFiltraggio;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;

/**
 * Tabella per gestire oggetti che possiede anche un filtro.
 * @author Damiano
 *
 * @param <T> Il tipo di oggetti da gestire
 * @param <U> L'oggetto che contiene i criteri di filtraggio, estende <code>CriteriFiltraggio</code>
 */
public abstract class TabellaCRUDConFiltro<T, U extends CriteriFiltraggio> extends TabellaCRUD<T> {
	
	public TabellaCRUDConFiltro(Composite parent) {
		this(parent, STILE_SEMPLICE, true, true);
	}
	
	public TabellaCRUDConFiltro(Composite parent, int style) {
		this(parent, style, true, true);
	}
	
	public TabellaCRUDConFiltro(Composite parent, int style, boolean cancellazionePossibile, boolean apriConDoppioClick) {
		super(parent, style, cancellazionePossibile, apriConDoppioClick);
	}

	protected FiltroTabella<T, U> filter;
	
	/**
	 * Restituisce il filtro della tabella.
	 * @return
	 */
	public FiltroTabella<T, U> getFiltro() {
		return filter;
	}

	/**
	 * Nel corpo di questo metodo va implementato il filtraggio degli elementi in tabella.
	 * Usualmente viene abbinato al viewer un oggetto <code>ViewerFilter</code>
	 */
	protected final void setFiltro() {
		filter = creaFiltro();
		if (filter != null)
			setFilters(filter);
	}
	
	/**
	 * Metodo astratto che deve essere implementato per restituire l'oggetto responsabile del filtraggio della tabella.
	 * @return
	 */
	protected abstract FiltroTabella<T, U> creaFiltro();
	
	/**
	 * Passa i criteri al filtro e aggiorna gli elementi mostrati nella tabella.
	 * @param criteri
	 */
	public void filtra(U criteri) {
		if (filter != null) {
			filter.setCriteri(criteri);
			refresh();
			aggiustaLarghezzaColonne();
		}
	}
	
	/**
	 * Annnulla i criteri di filtraggio precedentemente impostati.
	 */
	public void annullaFiltro() {
		if (filter != null) {
			filter.resetCriteri();
			refresh();
			aggiustaLarghezzaColonne();
		}
	}

}
