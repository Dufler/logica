package it.ltc.logica.trasporti.calcolo.algoritmi;

import java.util.LinkedList;
import java.util.List;

import it.ltc.logica.common.calcolo.algoritmi.Calcolo;
import it.ltc.logica.common.calcolo.algoritmi.Calcolo.Tipo;
import it.ltc.logica.common.calcolo.algoritmi.MVoceDiListino.Scopo;
import it.ltc.logica.common.calcolo.algoritmi.ModelDaCalcolare;
import it.ltc.logica.common.calcolo.algoritmi.VoceCalcolata;
import it.ltc.logica.common.controller.trasporti.ControllerContrassegni;
import it.ltc.logica.common.controller.trasporti.ControllerIndirizzi;
import it.ltc.logica.common.controller.trasporti.ControllerIndirizziSimulazione;
import it.ltc.logica.database.model.centrale.indirizzi.Indirizzo;
import it.ltc.logica.database.model.centrale.indirizzi.IndirizzoSimulazione;
import it.ltc.logica.database.model.centrale.trasporti.Assicurazione;
import it.ltc.logica.database.model.centrale.trasporti.Contrassegno;
import it.ltc.logica.database.model.centrale.trasporti.Giacenza;
import it.ltc.logica.database.model.centrale.trasporti.ParticolaritaSpedizione;
import it.ltc.logica.database.model.centrale.trasporti.Spedizione;
import it.ltc.logica.database.model.centrale.trasporti.SpedizioneSimulazione;
import it.ltc.logica.database.model.centrale.trasporti.Spedizione.Fatturazione;
import it.ltc.logica.trasporti.calcolo.ambiti.IAmbitoTrasporto;

/**
 * Model che ingloba tutti i dati relativi a una spedizione.
 * Viene utilizzato per il calcolo del costo di spedizioni, resi, ritiri e giacenze.
 * @author Damiano Bellucci - damiano.bellucci@gmail.com
 *
 */
public class SpedizioneModel extends ModelDaCalcolare {
	
	private static final ControllerIndirizziSimulazione controllerIndirizziSimulazione = ControllerIndirizziSimulazione.getInstance();
	private static final ControllerIndirizzi controllerIndirizzi = ControllerIndirizzi.getInstance();
	private static final ControllerContrassegni controllerContrassegni = ControllerContrassegni.getInstance();
	
	private String label;
	
	private int coloreScrittaTabella;
	
	private boolean fatturazioneAnnullata;
	
	private Spedizione spedizione;
	private Contrassegno contrassegno;
	private Assicurazione assicurazione;
	private Indirizzo destinazione;
	private Indirizzo partenza;
	private Indirizzo destinazioneGiacenza;
	private ParticolaritaSpedizione particolarita;
	private Giacenza giacenza;
	
	private Calcolo preventivoRicavo;
	private Calcolo preventivoCosto;
	
	private boolean datiPerCalcoloCompleti;
	private boolean datiPezzi;
	private boolean datiColli;
	private boolean datiPeso;
	private boolean datiVolume;

	public SpedizioneModel(Spedizione s, Contrassegno c, Assicurazione a, Indirizzo d, Indirizzo p, Indirizzo dg, ParticolaritaSpedizione ps, Giacenza g) {
		spedizione = s;
		contrassegno = c;
		assicurazione = a;
		destinazione = d;
		partenza = p;
		destinazioneGiacenza = dg;
		particolarita = ps;
		giacenza = g;
		setLabel("");
		setColoreScrittaTabella(2);
		verificaDatiFatturazione();
	}
	
	/**
	 * Verifica che i dati di fatturazione siano completi e salva l'esito in variabili d'appoggio interne all'oggetto.
	 */
	public void verificaDatiFatturazione() {
		Double peso = spedizione.getPeso();
		Double volume = spedizione.getVolume();
		Integer pezzi = spedizione.getPezzi();
		Integer colli = spedizione.getColli();
		datiPezzi = pezzi != null && pezzi > 0;
		datiColli = colli != null && colli > 0;
		datiPeso = peso != null && peso > 0;
		datiVolume = volume != null && volume > 0;
		datiPerCalcoloCompleti = datiPezzi && datiColli && datiPeso && datiVolume;
	}
	
	@Override
	public void resettaCalcoli() {
		mappaCalcoli.clear();
		if (preventivoRicavo != null)
			preventivoRicavo.pulisciVoci();
		if (preventivoCosto != null)
			preventivoCosto.pulisciVoci();
	}
	
