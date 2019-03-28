package it.ltc.logica.admin.gui.elements;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.admin.gui.dialogs.DialogSottoAmbito;
import it.ltc.logica.common.controller.fatturazione.ControllerAmbitiFatturazione;
import it.ltc.logica.database.model.centrale.fatturazione.AmbitoFattura;
import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.TabellaCRUDConFiltro;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;
import it.ltc.logica.utilities.variabili.Permessi;

public class TabellaSottoAmbiti extends TabellaCRUDConFiltro<SottoAmbitoFattura, CriteriAmbiti> {
	
	private FiltroAmbiti filtro;

	public TabellaSottoAmbiti(Composite parent) {
		super(parent);
	}
	
	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("ID", 100, 0);
		aggiungiColonna("Ambito", 100, 1);
		aggiungiColonna("Nome", 100, 2);
		aggiungiColonna("Descrizione", 100, 3);
		aggiungiColonna("Categoria", 100, 4);
		aggiungiColonna("Valore", 50, 5);
	}
	
	private static class EtichettatoreAmbiti extends Etichettatore<SottoAmbitoFattura> {

		@Override
		public String getTesto(SottoAmbitoFattura oggetto, int colonna) {
			String testo;
			switch (colonna) {
				case 0 : testo = oggetto.getId().toString(); break;
				case 1 : testo = getAmbito(oggetto); break;
				case 2 : testo = oggetto.getNome(); break;
				case 3 : testo = oggetto.getDescrizione() != null ? oggetto.getDescrizione() : ""; break;
				case 4 : testo = oggetto.getCategoriaAmbito(); break;
				case 5 : testo = oggetto.getValoreAmmesso() ? "Si" : "No"; break;
				default : testo = "";
			}
			return testo;
		}

		private String getAmbito(SottoAmbitoFattura oggetto) {
			AmbitoFattura ambito = ControllerAmbitiFatturazione.getInstance().getAmbito(oggetto.getIdAmbito());
			String testo = ambito != null ? ambito.getNome() : "-";
			return testo;
		}

		@Override
		public String getTestoTooltip(SottoAmbitoFattura oggetto, int colonna) {
			return getTesto(oggetto, colonna);
		}

		@Override
		public Image getIcona(SottoAmbitoFattura oggetto, int colonna) {
			return null;
		}
		
	}
	
	private static class OrdinatoreAmbiti extends Ordinatore<SottoAmbitoFattura> {

		@Override
		protected int compare(SottoAmbitoFattura t1, SottoAmbitoFattura t2, int property) {
			int compare;
			switch (property) {
				case 0 : compare = t1.getId().compareTo(t2.getId()); break;
				case 1 : compare = t1.getCategoriaAmbito().compareTo(t2.getCategoriaAmbito()); break;
				case 2 : compare = t1.getNome().compareTo(t2.getNome()); break;
				default : compare = 0;
			}
			return compare;
		}
		
	}
	
	private static class FiltroAmbiti extends FiltroTabella<SottoAmbitoFattura, CriteriAmbiti> {

		@Override
		protected boolean checkElemento(SottoAmbitoFattura item) {
			boolean toShow = criteri.getIdAmbito() != null ? criteri.getIdAmbito().equals(item.getIdAmbito()) : true;
			return toShow;
		}
		
	}

	@Override
	protected Ordinatore<SottoAmbitoFattura> creaOrdinatore() {
		return new OrdinatoreAmbiti();
	}

	@Override
	protected FiltroTabella<SottoAmbitoFattura, CriteriAmbiti> creaFiltro() {
		filtro = new FiltroAmbiti();
		return filtro;
	}

//	@Override
//	protected void apriDialog(SottoAmbitoFattura elemento) {
//		DialogSottoAmbito dialog = new DialogSottoAmbito(elemento);
//		int result = dialog.open();
//		if (result == Dialog.OK) {
//			refresh();
//		}
//	}
	
	@Override
	protected DialogSottoAmbito creaDialog(SottoAmbitoFattura elemento) {
		DialogSottoAmbito dialog = new DialogSottoAmbito(elemento);
		return dialog;
	}

	@Override
	protected boolean eliminaElemento(SottoAmbitoFattura elemento) {
		//TODO - Creare metodo per l'eliminazione nel controller.
		return false;
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.ADMIN_GESTIONE_FATTURAZIONE.getID();
	}

	@Override
	public void aggiornaContenuto() {
		setElementi(ControllerAmbitiFatturazione.getInstance().getSottoAmbiti());		
	}

	@Override
	protected Etichettatore<SottoAmbitoFattura> creaEtichettatore() {
		return new EtichettatoreAmbiti();
	}

	@Override
	protected ModificatoreValoriCelle<SottoAmbitoFattura> creaModificatore() {
		return null;
	}

}
