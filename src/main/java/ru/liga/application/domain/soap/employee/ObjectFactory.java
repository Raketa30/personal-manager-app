
package ru.liga.application.domain.soap.employee;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.liga.application.domain.soap.employee package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.liga.application.domain.soap.employee
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetEmployeeListRequest }
     * 
     */
    public GetEmployeeListRequest createGetEmployeeListRequest() {
        return new GetEmployeeListRequest();
    }

    /**
     * Create an instance of {@link GetEmployeeListResponse }
     * 
     */
    public GetEmployeeListResponse createGetEmployeeListResponse() {
        return new GetEmployeeListResponse();
    }

    /**
     * Create an instance of {@link EmployeeDto }
     * 
     */
    public EmployeeDto createEmployeeDto() {
        return new EmployeeDto();
    }

    /**
     * Create an instance of {@link GetEmployeeByIdRequest }
     * 
     */
    public GetEmployeeByIdRequest createGetEmployeeByIdRequest() {
        return new GetEmployeeByIdRequest();
    }

    /**
     * Create an instance of {@link GetEmployeeByIdResponse }
     * 
     */
    public GetEmployeeByIdResponse createGetEmployeeByIdResponse() {
        return new GetEmployeeByIdResponse();
    }

    /**
     * Create an instance of {@link AddEmployeeRequest }
     * 
     */
    public AddEmployeeRequest createAddEmployeeRequest() {
        return new AddEmployeeRequest();
    }

    /**
     * Create an instance of {@link AddEmployeeResponse }
     * 
     */
    public AddEmployeeResponse createAddEmployeeResponse() {
        return new AddEmployeeResponse();
    }

    /**
     * Create an instance of {@link UpdateEmployeeRequest }
     * 
     */
    public UpdateEmployeeRequest createUpdateEmployeeRequest() {
        return new UpdateEmployeeRequest();
    }

    /**
     * Create an instance of {@link UpdateEmployeeResponse }
     * 
     */
    public UpdateEmployeeResponse createUpdateEmployeeResponse() {
        return new UpdateEmployeeResponse();
    }

    /**
     * Create an instance of {@link DeleteEmployeeRequest }
     * 
     */
    public DeleteEmployeeRequest createDeleteEmployeeRequest() {
        return new DeleteEmployeeRequest();
    }

    /**
     * Create an instance of {@link DeleteEmployeeResponse }
     * 
     */
    public DeleteEmployeeResponse createDeleteEmployeeResponse() {
        return new DeleteEmployeeResponse();
    }

}
