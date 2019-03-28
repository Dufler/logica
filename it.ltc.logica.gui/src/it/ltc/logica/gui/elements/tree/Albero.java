package it.ltc.logica.gui.elements.tree;

import java.util.List;

import org.eclipse.jface.viewers.ColumnLabelProvider;
import org.eclipse.jface.viewers.DelegatingStyledCellLabelProvider;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.TreeViewerColumn;
import org.eclipse.swt.SWT;
import org.eclipse.swt.dnd.Clipboard;
import org.eclipse.swt.dnd.RTFTransfer;
import org.eclipse.swt.dnd.TextTransfer;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.TreeEvent;
import org.eclipse.swt.events.TreeListener;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;

import it.ltc.logica.gui.decoration.Immagine;

/**
 * Raffinamento della classe TreeViewer.
 * Va implementato il provider specifico per il tipo scelto.
 * @author Damiano Bellucci - damiano.bellucci@gmail.com
 *
 * @param <T> il tipo di oggetti contenuti nell'albero.
 */
public abstract class Albero<T> extends TreeViewer {
	
	public static final int DEFAULT_STYLE = SWT.BORDER | SWT.MULTI | SWT.H_SCROLL | SWT.V_SCROLL; 
	
	public static final int DEFAULT_WIDTH = 100;
	
	protected static final String TAB = "\t";
	protected static final String NEW_LINE = "\n"; 
	
	protected final Tree tree;
	protected Menu menuPopup;
	protected MenuItem copy;

	public Albero(Composite parent, AlberoProvider<T> provider) {
		super(parent, DEFAULT_STYLE);
		
		this.tree = getTree();
		tree.setHeaderVisible(true);
		tree.setLinesVisible(true);
		tree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		
		this.menuPopup = new Menu(tree);
		this.tree.setMenu(menuPopup);
		
		aggiungiMenuBase();
		
		aggiungiListenerBase();
		
		impostaColonne();
		
		setContentProvider(provider);
	}
	
	/**
	 * Imposta le colonne che compongono la tabella.
	 */
	protected abstract void impostaColonne();
	
	@SuppressWarnings("unchecked")
	public List<T> getElementi() {
		Object input = getInput();
		return (List<T>) input;
	}
	
	/**
	 * Imposta l'insieme di elementi all'interno dell'albero.
	 * @param elementi
	 */
	public void setElementi(List<T> elementi) {
		setInput(elementi);
		aggiustaLarghezzaColonne();
	}
	
	/**
	 * Ridimensiona la larghezza delle colonne in automatico.
	 */
	protected void aggiustaLarghezzaColonne() {
		AggiustaLarghezzaColonne runnable = new AggiustaLarghezzaColonne();
		tree.getDisplay().asyncExec(runnable);
	}
	
	/**
	 * Runnable che aggiusta la larghezza delle colonne.
	 * @author Damiano
	 *
	 */
	private class AggiustaLarghezzaColonne implements Runnable {
		
		public void run() {
			for (TreeColumn column : tree.getColumns()) { 
				column.pack(); 
			}
		}
	}
	
	/**
	 * Aggiunge una colonna con il nome e l'etichettatore specificati lasciando il resto come default.
	 * @param columnName il nome della colonna.
	 * @param etichettatore l'etichettatore che si occupera' di riempire il contenuto delle celle nella colonna.
	 */
	protected void aggiungiColonna(String columnName, AlberoEtichettatore etichettatore) {
		aggiungiColonna(columnName, DEFAULT_WIDTH, SWT.NONE, etichettatore);
	}
	
	protected void aggiungiColonna(String columnName, int width, int style, AlberoEtichettatore etichettatore) {
		TreeViewerColumn treeColumn = new TreeViewerColumn(this, style);
		TreeColumn column = treeColumn.getColumn();
		column.setText(columnName);
		column.setWidth(width);
		
		
		if (etichettatore == null) {
			treeColumn.setLabelProvider(new ColumnLabelProvider());
		} else {
//			etichettatore.setColumnIndex(indiceColonna);
			treeColumn.setLabelProvider(new DelegatingStyledCellLabelProvider(etichettatore));
		}
		
//		if (sorter != null)
//			tblclmn.addSelectionListener(new ListenerColonna<T>(indiceColonna, tblclmn, getTable(), sorter, this));
	}
	
	protected void aggiungiListenerBase() {
		
		TreeListener listener = new TreeListener() {

			@Override
			public void treeCollapsed(TreeEvent e) {
				aggiustaLarghezzaColonne();				
			}

			@Override
			public void treeExpanded(TreeEvent e) {
				aggiustaLarghezzaColonne();				
			}
			
		};
		tree.addTreeListener(listener);
		
		
		tree.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				if(((e.stateMask & SWT.CTRL) == SWT.CTRL) && (e.keyCode == 'c')) {
					CopiaSelezione();
                }
			}
		});
	}
	
	/**
	 * Aggiunge il menù base della tabella.
	 */
	protected void aggiungiMenuBase() {
	    copy = new MenuItem(menuPopup, SWT.PUSH);
	    copy.setText("Copia tabella");
	    copy.setImage(Immagine.COPIA_16X16.getImage());
	    copy.addListener(SWT.Selection, new Listener() {
	    	public void handleEvent(Event event) {
	    		CopiaSelezione();
	    	}
	    });	
	}
	
	/**
	 * Restituisce una stringa con tutto il testo della tabella.
	 * @return
	 */
	protected String getTreeText() {
		StringBuilder textData = new StringBuilder();
		//Aggiungo i nomi delle colonne
		int columns = tree.getColumnCount();
		for (int columnIndex = 0; columnIndex < columns; columnIndex++) {
			textData.append(tree.getColumn(columnIndex).getText());
			textData.append(TAB);
		}
		textData.setLength(textData.length() - 1);
		textData.append(NEW_LINE);
		//Aggiungo il contenuto selezionato
		for (TreeItem item : tree.getItems()) {
			for (int index = 0; index < columns; index++) {
				textData.append(item.getText(index));
				textData.append(TAB);
			}
			textData.setLength(textData.length() - 1);
			textData.append(NEW_LINE);
		}
		textData.setLength(textData.length() - 1);
		return textData.toString();
	}
	
	protected void CopiaSelezione() {
		String textData = getTreeText();
		Clipboard clipboard = new Clipboard(Display.getDefault());
		TextTransfer textTransfer = TextTransfer.getInstance();
		Transfer[] transfers = new Transfer[] { textTransfer };
		Object[] data = new Object[] { textData };
		clipboard.setContents(data, transfers);
		clipboard.dispose();
		System.out.println("Testo copiato.");
	}
	
	protected void copiaSelezioneConRichText() {
		String textData = getTreeText();
		Clipboard clipboard = new Clipboard(Display.getDefault());
		String rtfData = "{\\rtf1\\b\\i " + textData + "}";
		RTFTransfer rtfTransfer = RTFTransfer.getInstance();
		TextTransfer textTransfer = TextTransfer.getInstance();
		Transfer[] transfers = new Transfer[] { textTransfer, rtfTransfer };
		Object[] data = new Object[] { textData, rtfData };
		clipboard.setContents(data, transfers);
		clipboard.dispose();
		System.out.println("Testo ricco copiato.");
	}

}
