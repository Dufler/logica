package it.ltc.logica.admin.gui.dialogs;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;

import it.ltc.logica.common.controller.fatturazione.ControllerAmbitiFatturazione;
import it.ltc.logica.database.model.centrale.fatturazione.AmbitoFattura;
import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.gui.decoration.SpacerLabel;
import it.ltc.logica.gui.dialog.DialogModel;
import it.ltc.logica.gui.input.Bottone;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.input.TextForDescription;
import it.ltc.logica.gui.input.TextForString;

public class DialogSottoAmbito extends DialogModel<SottoAmbitoFattura> {
	
	public static final String title = "Sotto Ambito Fatturazione";
	
	private final ControllerAmbitiFatturazione controller;
	
	private ComboBox<AmbitoFattura> comboAmbito;
	private TextForString textCategoria;
	private TextForString textNome;
	private TextForDescription textDescrizione;
	private Bottone btnValore;

	public DialogSottoAmbito(SottoAmbitoFattura value) {
		super(title, value);
		controller = ControllerAmbitiFatturazione.getInstance();
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		GridLayout gridLayout = (GridLayout) container.getLayout();
		gridLayout.numColumns = 3;
		
		Label lblAmbito = new Label(container, SWT.NONE);
		lblAmbito.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblAmbito.setText("Ambito: ");
		
		comboAmbito = new ComboBox<AmbitoFattura>(container);
		comboAmbito.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		comboAmbito.setItems(controller.getAmbiti());
		addChild(comboAmbito);
		
		new SpacerLabel(container);
		
		Label lblCategoria = new Label(container, SWT.NONE);
		lblCategoria.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblCategoria.setText("Categoria: ");
		
		textCategoria = new TextForString(container);
		textCategoria.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		addChild(textCategoria);

		new SpacerLabel(container);
		
		Label lblValore = new Label(container, SWT.NONE);
		lblValore.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblValore.setText("Valore: ");
		
		btnValore = new Bottone(container, SWT.CHECK);
		btnValore.setText("Si/No");
		addChild(btnValore);

		new SpacerLabel(container);
		
		Label lblNome = new Label(container, SWT.NONE);
		lblNome.setLayoutData(new GridData(SWT.RIGHT, SWT.CENTER, false, false, 1, 1));
		lblNome.setText("Nome: ");
		
		textNome = new TextForString(container);
		textNome.setLayoutData(new GridData(SWT.FILL, SWT.CENTER, true, false, 1, 1));
		addChild(textNome);

		new SpacerLabel(container);
		
		Label lblDescrizione = new Label(container, SWT.NONE);
		lblDescrizione.setLayoutData(new GridData(SWT.RIGHT, SWT.TOP, false, false, 1, 1));
		lblDescrizione.setText("Descrizione: ");
		
		textDescrizione = new TextForDescription(container);
		textDescrizione.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 1, 1));
		addChild(textDescrizione);

		new SpacerLabel(container);
		
	}

	@Override
	public boolean isDirty() {
		return comboAmbito.isDirty() || textCategoria.isDirty() || textNome.isDirty() || textDescrizione.isDirty() || btnValore.isDirty();
	}

	@Override
	public void loadModel() {
		comboAmbito.setSelectedValue(controller.getAmbito(valore.getIdAmbito()));
		textCategoria.setText(valore.getCategoriaAmbito());
		textNome.setText(valore.getNome());
		textDescrizione.setText(valore.getDescrizione());
		btnValore.setSelection(valore.getValoreAmmesso());
	}

	@Override
	public void copyDataToModel() {
		valore.setIdAmbito(comboAmbito.getSelectedValue().getId());
		valore.setCategoriaAmbito(textCategoria.getText());
		valore.setNome(textNome.getText());
		valore.setDescrizione(textDescrizione.getText());
		valore.setValoreAmmesso(btnValore.getSelection());
	}

	@Override
	public List<String> validateModel() {
		return null;
	}

	@Override
	public boolean updateModel() {
		boolean update = controller.update(valore);
		return update;
	}

	@Override
	public boolean insertModel() {
		boolean insert = controller.insert(valore);
		return insert;
	}

	@Override
	public SottoAmbitoFattura createNewModel() {
		SottoAmbitoFattura nuovoAmbito = new SottoAmbitoFattura();
		return nuovoAmbito;
	}
	
	@Override
	public void prefillModel() {
		//DO NOTHING!		
	}

}
