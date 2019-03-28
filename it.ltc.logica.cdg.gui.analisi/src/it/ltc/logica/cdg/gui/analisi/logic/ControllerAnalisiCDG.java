package it.ltc.logica.cdg.gui.analisi.logic;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import it.ltc.database.dao.locali.CriteriSelezioneSpedizioni;
import it.ltc.logica.cdg.gui.analisi.model.AnalisiCommessa;
import it.ltc.logica.cdg.gui.analisi.model.ElementoBilancioCDG;
import it.ltc.logica.cdg.gui.analisi.model.RiepilogoGiornalieroCDG;
import it.ltc.logica.common.controller.cdg.ControllerCdgAssociazioni;
import it.ltc.logica.common.controller.cdg.ControllerCdgBudget;
import it.ltc.logica.common.controller.cdg.ControllerCdgBudgetPercentuali;
import it.ltc.logica.common.controller.cdg.ControllerCdgCostiRicaviCommesse;
import it.ltc.logica.common.controller.cdg.ControllerCdgCostiRicaviGenerici;
import it.ltc.logica.common.controller.cdg.ControllerCdgCostiRicaviGenericiDateValore;
import it.ltc.logica.common.controller.cdg.ControllerCdgCostiRicaviGenericiFasi;
import it.ltc.logica.common.controller.cdg.ControllerCdgEventi;
import it.ltc.logica.common.controller.cdg.ControllerCdgPezzi;
import it.ltc.logica.common.controller.cdg.ControllerCdgRiepilogoEventi;
import it.ltc.logica.common.controller.cdg.FiltroRiepilogo;
import it.ltc.logica.common.controller.sistema.ControllerCommesse;
import it.ltc.logica.common.controller.sistema.ControllerOperatori;
import it.ltc.logica.common.controller.trasporti.ControllerSpedizioni;
import it.ltc.logica.database.model.centrale.CategoriaMerceologica;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.Sede;
import it.ltc.logica.database.model.centrale.cdg.CdgBudget;
import it.ltc.logica.database.model.centrale.cdg.CdgBudgetPercentualiCosto;
import it.ltc.logica.database.model.centrale.cdg.CdgCostiRicaviGenerici;
import it.ltc.logica.database.model.centrale.cdg.CdgCostiRicaviGenerici.DriverRipartizione;
import it.ltc.logica.database.model.centrale.cdg.CdgCostiRicaviGenericiDateValore;
import it.ltc.logica.database.model.centrale.cdg.CdgCostiRicaviGenericiFase;
import it.ltc.logica.database.model.centrale.cdg.CdgCostoAssociazione;
import it.ltc.logica.database.model.centrale.cdg.CdgCostoRicavoCommessa;
import it.ltc.logica.database.model.centrale.cdg.CdgCostoRicavoCommessa.TipoBilancioCdg;
import it.ltc.logica.database.model.centrale.cdg.CdgEvento;
import it.ltc.logica.database.model.centrale.cdg.CdgEventoRiepilogo;
import it.ltc.logica.database.model.centrale.cdg.CdgFase;
import it.ltc.logica.database.model.centrale.cdg.CdgPezzo;
import it.ltc.logica.database.model.centrale.cdg.CdgPezzoEvento;
import it.ltc.logica.database.model.centrale.utenti.Operatore;

/**
 * Controller che permette di recuperare bilanci ed eventi specificando una commessa e un periodo.<br>
 * Tali dati vengono elaborati e raggruppati per giorno.
 * @author Damiano Bellucci - damiano.bellucci@gmail.com
 *
 */
public class ControllerAnalisiCDG {
	
	public static final String BUDGET_NON_PRESENTE = "BNP";

	private static ControllerAnalisiCDG instance;

	private final ControllerCdgRiepilogoEventi controller;
	private final ControllerCdgCostiRicaviCommesse controllerCommesse;
	private final ControllerCdgCostiRicaviGenerici controllerGeneriche;
	private final ControllerCdgCostiRicaviGenericiFasi controllerGenerichePerFasi;
	private final ControllerCdgCostiRicaviGenericiDateValore controllerGenerichePerDateValore;
	private final ControllerCdgPezzi controllerPezzi;
	private final ControllerCdgEventi controllerEventi;
	private final ControllerOperatori controllerOperatori;
	private final ControllerCdgAssociazioni controllerAssociazioni;
	
