package it.ltc.logica.trasporti.gui.fatturazione.wizards.giacenze;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.common.controller.sistema.ControllerCommesse;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.gui.composite.common.CompositeDate;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.wizard.PaginaWizard;
import it.ltc.logica.trasporti.controller.FatturazioneGiacenzeController;

public class SelezioneDatiFatturazioneWizardPage extends PaginaWizard {

	private static final String titolo = "Fatturazione Giacenze";
	private static final String descrizione = "Seleziona la commessa e il periodo.";
	
	private final FatturazioneGiacenzeController controller;
	
	private CompositeDate compositeDate;
	private ComboBox<Commessa> comboCommessa;
	
	public SelezioneDatiFatturazioneWizardPage() {
		super(titolo, descrizione);
		controller = FatturazioneGiacenzeController.getInstance();
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(3, false));
		
		Label lblCommessa = new Label(container, SWT.NONE);
		lblCommessa.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCommessa.setText("Commessa: ");
		
		comboCommessa = new ComboBox<Commessa>(container);
		comboCommessa.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboCommessa.setItems(ControllerCommesse.getInstance().getTutteCommesse());
		addChild(comboCommessa);
		
		new SpacerLabel(container, SWT.NONE, SpacerLabel.SHORT);
		
		Label lblData = new Label(container, SWT.NONE);
		lblData.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblData.setText("Data: ");
		
		compositeDate = new CompositeDate(this, container, false);
		compositeDate.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		compositeDate.setInizioEFineMesePrecedente();
		addChild(compositeDate);
		
		new SpacerLabel(container, SWT.NONE, SpacerLabel.SHORT);
				
	}

	@Override
	public void copyDataToModel() {
		controller.setCommessa(comboCommessa.getSelectedValue());
		controller.setDataInizio(compositeDate.getDaSoloGiorno());
		controller.setDataFine(compositeDate.getASoloGiorno());
	}


}
