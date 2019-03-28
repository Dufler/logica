package it.ltc.logica.amministrazione.gui.commesse.elements.preferenze;

import java.util.LinkedList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;

import it.ltc.logica.common.controller.fatturazione.ControllerAmbitiFatturazione;
import it.ltc.logica.common.controller.fatturazione.ControllerPreferenzeFatturazione;
import it.ltc.logica.database.model.centrale.fatturazione.AmbitoFattura;
import it.ltc.logica.database.model.centrale.fatturazione.PreferenzeFatturazione;
import it.ltc.logica.gui.common.dialogs.fatturazione.DialogPreferenzeFatturazione;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.TabellaCRUD;
import it.ltc.logica.utilities.variabili.Permessi;

public class TabellaPreferenzeFatturazione extends TabellaCRUD<PreferenzeFatturazione> {
	
	private final int commessa;
	private int ambitoSelezionato;
	protected MenuItem insertAmbiti;

	public TabellaPreferenzeFatturazione(Composite parent, int commessa) {
		super(parent, STILE_SELEZIONE_SINGOLA);
		
		this.commessa = commessa;
		
		aggiungiMenuAmbiti();
	}
	
	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Ambito", 120, 0);
	}
	
	public void setAmbitoSelezionato(int ambito) {
		ambitoSelezionato = ambito;
	}
	
	protected List<AmbitoFattura> getPreferenzeDisponibili() {
		//Trovo gli ambiti mancanti
		ControllerAmbitiFatturazione controllerAmbiti = ControllerAmbitiFatturazione.getInstance();
		ControllerPreferenzeFatturazione controllerPreferenze = ControllerPreferenzeFatturazione.getInstance();
		List<PreferenzeFatturazione> preferenze = controllerPreferenze.getPreferenzePerCommessa(commessa);
		List<AmbitoFattura> preferenzeDisponibili = new LinkedList<>();
		for (AmbitoFattura ambito : controllerAmbiti.getAmbiti()) {
			//Controllo se l'ambito è già stato registrato
			boolean registrato = false;
			for (PreferenzeFatturazione preferenza : preferenze) {
				if (preferenza.getAmbito() == ambito.getId()) {
					registrato = true;
					break;
				}
			}
			if (!registrato)
				preferenzeDisponibili.add(ambito);
		}
		return preferenzeDisponibili;
	}
	
	private void aggiungiMenuAmbiti() {
		if (insertAmbiti != null)
			insertAmbiti.dispose();
		boolean permesso = isPermesso();
		if (permesso) {
			insertAmbiti = new MenuItem(menuPopup, SWT.CASCADE);
			insertAmbiti.setText("Nuovo...");
			Menu menuAmbiti = new Menu(insertAmbiti);
			insertAmbiti.setMenu(menuAmbiti);
			
			List<AmbitoFattura> ambitiDisponibili = getPreferenzeDisponibili();
			for (AmbitoFattura ambito : ambitiDisponibili) {
				MenuItem item = new MenuItem(menuAmbiti, SWT.PUSH);
				item.setText(ambito.getNome());
				item.addListener(SWT.Selection, new Listener() {
			    	public void handleEvent(Event event) {
			    		//apriDialogInserimento(ambito.getId());
			    		ambitoSelezionato = ambito.getId();
			    		apriDialog(null);
			    	}
			    });
			}
			
		}
	}

	@Override
	protected void aggiungiMenu(Menu menu) {
		//Elimino la voce generica, la riampiazzo con quella contenuta nel metodo aggiungiMenuAmbiti.
		if (insert != null) insert.dispose();
	}

	@Override
	protected Ordinatore<PreferenzeFatturazione> creaOrdinatore() {
		return new OrdinatorePreferenzeFatturazione();
	}

	@Override
	protected boolean eliminaElemento(PreferenzeFatturazione elemento) {
		boolean elimina = ControllerPreferenzeFatturazione.getInstance().elimina(elemento);
		return elimina;
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.AMMINSTRAZIONE_PREFERENZE_FATTURAZIONE_CUD.getID();
	}

	@Override
	protected DialogPreferenzeFatturazione creaDialog(PreferenzeFatturazione elemento) {
		DialogPreferenzeFatturazione dialog = elemento != null ? new DialogPreferenzeFatturazione(elemento) : new DialogPreferenzeFatturazione(null, commessa, ambitoSelezionato);;
		return dialog;
	}

	@Override
	public void aggiornaContenuto() {
		setElementi(ControllerPreferenzeFatturazione.getInstance().getPreferenzePerCommessa(commessa));
		aggiungiMenuAmbiti();
	}

	@Override
	protected Etichettatore<PreferenzeFatturazione> creaEtichettatore() {
		return new EtichettatorePreferenzeFatturazione();
	}

	@Override
	protected ModificatoreValoriCelle<PreferenzeFatturazione> creaModificatore() {
		return null;
	}

}