	private Sede sedeInAnalisi;
	//private final List<AnalisiCommessa> mappaPerCommessa;

	private ControllerAnalisiCDG() {
		controller = ControllerCdgRiepilogoEventi.getInstance();
		controllerCommesse = ControllerCdgCostiRicaviCommesse.getInstance();
		controllerGeneriche = ControllerCdgCostiRicaviGenerici.getInstance();
		controllerPezzi = ControllerCdgPezzi.getInstance();
		controllerEventi = ControllerCdgEventi.getInstance();
		controllerOperatori = ControllerOperatori.getInstance();
		controllerAssociazioni = ControllerCdgAssociazioni.getInstance();
		controllerGenerichePerFasi = ControllerCdgCostiRicaviGenericiFasi.getInstance();
		controllerGenerichePerDateValore = ControllerCdgCostiRicaviGenericiDateValore.getInstance();
	}

	public static ControllerAnalisiCDG getInstance() {
		if (instance == null) {
			instance = new ControllerAnalisiCDG();
		}
		return instance;
	}

	public HashMap<Integer, RiepilogoGiornalieroCDG> elaboraRiepilogoControlloTempi(Commessa commessa, Date inizio, Date fine) {
		// List<RiepilogoGiornalieroCDG> riepilogo = new LinkedList<>();
		FiltroRiepilogo filtro = new FiltroRiepilogo(commessa.getId(), inizio, fine);
		// Ottengo i costi e ricavi specifici per la commessa (es. extra)
		List<CdgCostoRicavoCommessa> costiRicaviCommessa = controllerCommesse.getRiepilogo(filtro);
		// Ottengo gli eventi
		List<CdgEventoRiepilogo> eventi = controller.getRiepilogo(filtro);
		// elaborazione suddivisa per giorno.
		HashMap<Integer, RiepilogoGiornalieroCDG> mappaPerGiorno = new HashMap<>();
		// Elaboro i bilanci
		for (CdgCostoRicavoCommessa bilancio : costiRicaviCommessa) {
			ElementoBilancioCDG elementoBilancio = creaElementoDaElementoCommessa(bilancio);
			int giorno = trovaGiorno(bilancio.getDataEmissione());
			if (!mappaPerGiorno.containsKey(giorno)) {
				RiepilogoGiornalieroCDG riepilogo = new RiepilogoGiornalieroCDG(bilancio.getDataEmissione());
				mappaPerGiorno.put(giorno, riepilogo);
			}
			RiepilogoGiornalieroCDG riepilogo = mappaPerGiorno.get(giorno);
			riepilogo.aggiungiElemento(elementoBilancio);
		}
		// Elaboro gli eventi
		for (CdgEventoRiepilogo evento : eventi) {
			ElementoBilancioCDG elementoEvento = creaElementoDaRiepilogoEvento(evento);
			int giorno = trovaGiorno(evento.getGiorno());
			if (!mappaPerGiorno.containsKey(giorno)) {
				RiepilogoGiornalieroCDG riepilogo = new RiepilogoGiornalieroCDG(evento.getGiorno());
				mappaPerGiorno.put(giorno, riepilogo);
			}
			RiepilogoGiornalieroCDG riepilogo = mappaPerGiorno.get(giorno);
			riepilogo.aggiungiElemento(elementoEvento);
		}
		return mappaPerGiorno;
	}
	
	/**
	 * Restituisce una lista di interi che rappresentano i giorni comprese tra le date specificate.
	 */
	private List<Integer> getIndiceDeiGiorni(Date inizio, Date fine) {
		GregorianCalendar start = new GregorianCalendar();
		start.setTime(inizio);
		GregorianCalendar end = new GregorianCalendar();
		end.setTime(fine);
		List<Integer> indiceDeiGiorni = new LinkedList<>();
		int startDay = start.get(Calendar.DAY_OF_YEAR);
		int endDay = end.get(Calendar.DAY_OF_YEAR) + 1;
		for (int index = startDay; index < endDay; index++) {
			indiceDeiGiorni.add(index);
		}
		return indiceDeiGiorni;
	}
	
