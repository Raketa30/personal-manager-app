
package ru.liga.application.domain.soap.position;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ru.liga.application.domain.soap.position package. 
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
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ru.liga.application.domain.soap.position
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetPositionListRequest }
     * 
     */
    public GetPositionListRequest createGetPositionListRequest() {
        return new GetPositionListRequest();
    }

    /**
     * Create an instance of {@link GetPositionListResponse }
     * 
     */
    public GetPositionListResponse createGetPositionListResponse() {
        return new GetPositionListResponse();
    }

    /**
     * Create an instance of {@link PositionDto }
     * 
     */
    public PositionDto createPositionDto() {
        return new PositionDto();
    }

    /**
     * Create an instance of {@link AddPositionRequest }
     * 
     */
    public AddPositionRequest createAddPositionRequest() {
        return new AddPositionRequest();
    }

    /**
     * Create an instance of {@link AddPositionResponse }
     * 
     */
    public AddPositionResponse createAddPositionResponse() {
        return new AddPositionResponse();
    }

    /**
     * Create an instance of {@link UpdatePositionRequest }
     * 
     */
    public UpdatePositionRequest createUpdatePositionRequest() {
        return new UpdatePositionRequest();
    }

    /**
     * Create an instance of {@link UpdatePositionResponse }
     * 
     */
    public UpdatePositionResponse createUpdatePositionResponse() {
        return new UpdatePositionResponse();
    }

    /**
     * Create an instance of {@link DeletePositionRequest }
     * 
     */
    public DeletePositionRequest createDeletePositionRequest() {
        return new DeletePositionRequest();
    }

    /**
     * Create an instance of {@link DeletePositionResponse }
     * 
     */
    public DeletePositionResponse createDeletePositionResponse() {
        return new DeletePositionResponse();
    }

}
