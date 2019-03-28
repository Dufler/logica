package it.ltc.logica.gui.common.dialogs.fatturazione;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.controller.fatturazione.ControllerAmbitiFatturazione;
import it.ltc.logica.common.controller.fatturazione.ControllerCoordinateBancarie;
import it.ltc.logica.common.controller.fatturazione.ControllerPreferenzeFatturazione;
import it.ltc.logica.common.controller.sistema.ControllerCommesse;
import it.ltc.logica.database.model.centrale.fatturazione.AmbitoFattura;
import it.ltc.logica.database.model.centrale.fatturazione.PreferenzeFatturazione;
import it.ltc.logica.gui.common.composite.fatturazione.CompositeLayoutFattura;
import it.ltc.logica.gui.common.composite.fatturazione.CompositeLayoutFatturaSpedizioniItalia;
import it.ltc.logica.gui.common.composite.fatturazione.CompositePreferenzeFatturazione;
import it.ltc.logica.gui.dialog.DialogModel;
import org.eclipse.swt.layout.GridData;

public class DialogPreferenzeFatturazione extends DialogModel<PreferenzeFatturazione> {

	private static final String title = "Preferenze di Fatturazione";
	
	private CompositePreferenzeFatturazione composite;
	private CompositeLayoutFattura compositeLayout;
	
	private final int commessa;
	private final int ambito;
	
	private final ControllerCommesse controllerCommesse;
	private final ControllerAmbitiFatturazione controllerAmbitiFatturazione;
	private final ControllerPreferenzeFatturazione controllerPreferenzeFatturazione;
	private final ControllerCoordinateBancarie controllerCoordinate;
	
	/**
	 * @wbp.parser.constructor
	 */
	public DialogPreferenzeFatturazione(PreferenzeFatturazione value) {
		super(title, value);
		this.commessa = -1;
		this.ambito = -1;
		controllerCommesse = ControllerCommesse.getInstance();
		controllerAmbitiFatturazione = ControllerAmbitiFatturazione.getInstance();
		controllerPreferenzeFatturazione = ControllerPreferenzeFatturazione.getInstance();
		controllerCoordinate = ControllerCoordinateBancarie.getInstance();
	}
	
	public DialogPreferenzeFatturazione(PreferenzeFatturazione value, int commessa, int ambito) {
		super(title, value);
		this.commessa = commessa;
		this.ambito = ambito;
		controllerCommesse = ControllerCommesse.getInstance();
		controllerAmbitiFatturazione = ControllerAmbitiFatturazione.getInstance();
		controllerPreferenzeFatturazione = ControllerPreferenzeFatturazione.getInstance();
		controllerCoordinate = ControllerCoordinateBancarie.getInstance();
	}

	@Override
	public void loadModel() {
		composite.setCommessa(controllerCommesse.getCommessa(valore.getCommessa()));
		composite.setAmbito(controllerAmbitiFatturazione.getAmbito(valore.getAmbito()));
		composite.setModalitaPagamaneto(valore.getModalitaPagamento());
		composite.setCoordinatePagamento(controllerCoordinate.getCoordinata(valore.getCoordinatePagamento()));
		composite.setDescrizione(valore.getDescrizioneFattura());
		//Carico il layout
		if (compositeLayout != null)
			compositeLayout.setLayoutFatturazione(valore.getLayout());
	}

	@Override
	public void prefillModel() {
		composite.setCommessa(controllerCommesse.getCommessa(commessa));
		composite.setAmbito(controllerAmbitiFatturazione.getAmbito(ambito));
	}

	@Override
	public void copyDataToModel() {
		valore.setCommessa(composite.getCommessa().getId());
		valore.setAmbito(composite.getAmbito().getId());
		valore.setModalitaPagamento(composite.getModalitaPagamento());
		valore.setCoordinatePagamento(composite.getCoordinatePagamento().getId());
		valore.setDescrizioneFattura(composite.getDescrizione());
		//Copio il layout
		if (compositeLayout != null)
			valore.setLayout(compositeLayout.getLayoutFatturazione());
	}

	@Override
	public List<String> validateModel() {
		return null;
	}

	@Override
	public boolean updateModel() {
		return controllerPreferenzeFatturazione.aggiorna(valore);
	}

	@Override
	public boolean insertModel() {
		return controllerPreferenzeFatturazione.inserisci(valore);
	}

	@Override
	public PreferenzeFatturazione createNewModel() {
		PreferenzeFatturazione model = new PreferenzeFatturazione();
		return model;
	}

	@Override
	public boolean isDirty() {
		boolean preferenze = composite.isDirty();
		boolean layout = compositeLayout != null ? compositeLayout.isDirty() : false;
		return preferenze || layout;
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		composite = new CompositePreferenzeFatturazione(this, container);
		composite.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		composite.enablePrimaryKeyValues(commessa == -1);
		addChild(composite);
		
		//switch sull'ambito di fatturazione per costruire il giusto composite di layout
		switch (valore.getAmbito()) {
			case AmbitoFattura.ID_SPEDIZIONI : compositeLayout = new CompositeLayoutFatturaSpedizioniItalia(this, container); break;
			default : break;
		}
		if (compositeLayout != null) {
			compositeLayout.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
			addChild(compositeLayout);
		}
	}

}
