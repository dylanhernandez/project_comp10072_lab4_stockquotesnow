
package ca.mohawkcollege.my;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ca.mohawkcollege.my package. 
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

    private final static QName _GetCurrentPrice_QNAME = new QName("http://my.mohawkcollege.ca/", "getCurrentPrice");
    private final static QName _GetCurrentPriceResponse_QNAME = new QName("http://my.mohawkcollege.ca/", "getCurrentPriceResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ca.mohawkcollege.my
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetCurrentPrice }
     * 
     */
    public GetCurrentPrice createGetCurrentPrice() {
        return new GetCurrentPrice();
    }

    /**
     * Create an instance of {@link GetCurrentPriceResponse }
     * 
     */
    public GetCurrentPriceResponse createGetCurrentPriceResponse() {
        return new GetCurrentPriceResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCurrentPrice }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://my.mohawkcollege.ca/", name = "getCurrentPrice")
    public JAXBElement<GetCurrentPrice> createGetCurrentPrice(GetCurrentPrice value) {
        return new JAXBElement<GetCurrentPrice>(_GetCurrentPrice_QNAME, GetCurrentPrice.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetCurrentPriceResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://my.mohawkcollege.ca/", name = "getCurrentPriceResponse")
    public JAXBElement<GetCurrentPriceResponse> createGetCurrentPriceResponse(GetCurrentPriceResponse value) {
        return new JAXBElement<GetCurrentPriceResponse>(_GetCurrentPriceResponse_QNAME, GetCurrentPriceResponse.class, null, value);
    }

}
