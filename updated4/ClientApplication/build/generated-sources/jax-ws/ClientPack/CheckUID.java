
package ClientPack;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CheckUID complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CheckUID">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="eIn" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CheckUID", propOrder = {
    "eIn"
})
public class CheckUID {

    protected String eIn;

    /**
     * Gets the value of the eIn property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEIn() {
        return eIn;
    }

    /**
     * Sets the value of the eIn property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEIn(String value) {
        this.eIn = value;
    }

}
