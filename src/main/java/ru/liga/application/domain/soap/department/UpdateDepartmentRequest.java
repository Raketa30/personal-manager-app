
package ru.liga.application.domain.soap.department;

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
 *         &lt;element name="departmentDto" type="{http://liga.ru/application/domain/soap/department}departmentDto"/&gt;
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
    "departmentDto"
})
@XmlRootElement(name = "updateDepartmentRequest")
public class UpdateDepartmentRequest {

    @XmlElement(required = true)
    protected DepartmentDto departmentDto;

    /**
     * Gets the value of the departmentDto property.
     * 
     * @return
     *     possible object is
     *     {@link DepartmentDto }
     *     
     */
    public DepartmentDto getDepartmentDto() {
        return departmentDto;
    }

    /**
     * Sets the value of the departmentDto property.
     * 
     * @param value
     *     allowed object is
     *     {@link DepartmentDto }
     *     
     */
    public void setDepartmentDto(DepartmentDto value) {
        this.departmentDto = value;
    }

}
