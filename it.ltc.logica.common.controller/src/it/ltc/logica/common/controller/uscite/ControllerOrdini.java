package it.ltc.logica.common.controller.uscite;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import it.ltc.logica.common.controller.ControllerCommessa;
import it.ltc.logica.common.controller.processi.ProcessoAggiornamentoDati;
import it.ltc.logica.common.controller.processi.ProcessoCancellazioneDati;
import it.ltc.logica.common.controller.processi.ProcessoCaricamentoDati;
import it.ltc.logica.common.controller.processi.ProcessoCaricamentoDato;
import it.ltc.logica.common.controller.processi.ProcessoInserimentoDati;
import it.ltc.logica.common.controller.processi.ProcessoRicercaDati;
import it.ltc.logica.common.controller.sistema.ControllerSedi;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.Sede;
import it.ltc.logica.database.model.centrale.ordini.DatiSpedizione;
import it.ltc.logica.database.model.centrale.ordini.ImballoCollo;
import it.ltc.logica.database.model.centrale.ordini.OperatoreOrdine;
import it.ltc.logica.database.model.centrale.ordini.OrdineStato;
import it.ltc.logica.database.model.centrale.ordini.OrdineTestata;
import it.ltc.logica.database.model.centrale.ordini.ProblemaFinalizzazioneOrdine;
import it.ltc.logica.database.model.centrale.ordini.RisultatoAssegnazioneOrdine;
import it.ltc.logica.database.model.centrale.ordini.RisultatoAssegnazioneOrdine.StatoAssegnazione;
import it.ltc.logica.database.model.centrale.ordini.RisultatoAssegnazioneRigaOrdine;
import it.ltc.logica.database.model.centrale.ordini.RisultatoFinalizzazioneOrdine;
import it.ltc.logica.database.model.centrale.ordini.RisultatoGenerazioneMovimenti;
import it.ltc.logica.database.model.centrale.ordini.StatiOrdine;
import it.ltc.logica.database.model.centrale.ordini.StatiSpedizione;
import it.ltc.logica.gui.task.DialogProgresso;

public class ControllerOrdini extends ControllerCommessa<OrdineTestata> {
	
	public static final String title = "Ordini";
	public static final String resource = "ordine";

	public ControllerOrdini(Commessa commessa) {
		super(title, resource, OrdineTestata[].class, commessa);
	}
	
	public List<OrdineTestata> trovaOrdini(String riferimento, StatiOrdine stato, String tipo, Date da, Date a) {
		Sede sede = ControllerSedi.getInstance().getSede(commessa.getIdSede());
		OrdineTestata filtro = new OrdineTestata();
		filtro.setRiferimento(riferimento);
		filtro.setStato(stato);
		filtro.setTipo(tipo);
		filtro.setDa(da);
		filtro.setA(a);
		ProcessoTrovaOrdini processo = new ProcessoTrovaOrdini(sede, commessa, filtro);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		List<OrdineTestata> ordini = processo.getLista();
		return ordini;
	}
	
	protected class ProcessoTrovaOrdini extends ProcessoRicercaDati<OrdineTestata> {

		public ProcessoTrovaOrdini(Sede sede,	Commessa commessa, OrdineTestata filtro) {
			super(title, resource + "/cerca", c, sede, commessa, filtro);
		}
		
	}
	
	public boolean modificaStato(OrdineTestata ordine, StatiOrdine stato) {
		//Tengo in memoria lo stato attuale nel caso in cui l'aggiornamento fallisca.
		StatiOrdine statoAttuale = ordine.getStato();
		ordine.setStato(stato);
		ProcessoAggiornamentoDati<OrdineTestata> processo = new ProcessoAggiornamentoDati<OrdineTestata>(title, resource + "/modificastato", ordine, sede, commessa);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		boolean esito = processo.getEsito();
		//Se la richiesta non è andata a buon reimposto lo stato
		if (!esito) {
			ordine.setStato(statoAttuale);
		}
		return esito;
	}
	
