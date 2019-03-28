package it.ltc.logica.gui.dialog;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;

/**
 * Questa Dialog permette all'utente di selezionare un file nel suo computer.
 * L'utilizzo tipico è il seguente:
 * 
 * 	<code>
 * 	DialogSelezioneFile dialog = new DialogSelezioneFile();
 *	String path = dialog.open();
 *	if (path != null && !path.isEmpty()) {
 *		//Do Something
 *	}
 *	</code>
 * @author Damiano Bellucci - damiano.bellucci@gmail.com
 *
 */
public class DialogSelezioneFile extends FileDialog {

	public DialogSelezioneFile() {
		super(Display.getDefault().getActiveShell());
	}
	
	@Override
	protected void checkSubclass() {
		//DO NOTHING!
	}

}