	public List<AnalisiCommessa> elaboraRiepilogoControlloGestione(Sede sede, Date inizio, Date fine) {
		sedeInAnalisi = sede;
		List<AnalisiCommessa> mappaPerCommessa = new LinkedList<>();
		//Trovo tutte le commesse che afferiscono alla sede
		List<Commessa> commesse = ControllerCommesse.getInstance().getCommessePerSede(sede.getId());
		for (Commessa commessa : commesse) {
			// elaborazione suddivisa per giorno.
			HashMap<Integer, RiepilogoGiornalieroCDG> mappaPerGiorno = new HashMap<>();
			AnalisiCommessa analisi = new AnalisiCommessa(commessa, mappaPerGiorno, inizio, fine);
			mappaPerCommessa.add(analisi);
			FiltroRiepilogo filtro = new FiltroRiepilogo(commessa.getId(), inizio, fine);
			// Ottengo i bilanci
			List<CdgCostoRicavoCommessa> bilanci = controllerCommesse.getRiepilogo(filtro);
			// Ottengo gli eventi
			List<CdgEventoRiepilogo> eventi = controller.getRiepilogo(filtro);
			// Elaboro i bilanci
			for (CdgCostoRicavoCommessa bilancio : bilanci) {
				ElementoBilancioCDG elementoBilancio = creaElementoDaElementoCommessa(bilancio);
				int giorno = trovaGiorno(bilancio.getDataEmissione());
				if (!mappaPerGiorno.containsKey(giorno)) {
					RiepilogoGiornalieroCDG riepilogo = new RiepilogoGiornalieroCDG(bilancio.getDataEmissione());
					mappaPerGiorno.put(giorno, riepilogo);
				}
				RiepilogoGiornalieroCDG riepilogo = mappaPerGiorno.get(giorno);
				riepilogo.aggiungiElemento(elementoBilancio);
			}
			// Elaboro gli eventi
			for (CdgEventoRiepilogo evento : eventi) {
				ElementoBilancioCDG elementoEvento = creaElementoDaRiepilogoEvento(evento);
				int giorno = trovaGiorno(evento.getGiorno());
				if (!mappaPerGiorno.containsKey(giorno)) {
					RiepilogoGiornalieroCDG riepilogo = new RiepilogoGiornalieroCDG(evento.getGiorno());
					mappaPerGiorno.put(giorno, riepilogo);
				}
				RiepilogoGiornalieroCDG riepilogo = mappaPerGiorno.get(giorno);
				riepilogo.aggiungiElemento(elementoEvento);
			}
		}
		//Secondo ciclo, inputo i costi. Devo farlo successivamente a causa dei driver basati sui ricavi.
		//Trovo tutti i costi e ricavi generici
		Collection<CdgCostiRicaviGenerici> generici = controllerGeneriche.getCostiRicavi();
		for (CdgCostiRicaviGenerici generico : generici) {
			//Per ogni generico trovo le fasi su cui distribuirlo e tutti gli elementi che rientrano nelle date specificate.
			List<CdgCostiRicaviGenericiFase> fasi = controllerGenerichePerFasi.getPercentualiPerGenerico(generico.getId());
			List<CdgCostiRicaviGenericiDateValore> elementi = controllerGenerichePerDateValore.getDettagliPerGenerico(generico.getId(), inizio, fine, sede);
			//Per ogni elemento trovato vado a calcolare il costo effettivo suddiviso per fase e per giorno
			for (CdgCostiRicaviGenericiDateValore elementoDateValore : elementi) {
				double costoPerGiorno = calcolaCostoDivisoSuGiorni(elementoDateValore.getDataInizio(), elementoDateValore.getDataFine(), elementoDateValore.getValore()); 
				for (AnalisiCommessa analisi : mappaPerCommessa) {
					double moltiplicatoreDriver = calcolaMoltiplicatoreDriver(generico, analisi, mappaPerCommessa);
					double costoEffettivoPerGiorno = costoPerGiorno * moltiplicatoreDriver;
					for (Integer giorno : getIndiceDeiGiorni(inizio, fine)) {
						for (CdgCostiRicaviGenericiFase fase : fasi) {
							//Recupero il giorno di interesse
							GregorianCalendar gc = new GregorianCalendar();
							gc.set(Calendar.DAY_OF_YEAR, giorno);
							Date day = gc.getTime();
							//Se questo giorno è fuori dal range di date su cui spalmare lo salto
							if (day.after(elementoDateValore.getDataFine()) || day.before(elementoDateValore.getDataInizio()))
								continue;
							//Verifo di aver già dentro il report un elemento per questo giorno, se non fosse così lo creo.
							if (!analisi.getRiepilogoPerGiorno().containsKey(giorno)) {	
								RiepilogoGiornalieroCDG riepilogo = new RiepilogoGiornalieroCDG(day);
								analisi.getRiepilogoPerGiorno().put(giorno, riepilogo);
							}
							//Aggiungo il riepilogo su quel giorno con il costo aggiustato dalla dilazione sui giorni, dal driver etc.
							RiepilogoGiornalieroCDG riepilogo = analisi.getRiepilogoPerGiorno().get(giorno);
							double costoPerFase = costoEffettivoPerGiorno * fase.getPercentuale() / 100.0;
							ElementoBilancioCDG elemento = creaElementoDaGenerico(costoPerFase, generico.getNome(), fase.getFase());
							riepilogo.aggiungiElemento(elemento);
						}
					}
				}
			}
		}
		return mappaPerCommessa;
	}
	
