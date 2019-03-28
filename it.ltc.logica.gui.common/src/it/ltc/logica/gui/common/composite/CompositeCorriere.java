package it.ltc.logica.gui.common.composite;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.common.controller.trasporti.ControllerCodiciClienteCorriere;
import it.ltc.logica.common.controller.trasporti.ControllerCorrieri;
import it.ltc.logica.common.controller.trasporti.ControllerServizioSpedizione;
import it.ltc.logica.database.model.centrale.Commessa;
import it.ltc.logica.database.model.centrale.trasporti.CodiceClienteCorriere;
import it.ltc.logica.database.model.centrale.trasporti.Corriere;
import it.ltc.logica.database.model.centrale.trasporti.ServizioSpedizione;
import it.ltc.logica.gui.composite.Gruppo;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.validation.ParentValidationHandler;

public class CompositeCorriere extends Gruppo {

	public static final String title = "Corriere";
	
	private final Commessa commessa;
	
	private ComboBox<Corriere> comboCorriere;
	private ComboBox<CodiceClienteCorriere> comboCodiceCliente;
	private ComboBox<ServizioSpedizione> comboServizio;
	
	public CompositeCorriere(ParentValidationHandler parentValidator, Composite parent, Commessa commessa) {
		super(parentValidator, parent, SWT.NONE, title, commessa);
		this.commessa = commessa;
	}

	@Override
	public void aggiungiElementiGrafici() {
		setLayout(new GridLayout(3, false));
		
		Label lblCorriere = new Label(this, SWT.NONE);
		lblCorriere.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCorriere.setText("Corriere: ");
		
		comboCorriere = new ComboBox<>(this);
		comboCorriere.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboCorriere.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				setCodiciDisponibili();
			}
		});
		comboCorriere.setItems(ControllerCorrieri.getInstance().getCorrieri());
		
		new SpacerLabel(this);
		
		Label lblCodiceCliente = new Label(this, SWT.NONE);
		lblCodiceCliente.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCodiceCliente.setText("Codice Cliente: ");
		
		comboCodiceCliente = new ComboBox<>(this);
		comboCodiceCliente.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));

		new SpacerLabel(this);
		
		Label lblServizio = new Label(this, SWT.NONE);
		lblServizio.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblServizio.setText("Servizio: ");
		
		comboServizio = new ComboBox<>(this);
		comboServizio.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboServizio.setItems(ControllerServizioSpedizione.getInstance().getServizi());

		new SpacerLabel(this);
	}
	
	public Corriere getCorriere() {
		return comboCorriere.getSelectedValue();
	}
	
	public void setCorriere(Corriere value) {
		comboCorriere.setSelectedValue(value);
	}
	
	public CodiceClienteCorriere getCodice() {
		return comboCodiceCliente.getSelectedValue();
	}
	
	public void setCodice(CodiceClienteCorriere value) {
		comboCodiceCliente.setSelectedValue(value);
	}
	
	public ServizioSpedizione getServizio() {
		return comboServizio.getSelectedValue();
	}
	
	public void setServizio(ServizioSpedizione value) {
		comboServizio.setSelectedValue(value);
	}
	
	public void setCodiciDisponibili() {
		Corriere corriere = comboCorriere.getSelectedValue();
		if (corriere != null) {
			comboCodiceCliente.setItems(ControllerCodiciClienteCorriere.getInstance().getCodiciPerClienteECorriere(commessa.getId(), corriere.getId()));
			comboCodiceCliente.setEnabled(true);
		} else {
			comboCodiceCliente.setEnabled(false);
		}
	}

}
