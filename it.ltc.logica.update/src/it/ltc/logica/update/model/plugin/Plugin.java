//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2017.04.11 alle 05:07:05 PM CEST 
//


package it.ltc.logica.update.model.plugin;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.XmlValue;


/**
 * <p>Classe Java per anonymous complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="download-size" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *       &lt;attribute name="install-size" type="{http://www.w3.org/2001/XMLSchema}byte" />
 *       &lt;attribute name="version" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="unpack" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "value"
})
@XmlRootElement(name = "plugin")
public class Plugin {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "id")
    protected String id;
    @XmlAttribute(name = "download-size")
    protected Byte downloadSize;
    @XmlAttribute(name = "install-size")
    protected Byte installSize;
    @XmlAttribute(name = "version")
    protected String version;
    @XmlAttribute(name = "unpack")
    protected String unpack;

    /**
     * Recupera il valore della proprietà value.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getValue() {
        return value;
    }

    /**
     * Imposta il valore della proprietà value.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Recupera il valore della proprietà id.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getId() {
        return id;
    }

    /**
     * Imposta il valore della proprietà id.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * Recupera il valore della proprietà downloadSize.
     * 
     * @return
     *     possible object is
     *     {@link Byte }
     *     
     */
    public Byte getDownloadSize() {
        return downloadSize;
    }

    /**
     * Imposta il valore della proprietà downloadSize.
     * 
     * @param value
     *     allowed object is
     *     {@link Byte }
     *     
     */
    public void setDownloadSize(Byte value) {
        this.downloadSize = value;
    }

    /**
     * Recupera il valore della proprietà installSize.
     * 
     * @return
     *     possible object is
     *     {@link Byte }
     *     
     */
    public Byte getInstallSize() {
        return installSize;
    }

    /**
     * Imposta il valore della proprietà installSize.
     * 
     * @param value
     *     allowed object is
     *     {@link Byte }
     *     
     */
    public void setInstallSize(Byte value) {
        this.installSize = value;
    }

    /**
     * Recupera il valore della proprietà version.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Imposta il valore della proprietà version.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * Recupera il valore della proprietà unpack.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnpack() {
        return unpack;
    }

    /**
     * Imposta il valore della proprietà unpack.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnpack(String value) {
        this.unpack = value;
    }

}
