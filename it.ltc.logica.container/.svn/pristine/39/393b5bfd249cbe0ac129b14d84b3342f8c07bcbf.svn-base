 
package it.ltc.logica.container.trasporti.parts;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeItem;

public class ListaFunzioniPart {
	@Inject
	public ListaFunzioniPart() {
		
	}
	
	@PostConstruct
	public void postConstruct(Composite parent) {
		
		Composite composite = new Composite(parent, SWT.NONE);
		
		Label lblListaDelleFunzioni = new Label(composite, SWT.NONE);
		lblListaDelleFunzioni.setBounds(10, 10, 105, 15);
		lblListaDelleFunzioni.setText("Lista delle Funzioni: ");
		
		Tree tree = new Tree(composite, SWT.BORDER);
		tree.setBounds(10, 31, 428, 257);
		List<String> listaFunzioni = getFunzioni();
		for (String funzione : listaFunzioni) {
			TreeItem trtmNewTreeitem = new TreeItem(tree, SWT.NONE);
			trtmNewTreeitem.setText(funzione);
		}
		
	}
	
	private List<String> getFunzioni() {
		List<String> listaFunzioni = new ArrayList<String>();
		listaFunzioni.add("Listini");
		listaFunzioni.add("Statistiche");
		return listaFunzioni;
	}
}