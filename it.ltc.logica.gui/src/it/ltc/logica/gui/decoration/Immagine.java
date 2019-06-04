package it.ltc.logica.gui.decoration;

import java.io.InputStream;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.graphics.PaletteData;
import org.eclipse.swt.widgets.Display;

public enum Immagine {
	
	COPIA_16X16("/it/ltc/logica/gui/resources/copy_16x16.png"),
	
	CODICE_16X16("/it/ltc/logica/gui/resources/codice_16x16.png"),
	CAP_16X16("/it/ltc/logica/gui/resources/cap_16x16.png"),
	LISTINO_16X16("/it/ltc/logica/gui/resources/listino_16x16.png"),
	SALVADANAIO_16X16("/it/ltc/logica/gui/resources/salvadanaio_16x16.png"),
	IMPOSTAZIONI_16X16("/it/ltc/logica/gui/resources/impostazioni_16x16.png"),
	CALCOLO_16X16("/it/ltc/logica/gui/resources/calcolo_16x16.png"),
	WIZARD_16X16("/it/ltc/logica/gui/resources/wizard_16x16.png"),
	TRASPORTI_16X16("/it/ltc/logica/gui/resources/trasporti_16x16.png"),
	TELEFONO_16X16("/it/ltc/logica/gui/resources/telefono_16x16.png"),
	MONETE_16X16("/it/ltc/logica/gui/resources/monete_16x16.png"),
	MAIL_16X16("/it/ltc/logica/gui/resources/mail_16x16.png"),
	
	SCATOLA_16X16("/it/ltc/logica/gui/resources/scatola_16x16.png"),
	SCATOLA_ANNULLATA_16X16("/it/ltc/logica/gui/resources/scatolaAnnullata_16x16.png"),
	
	UOMOSCRIVANIA_16X16("/it/ltc/logica/gui/resources/uomoScrivania_16x16.png"),
	TIMER_16X16("/it/ltc/logica/gui/resources/timer_16x16.png"),
	REFRESH_16X16("/it/ltc/logica/gui/resources/refresh_16x16.png"),
	CESTINO_16X16("/it/ltc/logica/gui/resources/cestino_16x16.png"),
	CESTINO_ANNULLATO_16X16("/it/ltc/logica/gui/resources/cestinoAnnullato_16x16.png"),
	MATITA_16X16("/it/ltc/logica/gui/resources/matita_16x16.png"),
	MATITA_ANNULLATA_16X16("/it/ltc/logica/gui/resources/matitaAnnullata_16x16.png"),
	LENTE_16X16("/it/ltc/logica/gui/resources/lente_16x16.png"),
	LENTE_ANNULLATA_16X16("/it/ltc/logica/gui/resources/lenteAnnullata_16x16.png"),
	REPORT_16X16("/it/ltc/logica/gui/resources/report_16x16.png"),
	
	SACCO_SOLDI_16X16("/it/ltc/logica/gui/resources/saccoSoldi_16x16.png"),
	SOLDISU_16X32("/it/ltc/logica/gui/resources/soldiSu_16x32.png"),
	SOLDIGIU_16X32("/it/ltc/logica/gui/resources/soldiGiu_16x32.png"),
	SOLDIGIUGIALLO_16X32("/it/ltc/logica/gui/resources/soldiGiuGiallo_16x32.png"),
	
	SPUNTAVERDE_16X16("/it/ltc/logica/gui/resources/spuntaVerde_16x16.png"),
	SPUNTAVERDE_16X50("/it/ltc/logica/gui/resources/spuntaVerde_16x50.png"),
	CROCEROSSA_16X16("/it/ltc/logica/gui/resources/croceRossa_16x16.png"),
	CROCEROSSA_16X50("/it/ltc/logica/gui/resources/croceRossa_16x50.png"),
	
	CROCEVERDE_16X16("/it/ltc/logica/gui/resources/croceVerde_16x16.png"),
	CROCEVERDE_ANNULLATA_16X16("/it/ltc/logica/gui/resources/croceVerdeAnnullata_16x16.png"),
	CROCIVERDI_16X16("/it/ltc/logica/gui/resources/crociVerdi_16x16.png"),
	
