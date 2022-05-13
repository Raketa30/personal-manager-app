
package ru.liga.application.domain.soap.position;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


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
 *         &lt;element name="positionDtoList" type="{http://liga.ru/application/domain/soap/position}positionDto" maxOccurs="unbounded"/&gt;
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
    "positionDtoList"
})
@XmlRootElement(name = "getPositionListResponse")
public class GetPositionListResponse {

    @XmlElement(required = true)
    protected List<PositionDto> positionDtoList;

    /**
     * Gets the value of the positionDtoList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the positionDtoList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPositionDtoList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PositionDto }
     * 
     * 
     */
    public List<PositionDto> getPositionDtoList() {
        if (positionDtoList == null) {
            positionDtoList = new ArrayList<PositionDto>();
        }
        return this.positionDtoList;
    }

}
