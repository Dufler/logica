package it.ltc.logica.amministrazione.gui.fatturazione.controller;

import java.util.Date;
import java.util.List;

import it.ltc.logica.amministrazione.calcolo.algoritmi.CalcolatoreLogistica;
import it.ltc.logica.amministrazione.calcolo.algoritmi.LogisticaModel;
import it.ltc.logica.common.controller.listini.ControllerListiniClienti;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.listini.ListinoCommessa;
import it.ltc.logica.gui.dialog.DialogMessaggio;
import it.ltc.logica.gui.task.DialogProgresso;
import it.ltc.logica.gui.task.Processo;

public class FatturazioneIngressiController {
	
	private static FatturazioneIngressiController instance;
	
	private Commessa commessa;
	private Date inizio;
	private Date fine;
	
	private List<LogisticaModel> ingressiFatturati;
	
	private FatturazioneIngressiController() {}
	
	public static FatturazioneIngressiController getInstance() {
		if (instance == null) {
			instance = new FatturazioneIngressiController();
		}
		return instance;
	}

	public Commessa getCommessa() {
		return commessa;
	}

	public void setCommessa(Commessa commessa) {
		this.commessa = commessa;
	}

	public Date getInizio() {
		return inizio;
	}

	public void setInizio(Date inizio) {
		this.inizio = inizio;
	}

	public Date getFine() {
		return fine;
	}

	public void setFine(Date fine) {
		this.fine = fine;
	}
	
	public List<LogisticaModel> getIngressiFatturati() {
		return ingressiFatturati;
	}
	
	private ListinoCommessa getListinoLogistica(Commessa commessa) {
		ListinoCommessa listinoLogistica = null;
		List<ListinoCommessa> listini = ControllerListiniClienti.getInstance().getListiniClientiPerLogisitica();
		for (ListinoCommessa listino : listini) {
			if (listino.getIdCommessa() == commessa.getId()) {
				listinoLogistica = listino;
				break;
			}
		}
		return listinoLogistica;
	}

	public void calcolaFatturazioneLogistica() {
//		IRunnableWithProgress runnable = new IRunnableWithProgress() {
//			@Override
//			public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException {
//				monitor.beginTask("Calcolo della fatturazione logistica", -1);
//				CalcolatoreLogistica calcolatore = CalcolatoreLogistica.getInstance();
//				ListinoCommessa listinoCommessa = getListinoLogistica(commessa);
//				if (listinoCommessa == null) {
//					DialogMessaggio.openError("Listino Mancante", "Inserire un listino di logistica per la commessa: " + commessa.getNome());
//				} else {
//					ingressiFatturati = calcolatore.getModelIngressi(commessa, inizio, fine);
//					for (LogisticaModel ingresso : ingressiFatturati) {
//						calcolatore.calcolaRicavo(ingresso, listinoCommessa);
//					}
//				}
//				monitor.done();
//			}
//		};
//
//		try {
//			DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
//			dialog.run(true, true, runnable);
//		} catch (InvocationTargetException | InterruptedException e) {
//			e.printStackTrace();
//		}
		ProcessoCalcoloFatturazione pcf = new ProcessoCalcoloFatturazione();
		DialogProgresso dialog = new DialogProgresso(DialogProgresso.TITOLO_DEFAULT);
		dialog.esegui(pcf);
	}

	public boolean salvaDatiLogistica() {
		// TODO Auto-generated method stub
		return true;
	}
	
	private class ProcessoCalcoloFatturazione extends Processo {
		
		private static final String title = "Calcolo della fatturazione logistica";

		public ProcessoCalcoloFatturazione() {
			super(title, -1);
		}

		@Override
		public void eseguiOperazioni() throws Exception {
			CalcolatoreLogistica calcolatore = CalcolatoreLogistica.getInstance();
			ListinoCommessa listinoCommessa = getListinoLogistica(commessa);
			if (listinoCommessa == null) {
				DialogMessaggio.openError("Listino Mancante", "Inserire un listino di logistica per la commessa: " + commessa.getNome());
			} else {
				ingressiFatturati = calcolatore.getModelIngressi(commessa, inizio, fine);
				for (LogisticaModel ingresso : ingressiFatturati) {
					calcolatore.calcolaRicavo(ingresso, listinoCommessa);
				}
			}
		}
		
	}

}