	private double calcolaMoltiplicatoreDriver(CdgCostiRicaviGenerici generico, AnalisiCommessa analisi, List<AnalisiCommessa> mappaPerCommessa) {
		double moltiplicatoreDriver;
		DriverRipartizione driver = generico.getDriver();
		//FIXME - Scrivere gli ultimi driver.
		switch (driver) {
//			case COLLI : moltiplicatoreDriver =  getMoltiplicatoreDriverEquo(); break;
			case PEZZI : moltiplicatoreDriver = getMoltiplicatoreDriverPerPezzi(analisi, mappaPerCommessa); break;
			case RICAVI : moltiplicatoreDriver = getMoltiplicatoreDriverPerRicavi(analisi, mappaPerCommessa); break;
//			case SPAZI : moltiplicatoreDriver = getMoltiplicatoreDriverEquo(); break;
			case SPEDIZIONI : moltiplicatoreDriver = getMoltiplicatoreDriverPerSpedizioni(analisi, mappaPerCommessa); break;
			case TEMPO : moltiplicatoreDriver = getMoltiplicatoreDriverPerSecondi(analisi, mappaPerCommessa); break;
			default : moltiplicatoreDriver = getMoltiplicatoreDriverEquo();
		}
		return moltiplicatoreDriver;
	}
	
	private double getMoltiplicatoreDriverPerSecondi(AnalisiCommessa commessa, List<AnalisiCommessa> mappaPerCommessa) {
		double totaleSecondi = 0;
		double totaleSecondiCommessa = 0;
		for (RiepilogoGiornalieroCDG riepilogo : commessa.getRiepilogoPerGiorno().values()) {
			totaleSecondiCommessa += riepilogo.getTotaleSecondi();
		}
		for (AnalisiCommessa analisi : mappaPerCommessa) {
			for (RiepilogoGiornalieroCDG riepilogo : analisi.getRiepilogoPerGiorno().values()) {
				totaleSecondi += riepilogo.getTotaleSecondi();
			}
		}
		double moltiplicatore = totaleSecondiCommessa / totaleSecondi;
		return moltiplicatore;
	}
	
	private double getMoltiplicatoreDriverPerPezzi(AnalisiCommessa commessa, List<AnalisiCommessa> mappaPerCommessa) {
		double totalePezzi = 0;
		double totalePezziCommessa = 0;
		for (RiepilogoGiornalieroCDG riepilogo : commessa.getRiepilogoPerGiorno().values()) {
			totalePezziCommessa += riepilogo.getTotalePezzi();
		}
		for (AnalisiCommessa analisi : mappaPerCommessa) {
			for (RiepilogoGiornalieroCDG riepilogo : analisi.getRiepilogoPerGiorno().values()) {
				totalePezzi += riepilogo.getTotalePezzi();
			}
		}
		double moltiplicatore = totalePezziCommessa / totalePezzi;
		return moltiplicatore;
	}
	
	private double getMoltiplicatoreDriverPerSpedizioni(AnalisiCommessa commessa, List<AnalisiCommessa> mappaPerCommessa) {
		CriteriSelezioneSpedizioni criteri = new CriteriSelezioneSpedizioni();
		criteri.setCommessa(commessa.getCommessa().getId());
		criteri.setDataA(commessa.getFine());
		criteri.setDataDa(commessa.getInizio());
		double spedizioni = ControllerSpedizioni.getInstance().selezionaSpedizioni(criteri).size();
		double totaleSpedizioni = 0;
		for (AnalisiCommessa analisi : mappaPerCommessa) {
			CriteriSelezioneSpedizioni criteriAnalisi = new CriteriSelezioneSpedizioni();
			criteriAnalisi.setCommessa(analisi.getCommessa().getId());
			criteriAnalisi.setDataA(analisi.getFine());
			criteriAnalisi.setDataDa(analisi.getInizio());
			totaleSpedizioni += ControllerSpedizioni.getInstance().selezionaSpedizioni(criteriAnalisi).size();
		}
		double moltiplicatore = spedizioni / totaleSpedizioni;
		return moltiplicatore;
	}
	
