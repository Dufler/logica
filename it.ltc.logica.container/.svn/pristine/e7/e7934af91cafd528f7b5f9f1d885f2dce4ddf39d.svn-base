 
package it.ltc.logica.container.user.parts;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TabFolder;
import org.eclipse.swt.widgets.TabItem;

public class FunzioneSelezionataPart {
	
	private static FunzioneSelezionataPart instance;
	
	private TabFolder tabFolder;
	private TabItem tbtmInfo;
	private TabItem tbtmStatistiche;
	private TabItem tbtmToDo;
	private Composite compositeInfo;
	private Composite compositeStatistiche;
	private Composite compositeToDo;
	
	@Inject
	public FunzioneSelezionataPart() {
		instance = this;
	}
	
	public static FunzioneSelezionataPart getInstance() {
		return instance;
	}
	
	@PostConstruct
	public void postConstruct(Composite parent) {
		
		tabFolder = new TabFolder(parent, SWT.NONE);
		tabFolder.setBounds(0, 0, 121, 43);
		
		tbtmInfo = new TabItem(tabFolder, SWT.NONE);
		tbtmInfo.setToolTipText("Le tue informazioni personali");
		tbtmInfo.setText("Info");
		//Info
		compositeInfo = new Composite(tabFolder, SWT.NONE);
		tbtmInfo.setControl(compositeInfo);
		Label labelInfo = new Label(compositeInfo, SWT.NONE);
		labelInfo.setBounds(10, 10, 77, 15);
		labelInfo.setText("Info");
		
		tbtmStatistiche = new TabItem(tabFolder, SWT.NONE);
		tbtmStatistiche.setToolTipText("Puoi realizzare statistiche");
		tbtmStatistiche.setText("Statistiche");
		//Statistiche
		compositeStatistiche = new Composite(tabFolder, SWT.NONE);
		tbtmStatistiche.setControl(compositeStatistiche);
		Label labelStatistiche = new Label(compositeStatistiche, SWT.NONE);
		labelStatistiche.setBounds(10, 10, 77, 15);
		labelStatistiche.setText("Statistiche");
		
		tbtmToDo = new TabItem(tabFolder, SWT.NONE);
		tbtmToDo.setToolTipText("La lista dei compiti che ti sono stati assegnati");
		tbtmToDo.setText("TO-DO List");
		//TO-DO List
		compositeToDo = new Composite(tabFolder, SWT.NONE);
		tbtmToDo.setControl(compositeToDo);
		Label labelToDo = new Label(compositeToDo, SWT.NONE);
		labelToDo.setBounds(10, 10, 77, 15);
		labelToDo.setText("TO-DO List");
	}
	
	public void mostraInfo() {
		System.out.println("Mostra Info");
		tabFolder.setSelection(tbtmInfo);
	}
	
	public void mostraStatistiche() {
		System.out.println("Mostra Statistiche");
		tabFolder.setSelection(tbtmStatistiche);
	}
	
	public void mostraToDo() {
		System.out.println("Mostra TO-DO List");
		tabFolder.setSelection(tbtmToDo);
	}
}