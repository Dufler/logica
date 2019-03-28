package it.ltc.logica.cdg.gui.costiricavi.elements.commessa;

import java.util.Date;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.common.controller.sistema.ControllerCommesse;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.cdg.CdgCostoRicavoCommessa;
import it.ltc.logica.gui.elements.ToolbarCRUDConFiltro;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.input.DateField;
import it.ltc.logica.utilities.variabili.Permessi;

public class ToolbarCostiRicaviCommesse extends ToolbarCRUDConFiltro<TabellaCostiRicaviCommesse, CdgCostoRicavoCommessa, CriteriFiltraggioCostiRicaviCommesse> {

	private ComboBox<Commessa> comboCommessa;
	private DateField dataDa;
	private DateField dataA;
	
	public ToolbarCostiRicaviCommesse(Composite parent) {
		super(parent);
	}

	@Override
	protected void setupFiltri(Composite composite) {
		composite.setLayout(new GridLayout(4, false));
		
		Label labelFiltri = new Label(composite, SWT.NONE);
		labelFiltri.setText("Filtra per: ");
		
		comboCommessa = new ComboBox<>(composite);
		comboCommessa.setItems(ControllerCommesse.getInstance().getTutteCommesse());
		comboCommessa.setRequired(false);
		
		dataDa = new DateField(composite);
		dataDa.setRequired(false);
		
		dataA = new DateField(composite);
		dataA.setRequired(false);
	}

	@Override
	protected CriteriFiltraggioCostiRicaviCommesse getCriteriDiFiltraggio() {
		Integer commessa = comboCommessa.isSelected() ? comboCommessa.getSelectedValue().getId() : null;
		Date da = dataDa.isDirty() ? dataDa.getSimpleStartValue() : null;
		Date a = dataA.isDirty() ? dataA.getSimpleEndValue() : null;
		CriteriFiltraggioCostiRicaviCommesse criteri = new CriteriFiltraggioCostiRicaviCommesse(commessa, da, a);
		return criteri;
	}

	@Override
	protected void resetCampiFiltri() {
		comboCommessa.setSelectedValue(null);
		dataDa.setDirty(false);
		dataA.setDirty(false);
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
		aggiungiTastoElimina();
	}

}
