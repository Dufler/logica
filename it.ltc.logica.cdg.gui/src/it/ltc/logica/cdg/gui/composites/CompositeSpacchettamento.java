package it.ltc.logica.cdg.gui.composites;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.common.controller.cdg.ControllerCdgEventi;
import it.ltc.logica.common.controller.cdg.ControllerCdgFasi;
import it.ltc.logica.database.model.centrale.cdg.CdgEvento;
import it.ltc.logica.database.model.centrale.cdg.CdgFase;
import it.ltc.logica.gui.composite.Gruppo;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.input.TextForPercentage;
import it.ltc.logica.gui.validation.ParentValidationHandler;

public class CompositeSpacchettamento extends Gruppo {

	private static final String title = "Spacchettamento: Costo e Ricavo percentuale";
	
	private ComboBox<CdgFase> comboFase;
	private ComboBox<CdgEvento> comboEvento;
	private TextForPercentage textCosto;
	private TextForPercentage textRicavo;
	
	public CompositeSpacchettamento(ParentValidationHandler parentValidator, Composite parent) {
		super(parentValidator, parent, title);
	}

	@Override
	public void aggiungiElementiGrafici() {
		setLayout(new GridLayout(3, false));
		
		Label lblFase = new Label(this, SWT.NONE);
		lblFase.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblFase.setText("Fase: ");
		
		comboFase = new ComboBox<CdgFase>(this);
		comboFase.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				CdgFase faseSelezionata = comboFase.getSelectedValue();
				impostaEventi(faseSelezionata);
			}
		});
		comboFase.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboFase.setItems(ControllerCdgFasi.getInstance().getFasi());
		addChild(comboFase);
		
		new SpacerLabel(this);
		
		Label lblEvento = new Label(this, SWT.NONE);
		lblEvento.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblEvento.setText("Evento: ");
		
		comboEvento = new ComboBox<CdgEvento>(this);
		comboEvento.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboEvento.setEnabled(false);
		addChild(comboEvento);
		
		new SpacerLabel(this);
		
		Label lblPercentualeDiCosto = new Label(this, SWT.NONE);
		lblPercentualeDiCosto.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPercentualeDiCosto.setText("Percentuale di Costo: ");
		
		textCosto = new TextForPercentage(this);
		textCosto.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		addChild(textCosto);

		new SpacerLabel(this);
		
		Label lblPercentualeDiRicavo = new Label(this, SWT.NONE);
		lblPercentualeDiRicavo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblPercentualeDiRicavo.setText("Percentuale di Ricavo: ");
		
		textRicavo = new TextForPercentage(this);
		textRicavo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		addChild(textRicavo);

		new SpacerLabel(this);
	}
	
	private void impostaEventi(CdgFase faseSelezionata) {
		List<CdgEvento> eventi = ControllerCdgEventi.getInstance().getEventiPerFase(faseSelezionata);
		boolean enabled = !eventi.isEmpty();
		comboEvento.setItems(eventi);
		comboEvento.setEnabled(enabled);
	}

	public CdgFase getFase() {
		return comboFase.getSelectedValue();
	}

	public void setFase(CdgFase fase) {
		comboFase.setSelectedValue(fase);
		impostaEventi(fase);
	}

	public CdgEvento getEvento() {
		return comboEvento.getSelectedValue();
	}

	public void setEvento(CdgEvento evento) {
		comboEvento.setSelectedValue(evento);
	}

	public Double getCosto() {
		return textCosto.getValue();
	}

	public void setCosto(Double costo) {
		textCosto.setValue(costo);
	}

	public Double getRicavo() {
		return textRicavo.getValue();
	}

	public void setRicavo(Double ricavo) {
		textRicavo.setValue(ricavo);
	}

}