	/**
	 * Esegue una richiesta di finalizzazione per l'ordine.<br>
	 * L'ordine viene portato da uno stato di inserito o in errore a pronto per l'assegnazione (se vi è disponibilita' per tutti i prodotti richiesti) o in errore.<br>
	 * Vengono anche fatti i movimenti d'impegno della merce.
	 * @param ordine
	 * @return
	 */
	public RisultatoFinalizzazioneOrdine finalizza(OrdineTestata ordine) {
		Sede sede = ControllerSedi.getInstance().getSede(commessa.getIdSede());
		ProcessoFinalizzazione processo = new ProcessoFinalizzazione(sede, commessa, ordine);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		RisultatoFinalizzazioneOrdine finalizzazione = processo.getEsito() ? processo.getObject() : getProblemaFinalizzazione(ordine);
		return finalizzazione;
	}
	
	protected RisultatoFinalizzazioneOrdine getProblemaFinalizzazione(OrdineTestata ordine) {
		RisultatoFinalizzazioneOrdine problema = new RisultatoFinalizzazioneOrdine();
		problema.setOrdine(ordine);
		List<ProblemaFinalizzazioneOrdine> problemi = new LinkedList<>();
		ProblemaFinalizzazioneOrdine fallito = new ProblemaFinalizzazioneOrdine();
		fallito.setDescrizione("Errore lato server!");
		problemi.add(fallito);
		problema.setProblemi(problemi);
		return problema;
	}
	
	protected class ProcessoFinalizzazione extends ProcessoCaricamentoDato<RisultatoFinalizzazioneOrdine> {

		public ProcessoFinalizzazione(Sede sede, Commessa commessa, OrdineTestata filtro) {
			super(title, resource + "/finalizza/" + filtro.getId(), RisultatoFinalizzazioneOrdine.class, sede, commessa);
		}
		
	}
	
	/**
	 * Esegue una richiesta di generazione movimenti per l'ordine.<br>
	 * L'ordine viene portato da uno stato di imballo completato a elaborato.<br>
	 * Vengono anche fatti i movimenti d'uscita (e se necessario di rettifica) della merce.
	 * @param ordine
	 * @return
	 */
	public RisultatoGenerazioneMovimenti generaMovimenti(OrdineTestata ordine) {
		Sede sede = ControllerSedi.getInstance().getSede(commessa.getIdSede());
		ProcessoGenerazioneMovimenti processo = new ProcessoGenerazioneMovimenti(sede, commessa, ordine);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		RisultatoGenerazioneMovimenti generazione = processo.getEsito() ? processo.getObject() : getProblemaGenerazione(ordine);
		return generazione;
	}
	
	protected RisultatoGenerazioneMovimenti getProblemaGenerazione(OrdineTestata ordine) {
		RisultatoGenerazioneMovimenti problema = new RisultatoGenerazioneMovimenti();
		problema.setOrdine(ordine);
		problema.setEsito(false);
		List<String> problemi = new LinkedList<>();
		problemi.add("Errore lato server.");
		problema.setMessaggi(problemi);
		return problema;
	}
	
	protected class ProcessoGenerazioneMovimenti extends ProcessoCaricamentoDato<RisultatoGenerazioneMovimenti> {

		public ProcessoGenerazioneMovimenti(Sede sede, Commessa commessa, OrdineTestata filtro) {
			super(title, resource + "/generamovimenti/" + filtro.getId(), RisultatoGenerazioneMovimenti.class, sede, commessa);
		}
		
	}
	
	public boolean annullaImballo(OrdineTestata ordine) {
		Sede sede = ControllerSedi.getInstance().getSede(commessa.getIdSede());
		ProcessoAnnullamentoImballo processo = new ProcessoAnnullamentoImballo(sede, commessa, ordine);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		boolean esito = processo.getEsito(); 
		if (esito) {
			aggiornaInfoElemento(processo.getObject());
		}
		return esito;
	}
	
	protected class ProcessoAnnullamentoImballo extends ProcessoCaricamentoDato<OrdineTestata> {

		public ProcessoAnnullamentoImballo(Sede sede, Commessa commessa, OrdineTestata filtro) {
			super(title, resource + "/annullaimballo/" + filtro.getId(), OrdineTestata.class, sede, commessa);
		}
		
	}
	
	public boolean annullaAssegnazioneERiposiziona(OrdineTestata ordine) {
		Sede sede = ControllerSedi.getInstance().getSede(commessa.getIdSede());
		ProcessoAnnullamentoAssegnazioneConRiposizionamento processo = new ProcessoAnnullamentoAssegnazioneConRiposizionamento(sede, commessa, ordine);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		boolean esito = processo.getEsito(); 
		if (esito) {
			aggiornaInfoElemento(processo.getObject());
		}
		return esito;
	}
	
