
package ru.liga.application.domain.soap.employee;

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
 *         &lt;element name="employeeDtoList" type="{http://liga.ru/application/domain/soap/employee}employeeDto" maxOccurs="unbounded"/&gt;
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
    "employeeDtoList"
})
@XmlRootElement(name = "getEmployeeListResponse")
public class GetEmployeeListResponse {

    @XmlElement(required = true)
    protected List<EmployeeDto> employeeDtoList;

    /**
     * Gets the value of the employeeDtoList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the employeeDtoList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEmployeeDtoList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link EmployeeDto }
     * 
     * 
     */
    public List<EmployeeDto> getEmployeeDtoList() {
        if (employeeDtoList == null) {
            employeeDtoList = new ArrayList<EmployeeDto>();
        }
        return this.employeeDtoList;
    }

}