	private double getMoltiplicatoreDriverPerRicavi(AnalisiCommessa commessa, List<AnalisiCommessa> mappaPerCommessa) {
		double ricavoCommessa = commessa.getTotaleRicavi();
		double totaleRicavi = 0;
		for (AnalisiCommessa analisi : mappaPerCommessa) {
				totaleRicavi += analisi.getTotaleRicavi();
		}
		double moltiplicatore = ricavoCommessa / totaleRicavi;
		return moltiplicatore;
	}
	
	/**
	 * Ripartisce il costo in maniera equa fra tutte le commesse afferenti alla sede in analisi.
	 */
	private double getMoltiplicatoreDriverEquo() {
		double equo = 1.0 / ControllerCommesse.getInstance().getCommessePerSede(sedeInAnalisi.getId()).size();
		return equo;
	}
	
	/**
	 * Calcola il costo per giorno a partire dal totale del generico e il periodo su cui spalmarlo.
	 */
//	private double calcolaCostoDivisoSuGiorni(CdgCostiRicaviGenericiDateValore elementoDateValore) {
//		GregorianCalendar inizio = new GregorianCalendar();
//		inizio.setTime(elementoDateValore.getDataInizio());
//		GregorianCalendar fine = new GregorianCalendar();
//		fine.setTime(elementoDateValore.getDataFine());
//		int giornoFine = fine.get(Calendar.DAY_OF_YEAR);
//		int differenzaAnni = fine.get(Calendar.YEAR) - inizio.get(Calendar.YEAR);
//		giornoFine += differenzaAnni * 365;
//		double durataInGiorni = giornoFine - inizio.get(Calendar.DAY_OF_YEAR);
//		double costoPerGiorno = elementoDateValore.getValore() / durataInGiorni;
//		return costoPerGiorno;
//	}
	
	private double calcolaCostoDivisoSuGiorni(Date dataInizio, Date dataFine, double valore) {
		double durataInGiorni = calcolaDifferenzaGiorni(dataInizio, dataFine);
		double costoPerGiorno = valore / durataInGiorni;
		return costoPerGiorno;
	}
	
	private double calcolaDifferenzaGiorni(Date dataInizio, Date dataFine) {
		GregorianCalendar inizio = new GregorianCalendar();
		inizio.setTime(dataInizio);
		GregorianCalendar fine = new GregorianCalendar();
		fine.setTime(dataFine);
		int giornoFine = fine.get(Calendar.DAY_OF_YEAR);
		int differenzaAnni = fine.get(Calendar.YEAR) - inizio.get(Calendar.YEAR);
		giornoFine += differenzaAnni * 365;
		double durataInGiorni = giornoFine - inizio.get(Calendar.DAY_OF_YEAR) + 1; //Fix: è stato aggiunto un più 1 perchè alcuni calcoli non sembrano corretti. Es. se scegliessi come date da oggi a oggi? Sarebbero 0 giorni o 1 giorno?
		return durataInGiorni;
	}

	private int trovaGiorno(Date dataEmissione) {
		GregorianCalendar gc = new GregorianCalendar();
		gc.setTime(dataEmissione);
		int giornoDellAnno = gc.get(Calendar.DAY_OF_YEAR);
		return giornoDellAnno;
	}
	
	private ElementoBilancioCDG creaElementoDaRiepilogoEvento(CdgEventoRiepilogo riepilogoEvento) {
		int fase = trovaFaseDaRiepilogoEvento(riepilogoEvento);
		double costoIpotetico = calcolaCostoIpoteticoPezzo(riepilogoEvento);
		double ricavoIpotetico = calcolaRicavoIpoteticoPezzo(riepilogoEvento);
		double costoReale = calcolaValoreRiepilogoEvento(riepilogoEvento);
		String origine = "Manodopera diretta";
		//String origine = fase == CdgFase.ID_EXTRA ? "Extra" : "Manodopera diretta"; //Ulteriore differenziazione per ufficio?
		//String origine = trovaOrigineDaRiepilogoEvento(riepilogoEvento);
		Integer pezzi = riepilogoEvento.getPezzi();
		Integer secondi = riepilogoEvento.getDurataTotale();
		ElementoBilancioCDG elemento = new ElementoBilancioCDG(fase, costoIpotetico, ricavoIpotetico, costoReale, origine, pezzi, secondi);
		return elemento;
	}
	
