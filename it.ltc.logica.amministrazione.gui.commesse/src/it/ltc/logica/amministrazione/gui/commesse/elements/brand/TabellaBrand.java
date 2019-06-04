package it.ltc.logica.amministrazione.gui.commesse.elements.brand;

import java.util.List;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.amministrazione.gui.commesse.dialogs.DialogBrand;
import it.ltc.logica.common.controller.prodotti.ControllerBrand;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.prodotto.Brand;
import it.ltc.logica.gui.dialog.DialogApribile;
import it.ltc.logica.gui.dialog.DialogMessaggio;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.TabellaCRUDConFiltro;
import it.ltc.logica.gui.elements.table.filter.FiltroTabella;
import it.ltc.logica.utilities.variabili.Permessi;

public class TabellaBrand extends TabellaCRUDConFiltro<Brand, CriteriFiltraggioBrand> {
	
	protected Commessa commessa;

	public TabellaBrand(Composite parent) {
		super(parent);
	}
	
	public void setCommessa(Commessa commessa) {
		this.commessa = commessa;
		annullaFiltro();
	}

	@Override
	protected FiltroTabella<Brand, CriteriFiltraggioBrand> creaFiltro() {
		//In realtà non serve.
		return null;
	}

	@Override
	protected boolean eliminaElemento(Brand elemento) {
		boolean delete = false;
		if (commessa != null) {
			ControllerBrand controller = ControllerBrand.getInstance(commessa);
			delete = controller.elimina(elemento);
		}
		return delete;
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.AMMINISTRAZIONE_CLIENTI_COMMESSE_CUD.getID();
	}

	@Override
	protected DialogApribile creaDialog(Brand elemento) {
		DialogApribile dialog;
		if (commessa != null) {
			DialogBrand dialogCarico = new DialogBrand(commessa, elemento);
			dialog = dialogCarico;
		} else {
			dialog = DialogMessaggio.getWarning("Selezione commessa", "Va selezionata una commessa");
		}
		return dialog;
	}

	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Codice", 100, 0);
		aggiungiColonna("Descrizione", 100, 1);	
	}

	@Override
	public void aggiornaContenuto() {
		if (criteri != null && commessa != null) {
			ControllerBrand controller = ControllerBrand.getInstance(commessa);
			setElementi(controller.getBrand());
		} else {
			List<Brand> listaVuota = null;
			setElementi(listaVuota);
		}
	}

	@Override
	protected Ordinatore<Brand> creaOrdinatore() {
		return new OrdinatoreBrand();
	}

	@Override
	protected Etichettatore<Brand> creaEtichettatore() {
		return new EtichettatoreBrand();
	}

	@Override
	protected ModificatoreValoriCelle<Brand> creaModificatore() {
		return null;
	}
	
	@Override
	public void filtra(CriteriFiltraggioBrand criteri) {
		this.criteri = criteri;
		aggiornaContenuto();
	}
	
	@Override
	public void annullaFiltro() {
		this.criteri = null;
		aggiornaContenuto();
	}

}