	EXCEL_16X16("/it/ltc/logica/gui/resources/excel_16x16.png"),
	EXCEL_ANNULLATO_16X16("/it/ltc/logica/gui/resources/excelAnnullato_16x16.png"),
	
	PDF_16X16("/it/ltc/logica/gui/resources/pdf_16x16.png"),
	PDF_ANNULLATO_16X16("/it/ltc/logica/gui/resources/pdfAnnullato_16x16.png"),
	
	FRECCIAVERDESU_16X16("/it/ltc/logica/gui/resources/frecciaVerdeSu_16x16.png"),
	FRECCIABLUSU_16X16("/it/ltc/logica/gui/resources/frecciaBluSu_16x16.png"),
	FRECCIAGIALLAGIU_16X16("/it/ltc/logica/gui/resources/frecciaGiallaGiu_16x16.png"),
	FRECCIAROSSAGIU_16X16("/it/ltc/logica/gui/resources/frecciaRossaGiu_16x16.png"),
	FRECCEROSSEGIU_16X16("/it/ltc/logica/gui/resources/frecceRosseGiu_16x16.png"),
	
	RED_QUESTION_MARK_12X20("/it/ltc/logica/gui/resources/redQuestionMark_12x20.png"),
	RED_MARK_12X20("/it/ltc/logica/gui/resources/redMark_12x20.png"),
	ORANGE_MARK_12X20("/it/ltc/logica/gui/resources/orangeMark_12x20.png"),
	YELLOW_MARK_12X20("/it/ltc/logica/gui/resources/yellowMark_12x20.png"),
	
	UTENTE_16X16("/it/ltc/logica/gui/resources/user_16x16.png"),
	
	TRASPARENTE_16X16("/it/ltc/logica/gui/resources/trasparente_16x16.png"),
	
	LOADING_256X256("/it/ltc/logica/gui/resources/loading_256x256.png");
	
	private final String path;
	private final Image image;
	private final Image imageWithLeftPadding;
	
	private Immagine(String path) {
		this.path = path;
		InputStream imageStream = Immagine.class.getResourceAsStream(path);
		InputStream paddingStream = Immagine.class.getResourceAsStream("/it/ltc/logica/gui/resources/trasparente_16x16.png");
		image = new Image(Display.getCurrent(), imageStream);
		Image padding = new Image(Display.getCurrent(), paddingStream);
		imageWithLeftPadding = combineImages(padding, image);
		//pulisco
		padding.dispose();
	}
	
	public String getPath() {
		return path;
	}
	
	public Image getImage() {
		return image;
	}
	
	public Image getImageWithLeftPadding() {
		return imageWithLeftPadding;
	}

	private static Image combineImages(Image... images) {
		Image combinedImages;
		if (images.length > 0) {
				int h = 0;
				int w = 0;
				int d = 0;
				PaletteData pd = null;
				for (Image image : images) {
					ImageData data = image.getImageData();
					// prendo la profondità maggiore
					if (d < data.depth)
						d = data.depth;
					// Palette data
					if (pd == null)
						pd = data.palette;
					// Prendo l'altezza maggiore
					if (h < data.height) {
						h = data.height;
					}
					// Combino le lunghezze
					w += data.width;
				}
				// Creo la base
				ImageData combinedImagesData = new ImageData(w, h, d, pd);
				// Copio le immagini dentro
				int cw = 0;
				for (Image image : images) {
					ImageData data = image.getImageData();
					for (int x = data.x; x < data.width; x++) {
						for (int y = data.y; y < data.height; y++) {
							combinedImagesData.setPixel(x + cw, y, data.getPixel(x, y));
						}
					}
					cw += data.width;
				}
				combinedImages = new Image(Display.getCurrent(), combinedImagesData);
		} else {
			combinedImages = null;
		}
		return combinedImages;
	}

}