	protected class ProcessoAnnullamentoAssegnazioneConRiposizionamento extends ProcessoCaricamentoDato<OrdineTestata> {

		public ProcessoAnnullamentoAssegnazioneConRiposizionamento(Sede sede, Commessa commessa, OrdineTestata filtro) {
			super(title, resource + "/annullaassegnazioneriposiziona/" + filtro.getId(), OrdineTestata.class, sede, commessa);
		}
		
	}
	
	public boolean annullaAssegnazioneERicarica(OrdineTestata ordine) {
		Sede sede = ControllerSedi.getInstance().getSede(commessa.getIdSede());
		ProcessoAnnullamentoAssegnazioneConNuovoCarico processo = new ProcessoAnnullamentoAssegnazioneConNuovoCarico(sede, commessa, ordine);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		boolean esito = processo.getEsito(); 
		if (esito) {
			aggiornaInfoElemento(processo.getObject());
		}
		return esito;
	}
	
	protected class ProcessoAnnullamentoAssegnazioneConNuovoCarico extends ProcessoCaricamentoDato<OrdineTestata> {

		public ProcessoAnnullamentoAssegnazioneConNuovoCarico(Sede sede, Commessa commessa, OrdineTestata filtro) {
			super(title, resource + "/annullaassegnazionenuovocarico/" + filtro.getId(), OrdineTestata.class, sede, commessa);
		}
		
	}
	
	public boolean annullaImportazione(OrdineTestata ordine) {
		Sede sede = ControllerSedi.getInstance().getSede(commessa.getIdSede());
		ProcessoAnnullamentoImportazione processo = new ProcessoAnnullamentoImportazione(sede, commessa, ordine);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		boolean esito = processo.getEsito(); 
		if (esito) {
			aggiornaInfoElemento(processo.getObject());
		}
		return esito;
	}
	
	protected class ProcessoAnnullamentoImportazione extends ProcessoCaricamentoDato<OrdineTestata> {

		public ProcessoAnnullamentoImportazione(Sede sede, Commessa commessa, OrdineTestata filtro) {
			super(title, resource + "/annullaimportazione/" + filtro.getId(), OrdineTestata.class, sede, commessa);
		}
		
	}
	
	public DatiSpedizione trovaDatiSpedizione(OrdineTestata ordine) {
		Sede sede = ControllerSedi.getInstance().getSede(commessa.getIdSede());
		ProcessoRecuperoDatiSpedizione processo = new ProcessoRecuperoDatiSpedizione(sede, commessa, ordine);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		DatiSpedizione datiSpedizione = processo.getEsito() ? processo.getObject() : null; //getProblemaDatiSpedizione(ordine);
		return datiSpedizione;
	}
	
	protected class ProcessoRecuperoDatiSpedizione extends ProcessoCaricamentoDato<DatiSpedizione> {

		public ProcessoRecuperoDatiSpedizione(Sede sede, Commessa commessa, OrdineTestata filtro) {
			super(title, resource + "/trovadatispedizione/" + filtro.getId(), DatiSpedizione.class, sede, commessa);
		}
		
	}
	
	public DatiSpedizione trovaDatiSpedizioneDaID(int idSpedizione) {
		Sede sede = ControllerSedi.getInstance().getSede(commessa.getIdSede());
		ProcessoRecuperoDatiSpedizioneDaID processo = new ProcessoRecuperoDatiSpedizioneDaID(sede, commessa, idSpedizione);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		DatiSpedizione datiSpedizione = processo.getEsito() ? processo.getObject() : null;
		return datiSpedizione;
	}
	
	protected class ProcessoRecuperoDatiSpedizioneDaID extends ProcessoCaricamentoDato<DatiSpedizione> {

		public ProcessoRecuperoDatiSpedizioneDaID(Sede sede, Commessa commessa, int id) {
			super(title, "spedizione/" + id, DatiSpedizione.class, sede, commessa);
		}
		
	}
	
