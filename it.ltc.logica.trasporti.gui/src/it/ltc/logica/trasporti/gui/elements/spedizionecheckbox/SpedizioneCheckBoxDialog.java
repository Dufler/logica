package it.ltc.logica.trasporti.gui.elements.spedizionecheckbox;

import java.util.List;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.database.model.centrale.trasporti.Spedizione;
import it.ltc.logica.gui.dialog.DialogModel;

public class SpedizioneCheckBoxDialog extends DialogModel<Spedizione> {

	private static final String title = "Dettaglio Spedizione";
	
//	private CompositeDatiSpedizione compositeDatiSpedizione;
//	private CompositeDatiOrdineDaSpedizione compositeOrdine;
//	private CompositeContrassegno compositeContrassegno;
//	private CompositeIndirizzo compositeDestinatario;
//	private CompositeIndirizzo compositeMittente;
	
	public SpedizioneCheckBoxDialog(Spedizione value) {
		super(title, value);
	}

	@Override
	public void aggiungiElementiGrafici(Composite container) {
		container.setLayout(new GridLayout(1, false));
		
		
	}

	@Override
	public boolean isDirty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void loadModel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void copyDataToModel() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<String> validateModel() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean updateModel() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insertModel() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public void prefillModel() {
		//DO NOTHING!		
	}

	@Override
	public Spedizione createNewModel() {
		// TODO Auto-generated method stub
		return null;
	}

}