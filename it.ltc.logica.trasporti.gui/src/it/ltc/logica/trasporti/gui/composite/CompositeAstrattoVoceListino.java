package it.ltc.logica.trasporti.gui.composite;

import org.eclipse.swt.widgets.Composite;

import it.ltc.logica.common.controller.fatturazione.ControllerAmbitiFatturazione;
import it.ltc.logica.database.model.centrale.fatturazione.SottoAmbitoFattura;
import it.ltc.logica.gui.composite.Gruppo;
import it.ltc.logica.gui.dialog.DialogMessaggio;
import it.ltc.logica.gui.input.ComboBox;
import it.ltc.logica.gui.input.TextForDescription;
import it.ltc.logica.gui.input.TextForString;
import it.ltc.logica.gui.validation.ParentValidationHandler;
import it.ltc.logica.trasporti.gui.dialog.DialogGiorniFranchigia;
import it.ltc.logica.trasporti.gui.dialog.DialogSelezioneNazioni;
import it.ltc.logica.trasporti.gui.dialog.DialogSelezioneRegioni;
import it.ltc.logica.trasporti.gui.dialog.DialogSogliaContrassegno;
import it.ltc.logica.trasporti.gui.dialog.DialogSogliaVolume;
import it.ltc.logica.trasporti.gui.dialog.DialogSpedizioneConMoltiColli;
import it.ltc.logica.trasporti.gui.elements.ETipoListino;

public abstract class CompositeAstrattoVoceListino extends Gruppo {
	
	public static final String tooltipValoreAmbito = "Qui va inserito un valore per l'ambito. Es. il numero di giorni di franchigia per una sosta di giacenza";
	
	protected TextForString textNome;
	protected TextForDescription textDescrizione;
	protected ComboBox<SottoAmbitoFattura> comboAmbito;
	protected TextForString textValoreAmbito;

	public CompositeAstrattoVoceListino(ParentValidationHandler parentValidator, Composite parent, String title) {
		super(parentValidator, parent, title);
	}
	
	protected final void avviaInserimentoGuidato() {
		SottoAmbitoFattura ambitoSelezionato = comboAmbito.getSelectedValue();
		if (ambitoSelezionato != null && ambitoSelezionato.getValoreAmmesso()) {
			int idAmbito = ambitoSelezionato.getId();
			String valorePrecedente = textValoreAmbito.getText();
			String valore;
			switch (idAmbito) {
				case 30: case 65 : case 94 : case 95 : case 99 : {DialogSelezioneRegioni dialog = new DialogSelezioneRegioni(valorePrecedente); valore = dialog.apri();} break;
				case 111 : {DialogSelezioneNazioni dialog = new DialogSelezioneNazioni(valorePrecedente); valore = dialog.apri();} break;
				case 17 : {DialogGiorniFranchigia dialog = new DialogGiorniFranchigia(valorePrecedente); valore = dialog.apri();} break;
				case 10 : case 11 : case 63 : case 64 : case 112 : case 113 : {DialogSogliaContrassegno dialog = new DialogSogliaContrassegno(valorePrecedente); valore = dialog.apri();} break;
				case 19 : case 54 : case 82 : case 89 : {DialogSpedizioneConMoltiColli dialog = new DialogSpedizioneConMoltiColli(valorePrecedente); valore = dialog.apri();} break;
				case 134 : case 135 : case 136 : { DialogSogliaVolume dialog = new DialogSogliaVolume(valorePrecedente); valore = dialog.apri(); }; break;
				default : valore = "";
			}
			textValoreAmbito.setText(valore);
			textValoreAmbito.setDirty(true);
		} else {
			DialogMessaggio.openWarning("Selezione Ambito Necessaria", "Bisogna prima selezionare un ambito che ammetta valori.");
		}
	}
	
	protected final void setAmbiti(ETipoListino type) {
		comboAmbito.setItems(ControllerAmbitiFatturazione.getInstance().getSottoAmbitiPerAmbito(type.getIdAmbito()));
	}

}