	private ElementoBilancioCDG creaElementoDaElementoCommessa(CdgCostoRicavoCommessa bilancio) {
		int fase = bilancio.getFase();
		double costoIpotetico = bilancio.getTipo() == TipoBilancioCdg.USCITA ? bilancio.getValore() : 0;
		double ricavoIpotetico = bilancio.getTipo() == TipoBilancioCdg.INGRESSO ? bilancio.getValore() : 0;
		String origine = bilancio.getDescrizione();
		ElementoBilancioCDG elemento = new ElementoBilancioCDG(fase, costoIpotetico, ricavoIpotetico, 0, origine, null, null);
		return elemento;
	}
	
	private ElementoBilancioCDG creaElementoDaGenerico(double costo, String origine, int fase) {
		ElementoBilancioCDG elemento = new ElementoBilancioCDG(fase, costo, 0, costo, origine, null, null);
		return elemento;
	}
	
	private double calcolaRicavoIpoteticoPezzo(CdgEventoRiepilogo riepilogoEvento) {
		double ricavoIpotetico;
		CdgEvento evento = controllerEventi.getEvento(riepilogoEvento.getEvento());
		//Se l'evento rientra nella fase logistica "Extra" oppure se i pezzi movimentati sono 0 allora imposto il ricavo come 0 senza eseguire calcoli
		if (evento.getFase() == CdgFase.ID_EXTRA || riepilogoEvento.getPezzi() == 0) {
			ricavoIpotetico = 0;
		} else {		
			//Recupero tutti i ricavi e spacchettamenti per la commessa.
			List<CdgPezzo> pezzi = controllerPezzi.getPezzoDaCommessa(riepilogoEvento.getCommessa());
			//Se la categoria merceologica è "NESSUNA" allora faccio una media ponderata dell'incidenza su tutte le categorie
			//Altrimenti trovo l'incidenza a pezza giusta in base alla categoria.
			if (evento.getCategoriaMerceologica().equals(CategoriaMerceologica.NESSUNA)) {
				double totaleIncidenzaAPezzo = 0;
				for (CdgPezzo pezzo : pezzi) {
					totaleIncidenzaAPezzo += calcolaIncidenzaDiRicavo(pezzo, riepilogoEvento);
				}
				ricavoIpotetico = totaleIncidenzaAPezzo / pezzi.size() * riepilogoEvento.getPezzi();
			} else {
				CdgPezzo pezzoGiusto = null;
				for (CdgPezzo pezzo : pezzi) {
					if (pezzo.getCategoriaMerceologica().equals(evento.getCategoriaMerceologica())) {
						pezzoGiusto = pezzo;
						break;
					}
				}
				//Se non è stata inserita la giusta associazione lancio un'eccezione!
				if (pezzoGiusto != null) {
					ricavoIpotetico = calcolaIncidenzaDiRicavo(pezzoGiusto, riepilogoEvento) * riepilogoEvento.getPezzi();
				} else {
					throw new RuntimeException("Non \u00E8 stata inserita la giusta associazione tra la commessa selezionata e la categoria merceologica: " + evento.getCategoriaMerceologica());
				}
			}
		}
		return ricavoIpotetico;
	}
	
	private double calcolaIncidenzaDiRicavo(CdgPezzo pezzo, CdgEventoRiepilogo evento) {
		double percentualeSpacchettamento = 0;
		for (CdgPezzoEvento spacchettamento : pezzo.getSpacchettamenti()) {
			if (spacchettamento.getEvento() == evento.getEvento()) {
				percentualeSpacchettamento = spacchettamento.getRicavo() / 100.0;
				break;
			}
		}
		double incidenzaDiRicavo = pezzo.getRicavo() * percentualeSpacchettamento;
		return incidenzaDiRicavo;
	}
	
