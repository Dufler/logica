package it.ltc.logica.trasporti.calcolo.algoritmi;

import it.ltc.logica.common.calcolo.algoritmi.IAlgoritmo;
import it.ltc.logica.common.calcolo.algoritmi.MDettaglioVoce;
import it.ltc.logica.common.calcolo.algoritmi.MVoceDiListino;
import it.ltc.logica.common.controller.listini.ControllerListiniClienti;
import it.ltc.logica.database.model.centrale.listini.VoceDiListino;
import it.ltc.logica.trasporti.calcolo.ambiti.IAmbitoTrasporto;
import it.ltc.logica.trasporti.calcolo.ambiti.trasporti.FactoryAmbitiTrasporti;

/**
 * Modello che rappresenta una voce di listino specifica per i trasporti utilizzabile per il calcolo.
 * @author Damiano Bellucci - damiano.bellucci@gmail.com
 *
 */
public class VoceDiListinoTrasporti extends MVoceDiListino<SpedizioneModel> {
	
	private final IAmbitoTrasporto ambito;
	
	/**
	 * Vanno inseriti i seguenti parametri ricavabili dalla voce di listino collegata.
	 * @param idListino
	 * @param idVoce
	 * @param nomeVoce
	 * @param ambito
	 * @param algoritmo
	 * @param minimo
	 * @param massimo
	 */
	public VoceDiListinoTrasporti(Integer idListino, Integer idVoce, String nomeVoce, IAmbitoTrasporto ambito, IAlgoritmo algoritmo, MDettaglioVoce dettaglio, /*Double minimo, Double massimo,*/ Scopo scopo) {
		super(idListino, idVoce, nomeVoce, ambito, algoritmo, dettaglio, scopo);
		this.ambito = ambito;
	}
	
	public static VoceDiListinoTrasporti getVoceCliente(VoceDiListino voce) {
		int idListino = voce.getIdListino();
		int idVoce = voce.getId();
		String nomeVoce = voce.getNome();
		IAmbitoTrasporto ambito = FactoryAmbitiTrasporti.getInstance().getAmbito(voce.getIdSottoAmbito(), voce.getValoreSottoAmbito());
		IAlgoritmo algoritmo = FactoryAlgoritmiTrasporti.getInstance().getAlgoritmo(voce.getTipoCalcolo(), voce.getStrategiaCalcolo());
		MDettaglioVoce dettagli = ControllerListiniClienti.getInstance().getDettagliVoce(voce);
		VoceDiListinoTrasporti nuovaVoce = new VoceDiListinoTrasporti(idListino, idVoce, nomeVoce, ambito, algoritmo, dettagli, Scopo.RICAVO);
		return nuovaVoce;
	}
	
//	public static VoceDiListinoTrasporti getVoceCorriere(VoceDiListinoCorriere voce) {
//		int idListino = voce.getIdListino();
//		int idVoce = voce.getId();
//		String nomeVoce = voce.getNome();
//		IAmbitoTrasporto ambito = FactoryAmbiti.getAmbito(voce.getIdSottoAmbito());
//		IAlgoritmo algoritmo = FactoryAlgoritmiTrasporti.getInstance().getAlgoritmo(voce.getTipo(), voce.getStrategia());
//		MDettaglioVoce dettagli = ControllerListiniCorrieri.getInstance().getDettagliVoce(voce);
//		VoceDiListinoTrasporti nuovaVoce = new VoceDiListinoTrasporti(idListino, idVoce, nomeVoce, ambito, algoritmo, dettagli, Scopo.COSTO);
//		return nuovaVoce;
//	}

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
		if (ordinamento == 0 && voce instanceof VoceDiListinoTrasporti) {
			VoceDiListinoTrasporti voceTrasporti = (VoceDiListinoTrasporti) voce;
			IAmbitoTrasporto ambitoTrasporti = voceTrasporti.getAmbito();
			ordinamento = ambito.getTipo().compareTo(ambitoTrasporti.getTipo());
		}
		return ordinamento;
	}

}