	public DatiSpedizione salvaDatiSpedizione(DatiSpedizione dati) {
		Sede sede = ControllerSedi.getInstance().getSede(commessa.getIdSede());
		ProcessoSalvataggioDatiSpedizione processo = new ProcessoSalvataggioDatiSpedizione(sede, commessa, dati);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		DatiSpedizione datiSpedizione = processo.getEsito() ? processo.getObject() : null;
		return datiSpedizione;
	}
	
//	protected DatiSpedizione getProblemaSalvataggioDatiSpedizione(DatiSpedizione dati) {
//		DatiSpedizione datiMancanti = new DatiSpedizione();
//		datiMancanti.setNote("Dati non presenti");
//		return datiMancanti;
//	}
	
	protected class ProcessoSalvataggioDatiSpedizione extends ProcessoInserimentoDati<DatiSpedizione> {

		public ProcessoSalvataggioDatiSpedizione(Sede sede, Commessa commessa, DatiSpedizione dati) {
			super(title, resource + "/generadatispedizione", sede, commessa, dati);
		}
		
	}
	
	public DatiSpedizione abilitaSpedizione(DatiSpedizione spedizione, boolean abilita) {
		Sede sede = ControllerSedi.getInstance().getSede(commessa.getIdSede());
		ProcessoAbilitaSpedizione processo = new ProcessoAbilitaSpedizione(sede, commessa, spedizione, abilita);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		DatiSpedizione datiSpedizione = processo.getEsito() ? processo.getObject() : null;
		return datiSpedizione;
	}
	
	protected class ProcessoAbilitaSpedizione extends ProcessoAggiornamentoDati<DatiSpedizione> {

		public ProcessoAbilitaSpedizione(Sede sede, Commessa commessa, DatiSpedizione spedizione, boolean abilita) {
			super(title, abilita ? "spedizione/abilita/" : "spedizione/disabilita/", spedizione, sede, commessa);
		}
		
	}
	
	public boolean eliminaSpedizione(DatiSpedizione spedizione) {
		Sede sede = ControllerSedi.getInstance().getSede(commessa.getIdSede());
		ProcessoEliminaSpedizione processo = new ProcessoEliminaSpedizione(sede, commessa, spedizione);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		return processo.getEsito();
	}
	
	protected class ProcessoEliminaSpedizione extends ProcessoCancellazioneDati<DatiSpedizione> {

		public ProcessoEliminaSpedizione(Sede sede, Commessa commessa, DatiSpedizione spedizione) {
			super(title, "spedizione/elimina/", spedizione, sede, commessa);
		}
		
	}
	
	public List<DatiSpedizione> trovaSpedizioni(String riferimento, String corriere, StatiSpedizione stato, Date da, Date a) {
		Sede sede = ControllerSedi.getInstance().getSede(commessa.getIdSede());
		DatiSpedizione filtro = new DatiSpedizione();
		filtro.setRiferimentoDocumento(riferimento);
		filtro.setStato(stato);
		filtro.setDa(da);
		filtro.setA(a);
		filtro.setCorriere(corriere);
		ProcessoTrovaSpedizioni processo = new ProcessoTrovaSpedizioni(sede, commessa, filtro);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		List<DatiSpedizione> spedizioni = processo.getLista();
		return spedizioni;
	}
	
	protected class ProcessoTrovaSpedizioni extends ProcessoRicercaDati<DatiSpedizione> {

		public ProcessoTrovaSpedizioni(Sede sede, Commessa commessa, DatiSpedizione filtro) {
			super(title, "spedizione/cerca", DatiSpedizione[].class, sede, commessa, filtro);
		}
		
	}
	
	
	
	public RisultatoAssegnazioneOrdine assegna(OrdineTestata ordine) {
		Sede sede = ControllerSedi.getInstance().getSede(commessa.getIdSede());
		ProcessoAssegnazione processo = new ProcessoAssegnazione(sede, commessa, ordine);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		RisultatoAssegnazioneOrdine assegnazione = processo.getEsito() ? processo.getObject() : getProblemaAssegnazione(ordine);
		return assegnazione;
	}
	
	protected RisultatoAssegnazioneOrdine getProblemaAssegnazione(OrdineTestata ordine) {
		RisultatoAssegnazioneOrdine problema = new RisultatoAssegnazioneOrdine();
		problema.setOrdine(ordine);
		problema.setStato(StatoAssegnazione.NONDEFINITA);
		List<RisultatoAssegnazioneRigaOrdine> assegnazioni = new LinkedList<>();
		RisultatoAssegnazioneRigaOrdine fallito = new RisultatoAssegnazioneRigaOrdine();
		fallito.setAnomalie("Errore lato server!");
		problema.setAssegnazioni(assegnazioni);
		return problema;
	}
	
