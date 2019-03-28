package it.ltc.logica.utilities.pdf.tabelle;

public class ColonnaPDF {

    private String name;
    private float width;

    public ColonnaPDF(String name, float width) {
        this.name = name;
        this.width = width;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

	@Override
	public String toString() {
		return "ColonnaPDF [name=" + name + ", width=" + width + "]";
	}
    
}