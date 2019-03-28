package it.ltc.logica.common.controller.fatturazione;

import java.util.LinkedList;
import java.util.List;

import it.ltc.logica.common.controller.ControllerCRUD;
import it.ltc.logica.database.model.centrale.fatturazione.PreferenzeFatturazione;

public class ControllerPreferenzeFatturazione extends ControllerCRUD<PreferenzeFatturazione> {
	
	private static final String title = "Preferenze di Fatturazione";
	private static final String resource = "preferenzefatturazione";
	
	private static ControllerPreferenzeFatturazione instance;
	
	private final List<PreferenzeFatturazione> preferenze;

	private ControllerPreferenzeFatturazione() {
		super(title, resource, PreferenzeFatturazione[].class);
		preferenze = new LinkedList<>();
		caricaDati();
	}

	public static ControllerPreferenzeFatturazione getInstance() {
		if (instance == null) {
			instance = new ControllerPreferenzeFatturazione();
		}
		return instance;
	}
	
	public List<PreferenzeFatturazione> getPreferenze() {
		return preferenze;
	}
	
	/**
	 * Restituisce tutte le preferenze di fatturazione salvate per una commessa.
	 * @param commessa la commessa selezionata
	 * @return tutt le preferenze legate alla commessa.
	 */
	public List<PreferenzeFatturazione> getPreferenzePerCommessa(int commessa) {
		List<PreferenzeFatturazione> preferenzePerCommessa = new LinkedList<>();
		for (PreferenzeFatturazione preferenza : preferenze) {
			if (preferenza.getCommessa() == commessa)
				preferenzePerCommessa.add(preferenza);
		}
		return preferenzePerCommessa;
	}
	
	/**
	 * Restituisce la preferenza di fatturazione per la commessa specificata nell'ambito specificato.
	 * @param commessa la commessa selezionata
	 * @param ambito l'ambito selezionato
	 * @return le preferenze di fatturazione.
	 */
	public PreferenzeFatturazione getPreferenzePerCommessaSuAmbito(int commessa, int ambito) {
		PreferenzeFatturazione preferenzaCommessaAmbito = null;
		for (PreferenzeFatturazione preferenza : preferenze) {
			if (preferenza.getCommessa() == commessa && preferenza.getAmbito() == ambito) {
				preferenzaCommessaAmbito = preferenza;
				break;
			}
		}
		return preferenzaCommessaAmbito;
	}

	@Override
	protected boolean aggiornaInfoTuttiDati(List<PreferenzeFatturazione> lista) {
		preferenze.clear();
		preferenze.addAll(lista);
		return true;
	}

	@Override
	protected void aggiornaInfoInserimento(PreferenzeFatturazione object, PreferenzeFatturazione nuovo) {
		preferenze.add(nuovo);
	}

	@Override
	protected void aggiornaInfoElemento(PreferenzeFatturazione object) {
		//TODO - ricarica i dati
	}

	@Override
	protected void aggiornaInfoEliminazione(PreferenzeFatturazione preferenzeFatturazione) {
		preferenze.remove(preferenzeFatturazione);
	}

}
