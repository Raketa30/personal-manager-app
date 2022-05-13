
package ru.liga.application.domain.soap.position;

import javax.xml.bind.annotation.*;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="positionDto" type="{http://liga.ru/application/domain/soap/position}positionDto"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "positionDto"
})
@XmlRootElement(name = "addPositionRequest")
public class AddPositionRequest {

    @XmlElement(required = true)
    protected PositionDto positionDto;

    /**
     * Gets the value of the positionDto property.
     * 
     * @return
     *     possible object is
     *     {@link PositionDto }
     *     
     */
    public PositionDto getPositionDto() {
        return positionDto;
    }

    /**
     * Sets the value of the positionDto property.
     * 
     * @param value
     *     allowed object is
     *     {@link PositionDto }
     *     
     */
    public void setPositionDto(PositionDto value) {
        this.positionDto = value;
    }

}