	protected class ProcessoAssegnazione extends ProcessoCaricamentoDato<RisultatoAssegnazioneOrdine> {

		public ProcessoAssegnazione(Sede sede, Commessa commessa, OrdineTestata filtro) {
			super(title, resource + "/assegna/" + filtro.getId(), RisultatoAssegnazioneOrdine.class, sede, commessa);
		}
		
	}
	
	public RisultatoAssegnazioneOrdine recuperaAssegnazione(OrdineTestata ordine) {
		Sede sede = ControllerSedi.getInstance().getSede(commessa.getIdSede());
		ProcessoRecuperaAssegnazione processo = new ProcessoRecuperaAssegnazione(sede, commessa, ordine);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		RisultatoAssegnazioneOrdine assegnazione = processo.getEsito() ? processo.getObject() : getProblemaRecuperoAssegnazione(ordine);
		return assegnazione;
	}
	
	protected RisultatoAssegnazioneOrdine getProblemaRecuperoAssegnazione(OrdineTestata ordine) {
		RisultatoAssegnazioneOrdine problema = new RisultatoAssegnazioneOrdine();
		problema.setOrdine(ordine);
		problema.setStato(StatoAssegnazione.NONDEFINITA);
		return problema;
	}
	
	protected class ProcessoRecuperaAssegnazione extends ProcessoCaricamentoDato<RisultatoAssegnazioneOrdine> {

		public ProcessoRecuperaAssegnazione(Sede sede, Commessa commessa, OrdineTestata filtro) {
			super(title, resource + "/recuperaassegnazione/" + filtro.getId(), RisultatoAssegnazioneOrdine.class, sede, commessa);
		}
		
	}
	
	public List<ImballoCollo> trovaImballi(int id) {
		Sede sede = ControllerSedi.getInstance().getSede(commessa.getIdSede());
		OrdineTestata filtro = new OrdineTestata();
		filtro.setId(id);
		ProcessoTrovaImballi processo = new ProcessoTrovaImballi(sede, commessa, filtro);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		List<ImballoCollo> imballi = processo.getLista();
		return imballi;
	}
	
	protected class ProcessoTrovaImballi extends ProcessoCaricamentoDati<ImballoCollo> {

		public ProcessoTrovaImballi(Sede sede, Commessa commessa, OrdineTestata filtro) {
			super(title, resource + "/imballi/" + filtro.getId(), ImballoCollo[].class, sede, commessa);
		}
		
	}
	
	public List<OrdineStato> trovaStati(int id) {
		Sede sede = ControllerSedi.getInstance().getSede(commessa.getIdSede());
		OrdineTestata filtro = new OrdineTestata();
		filtro.setId(id);
		ProcessoTrovaStati processo = new ProcessoTrovaStati(sede, commessa, filtro);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		List<OrdineStato> stati = processo.getLista();
		return stati;
	}
	
	protected class ProcessoTrovaStati extends ProcessoCaricamentoDati<OrdineStato> {

		public ProcessoTrovaStati(Sede sede, Commessa commessa, OrdineTestata filtro) {
			super(title, resource + "/stati/" + filtro.getId(), OrdineStato[].class, sede, commessa);
		}
		
	}
	
	public List<OperatoreOrdine> trovaOperatori(int id) {
		Sede sede = ControllerSedi.getInstance().getSede(commessa.getIdSede());
		OrdineTestata filtro = new OrdineTestata();
		filtro.setId(id);
		ProcessoTrovaOperatori processo = new ProcessoTrovaOperatori(sede, commessa, filtro);
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(processo);
		List<OperatoreOrdine> stati = processo.getLista();
		return stati;
	}
	
	protected class ProcessoTrovaOperatori extends ProcessoCaricamentoDati<OperatoreOrdine> {

		public ProcessoTrovaOperatori(Sede sede, Commessa commessa, OrdineTestata filtro) {
			super(title, resource + "/operatori/" + filtro.getId(), OperatoreOrdine[].class, sede, commessa);
		}
		
	}

}
