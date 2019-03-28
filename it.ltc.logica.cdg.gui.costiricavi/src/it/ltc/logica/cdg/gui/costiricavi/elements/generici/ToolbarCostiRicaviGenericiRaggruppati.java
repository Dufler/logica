package it.ltc.logica.cdg.gui.costiricavi.elements.generici;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.common.controller.sistema.ControllerSedi;
import it.ltc.logica.database.model.centrale.Sede;
import it.ltc.logica.database.model.centrale.cdg.CdgCostiRicaviGenerici;
import it.ltc.logica.database.model.centrale.cdg.CdgCostiRicaviGenerici.DriverRipartizione;
import it.ltc.logica.gui.elements.ToolbarCRUDConFiltro;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.utilities.variabili.Permessi;

public class ToolbarCostiRicaviGenericiRaggruppati extends ToolbarCRUDConFiltro<TabellaCostiRicaviGenerici, CdgCostiRicaviGenerici, CriteriFiltraggioCostiRicaviGenerici> {

	private ComboBox<Sede> comboSede;
	private ComboBox<DriverRipartizione> comboDriver;
	
	public ToolbarCostiRicaviGenericiRaggruppati(Composite parent) {
		super(parent);
	}

	@Override
	protected void setupFiltri(Composite composite) {
		composite.setLayout(new GridLayout(4, false));
		
		Label labelFiltri = new Label(composite, SWT.NONE);
		labelFiltri.setText("Filtra per: ");
		
		aggiungiCampoDiTestoGenericoPerFiltro(composite);
		
		comboSede = new ComboBox<>(composite);
		comboSede.setItems(ControllerSedi.getInstance().getSedi());
		comboSede.setRequired(false);
		
		comboDriver = new ComboBox<>(composite);
		comboDriver.setItems(DriverRipartizione.values());
		comboDriver.setRequired(false);
	}

	@Override
	protected CriteriFiltraggioCostiRicaviGenerici getCriteriDiFiltraggio() {
		String testo = filterText.getText();
		Integer sede = comboSede.isSelected() ? comboSede.getSelectedValue().getId() : null;
		DriverRipartizione driver = comboDriver.isSelected() ? comboDriver.getSelectedValue() : null;
		CriteriFiltraggioCostiRicaviGenerici criteri = new CriteriFiltraggioCostiRicaviGenerici(testo, sede, driver);
		return criteri;
	}

	@Override
	protected void resetCampiFiltri() {
		filterText.setText("");
		comboSede.setSelectedValue(null);
		comboDriver.setSelectedValue(null);
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.CDG_COSTI_RICAVI_CUD.getID();
	}

	@Override
	protected void setupTastiToolbar() {
		aggiungiTastoFiltra();
		aggiungiTastoAnnullaFiltra();
		aggiungiTastoNuovo();
		aggiungiTastoModifica();
		aggiungiTastoElimina();
	}

}
