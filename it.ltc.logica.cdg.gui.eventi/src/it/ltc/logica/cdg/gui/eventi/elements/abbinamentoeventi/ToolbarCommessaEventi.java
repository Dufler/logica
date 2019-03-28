package it.ltc.logica.cdg.gui.eventi.elements.abbinamentoeventi;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.common.controller.cdg.ControllerCdgEventi;
import it.ltc.logica.common.controller.sistema.ControllerCommesse;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.cdg.CdgCommessaEvento;
import it.ltc.logica.database.model.centrale.cdg.CdgEvento;
import it.ltc.logica.gui.elements.ToolbarCRUDConFiltro;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.utilities.variabili.Permessi;

public class ToolbarCommessaEventi extends ToolbarCRUDConFiltro<TabellaCommessaEventi, CdgCommessaEvento, CriteriFiltraggioCommessaEventi> {
	
	private ComboBox<Commessa> comboCommessa;
	private ComboBox<CdgEvento> comboEvento;
	
	public ToolbarCommessaEventi(Composite parent) {
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
		composite.setLayout(new GridLayout(3, false));
		
		Label labelFiltri = new Label(composite, SWT.NONE);
		labelFiltri.setText("Filtra per: ");
		
		comboCommessa = new ComboBox<>(composite);
		comboCommessa.setItems(ControllerCommesse.getInstance().getTutteCommesse());
		comboCommessa.setRequired(false);
		
		comboEvento = new ComboBox<>(composite);
		comboEvento.setItems(ControllerCdgEventi.getInstance().getEventi());
		comboEvento.setRequired(false);
	}

	@Override
	protected CriteriFiltraggioCommessaEventi getCriteriDiFiltraggio() {
		Integer commessa = comboCommessa.getSelectedValue() != null ? comboCommessa.getSelectedValue().getId() : null;
		Integer evento = comboEvento.getSelectedValue() != null ? comboEvento.getSelectedValue().getId() : null;
		CriteriFiltraggioCommessaEventi criteri = new CriteriFiltraggioCommessaEventi();
		criteri.setCommessa(commessa);
		criteri.setEvento(evento);
		return criteri;
	}

	@Override
	protected void resetCampiFiltri() {
		comboCommessa.setSelectedValue(null);
		comboEvento.setSelectedValue(null);
	}

	@Override
	protected int getIDPermesso() {
		return Permessi.CDG_EVENTI_CUD.getID();
	}

}
