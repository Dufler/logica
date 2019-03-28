package it.ltc.logica.amministrazione.calcolo.algoritmi;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import it.ltc.logica.amministrazione.calcolo.ambiti.FactoryAmbiti;
import it.ltc.logica.amministrazione.calcolo.ambiti.IAmbitoLogistica;
import it.ltc.logica.amministrazione.calcolo.ambiti.IngressoAppeso;
import it.ltc.logica.amministrazione.calcolo.ambiti.IngressoCalzatura;
import it.ltc.logica.amministrazione.calcolo.ambiti.IngressoSteso;
import it.ltc.logica.common.calcolo.algoritmi.Calcolatore;
import it.ltc.logica.common.calcolo.algoritmi.Calcolo;
import it.ltc.logica.common.controller.listini.ControllerListiniClienti;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.Prodotto;
import it.ltc.logica.database.model.centrale.ingressi.Ingresso;
import it.ltc.logica.database.model.centrale.ingressi.IngressoDettaglio;
import it.ltc.logica.database.model.centrale.ingressi.StatiCarico;
import it.ltc.logica.database.model.centrale.listini.ListinoCommessa;
import it.ltc.logica.database.model.centrale.listini.VoceDiListino;
import it.ltc.logica.database.model.centrale.ordini.OrdineTestata;

public class CalcolatoreLogistica extends Calcolatore<LogisticaModel, VoceDiListinoLogistica> {
	
	private static CalcolatoreLogistica instance;
	
	//private final ControllerProdotti controllerProdotti;
	private final ControllerListiniClienti controllerListini;
	//private final ControllerIngressi controllerIngressi;
	//private final ControllerOrdini controllerOrdini;
	
	private CalcolatoreLogistica() {
		controllerListini = ControllerListiniClienti.getInstance();
		//controllerIngressi = ControllerIngressi.getInstance();
		//controllerProdotti = ControllerProdotti.getInstance();
		//controllerOrdini = ControllerOrdini.getInstance();
	}
	
	public static CalcolatoreLogistica getInstance() {
		if (instance == null) {
			instance = new CalcolatoreLogistica();
		}
		return instance;
	}

	@Override
	public List<VoceDiListinoLogistica> getVociDiListinoUsabili(Calcolo computo, LogisticaModel model) {
		List<VoceDiListino> vociCaricate = controllerListini.getVociDiListino(computo.getIdListino());
		LinkedList<VoceDiListinoLogistica> voci = new LinkedList<VoceDiListinoLogistica>();
		for (VoceDiListino voce : vociCaricate) {
			VoceDiListinoLogistica voceModel = getVoceModel(voce);
			voci.add(voceModel);
		}
		voci.sort(null);
		return voci;
	}

	private VoceDiListinoLogistica getVoceModel(VoceDiListino voce) {
		VoceDiListinoLogistica vl = VoceDiListinoLogistica.getVoce(voce);
		return vl;
	}
	
	public List<LogisticaModel> getModelIngressi(Commessa commessa, Date da, Date a) {
		List<LogisticaModel> models = new LinkedList<LogisticaModel>();
//		List<Ingresso> ingressi = controllerIngressi.getListaIngressi(commessa, false);
//		for (Ingresso ingresso : ingressi) {
//			if (checkStato(ingresso) && checkData(ingresso.getDataArrivo(), da, a)) {
//				String riferimento = ingresso.getRiferimentoCliente();
//				Date dataIngresso = ingresso.getDataArrivo();
//				TipoIngresso tipoIngresso = TipoIngresso.valueOf(ingresso.getTipo());
//				String tipo = tipoIngresso != null ? tipoIngresso.toString() : "-";
//				LogisticaModel model = new LogisticaModel(riferimento, dataIngresso, tipo);
//				for (IngressoDettaglio dettaglio : controllerIngressi.getListaDettagli(ingresso.getId(), false)) {
//					IAmbitoLogistica ambito = getAmbito(dettaglio);
//					EventoLogisticaModel evento = new EventoLogisticaModel(ambito, 0, dettaglio.getQuantitaLetta(), 0);
//					model.addEvento(evento);
//				}
//				models.add(model);
//			}
//		}
		return models;
	}
	
	public List<LogisticaModel> getModelUscite(Commessa commessa, Date da, Date a) {
		List<LogisticaModel> models = new LinkedList<LogisticaModel>();
//		List<Ordine> uscite = controllerOrdini.getListaOrdini(commessa);
//		for (Ordine uscita : uscite) {
//			if (checkStato(uscita) && checkData(uscita.getDataRicezione(), da, a)) {
//				String riferimento = uscita.getRiferimentoCliente();
//				Date dataIngresso = uscita.getDataRicezione();
//				TipoOrdine tipoUscita = TipoOrdine.valueOf(uscita.getTipo());
//				String tipo = tipoUscita != null ? tipoUscita.toString() : "-";
//				LogisticaModel model = new LogisticaModel(riferimento, dataIngresso, tipo);
//				for (IngressoDettaglio dettaglio : controllerIngressi.getListaDettagli(uscita.getId(), false)) {
//					IAmbitoLogistica ambito = getAmbito(dettaglio);
//					EventoLogisticaModel evento = new EventoLogisticaModel(ambito, 0, dettaglio.getQuantitaLetta(), 0);
//					model.addEvento(evento);
//				}
//				models.add(model);
//			}
//		}
		return models;
	}
	
	private boolean checkStato(Ingresso ingresso) {
		boolean checkStato = false;
		if (ingresso.getStato() != null) {
			StatiCarico stato = StatiCarico.valueOf(ingresso.getStato());
			checkStato = stato == StatiCarico.LAVORATO;
		}
		return checkStato;
	}
	
	private boolean checkStato(OrdineTestata uscita) {
		boolean checkStato = false;
		if (uscita.getStato() != null) {
			checkStato = uscita.getStato().equals("XXX");
		}
		return checkStato;
	}
	
	private boolean checkData(Date data, Date da, Date a) {
		boolean checkData = false;
		if (data != null) {
			checkData = data.after(da) && data.before(a);
		}
		return checkData;
	}
	
	private IAmbitoLogistica getAmbito(IngressoDettaglio dettaglio) {
		IAmbitoLogistica ambito;
		Prodotto prodotto = null; //controllerProdotti.getProdotto(dettaglio.getIdProdotto());
		//CategoriaMerceologica cm = CategoriaMerceologica.getCategoria(prodotto.getIdCategoria());
		//FIXME - prenderli da altro invece che metterli statitici
		switch (prodotto.getCategoria()) {
			case "CALZATURA" : ambito = FactoryAmbiti.getAmbito(IngressoCalzatura.ID); break;
			case "STESO" : ambito = FactoryAmbiti.getAmbito(IngressoSteso.ID); break;
			case "APPESO" : ambito = FactoryAmbiti.getAmbito(IngressoAppeso.ID); break;
			default : ambito = null;
		}
		return ambito;
	}
	
	public void calcolaRicavo(LogisticaModel model, ListinoCommessa listino) {
		Calcolo preventivoRicavo = new Calcolo(listino.getNome(), listino.getId(), Calcolo.Tipo.RICAVO);
		calcola(preventivoRicavo, model);
	}

}
