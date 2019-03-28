package it.ltc.logica.cdg.gui.analisi.elements;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;

import it.ltc.logica.cdg.gui.analisi.model.RiepilogoGiornalieroPerFaseCDG;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.Tabella;

public class TabellaRiepilogoGiornalieroPerFase extends Tabella<RiepilogoGiornalieroPerFaseCDG> {

	public enum Tipo { TEMPI, CDG }
	
	public TabellaRiepilogoGiornalieroPerFase(Composite parent, Tipo tipo) {
		super(parent, STILE_SEMPLICE);
		
		if (tipo == Tipo.TEMPI) {
			aggiungiColonnaVuota();
			aggiungiColonna("Fase", 100, 0);
			aggiungiColonna("Totale pezzi", 100, 6);
			aggiungiColonna("Totale secondi", 100, 7);
			aggiungiColonna("Costi Reali", 100, 2);
			aggiungiColonna("Costi Ipotetici", 100, 3);
			aggiungiColonna("Scostamento", 100, 4);
		} else if (tipo == Tipo.CDG) {
			aggiungiColonnaVuota();
			aggiungiColonna("Fase", 100, 0);
			aggiungiColonna("Ricavi", 100, 1);
			aggiungiColonna("Costi", 100, 2);
			aggiungiColonna("Scostamento", 100, 5);
		}

	}
	
	@Override
	protected void aggiungiColonne() {
		//Vengono aggiunte dinamicamente in base al tipo richiesto.
	}

	@Override
	protected void aggiungiListener() {
		//DO NOTHING!
	}

	@Override
	protected void aggiungiMenu(Menu menu) {
		//DO NOTHING!
	}

	@Override
	protected Ordinatore<RiepilogoGiornalieroPerFaseCDG> creaOrdinatore() {
		return new OrdinatoreRiepilogoGiornalieroPerFase();
	}

	@Override
	public void aggiornaContenuto() {
		//DO NOTHING!
	}

	@Override
	protected Etichettatore<RiepilogoGiornalieroPerFaseCDG> creaEtichettatore() {
		return new EtichettatoreRiepilogoGiornalieroPerFase();
	}

	@Override
	protected ModificatoreValoriCelle<RiepilogoGiornalieroPerFaseCDG> creaModificatore() {
		return null;
	}

}
