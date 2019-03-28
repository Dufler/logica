package it.ltc.logica.ufficio.gui.uscite.elements.assegnazione;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;

import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.ordini.RisultatoAssegnazioneOrdine;
import it.ltc.logica.gui.decoration.Immagine;
import it.ltc.logica.gui.dialog.DialogSemplice;

public class DialogStatoAssegnazione extends DialogSemplice {
	
	private static final String title = "Stato assegnazione ordine";
	
	private TabellaRisultatoAssegnazione tabella;
	
	private final Commessa commessa;
	private final RisultatoAssegnazioneOrdine assegnazione;
	
	private Text textLista;
	private Text textRiferimento;
	private CompositeControlliAssegnazione compositeControlli;

	public DialogStatoAssegnazione(Commessa commessa, RisultatoAssegnazioneOrdine assegnazione) {
		super(title, Immagine.WIZARD_16X16.getImage(), true);
		this.commessa = commessa;
		this.assegnazione = assegnazione;
	}

	@Override
	protected void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(1, false));
		
		Composite compositeDatiOrdine = new Composite(container, SWT.NONE);
		compositeDatiOrdine.setLayout(new GridLayout(2, false));
		
		Label lblDatiOrdine = new Label(compositeDatiOrdine, SWT.NONE);
		lblDatiOrdine.setLayoutData(new GridData(SWT.LEFT, SWT.CENTER, false, false, 2, 1));
		lblDatiOrdine.setText("Dati ordine: ");
		
		Label lblLista = new Label(compositeDatiOrdine, SWT.NONE);
		lblLista.setSize(30, 15);
		lblLista.setText("Lista: ");
		
		textLista = new Text(compositeDatiOrdine, SWT.BORDER);
		textLista.setEditable(false);
		textLista.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		textLista.setSize(0, 21);
		
		Label lblRiferimento = new Label(compositeDatiOrdine, SWT.NONE);
		lblRiferimento.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblRiferimento.setText("Riferimento: ");
		
		textRiferimento = new Text(compositeDatiOrdine, SWT.BORDER);
		textRiferimento.setEditable(false);
		textRiferimento.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		
		tabella = new TabellaRisultatoAssegnazione(container);
		Tree tree = tabella.getTree();
		GridData layout = new GridData(SWT.LEFT, SWT.TOP, false, false, 1, 1);
		layout.heightHint = 400;
		tree.setLayoutData(layout);
		
		compositeControlli = new CompositeControlliAssegnazione(commessa, null, container);
		compositeControlli.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, false, false, 1, 1));
		
		
		setDati();
	}

	@Override
	protected void checkElementiGrafici() {
		//DO NOTHING!
	}
	
	private void setDati() {
		//Dati ordine
		textLista.setText(assegnazione.getOrdine().getNumeroLista());
		textRiferimento.setText(assegnazione.getOrdine().getRiferimento());
		//Dati assegnazione
		List<RisultatoAssegnazioneOrdine> list = new LinkedList<>();
		list.add(assegnazione);
		tabella.setElementi(list);
		compositeControlli.setRisultati(list);
	}

}
