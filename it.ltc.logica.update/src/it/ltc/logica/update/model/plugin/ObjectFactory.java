//
// Questo file è stato generato dall'architettura JavaTM per XML Binding (JAXB) Reference Implementation, v2.2.8-b130911.1802 
// Vedere <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Qualsiasi modifica a questo file andrà persa durante la ricompilazione dello schema di origine. 
// Generato il: 2017.04.11 alle 05:07:05 PM CEST 
//


package it.ltc.logica.update.model.plugin;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the it.ltc.logica.update.model.plugin package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: it.ltc.logica.update.model.plugin
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link License }
     * 
     */
    public License createLicense() {
        return new License();
    }

    /**
     * Create an instance of {@link Copyright }
     * 
     */
    public Copyright createCopyright() {
        return new Copyright();
    }

    /**
     * Create an instance of {@link Plugin }
     * 
     */
    public Plugin createPlugin() {
        return new Plugin();
    }

    /**
     * Create an instance of {@link Feature }
     * 
     */
    public Feature createFeature() {
        return new Feature();
    }

    /**
     * Create an instance of {@link Description }
     * 
     */
    public Description createDescription() {
        return new Description();
    }

    /**
     * Create an instance of {@link Includes }
     * 
     */
    public Includes createIncludes() {
        return new Includes();
    }

}
