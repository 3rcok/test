//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2014.04.18 at 05:44:47 AM PDT 
//


package com.odisei.provisioning;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SlaveExtensionInfoType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SlaveExtensionInfoType">
 *   &lt;complexContent>
 *     &lt;extension base="{}ExtensionInfoType">
 *       &lt;attribute name="masterExt" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SlaveExtensionInfoType")
public class SlaveExtensionInfoType
    extends ExtensionInfoType
{

    @XmlAttribute
    protected String masterExt;

    /**
     * Gets the value of the masterExt property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMasterExt() {
        return masterExt;
    }

    /**
     * Sets the value of the masterExt property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMasterExt(String value) {
        this.masterExt = value;
    }

}
