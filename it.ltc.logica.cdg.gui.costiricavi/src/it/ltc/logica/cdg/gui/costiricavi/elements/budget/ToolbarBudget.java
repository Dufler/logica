package it.ltc.logica.cdg.gui.costiricavi.elements.budget;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.common.controller.sistema.ControllerCommesse;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.cdg.CdgBudget;
import it.ltc.logica.gui.elements.ToolbarCRUDConFiltro;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.utilities.variabili.Permessi;

public class ToolbarBudget extends ToolbarCRUDConFiltro<TabellaBudget, CdgBudget, CriteriFiltraggioBudget> {

	private ComboBox<Commessa> comboCommessa;
	
	public ToolbarBudget(Composite parent) {
		super(parent);
	}

	@Override
	protected void setupFiltri(Composite composite) {
		composite.setLayout(new GridLayout(2, false));
		
		Label labelFiltri = new Label(composite, SWT.NONE);
		labelFiltri.setText("Filtra per: ");
		
		comboCommessa = new ComboBox<>(composite);
		comboCommessa.setItems(ControllerCommesse.getInstance().getTutteCommesse());
		comboCommessa.setRequired(false);
	}

	@Override
	protected CriteriFiltraggioBudget getCriteriDiFiltraggio() {
		Integer commessa = comboCommessa.isSelected() ? comboCommessa.getSelectedValue().getId() : null;
		CriteriFiltraggioBudget criteri = new CriteriFiltraggioBudget(commessa);
		return criteri;
	}

	@Override
	protected void resetCampiFiltri() {
		comboCommessa.setSelectedValue(null);
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.CDG_COSTI_RICAVI_CUD.getID();
	}

	@Override
	protected void setupTastiToolbar() {
		aggiungiTastoFiltra();
		aggiungiTastoAnnullaFiltra();
		aggiungiSeparatore();
		aggiungiTastoNuovo();
		aggiungiTastoModifica();
	}

}
