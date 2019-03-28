package it.ltc.logica.gui.task;

import java.lang.reflect.InvocationTargetException;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;

import it.ltc.logica.gui.dialog.DialogMessaggio;

/**
 * Classe astratta da estendere nei punti di codice dove vanno eseguite operazioni lente.
 * @author Damiano
 *
 */
public abstract class Processo {
	
	protected final String titolo;
	protected final Integer operazioni;
	private IProgressMonitor monitor;
	private final IRunnableWithProgress runnable;
	
	/**
	 * Costruisce un processo per cui andranno specificate le operazioni da eseguire.
	 * Se viene indicato un numero di operazioni nullo o minore di 0 allora vienesupposto che non si sappia quante sono.
	 * @param titolo Il messaggio da mostrare nella schermata di caricamento
	 * @param operazioni Il numero di operazioni da eseguire
	 */
	public Processo(String title, Integer operations) {
		titolo = title;
		if (operations == null || operations <= 0)
			operations = IProgressMonitor.UNKNOWN;
		operazioni = operations;
		runnable = new IRunnableWithProgress() {

			@Override
			public void run(IProgressMonitor m) throws InvocationTargetException, InterruptedException {
				monitor = m;
				if (monitor != null)
					monitor.beginTask(titolo, operazioni);
				try {
					eseguiOperazioni();
				} catch (Exception e) {
					throw new InvocationTargetException(e);
				}
				if (monitor != null)
					monitor.done();
			}
			
		};
	}
	
	public void aumentaProgresso(int progresso) {
		if (monitor != null)
			monitor.worked(progresso);
	}
	
	/**
	 * Questo metodo va implementato, qui vanno inserite le operazioni da eseguire.
	 * E' possibile e doveroso chiamare all'interno il metodo <code>monitor.worked(1)</code> ogniqualvolta viene eseguita una operazione
	 * @param monitor Il monitor che indica l'avanzamento di processo.
	 */
	public abstract void eseguiOperazioni() throws Exception;
	
	/**
	 * Metodo di convenienza, non va invocato mai a meno che non sia l'unico modo per eseguire il processo senza l'utilizzo di una dialog con progress bar.
	 */
	public void eseguiOperazioniSenzaProgresso() {
		try {
			eseguiOperazioni();
		} catch (Exception e) {
			String messaggio = "La procedura \u00E8 ha riscontrato un errore imprevisto: " + e.getLocalizedMessage() + "\r\n";
			for (StackTraceElement elemente : e.getStackTrace()) {
				messaggio += elemente.toString() + "\r\n";
			}
			DialogMessaggio.openError("Procedura interrotta", messaggio);
			e.printStackTrace();
		}
	}

	protected IRunnableWithProgress getRunnable() {
		return runnable;
	}

}
