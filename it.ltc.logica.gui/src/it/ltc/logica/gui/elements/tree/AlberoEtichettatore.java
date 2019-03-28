package it.ltc.logica.gui.elements.tree;

import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider.IStyledLabelProvider;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.StyledString;
import org.eclipse.swt.graphics.Image;

/**
 * Classe astratta da implementare per lo specifico albero che gestisce i tipi T.
 * @author Damiano Bellucci - damiano.bellucci@gmail.com
 *
 * @param <T> il tipo da gestire.
 */
public abstract class AlberoEtichettatore extends LabelProvider implements IStyledLabelProvider {
	
	private final int columnIndex;
	
	public AlberoEtichettatore(int columnIndex) {
		this.columnIndex = columnIndex;
	}

	@Override
	public StyledString getStyledText(Object element) {
		StyledString testoConStile = new StyledString();
		String testo =  getTestoConStile(element, columnIndex);
		testoConStile.append(testo != null ? testo : "");
		return testoConStile;
	}
	
	/**
	 * Metodo astratto da implementare che restituisce il testo che descrive la proprieta' dell'oggetto nella specifica colonna.
	 * @param oggetto l'oggetto da descrivere.
	 * @param indiceColonna la colonna da riempire.
	 * @return una stringa con stile.
	 */
	protected abstract String getTestoConStile(Object oggetto, int indiceColonna);
	
	@Override
    public Image getImage(Object element) {
		return getImmagine(element, columnIndex);
	}
	
	/**
	 * Metodo astratto da implementare che restituisce l'immagine da piazzare nella cella individuata dell'oggetto e dalla colonna. 
	 * @param oggetto l'oggetto da descrivere.
	 * @param indiceColonna la colonna da riempire.
	 * @return un immagine che verrà posizionata nella cella.
	 */
	protected abstract Image getImmagine(Object oggetto, int indiceColonna);

}
