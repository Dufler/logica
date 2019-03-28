package it.ltc.logica.trasporti.gui.elements.tracking;

import java.util.List;

import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.jface.dialogs.IDialogConstants;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;

import it.ltc.logica.database.model.centrale.Tracking;

public class DialogTracking extends Dialog {

	private final String riferimento;
	private final List<Tracking> lista;
	
	private Composite container;
	
	public DialogTracking(String riferimento, List<Tracking> lista) {
		super(Display.getDefault().getActiveShell());
		setShellStyle(SWT.DIALOG_TRIM);
		this.riferimento = riferimento;
		this.lista = lista;
	}

	
	@Override
	protected Control createDialogArea(Composite parent) {
		container = (Composite) super.createDialogArea(parent);
		
		Label lblTrackingDellordine = new Label(container, SWT.NONE);
		lblTrackingDellordine.setText("Tracking dell'ordine: " + riferimento);
		
		TabellaTracking tabella = new TabellaTracking(container);
		Table table = tabella.getTable();
		table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		tabella.setInput(lista);
		
		return container;
	}
	
	@Override
    protected void configureShell(Shell newShell) {
		super.configureShell(newShell);
		newShell.setText("Tracking dell'ordine " + riferimento);
    }
	
	@Override
	protected void createButtonsForButtonBar(Composite parent) {
		createButton(parent, IDialogConstants.OK_ID, IDialogConstants.OK_LABEL,	true);
	}
}
