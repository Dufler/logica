package it.ltc.logica.common.calcolo.algoritmi;

public class AlgoritmoFisso implements IAlgoritmo {
	
	private static AlgoritmoFisso instance;
	
	public static final String VERSIONE = "1.0";
	
	private AlgoritmoFisso() {
		//Istanzia la strategia
	}
	
	public static AlgoritmoFisso getInstance() {
		if (instance == null) {
			instance = new AlgoritmoFisso();
		}
		return instance;
	}

	//@Override
	public double calcolaCosto(double costo) {
		return costo;
	}

	@Override
	public String getVersione() {
		return VERSIONE;
	}

	@Override
	public String getNome() {
		return TipoAlgoritmo.FISSO.getNome();
	}
	
	public String toString() {
		return TipoAlgoritmo.FISSO.getNome();
	}

	@Override
	public TipoAlgoritmo getTipoAlgoritmo() {
		return TipoAlgoritmo.FISSO;
	}

}