	private double calcolaIncidenzaDiCosto(CdgPezzo pezzo, CdgEventoRiepilogo evento) {
		double percentualeSpacchettamento = 0;
		for (CdgPezzoEvento spacchettamento : pezzo.getSpacchettamenti()) {
			if (spacchettamento.getEvento() == evento.getEvento()) {
				percentualeSpacchettamento = spacchettamento.getCosto() / 100.0;
				break;
			}
		}
		return pezzo.getCosto() * percentualeSpacchettamento;
	}

	private double calcolaCostoIpoteticoPezzo(CdgEventoRiepilogo riepilogoEvento) {
		double costoIpotetico;
		CdgEvento evento = controllerEventi.getEvento(riepilogoEvento.getEvento());
		//Se l'evento rientra nella fase logistica "Extra" allora imposto il costo come 0 senza eseguire calcoli
		if (evento.getFase() == CdgFase.ID_EXTRA || riepilogoEvento.getPezzi() == 0) {
			costoIpotetico = 0;
		} else {
			//Recupero tutti i costi e spacchettamenti per la commessa.
			List<CdgPezzo> pezzi = controllerPezzi.getPezzoDaCommessa(riepilogoEvento.getCommessa());
			//Se la categoria merceologica è "NESSUNA" allora faccio una media ponderata dell'incidenza su tutte le categorie
			//Altrimenti trovo l'incidenza a pezza giusta in base alla categoria.
			if (evento.getCategoriaMerceologica().equals(CategoriaMerceologica.NESSUNA)) {
				double totaleIncidenzaAPezzo = 0;
				for (CdgPezzo pezzo : pezzi) {
					totaleIncidenzaAPezzo += calcolaIncidenzaDiCosto(pezzo, riepilogoEvento);
				}
				costoIpotetico = totaleIncidenzaAPezzo / pezzi.size() * riepilogoEvento.getPezzi();
			} else {
				CdgPezzo pezzoGiusto = null;
				for (CdgPezzo pezzo : pezzi) {
					if (pezzo.getCategoriaMerceologica().equals(evento.getCategoriaMerceologica())) {
						pezzoGiusto = pezzo;
						break;
					}
				}
				//Se non è stata inserita la giusta associazione lancio un'eccezione!
				if (pezzoGiusto != null) {
					costoIpotetico = calcolaIncidenzaDiCosto(pezzoGiusto, riepilogoEvento) * riepilogoEvento.getPezzi();
				} else {
					throw new RuntimeException("Non \u00E8 stata inserita la giusta associazione tra la commessa selezionata e la categoria merceologica: " + evento.getCategoriaMerceologica());
				}
			}
		}
		return costoIpotetico;
	}

	private String trovaOrigineDaRiepilogoEvento(CdgEventoRiepilogo riepilogoEvento) {
		CdgEvento evento = controllerEventi.getEvento(riepilogoEvento.getEvento());
		String origine = evento != null? evento.getNome() : "";
		return origine;
	}
	
	private int trovaFaseDaRiepilogoEvento(CdgEventoRiepilogo riepilogoEvento) {
		CdgEvento evento = controllerEventi.getEvento(riepilogoEvento.getEvento());
		int fase = evento != null? evento.getFase() : -1;
		return fase;
	}
	
	private double calcolaValoreRiepilogoEvento(CdgEventoRiepilogo riepilogoEvento) {
		int durata = riepilogoEvento.getDurataTotale();
		CdgCostoAssociazione associazione = getAssociazioneDaOperatore(riepilogoEvento.getOperatore());
		double valore = durata * associazione.getCosto();
		return valore;
	}
	
	private CdgCostoAssociazione getAssociazioneDaOperatore(String username) {
		Operatore operatore = controllerOperatori.getOperatore(username);
		if (operatore == null)
			throw new RuntimeException("Non \u00E8 stato trovato l'operatore: " + username);
		CdgCostoAssociazione associazione = controllerAssociazioni.getAssociazione(operatore.getAssociazione());
		if (associazione == null)
			throw new RuntimeException("Non \u00E8 stato trovata nessuna cooperativa per l'operatore: " + username);
		return associazione;
	}

	public HashMap<String, Double> elaboraRicaviBudget(Sede sede, Date inizio, Date fine) {
		HashMap<String, Double> mappaRicavi = new HashMap<>();
		//Recupero tutti i budget relativi alle commesse afferenti alla sede.
		for (Commessa commessa : ControllerCommesse.getInstance().getCommessePerSede(sede.getId())) {
			//Calcolo i ricavi per la commessa.
			HashMap<String, Double> mappaCommessa = elaboraRicaviBudget(commessa, inizio, fine);
			//Li aggiungo ai totali
			for (String key : mappaCommessa.keySet()) {
				double valore = mappaRicavi.containsKey(key) ? mappaRicavi.get(key) : 0.0;
				valore += mappaCommessa.get(key);
				mappaRicavi.put(key, valore);
			}
		}
		return mappaRicavi;
	}

