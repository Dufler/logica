package it.ltc.logica.cdg.gui.eventi.elements.eventi;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.common.controller.cdg.ControllerCdgFasi;
import it.ltc.logica.common.controller.sistema.ControllerCategorieMerceologiche;
import it.ltc.logica.database.model.centrale.CategoriaMerceologica;
import it.ltc.logica.database.model.centrale.cdg.CdgEvento;
import it.ltc.logica.database.model.centrale.cdg.CdgFase;
import it.ltc.logica.gui.elements.ToolbarCRUDConFiltro;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.utilities.variabili.Permessi;

public class ToolbarEventi extends ToolbarCRUDConFiltro<TabellaCdgEventi, CdgEvento, CriteriFiltroEventi> {
	
	private ComboBox<CdgFase> comboFase;
	private ComboBox<CategoriaMerceologica> comboCategoria;

	public ToolbarEventi(Composite parent) {
		super(parent);
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
	protected void setupFiltri(Composite composite) {
		composite.setLayout(new GridLayout(4, false));
		
		Label labelFiltri = new Label(composite, SWT.NONE);
		labelFiltri.setText("Filtra per: ");
		
		comboFase = new ComboBox<>(composite);
		comboFase.setItems(ControllerCdgFasi.getInstance().getFasi());
		comboFase.setRequired(false);
		
		comboCategoria = new ComboBox<>(composite);
		comboCategoria.setItems(ControllerCategorieMerceologiche.getInstance().getCategorie());
		comboCategoria.setRequired(false);
		
		aggiungiCampoDiTestoGenericoPerFiltro(composite);
	}

	@Override
	protected CriteriFiltroEventi getCriteriDiFiltraggio() {
		Integer fase = comboFase.getSelectedValue() != null ? comboFase.getSelectedValue().getId() : null;
		String categoria = comboCategoria.getSelectedValue() != null ? comboCategoria.getSelectedValue().getNome() : null;
		String testo = filterText.getText();
		CriteriFiltroEventi criteri = new CriteriFiltroEventi(fase, categoria, testo);
		return criteri;
	}

	@Override
	protected void resetCampiFiltri() {
		comboFase.setSelectedValue(null);
		comboCategoria.setSelectedValue(null);
		filterText.setText("");
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.CDG_EVENTI_CUD.getID();
	}

}
