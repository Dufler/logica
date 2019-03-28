package it.ltc.logica.database.model.centrale.ordini;

import java.util.LinkedList;
import java.util.List;

import it.ltc.logica.database.model.centrale.ordini.RisultatoAssegnazioneRigaOrdine.StatoAssegnazioneRiga;

/**
 * Questa classe modella un JSON che contiene le informazioni sull'assegnazione di un ordine.
 * @author Damiano
 *
 */
public class RisultatoAssegnazioneOrdine {
	
	public enum StatoAssegnazione { 
		
		OK("Ok"), 
		PARZIALE("Parziale"), 
		NONDEFINITA("Errore");
		
		private final String descrizione;
		
		private StatoAssegnazione(String descrizione) {
			this.descrizione = descrizione;
		}
		
		@Override
		public String toString() {
			return descrizione;
		}
	}
	
	private OrdineTestata ordine;
	private StatoAssegnazione stato;
	private List<RisultatoAssegnazioneRigaOrdine> assegnazioni;
	
	public RisultatoAssegnazioneOrdine() {}
	
	/**
	 * Indica se è possibile prelevare almeno un pezzo.
	 */
	public boolean isPrelevabile() {
		boolean prelevabile = false;
		for (RisultatoAssegnazioneRigaOrdine assegnazione : assegnazioni) {
			if (assegnazione.getStato() == StatoAssegnazioneRiga.PRELIEVO) {
				prelevabile = true;
				break;
			}
		}
		return prelevabile;
	}
	
	/**
	 * Restituisce la lista degli elementi d'assegnazione che sono prelevabili.
	 */
	public List<RisultatoAssegnazioneRigaOrdine> getPrelevabili() {
		List<RisultatoAssegnazioneRigaOrdine> prelevabili = new LinkedList<>();
		for (RisultatoAssegnazioneRigaOrdine assegnazione : assegnazioni) {
			if (assegnazione.getStato() == StatoAssegnazioneRiga.PRELIEVO) {
				prelevabili.add(assegnazione);
			}
		}
		return prelevabili;
	}
	
	/**
	 * Indica se c'è almeno un pezzo a scorta.
	 */
	public boolean isAScorta() {
		boolean aScorta = false;
		for (RisultatoAssegnazioneRigaOrdine assegnazione : assegnazioni) {
			if (assegnazione.getStato() == StatoAssegnazioneRiga.SCORTA) {
				aScorta = true;
				break;
			}
		}
		return aScorta;
	}
	
	/**
	 * Restituisce la lista degli elementi d'assegnazione che sono scorte.
	 */
	public List<RisultatoAssegnazioneRigaOrdine> getScorte() {
		List<RisultatoAssegnazioneRigaOrdine> scorte = new LinkedList<>();
		for (RisultatoAssegnazioneRigaOrdine assegnazione : assegnazioni) {
			if (assegnazione.getStato() == StatoAssegnazioneRiga.SCORTA) {
				scorte.add(assegnazione);
			}
		}
		return scorte;
	}
	
	/**
	 * Indica se c'è almeno un pezzo non ubicato.
	 */
	public boolean isNonUbicato() {
		boolean nonUbicato = false;
		for (RisultatoAssegnazioneRigaOrdine assegnazione : assegnazioni) {
			if (assegnazione.getStato() == StatoAssegnazioneRiga.NONUBICATO) {
				nonUbicato = true;
				break;
			}
		}
		return nonUbicato;
	}
	
	/**
	 * Restituisce la lista degli elementi d'assegnazione che non sono ubicati.
	 */
	public List<RisultatoAssegnazioneRigaOrdine> getNonUbicati() {
		List<RisultatoAssegnazioneRigaOrdine> nonUbicati = new LinkedList<>();
		for (RisultatoAssegnazioneRigaOrdine assegnazione : assegnazioni) {
			if (assegnazione.getStato() == StatoAssegnazioneRiga.NONUBICATO) {
				nonUbicati.add(assegnazione);
			}
		}
		return nonUbicati;
	}
	
	/**
	 * Indica se c'è almeno un pezzo a scorta.
	 */
	public boolean isNonPresente() {
		boolean nonUbicato = false;
		for (RisultatoAssegnazioneRigaOrdine assegnazione : assegnazioni) {
			if (assegnazione.getStato() == StatoAssegnazioneRiga.NONPRESENTE) {
				nonUbicato = true;
				break;
			}
		}
		return nonUbicato;
	}
	
	/**
	 * Restituisce la lista degli elementi d'assegnazione che sono scorte.
	 */
	public List<RisultatoAssegnazioneRigaOrdine> getNonPresenti() {
		List<RisultatoAssegnazioneRigaOrdine> nonUbicati = new LinkedList<>();
		for (RisultatoAssegnazioneRigaOrdine assegnazione : assegnazioni) {
			if (assegnazione.getStato() == StatoAssegnazioneRiga.NONPRESENTE) {
				nonUbicati.add(assegnazione);
			}
		}
		return nonUbicati;
	}

	public OrdineTestata getOrdine() {
		return ordine;
	}

	public void setOrdine(OrdineTestata ordine) {
		this.ordine = ordine;
	}

	public StatoAssegnazione getStato() {
		return stato;
	}

	public void setStato(StatoAssegnazione stato) {
		this.stato = stato;
	}

	public List<RisultatoAssegnazioneRigaOrdine> getAssegnazioni() {
		return assegnazioni;
	}

	public void setAssegnazioni(List<RisultatoAssegnazioneRigaOrdine> assegnazioni) {
		this.assegnazioni = assegnazioni;
	}

}
