package it.ltc.logica.utilities.report;

public enum ReportExportType {
	
	PDF(".pdf"),
	XLS(".xlsx");
	
	private final String fileExtension;
	
	private ReportExportType(String fileExtension) {
		this.fileExtension = fileExtension;
	}
	
	public String getExtension() {
		return fileExtension;
	}

}
