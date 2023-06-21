package uk.gov.moj.sdt.producers.comx;

import javax.xml.bind.JAXBElement;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import uk.gov.moj.sdt.ws._2013.sdt.bulkrequestschema.BulkRequestType;
import uk.gov.moj.sdt.ws._2013.sdt.bulkresponseschema.BulkResponseType;
import uk.gov.moj.sdt.ws._2013.sdt.bulkresponseschema.ObjectFactory;
import uk.gov.moj.sdt.ws._2013.sdt.sdtendpoint.ISdtEndpointPortType;

/**
 * Test class for end to end web service tests..
 *
 * @author Robin Compston
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:/uk/gov/moj/sdt/producers/comx/spring*e2e.test.xml",
        "classpath*:/uk/gov/moj/sdt/utils/**/spring*.xml",
        "classpath*:/uk/gov/moj/sdt/transformers/**/spring*.xml"})
public class SubmitBulkTest extends AbstractWebServiceTest<BulkRequestType, BulkResponseType> {

    /**
     * Method to call remote submit bulk endpoint to be tested.
     */
    @Test
    public void testValid() {
        this.callWebService(BulkRequestType.class);
    }

    /**
     * Method to call remote submit bulk endpoint to be tested.
     */
    @Test
    public void testCountMismatch() {
        this.callWebService(BulkRequestType.class);
    }

    /**
     * Scenario - Request rejected with duplicate customer ref error.
     */
    @Test
    public void testDuplicateCustomerRef() {
        this.callWebService(BulkRequestType.class);
    }

    @Override
    protected BulkResponseType callTestWebService(final BulkRequestType request) {
        // Get the SOAP proxy client.
        ISdtEndpointPortType client = getSdtEndpointClient();

        // Call the specific business method for this text - note that a single test can only use one web service
        // business method.
        return client.submitBulk(request);
    }

    @Override
    protected JAXBElement<BulkResponseType> wrapJaxbObject(final BulkResponseType response) {
        // Use the provided factor to create a wrapped instance of the response.
        ObjectFactory objectFactory = new ObjectFactory();
        return objectFactory.createBulkResponse(response);
    }
}
