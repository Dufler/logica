package it.ltc.logica.trasporti.gui.fatturazione.wizards.spedizioni;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.common.controller.listini.ControllerListiniClienti;
import it.ltc.logica.common.controller.sistema.ControllerCommesse;
import it.ltc.logica.common.controller.trasporti.ControllerCodiciClienteCorriere;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.listini.ListinoCommessa;
import it.ltc.logica.database.model.centrale.trasporti.CodiceClienteCorriere;
import it.ltc.logica.database.model.centrale.trasporti.Spedizione.TipoSpedizione;
import it.ltc.logica.gui.composite.common.CompositeDate;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.wizard.PaginaWizard;
import it.ltc.logica.trasporti.controller.FatturazioneSpedizioniController;
import org.eclipse.swt.widgets.Button;

public class SelezioneDatiFatturazioneWizardPage extends PaginaWizard {

	private static final String titolo = "Fatturazione Spedizioni";
	private static final String descrizione = "Seleziona la commessa, il listino e il periodo. Se necessario va indicato un codice cliente per filtrare le spedizioni.";
	
	private final FatturazioneSpedizioniController controller;
	
	private CompositeDate compositeDate;
	private ComboBox<Commessa> comboCommessa;
	private ComboBox<ListinoCommessa> comboListino;
	private ComboBox<CodiceClienteCorriere> comboCodiceCliente;
	private ComboBox<TipoSpedizione> comboTipo;
	
	public SelezioneDatiFatturazioneWizardPage() {
		super(titolo, descrizione);
		controller = FatturazioneSpedizioniController.getInstance();
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(3, false));
		
		Label lblCommessa = new Label(container, SWT.NONE);
		lblCommessa.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCommessa.setText("Commessa: ");
		
		comboCommessa = new ComboBox<Commessa>(container);
		comboCommessa.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				impostaValoriCombo();
			}
		});
		comboCommessa.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboCommessa.setItems(ControllerCommesse.getInstance().getTutteCommesse());
		addChild(comboCommessa);
		
		new SpacerLabel(container);
		
		Label lblListino = new Label(container, SWT.NONE);
		lblListino.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblListino.setText("Listino: ");
		
		comboListino = new ComboBox<>(container);
		comboListino.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		addChild(comboListino);
		
		new SpacerLabel(container);
		
		Label lblfacoltativoCodiceCliente = new Label(container, SWT.NONE);
		lblfacoltativoCodiceCliente.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblfacoltativoCodiceCliente.setText("(Facoltativo) Codice Cliente: ");
		
		comboCodiceCliente = new ComboBox<>(container);
		comboCodiceCliente.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboCodiceCliente.setRequired(false);
		addChild(comboCodiceCliente);
		
		new SpacerLabel(container);
		
		Label lblfacoltativoTipo = new Label(container, SWT.NONE);
		lblfacoltativoTipo.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblfacoltativoTipo.setText("(Facoltativo) Tipo: ");
		
		comboTipo = new ComboBox<>(container);
		comboTipo.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboTipo.setItems(TipoSpedizione.values());
		comboTipo.setRequired(false);
		addChild(comboTipo);
		
		new SpacerLabel(container);
		
		Label lblData = new Label(container, SWT.NONE);
		lblData.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblData.setText("Data: ");
		
		compositeDate = new CompositeDate(this, container, false);
		compositeDate.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		compositeDate.setInizioEFineMesePrecedente();
		addChild(compositeDate);
		
		new SpacerLabel(container);
		
		Button btnResetFiltri = new Button(container, SWT.NONE);
		btnResetFiltri.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				resetFiltri();
			}
		});
		btnResetFiltri.setText("Reset filtri");
		new Label(container, SWT.NONE);
		new Label(container, SWT.NONE);
				
	}
	
	private void resetFiltri() {
		comboCommessa.setSelectedValue(null);
		comboListino.setSelectedValue(null);
		comboCodiceCliente.setSelectedValue(null);
		comboTipo.setSelectedValue(null);
		validate();
	}
	
	private void impostaValoriCombo() {
		Commessa commessaSelezionata = comboCommessa.getSelectedValue();
		if (commessaSelezionata != null) {
			comboListino.setEnabled(true);
			comboListino.setItems(ControllerListiniClienti.getInstance().getListiniClientePerTrasporti(commessaSelezionata.getId()));
			comboCodiceCliente.setEnabled(true);
			comboCodiceCliente.setItems(ControllerCodiciClienteCorriere.getInstance().getCodiciPerClienteECorriere(commessaSelezionata.getId(), -1));
		} else {
			comboListino.setEnabled(false);
			comboCodiceCliente.setEnabled(false);
		}
	}

	@Override
	public void copyDataToModel() {
		controller.setCommessa(comboCommessa.getSelectedValue());
		controller.setDataInizio(compositeDate.getDaSoloGiorno());
		controller.setDataFine(compositeDate.getASoloGiorno());
		controller.setListinoSelezionato(comboListino.getSelectedValue());
		controller.setCodiceClienteCorriereSelezionato(comboCodiceCliente.getSelectedValue());
		controller.setTipoSpedizioniSelezionato(comboTipo.getSelectedValue());
	}

}