	/**
	 * Genera un model a partire dai dati di una spedizione.
	 * @param ss la spedizione da cui recuperare i dati.
	 * @return il model per effettuare i calcoli.
	 */
	public static SpedizioneModel caricaSpedizione(Spedizione s) {
		SpedizioneModel spedizione = null;
		if (s != null) {
			Contrassegno c = caricaContrassegno(s);
			Assicurazione a = caricaAssicurazione(s);
			Indirizzo d = caricaIndirizzo(s.getIndirizzoDestinazione());
			Indirizzo p = caricaIndirizzo(s.getIndirizzoPartenza());
			ParticolaritaSpedizione ps = caricaParticolarita(s);
			Giacenza g = null; //caricaPrimaGiacenza(s);
			Indirizzo dg = g != null ? caricaIndirizzo(g.getIdDestinatario()) : null;
			spedizione = new SpedizioneModel(s, c, a, d, p, dg, ps, g);
		}
		return spedizione;
	}
	
	/**
	 * Genera un model a partire dai dati di una spedizione.
	 * @param ss la spedizione da cui recuperare i dati.
	 * @return il model per effettuare i calcoli.
	 */
	public static SpedizioneModel caricaSpedizioneSimulazione(SpedizioneSimulazione s) {
		SpedizioneModel spedizione = null;
		if (s != null) {
			Contrassegno c = caricaContrassegnoSimulazione(s);
			Assicurazione a = null;
			Indirizzo d = caricaIndirizzoSimulazione(s.getIndirizzoDestinazione());
			Indirizzo p = caricaIndirizzoSimulazione(s.getIndirizzoPartenza());
			ParticolaritaSpedizione ps = null;
			Giacenza g = null; //caricaPrimaGiacenza(s);
			Indirizzo dg = g != null ? caricaIndirizzoSimulazione(g.getIdDestinatario()) : null;
			spedizione = new SpedizioneModel(convertiOggetto(s), c, a, d, p, dg, ps, g);
		}
		return spedizione;
	}
	
	public static Spedizione convertiOggetto(SpedizioneSimulazione entity) {
		Spedizione spedizione = new Spedizione();
		spedizione.setAssicurazione(entity.getAssicurazione());
		spedizione.setCodiceCliente(entity.getCodiceCliente());
		spedizione.setColli(entity.getColli());
		spedizione.setContrassegno(entity.getContrassegno());
		spedizione.setCosto(entity.getCosto());
		spedizione.setDataPartenza(entity.getDataPartenza());
		spedizione.setDatiCompleti(entity.getDatiCompleti());
		spedizione.setFatturazione(Fatturazione.NON_FATTURABILE);
		spedizione.setGiacenza(entity.getGiacenza());
		spedizione.setIdCommessa(entity.getIdCommessa());
		spedizione.setIdCorriere(entity.getIdCorriere());
		spedizione.setIdDocumento(entity.getIdDocumento());
		spedizione.setIndirizzoDestinazione(entity.getIndirizzoDestinazione());
		spedizione.setIndirizzoPartenza(entity.getIndirizzoPartenza());
		spedizione.setInRitardo(entity.getInRitardo());
		spedizione.setLetteraDiVettura(entity.getLetteraDiVettura());
		spedizione.setNote(entity.getNote());
		spedizione.setParticolarita(entity.getParticolarita());
		spedizione.setPeso(entity.getPeso());
		spedizione.setPezzi(entity.getPezzi());
		spedizione.setRagioneSocialeDestinatario(entity.getRagioneSocialeDestinatario());
		spedizione.setRicavo(entity.getRicavo());
		spedizione.setRiferimentoCliente(entity.getRiferimentoCliente());
		spedizione.setRiferimentoMittente(entity.getRiferimentoMittente());
		spedizione.setServizio(entity.getServizio());
		spedizione.setStato(entity.getStato());
		spedizione.setTipo(entity.getTipo());
		spedizione.setValoreMerceDichiarato(entity.getValoreMerceDichiarato());
		spedizione.setVolume(entity.getVolume());
		return spedizione;
	}

	/**
	 * Genera un model a partire dai dati di una spedizione storica.
	 * @param ss la spedizione storica da cui recuperare i dati.
	 * @return il model per effettuare i calcoli.
	 */
	public static SpedizioneModel caricaSpedizione(Spedizione s, Giacenza g) {
		SpedizioneModel spedizione = null;
		if (s != null) {
			Contrassegno c = caricaContrassegno(s);
			Assicurazione a = caricaAssicurazione(s);
			Indirizzo d = caricaIndirizzo(s.getIndirizzoDestinazione());
			Indirizzo p = caricaIndirizzo(s.getIndirizzoPartenza());
			ParticolaritaSpedizione ps = caricaParticolarita(s);
			Indirizzo dg = caricaIndirizzo(g.getIdDestinatario());
			spedizione = new SpedizioneModel(s, c, a, d, p, dg, ps, g);
		}
		return spedizione;
	}
	
