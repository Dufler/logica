package it.ltc.logica.amministrazione.gui.commesse.dialogs;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;

import it.ltc.logica.amministrazione.gui.commesse.elements.preferenze.TabellaPreferenzeFatturazione;
import it.ltc.logica.amministrazione.gui.commesse.elements.preferenze.ToolbarPreferenzeFatturazione;
import it.ltc.logica.amministrazione.gui.composite.CompositeCommessa;
import it.ltc.logica.common.controller.fatturazione.ControllerPreferenzeFatturazione;
import it.ltc.logica.common.controller.sistema.ControllerClienti;
import it.ltc.logica.common.controller.sistema.ControllerCommesseCentrale;
import it.ltc.logica.common.controller.sistema.ControllerSedi;
import it.ltc.logica.database.model.centrale.CommessaCentrale;
import it.ltc.logica.gui.dialog.DialogModel;
import it.ltc.logica.utilities.variabili.ControllerVariabiliGlobali;
import it.ltc.logica.utilities.variabili.Permessi;

public class DialogCommessa extends DialogModel<CommessaCentrale> {

	public static final String title = "CommessaCentrale";
	
	private CompositeCommessa composite;
	private TabellaPreferenzeFatturazione tabellaPreferenze;
	
	private final ControllerSedi controllerSedi;
	private final ControllerClienti controllerClienti;
	private final ControllerCommesseCentrale controllerCommesse;
	private final ControllerPreferenzeFatturazione controllerPreferenze;
	
	public DialogCommessa(CommessaCentrale value) {
		super(title, value);
		controllerSedi = ControllerSedi.getInstance();
		controllerClienti = ControllerClienti.getInstance();
		controllerCommesse = ControllerCommesseCentrale.getInstance();
		controllerPreferenze = ControllerPreferenzeFatturazione.getInstance();
	}

	@Override
	public void loadModel() {
		composite.setNome(valore.getNome());
		composite.setDescrizione(valore.getDescrizione());
		composite.setCliente(controllerClienti.getCliente(valore.getIdCliente()));
		composite.setSede(controllerSedi.getSede(valore.getIdSede()));
		tabellaPreferenze.setElementi(controllerPreferenze.getPreferenzePerCommessa(valore.getId()));
	}

	@Override
	public void prefillModel() {
		// TODO Auto-generated method stub
	}

	@Override
	public void copyDataToModel() {
		valore.setNome(composite.getNome());
		valore.setDescrizione(composite.getDescrizione());
		valore.setIdCliente(composite.getCliente().getId());
		valore.setIdSede(composite.getSede().getId());
	}

	@Override
	public List<String> validateModel() {
		return null;
	}

	@Override
	public boolean updateModel() {
		boolean update = controllerCommesse.aggiorna(valore);
		return update;
	}

	@Override
	public boolean insertModel() {
		boolean insert = controllerCommesse.inserisci(valore);
		return insert;
	}

	@Override
	public CommessaCentrale createNewModel() {
		CommessaCentrale commessa = new CommessaCentrale();
		return commessa;
	}

	@Override
	public boolean isDirty() {
		boolean compositeDirty = composite.isDirty();
		return compositeDirty;
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(1, false));
		composite = new CompositeCommessa(this, container);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		composite.enableElement(ControllerVariabiliGlobali.getInstance().haPermesso(Permessi.AMMINISTRAZIONE_CLIENTI_COMMESSE_CUD.getID()));
		
		//Gli permetto di inserire o modificare preferenze di fatturazione solo se la commessa è già stata inserita a sistema.
		if (modify) {
			Label lblFatturazione = new Label(container, SWT.NONE);
			lblFatturazione.setText("Preferenze di fatturazione:");
			
			ToolbarPreferenzeFatturazione toolbarPreferenze = new ToolbarPreferenzeFatturazione(container);
			
			tabellaPreferenze = new TabellaPreferenzeFatturazione(container, modify ? valore.getId() : -1);
			Table table = tabellaPreferenze.getTable();
			table.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
			
			toolbarPreferenze.setTabella(tabellaPreferenze);
		}
	}

}
