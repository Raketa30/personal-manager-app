
package ru.liga.application.domain.soap.department;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.liga.application.domain.soap.department package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.liga.application.domain.soap.department
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetDepartmentListRequest }
     * 
     */
    public GetDepartmentListRequest createGetDepartmentListRequest() {
        return new GetDepartmentListRequest();
    }

    /**
     * Create an instance of {@link GetDepartmentListResponse }
     * 
     */
    public GetDepartmentListResponse createGetDepartmentListResponse() {
        return new GetDepartmentListResponse();
    }

    /**
     * Create an instance of {@link DepartmentDto }
     * 
     */
    public DepartmentDto createDepartmentDto() {
        return new DepartmentDto();
    }

    /**
     * Create an instance of {@link AddDepartmentRequest }
     * 
     */
    public AddDepartmentRequest createAddDepartmentRequest() {
        return new AddDepartmentRequest();
    }

    /**
     * Create an instance of {@link AddDepartmentResponse }
     * 
     */
    public AddDepartmentResponse createAddDepartmentResponse() {
        return new AddDepartmentResponse();
    }

    /**
     * Create an instance of {@link UpdateDepartmentRequest }
     * 
     */
    public UpdateDepartmentRequest createUpdateDepartmentRequest() {
        return new UpdateDepartmentRequest();
    }

    /**
     * Create an instance of {@link UpdateDepartmentResponse }
     * 
     */
    public UpdateDepartmentResponse createUpdateDepartmentResponse() {
        return new UpdateDepartmentResponse();
    }

    /**
     * Create an instance of {@link DeleteDepartmentRequest }
     * 
     */
    public DeleteDepartmentRequest createDeleteDepartmentRequest() {
        return new DeleteDepartmentRequest();
    }

    /**
     * Create an instance of {@link DeleteDepartmentResponse }
     * 
     */
    public DeleteDepartmentResponse createDeleteDepartmentResponse() {
        return new DeleteDepartmentResponse();
    }

}
