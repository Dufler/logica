package it.ltc.logica.trasporti.gui.elements.spedizionemodel;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import org.eclipse.jface.dialogs.IInputValidator;
import org.eclipse.jface.dialogs.InputDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.TableColumn;

import it.ltc.logica.common.calcolo.algoritmi.Calcolo;
import it.ltc.logica.common.calcolo.algoritmi.VoceCalcolata;
import it.ltc.logica.gui.dialog.DialogMessaggio;
import it.ltc.logica.gui.elements.Etichettatore;
import it.ltc.logica.gui.elements.ModificatoreValoriCelle;
import it.ltc.logica.gui.elements.Ordinatore;
import it.ltc.logica.gui.elements.Tabella;

public class TabellaVoceCalcolata extends Tabella<VoceCalcolata> {

	private static final String titoloErroreModifica = "Valore non valido";
	private static final String messaggioErroreModifica = "Il valore inserito non \u00E8 valido.";

	private static final String titoloErroreElimina = "Impossibile Eliminare";
	private static final String messaggioErroreElimina = "La voce selezionata non pu\u00F2 essere eliminata.";
	
	private static final String titoloValoriModificati = "Valori Spedizione Modificati";
	private static final String messaggioValoriModificati = "I valori base della spedizione sono stati modificati. Chiudere e salvare per ricalcolare le voci prima di inserirne o modificare altre";

	private static final String regex = "^\\d+((\\.|,)\\d{1,3})?";
	private final IInputValidator inputValidator;
	private final DecimalFormat formatter;
	private final DecimalFormatSymbols symbols;

	private MenuItem insert;
	private MenuItem modify;
	private MenuItem delete;
	
	private Calcolo risultato;
	
	/**
	 * Indica se i valori base della spedizione (pezzi, colli, peso, ...) sono cambiati e di conseguenza se le voci vanno ricalcolate.
	 */
	private boolean valoriSpedizioneModificati;
	
	/**
	 * Indica se l'utente ha aggiunto, modificato o eliminato voci di fatturazione dal model.
	 */
	private boolean voceModificata;

	public TabellaVoceCalcolata(Composite parent) {
		super(parent);

		inputValidator = getValidator();
		symbols = new DecimalFormatSymbols();
		symbols.setDecimalSeparator('.');
		formatter = new DecimalFormat("#0.000", symbols);
		
		valoriSpedizioneModificati = false;
	}
	
	@Override
	protected void aggiungiColonne() {
		aggiungiColonna("Voce", 100, 0);
		aggiungiColonna("Ambito", 100, 1);
		aggiungiColonna("Valore", 100, 2);
	}

	@Override
	protected void aggiungiListener() {
		// DO NOTHING!
	}

	public void setInput(Calcolo calcolo) {
		risultato = calcolo;
		setInput(risultato.getVoci());
		for (TableColumn column : table.getColumns()) {
			column.pack();
		}
	}

	@Override
	protected void aggiungiMenu(Menu menu) {
		// Inserisci
		insert = new MenuItem(menu, SWT.PUSH);
		insert.setText("Inserisci");
		insert.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				apriDialogInserisci();
			}
		});
		// Modifica
		modify = new MenuItem(menu, SWT.PUSH);
		modify.setText("Modifica");
		modify.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				apriDialogModifica();
			}
		});
		// Elimina
		delete = new MenuItem(menu, SWT.PUSH);
		delete.setText("Elimina");
		delete.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event event) {
				apriDialogElimina();
			}
		});
		abilitaMenu(false);
	}
	
	public void abilitaMenu(boolean abilita) {
		insert.setEnabled(abilita);
		modify.setEnabled(abilita);
		delete.setEnabled(abilita);
	}

	private IInputValidator getValidator() {
		IInputValidator inputValidator = new IInputValidator() {
			@Override
			public String isValid(String newText) {
				String error = null;
				if (newText == null || newText.isEmpty()) {
					error = "Inserisci un valore in euro";
				} else if (!newText.matches(regex)) {
					error = "Inserisci un valore in euro valido.\r\nEs. 123.45";
				}
				return error;
			}
		};
		return inputValidator;
	}

	private VoceCalcolata getVoceSelezionata() {
		VoceCalcolata selezione = null;
		int selectionIndex = table.getSelectionIndex();
		if (selectionIndex != -1) {
			selezione = (VoceCalcolata) table.getItem(selectionIndex).getData();
		}
		return selezione;
	}

	private void apriDialogInserisci() {
		if (valoriSpedizioneModificati) {
			DialogMessaggio.openError(titoloValoriModificati, messaggioValoriModificati);
		} else {
			DialogNuovaVoceCalcolata dialog = new DialogNuovaVoceCalcolata(risultato);
			if (dialog.open() == Window.OK) {
				voceModificata = true;
				refresh();
			}
		}
	}

	private void apriDialogModifica() {
		if (valoriSpedizioneModificati) {
			DialogMessaggio.openError(titoloValoriModificati, messaggioValoriModificati);
		} else {
			VoceCalcolata selezione = getVoceSelezionata();
			if (selezione != null) {
				String valore = formatter.format(selezione.getCosto());
				InputDialog dialog = new InputDialog(null, "Nuovo Valore", "Inserisci il nuovo valore economico per la voce.", valore, inputValidator);
				if (dialog.open() == Window.OK) {
					try {
						String nuovoValore = dialog.getValue();
						selezione.setCosto(Double.parseDouble(nuovoValore));
						voceModificata = true;
						refresh();
					} catch (NumberFormatException e) {
						DialogMessaggio.openError(titoloErroreModifica, messaggioErroreModifica);
					}
				}
			}
		}
	}

	private void apriDialogElimina() {
		if (valoriSpedizioneModificati) {
			DialogMessaggio.openError(titoloValoriModificati, messaggioValoriModificati);
		} else {
			VoceCalcolata selezione = getVoceSelezionata();
			if (selezione != null) {
				boolean remove = risultato.getVoci().remove(selezione);
				if (remove) {
					voceModificata = true;
					refresh();
				} else {
					DialogMessaggio.openError(titoloErroreElimina, messaggioErroreElimina);
				}
			}
		}
	}

	public boolean isValoriSpedizioneModificati() {
		return valoriSpedizioneModificati;
	}

	public void setValoriSpedizioneModificati(boolean valoriSpedizioneModificati) {
		this.valoriSpedizioneModificati = valoriSpedizioneModificati;
	}

	public boolean isVoceModificata() {
		return voceModificata;
	}

	public void setVoceModificata(boolean voceModificata) {
		this.voceModificata = voceModificata;
	}

	@Override
	protected Ordinatore<VoceCalcolata> creaOrdinatore() {
		return new OrdinatoreVoceCalcolata();
	}

	@Override
	public void aggiornaContenuto() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected Etichettatore<VoceCalcolata> creaEtichettatore() {
		return new EtichettatoreVoceCalcolata();
	}

	@Override
	protected ModificatoreValoriCelle<VoceCalcolata> creaModificatore() {
		return null;
	}

}
