package it.ltc.logica.trasporti.gui.codicicliente.elements;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.controller.sistema.ControllerCommesse;
import it.ltc.logica.common.controller.trasporti.ControllerCorrieri;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.trasporti.CodiceClienteCorriere;
import it.ltc.logica.database.model.centrale.trasporti.Corriere;
import it.ltc.logica.gui.elements.ToolbarCRUDConFiltro;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.utilities.variabili.Permessi;

public class ToolbarCodiciCliente extends ToolbarCRUDConFiltro<TabellaCodiciCliente, CodiceClienteCorriere, CriteriFiltraggioCodiciCliente> {

	private ComboBox<Commessa> comboCommessa;
	private ComboBox<Corriere> comboCorriere;
	private ComboBox<CodiceClienteCorriere.Stato> comboStato;
	
	public ToolbarCodiciCliente(Composite parent) {
		super(parent);
	}

	@Override
	protected void setupFiltri(Composite composite) {
		composite.setLayout(new GridLayout(4, false));
		
		aggiungiCampoDiTestoGenericoPerFiltro(composite);
		
		comboCommessa = new ComboBox<Commessa>(composite);
		comboCommessa.setItems(ControllerCommesse.getInstance().getTutteCommesse());
		comboCommessa.setRequired(false);
		
		comboCorriere = new ComboBox<Corriere>(composite);
		comboCorriere.setItems(ControllerCorrieri.getInstance().getCorrieri());
		comboCorriere.setRequired(false);
		
		comboStato = new ComboBox<CodiceClienteCorriere.Stato>(composite);
		comboStato.setItems(CodiceClienteCorriere.Stato.values());
		comboStato.setRequired(false);
		
	}

	@Override
	protected CriteriFiltraggioCodiciCliente getCriteriDiFiltraggio() {
		String codice = filterText.getText();
		Integer commessa = comboCommessa.isSelected() ? comboCommessa.getSelectedValue().getId() : null;
		Integer corriere = comboCorriere.isSelected() ? comboCorriere.getSelectedValue().getId() : null;
		String stato = comboStato.isSelected() ? comboStato.getSelectedValue().getCodice() : null;
		CriteriFiltraggioCodiciCliente criteri = new CriteriFiltraggioCodiciCliente(codice, stato, corriere, commessa);
		return criteri;
	}

	@Override
	protected void resetCampiFiltri() {
		filterText.setText("");
		comboCommessa.setSelectedValue(null);
		comboCorriere.setSelectedValue(null);
		comboStato.setSelectedValue(null);
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.TRASPORTI_CODICI_CLIENTE_GESTIONE.getID();
	}

	@Override
	protected void setupTastiToolbar() {
		aggiungiTastoNuovo();
		aggiungiTastoModifica();
		aggiungiTastoFiltra();
		aggiungiTastoAnnullaFiltra();
	}

}
