package it.ltc.logica.common.calcolo.algoritmi;

import java.util.List;

public abstract class Calcolatore<T extends ModelDaCalcolare, U extends MVoceDiListino<T>> {
	
	public void calcola(Calcolo computo, T model) {
		List<U> vociDiListino = getVociDiListinoUsabili(computo, model);
		model.addCalcolo(computo);
		for (MVoceDiListino<T> voce : vociDiListino) {
			voce.calcola(model);
		}
	}
	
	public abstract List<U> getVociDiListinoUsabili(Calcolo computo, T model);

}
