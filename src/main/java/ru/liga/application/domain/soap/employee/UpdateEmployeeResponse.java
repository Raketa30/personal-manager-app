
package ru.liga.application.domain.soap.employee;

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
 *         &lt;element name="employeeDto" type="{http://liga.ru/application/domain/soap/employee}employeeDto"/&gt;
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
    "employeeDto"
})
@XmlRootElement(name = "updateEmployeeResponse")
public class UpdateEmployeeResponse {

    @XmlElement(required = true)
    protected EmployeeDto employeeDto;

    /**
     * Gets the value of the employeeDto property.
     * 
     * @return
     *     possible object is
     *     {@link EmployeeDto }
     *     
     */
    public EmployeeDto getEmployeeDto() {
        return employeeDto;
    }

    /**
     * Sets the value of the employeeDto property.
     * 
     * @param value
     *     allowed object is
     *     {@link EmployeeDto }
     *     
     */
    public void setEmployeeDto(EmployeeDto value) {
        this.employeeDto = value;
    }

}
