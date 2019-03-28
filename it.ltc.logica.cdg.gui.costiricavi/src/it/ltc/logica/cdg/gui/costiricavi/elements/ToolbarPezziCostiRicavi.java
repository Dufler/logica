package it.ltc.logica.cdg.gui.costiricavi.elements;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.common.controller.sistema.ControllerCategorieMerceologiche;
import it.ltc.logica.common.controller.sistema.ControllerCommesse;
import it.ltc.logica.database.model.centrale.CategoriaMerceologica;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.cdg.CdgPezzo;
import it.ltc.logica.gui.elements.ToolbarCRUDConFiltro;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.utilities.variabili.Permessi;

public class ToolbarPezziCostiRicavi extends ToolbarCRUDConFiltro<TabellaPezziCostiRicavi, CdgPezzo, CriteriFiltroPezziCostiRicavi> {

	private ComboBox<Commessa> comboCommessa;
	private ComboBox<CategoriaMerceologica> comboCategoria;
	
	public ToolbarPezziCostiRicavi(Composite parent) {
		super(parent);
	}

	@Override
	protected void setupFiltri(Composite composite) {
		composite.setLayout(new GridLayout(3, false));
		
		Label labelFiltri = new Label(composite, SWT.NONE);
		labelFiltri.setText("Filtra per: ");
		
		comboCommessa = new ComboBox<>(composite);
		comboCommessa.setItems(ControllerCommesse.getInstance().getTutteCommesse());
		comboCommessa.setRequired(false);
		
		comboCategoria = new ComboBox<>(composite);
		comboCategoria.setItems(ControllerCategorieMerceologiche.getInstance().getCategorie());
		comboCategoria.setRequired(false);
	}

	@Override
	protected CriteriFiltroPezziCostiRicavi getCriteriDiFiltraggio() {
		Integer commessa = comboCommessa.isSelected() ? comboCommessa.getSelectedValue().getId() : null;
		String categoria = comboCategoria.isSelected() ? comboCategoria.getSelectedValue().getNome() : null;
		CriteriFiltroPezziCostiRicavi criteri = new CriteriFiltroPezziCostiRicavi(commessa, categoria);
		return criteri;
	}

	@Override
	protected void setupTastiToolbar() {
		aggiungiTastoFiltra();
		aggiungiTastoAnnullaFiltra();
		aggiungiSeparatore();
		aggiungiTastoNuovo();
		aggiungiTastoModifica();
	}

	@Override
	protected void resetCampiFiltri() {
		comboCommessa.setSelectedValue(null);
		comboCategoria.setSelectedValue(null);
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.CDG_COSTI_RICAVI_CUD.getID();
	}

}
