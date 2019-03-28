package it.ltc.logica.amministrazione.calcolo.algoritmi;

import it.ltc.logica.amministrazione.calcolo.ambiti.FactoryAmbiti;
import it.ltc.logica.amministrazione.calcolo.ambiti.IAmbitoLogistica;
import it.ltc.logica.common.calcolo.algoritmi.IAlgoritmo;
import it.ltc.logica.common.calcolo.algoritmi.MDettaglioVoce;
import it.ltc.logica.common.calcolo.algoritmi.MVoceDiListino;
import it.ltc.logica.common.controller.listini.ControllerListiniClienti;
import it.ltc.logica.database.model.centrale.listini.VoceDiListino;

public class VoceDiListinoLogistica extends MVoceDiListino<LogisticaModel> {

	private final IAmbitoLogistica ambito;
	
	public VoceDiListinoLogistica(Integer idListino, Integer idVoce, String nomeVoce, IAmbitoLogistica ambito, IAlgoritmo algoritmo, MDettaglioVoce dettagli, Scopo scopo) {
		super(idListino, idVoce, nomeVoce, ambito, algoritmo, dettagli, scopo);
		this.ambito = ambito;
	}
	
	@Override
	public IAmbitoLogistica getAmbito() {
		return ambito;
	}

	public static VoceDiListinoLogistica getVoce(VoceDiListino voce) {
		int idListino = voce.getIdListino();
		int idVoce = voce.getId();
		String nomeVoce = voce.getNome();
		IAmbitoLogistica ambito = FactoryAmbiti.getAmbito(voce.getIdSottoAmbito());
		IAlgoritmo algoritmo = FactoryAlgoritmiAmministrazione.getInstance().getAlgoritmo(voce.getTipoCalcolo(), voce.getStrategiaCalcolo());
		MDettaglioVoce dettagli = ControllerListiniClienti.getInstance().getDettagliVoce(voce);
		VoceDiListinoLogistica nuovaVoce = new VoceDiListinoLogistica(idListino, idVoce, nomeVoce, ambito, algoritmo, dettagli, Scopo.RICAVO);
		return nuovaVoce;
	}

}
