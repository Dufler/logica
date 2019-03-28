package it.ltc.logica.amministrazione.gui.fatturazione.elements.preferenze;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.common.controller.fatturazione.ControllerAmbitiFatturazione;
import it.ltc.logica.common.controller.sistema.ControllerCommesse;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.fatturazione.AmbitoFattura;
import it.ltc.logica.database.model.centrale.fatturazione.PreferenzeFatturazione;
import it.ltc.logica.gui.elements.ToolbarCRUDConFiltro;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.utilities.variabili.Permessi;

public class ToolbarPreferenze extends ToolbarCRUDConFiltro<TabellaPreferenzeFatturazione, PreferenzeFatturazione, CriteriFiltraggioPreferenzeFatturazione> {

	private ComboBox<Commessa> comboCommessa;
	private ComboBox<AmbitoFattura> comboAmbito;
	
	public ToolbarPreferenze(Composite parent) {
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
		
		comboAmbito = new ComboBox<>(composite);
		comboAmbito.setItems(ControllerAmbitiFatturazione.getInstance().getAmbiti());
		comboAmbito.setRequired(false);
	}

	@Override
	protected CriteriFiltraggioPreferenzeFatturazione getCriteriDiFiltraggio() {
		Integer commessa = comboCommessa.isSelected() ? comboCommessa.getSelectedValue().getId() : null;
		Integer ambito = comboAmbito.isSelected() ? comboAmbito.getSelectedValue().getId() : null;
		CriteriFiltraggioPreferenzeFatturazione criteri = new CriteriFiltraggioPreferenzeFatturazione(commessa, ambito);
		return criteri;
	}

	@Override
	protected void resetCampiFiltri() {
		comboCommessa.setSelectedValue(null);
		comboAmbito.setSelectedValue(null);
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.AMMINSTRAZIONE_PREFERENZE_FATTURAZIONE_CUD.getID();
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