	public HashMap<String, Double> elaboraCostiBudget(Sede sede, Date inizio, Date fine) {
		HashMap<String, Double> mappaCosti = new HashMap<>();
		//Recupero tutti i budget relativi alle commesse afferenti alla sede.
		for (Commessa commessa : ControllerCommesse.getInstance().getCommessePerSede(sede.getId())) {
			//Calcolo i costi per la commessa.
			HashMap<String, Double> mappaCommessa = elaboraCostiBudget(commessa, inizio, fine);
			//Li aggiungo ai totali
			for (String key : mappaCommessa.keySet()) {
				double valore = mappaCosti.containsKey(key) ? mappaCosti.get(key) : 0.0;
				valore += mappaCommessa.get(key);
				mappaCosti.put(key, valore);
			}
		}
		return mappaCosti;
	}

	public HashMap<String, Double> elaboraRicaviBudget(Commessa commessa, Date inizio, Date fine) {
		HashMap<String, Double> mappaRicavi = new HashMap<>();
		GregorianCalendar data = new GregorianCalendar();
		data.setTime(inizio);
		GregorianCalendar termine = new GregorianCalendar();
		termine.setTime(fine);
		while (data.before(termine)) {
			CdgBudget budget = ControllerCdgBudget.getInstance().getBudgetPerCommessa(commessa.getId(), data.getTime());
			//Se l'ho trovato calcolo il ricavo medio per giorno e lo moltiplico per i giorni presi in esame.
			//Altrimenti inserisco 0.
			if (budget != null) {
				double ricavoMedio = calcolaCostoDivisoSuGiorni(budget.getDataInizio(), budget.getDataFine(), budget.getRicavo());
				double valore = mappaRicavi.containsKey(commessa.getNome()) ? mappaRicavi.get(commessa.getNome()) : 0.0;
				valore += ricavoMedio;
				mappaRicavi.put(commessa.getNome(), valore);
			} else {
				mappaRicavi.put(BUDGET_NON_PRESENTE, 0.0);
			}
			//Aumento di un giorno
			data.add(Calendar.DAY_OF_YEAR, 1);
		}
		return mappaRicavi;
	}

	public HashMap<String, Double> elaboraCostiBudget(Commessa commessa, Date inizio, Date fine) {
		HashMap<String, Double> mappaCosti = new HashMap<>();
		GregorianCalendar data = new GregorianCalendar();
		data.setTime(inizio);
		GregorianCalendar termine = new GregorianCalendar();
		termine.setTime(fine);
		while (data.before(termine)) {
			CdgBudget budget = ControllerCdgBudget.getInstance().getBudgetPerCommessa(commessa.getId(), data.getTime());
			//Se l'ho trovato calcolo il costo medio per giorno e lo moltiplico per i giorni presi in esame per ogni percentuale.
			//Altrimenti inserisco 0.
			if (budget != null) {
				List<CdgBudgetPercentualiCosto> percentuali = ControllerCdgBudgetPercentuali.getInstance().getPercentualiPerBudget(budget.getId());
				double costoMedio = calcolaCostoDivisoSuGiorni(budget.getDataInizio(), budget.getDataFine(), budget.getCosto());
				for (CdgBudgetPercentualiCosto percentuale : percentuali) {
					CdgCostiRicaviGenerici generico = ControllerCdgCostiRicaviGenerici.getInstance().getCostoRicavoGenericoDaID(percentuale.getCostoGenerico());
					String key = generico != null ? generico.getNome() : "N/A";
					double valorePresente = mappaCosti.containsKey(key) ? mappaCosti.get(key) : 0.0;
					valorePresente += costoMedio * percentuale.getPercentuale() / 100.0;
					mappaCosti.put(key, valorePresente);
				}
			} else {
				mappaCosti.put(BUDGET_NON_PRESENTE, 0.0);
			}
			//Aumento di un giorno
			data.add(Calendar.DAY_OF_YEAR, 1);
		}
		return mappaCosti;
	}

}