	private static Indirizzo caricaIndirizzo(Integer id) {
		Indirizzo destinatario = id != null ? controllerIndirizzi.getIndirizzo(id) : null;
		return destinatario;
	}
	
	private static Indirizzo caricaIndirizzoSimulazione(Integer id) {
		IndirizzoSimulazione entity = id != null ? controllerIndirizziSimulazione.getIndirizzo(id) : null;
		Indirizzo destinatario = new Indirizzo();
		destinatario.setCap(entity.getCap());
		destinatario.setConsegnaAlPiano(entity.getConsegnaAlPiano());
		destinatario.setConsegnaAppuntamento(entity.getConsegnaAppuntamento());
		destinatario.setConsegnaGdo(entity.getConsegnaGdo());
		destinatario.setConsegnaPrivato(entity.getConsegnaPrivato());
		destinatario.setEmail(entity.getEmail());
		destinatario.setIndirizzo(entity.getIndirizzo());
		destinatario.setLocalita(entity.getLocalita());
		destinatario.setNazione(entity.getNazione());
		destinatario.setProvincia(entity.getProvincia());
		destinatario.setRagioneSociale(entity.getRagioneSociale());
		destinatario.setTelefono(entity.getTelefono());	
		return destinatario;
	}
	
	private static Contrassegno caricaContrassegno(Spedizione spedizione) {
		Contrassegno contrassegno = spedizione.getContrassegno() ? controllerContrassegni.getContrassegno(spedizione.getId()) : null;
		return contrassegno;
	}
	
	private static Contrassegno caricaContrassegnoSimulazione(SpedizioneSimulazione spedizione) {
		Contrassegno contrassegno = spedizione.getContrassegno() ? controllerContrassegni.getContrassegno(spedizione.getId()) : null;
		return contrassegno;
	}
	
	private static Assicurazione caricaAssicurazione(Spedizione s) {
		Assicurazione a = null;
		//FIXME - C'è davvero bisogno di caricare questo dato?
		return a;
	}
	
	private static ParticolaritaSpedizione caricaParticolarita(Spedizione s) {
		ParticolaritaSpedizione ps = null;
		//FIXME - C'è davvero bisogno di caricare questo dato?
		return ps;
	}
	
//	private static Giacenza caricaPrimaGiacenza(Spedizione spedizione) {
//		Giacenza giacenza = null;
//		if (spedizione.getGiacenza() != null && spedizione.getGiacenza()) {
//			giacenza = controllerGiacenze.getPrimaGiacenza(spedizione);
//		}
//		return giacenza;
//	}
	
	public Spedizione getSpedizione() {
		return spedizione;
	}
	public void setSpedizione(Spedizione spedizione) {
		this.spedizione = spedizione;
	}
	public Contrassegno getContrassegno() {
		return contrassegno;
	}
	public void setContrassegno(Contrassegno contrassegno) {
		this.contrassegno = contrassegno;
	}
	public Assicurazione getAssicurazione() {
		return assicurazione;
	}
	public void setAssicurazione(Assicurazione assicurazione) {
		this.assicurazione = assicurazione;
	}

	public Indirizzo getDestinazione() {
		return destinazione;
	}

	public void setDestinazione(Indirizzo destinazione) {
		this.destinazione = destinazione;
	}

	public Indirizzo getPartenza() {
		return partenza;
	}

	public void setPartenza(Indirizzo partenza) {
		this.partenza = partenza;
	}

	public ParticolaritaSpedizione getParticolarita() {
		return particolarita;
	}

	public void setParticolarita(ParticolaritaSpedizione particolarita) {
		this.particolarita = particolarita;
	}

	public Giacenza getGiacenza() {
		return giacenza;
	}

