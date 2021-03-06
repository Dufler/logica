package it.ltc.logica.trasporti.calcolo.algoritmi;

import it.ltc.logica.common.calcolo.algoritmi.IAlgoritmo;
import it.ltc.logica.common.calcolo.algoritmi.MDettaglioVoce;
import it.ltc.logica.common.calcolo.algoritmi.MVoceDiListino;
import it.ltc.logica.common.controller.listini.ControllerListiniClienti;
import it.ltc.logica.database.model.centrale.listini.VoceDiListino;
import it.ltc.logica.trasporti.calcolo.ambiti.IAmbitoTrasporto;
import it.ltc.logica.trasporti.calcolo.ambiti.giacenza.FactoryAmbitiGiacenza;

public class VoceDiListinoGiacenza extends MVoceDiListino<SpedizioneModel> {

	private final IAmbitoTrasporto ambito;
	
	public VoceDiListinoGiacenza(Integer idListino, Integer idVoce, String nomeVoce, IAmbitoTrasporto ambito, IAlgoritmo algoritmo, MDettaglioVoce dettagli, Scopo scopo) {
		super(idListino, idVoce, nomeVoce, ambito, algoritmo, dettagli, scopo);
		this.ambito = ambito;
	}
	
	public static VoceDiListinoGiacenza getVoceGiacenza(VoceDiListino voce) {
		int idListino = voce.getIdListino();
		int idVoce = voce.getId();
		String nomeVoce = voce.getNome();
		IAmbitoTrasporto ambito = FactoryAmbitiGiacenza.getInstance().getAmbito(voce.getIdSottoAmbito(), voce.getValoreSottoAmbito());
		IAlgoritmo algoritmo = FactoryAlgoritmiTrasporti.getInstance().getAlgoritmo(voce.getTipoCalcolo(), voce.getStrategiaCalcolo());
		MDettaglioVoce dettagli = ControllerListiniClienti.getInstance().getDettagliVoce(voce);
		VoceDiListinoGiacenza nuovaVoce = new VoceDiListinoGiacenza(idListino, idVoce, nomeVoce, ambito, algoritmo, dettagli, Scopo.RICAVO);
		return nuovaVoce;
	}

	@Override
	public IAmbitoTrasporto getAmbito() {
		return ambito;
	}

	@Override
	public String toString() {
		return "VoceModel [ambito=" + ambito + ", tipoAlgoritmo=" + algoritmo + "]";
	}
	
	@Override
	public int compareTo(MVoceDiListino<SpedizioneModel> voce) {
		int ordinamento = super.compareTo(voce);
		if (ordinamento == 0 && voce instanceof VoceDiListinoSimulazione) {
			VoceDiListinoGiacenza voceTrasporti = (VoceDiListinoGiacenza) voce;
			IAmbitoTrasporto ambitoTrasporti = voceTrasporti.getAmbito();
			ordinamento = ambito.getTipo().compareTo(ambitoTrasporti.getTipo());
		}
		return ordinamento;
	}

}
