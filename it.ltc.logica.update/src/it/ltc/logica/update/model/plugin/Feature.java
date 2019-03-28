//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2017.04.11 alle 05:07:05 PM CEST 
//


package it.ltc.logica.update.model.plugin;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java per anonymous complex type.
 * 
 * <p>Il seguente frammento di schema specifica il contenuto previsto contenuto in questa classe.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}description"/>
 *         &lt;element ref="{}copyright"/>
 *         &lt;element ref="{}license"/>
 *         &lt;element ref="{}includes"/>
 *         &lt;element ref="{}plugin" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="label" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="version" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="provider-name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "description",
    "copyright",
    "license",
    "includes",
    "plugin"
})
@XmlRootElement(name = "feature")
public class Feature {

    @XmlElement(required = true)
    protected Description description;
    @XmlElement(required = true)
    protected Copyright copyright;
    @XmlElement(required = true)
    protected License license;
    @XmlElement(required = true)
    protected Includes includes;
    protected List<Plugin> plugin;
    @XmlAttribute(name = "id")
    protected String id;
    @XmlAttribute(name = "label")
    protected String label;
    @XmlAttribute(name = "version")
    protected String version;
    @XmlAttribute(name = "provider-name")
    protected String providerName;

    /**
     * Recupera il valore della proprietà description.
     * 
     * @return
     *     possible object is
     *     {@link Description }
     *     
     */
    public Description getDescription() {
        return description;
    }

    /**
     * Imposta il valore della proprietà description.
     * 
     * @param value
     *     allowed object is
     *     {@link Description }
     *     
     */
    public void setDescription(Description value) {
        this.description = value;
    }

    /**
     * Recupera il valore della proprietà copyright.
     * 
     * @return
     *     possible object is
     *     {@link Copyright }
     *     
     */
    public Copyright getCopyright() {
        return copyright;
    }

    /**
     * Imposta il valore della proprietà copyright.
     * 
     * @param value
     *     allowed object is
     *     {@link Copyright }
     *     
     */
    public void setCopyright(Copyright value) {
        this.copyright = value;
    }

    /**
     * Recupera il valore della proprietà license.
     * 
     * @return
     *     possible object is
     *     {@link License }
     *     
     */
    public License getLicense() {
        return license;
    }

    /**
     * Imposta il valore della proprietà license.
     * 
     * @param value
     *     allowed object is
     *     {@link License }
     *     
     */
    public void setLicense(License value) {
        this.license = value;
    }

    /**
     * Recupera il valore della proprietà includes.
     * 
     * @return
     *     possible object is
     *     {@link Includes }
     *     
     */
    public Includes getIncludes() {
        return includes;
    }

    /**
     * Imposta il valore della proprietà includes.
     * 
     * @param value
     *     allowed object is
     *     {@link Includes }
     *     
     */
    public void setIncludes(Includes value) {
        this.includes = value;
    }

    /**
     * Gets the value of the plugin property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the plugin property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPlugin().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Plugin }
     * 
     * 
     */
    public List<Plugin> getPlugin() {
        if (plugin == null) {
            plugin = new ArrayList<Plugin>();
        }
        return this.plugin;
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
     * Recupera il valore della proprietà label.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabel() {
        return label;
    }

    /**
     * Imposta il valore della proprietà label.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLabel(String value) {
        this.label = value;
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
     * Recupera il valore della proprietà providerName.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProviderName() {
        return providerName;
    }

    /**
     * Imposta il valore della proprietà providerName.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProviderName(String value) {
        this.providerName = value;
    }

}