	public void setGiacenze(Giacenza giacenza) {
		this.giacenza = giacenza;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public int getColoreScrittaTabella() {
		return coloreScrittaTabella;
	}

	public void setColoreScrittaTabella(int coloreScrittaTabella) {
		this.coloreScrittaTabella = coloreScrittaTabella;
	}

	public Calcolo getPreventivoRicavo() {
		return preventivoRicavo;
	}
	
	public void addCalcolo(Calcolo calcolo) {
		if (calcolo.getTipo() == Tipo.COSTO)
			this.preventivoCosto = calcolo;
		else if (calcolo.getTipo() == Tipo.RICAVO)
			this.preventivoRicavo = calcolo;
		mappaCalcoli.put(calcolo.getIdListino(), calcolo);
	}

	public Calcolo getPreventivoCosto() {
		return preventivoCosto;
	}

	public List<Calcolo> getPreventiviRicavo() {
		List<Calcolo> preventiviRicavo = new LinkedList<Calcolo>();
		for (Calcolo calcolo : getCalcoli()) {
			if (calcolo.getTipo() == Calcolo.Tipo.RICAVO)
				preventiviRicavo.add(calcolo);
		}
		return preventiviRicavo;
	}

	public List<Calcolo> getPreventiviCosto() {
		List<Calcolo> preventiviRicavo = new LinkedList<Calcolo>();
		for (Calcolo calcolo : getCalcoli()) {
			if (calcolo.getTipo() == Calcolo.Tipo.COSTO)
				preventiviRicavo.add(calcolo);
		}
		return preventiviRicavo;
	}
	
	public double getCostoTotale(Scopo scopo) {
		double totale = 0;
		Calcolo calcolo = getCalcoloRichiesto(scopo);
		if (calcolo != null)
			for (VoceCalcolata voce : calcolo.getVoci()) {
				totale += voce.getCosto();
			}
		return totale;
	}
	
	public double getCostoNolo(Scopo scopo) {
		double totale = 0;
		Calcolo calcolo = getCalcoloRichiesto(scopo);
		if (calcolo != null)
			for (VoceCalcolata voce : calcolo.getVoci()) {
				IAmbitoTrasporto.Tipo categoria = IAmbitoTrasporto.Tipo.valueOf(voce.getCategoriaAmbito());
				if (categoria == IAmbitoTrasporto.Tipo.NOLO_BASE || categoria == IAmbitoTrasporto.Tipo.NOLO_EXTRA)
					totale += voce.getCosto();
			}
		return totale;
	}
	
	public double getCostoNoloBase(Scopo scopo) {
		double totale = 0;
		Calcolo calcolo = getCalcoloRichiesto(scopo);
		if (calcolo != null)
			for (VoceCalcolata voce : calcolo.getVoci()) {
				IAmbitoTrasporto.Tipo categoria = IAmbitoTrasporto.Tipo.valueOf(voce.getCategoriaAmbito());
				if (categoria == IAmbitoTrasporto.Tipo.NOLO_BASE)
					totale += voce.getCosto();
			}
		return totale;
	}
	
	public double getCostoContrassegno(Scopo scopo) {
		double totale = 0;
		Calcolo calcolo = getCalcoloRichiesto(scopo);
		if (calcolo != null)
			for (VoceCalcolata voce : calcolo.getVoci()) {
				IAmbitoTrasporto.Tipo categoria = IAmbitoTrasporto.Tipo.valueOf(voce.getCategoriaAmbito());
				if (categoria == IAmbitoTrasporto.Tipo.CONTRASSEGNO)
					totale += voce.getCosto();
			}
		return totale;
	}
	
	public Calcolo getCalcoloRichiesto(Scopo scopo) {
		Calcolo calcolo;
		if (scopo == Scopo.COSTO)
			calcolo = preventivoCosto;
		else if (scopo == Scopo.RICAVO)
			calcolo = preventivoRicavo;
		else
			calcolo = null;
		return calcolo;
	}

	public Indirizzo getDestinatarioGiacenza() {
		return destinazioneGiacenza;
	}

	/**
	 * Restituisce un'indicazione sul fatto che i dati sulla spedizione siano sufficienti al corretto calcolo del preventivo.
	 * @return true se i valori sono tutti validi, false altrimenti
	 */
	public boolean isDatiPerCalcoloCompleti() {
		return datiPerCalcoloCompleti;
	}

	/**
	 * Restituisce un'indicazione se è possibile calcolare voci basate sul numero dei pezzi.
	 * @return true se i pezzi sono validi, false altrimenti
	 */
	public boolean isDatiPezzi() {
		return datiPezzi;
	}

	/**
	 * Restituisce un'indicazione se è possibile calcolare voci basate sul numero di colli.
	 * @return true se i colli sono validi, false altrimenti
	 */
	public boolean isDatiColli() {
		return datiColli;
	}

	/**
	 * Restituisce un'indicazione se è possibile calcolare voci basate sul peso.
	 * @return true se il peso è valido, false altrimenti
	 */
	public boolean isDatiPeso() {
		return datiPeso;
	}

	/**
	 * Restituisce un'indicazione se è possibile calcolare voci basate sul volume.
	 * @return true se il volume è valido, false altrimenti
	 */
	public boolean isDatiVolume() {
		return datiVolume;
	}

	public boolean isFatturazioneAnnullata() {
		return fatturazioneAnnullata;
	}

	public void setFatturazioneAnnullata(boolean fatturazioneAnnullata) {
		this.fatturazioneAnnullata = fatturazioneAnnullata;
	}

}
