
package ru.liga.application.domain.soap.department;

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
 *         &lt;element name="departmentDtoList" type="{http://liga.ru/application/domain/soap/department}departmentDto" maxOccurs="unbounded"/&gt;
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
    "departmentDtoList"
})
@XmlRootElement(name = "getDepartmentListResponse")
public class GetDepartmentListResponse {

    @XmlElement(required = true)
    protected List<DepartmentDto> departmentDtoList;

    /**
     * Gets the value of the departmentDtoList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the departmentDtoList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDepartmentDtoList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DepartmentDto }
     * 
     * 
     */
    public List<DepartmentDto> getDepartmentDtoList() {
        if (departmentDtoList == null) {
            departmentDtoList = new ArrayList<DepartmentDto>();
        }
        return this.departmentDtoList;
    }

}
