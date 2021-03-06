package it.ltc.logica.container.lifecycle;

import java.io.File;

import org.eclipse.e4.core.contexts.IEclipseContext;
import org.eclipse.e4.ui.di.UISynchronize;
import org.eclipse.e4.ui.workbench.lifecycle.PostContextCreate;
import org.eclipse.equinox.app.IApplicationContext;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Monitor;
import org.eclipse.swt.widgets.Shell;

import it.ltc.logica.container.controller.ControllerUtente;
import it.ltc.logica.container.dialogs.PasswordDialog;

@SuppressWarnings("restriction")
public class Manager {

	@PostContextCreate
	void postContextCreate(IEclipseContext context, IApplicationContext appContext, Display display, UISynchronize sync) {
		
		//Genero il controller utente
		ControllerUtente.createInstance(sync, context);
		
		//Ripulisco i file temporanei non più necessari
		pulisciFileTemporanei();
		
		// Inizializzo il DB Locale se non esiste
		//ControllerLocalDB.getInstance(); //TODO - Controllare se fosse ancora necessario.
		
		final Shell shell = new Shell(SWT.SHELL_TRIM);
		PasswordDialog dialog = new PasswordDialog(shell);

		// close the static splash screen
		appContext.applicationRunning();

		// position the shell
		setLocation(display, shell);

		if (dialog.open() != Window.OK) {
			// close the application
			System.exit(-1);
		}
	}

	private void pulisciFileTemporanei() {
		//Report vecchi
		String baseFolder = System.getProperty("user.dir");
		String separator = System.getProperty("file.separator");
		File folder = new File(baseFolder + separator + "report" + separator + "temp" + separator);
		if (!folder.exists())
			folder.mkdirs();
		for (File vecchioReport : folder.listFiles()) {
			if (vecchioReport.isFile() && (vecchioReport.getName().endsWith("pdf") || vecchioReport.getName().endsWith("PDF"))) {
				boolean delete = vecchioReport.delete();
				if (!delete)
					System.out.println("Impossibile cancellare il file temporaneo: " + vecchioReport.getAbsolutePath());
			}
		}
	}

	private void setLocation(Display display, Shell shell) {
		Monitor monitor = display.getPrimaryMonitor();
		Rectangle monitorRect = monitor.getBounds();
		Rectangle shellRect = shell.getBounds();
		int x = monitorRect.x + (monitorRect.width - shellRect.width) / 2;
		int y = monitorRect.y + (monitorRect.height - shellRect.height) / 2;
		shell.setLocation(x, y);
	}
}
