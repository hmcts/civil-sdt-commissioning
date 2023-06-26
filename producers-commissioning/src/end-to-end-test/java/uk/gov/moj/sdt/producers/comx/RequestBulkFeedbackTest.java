package uk.gov.moj.sdt.producers.comx;

import javax.xml.bind.JAXBElement;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import uk.gov.moj.sdt.ws._2013.sdt.bulkfeedbackrequestschema.BulkFeedbackRequestType;
import uk.gov.moj.sdt.ws._2013.sdt.bulkfeedbackresponseschema.BulkFeedbackResponseType;
import uk.gov.moj.sdt.ws._2013.sdt.bulkfeedbackresponseschema.ObjectFactory;
import uk.gov.moj.sdt.ws._2013.sdt.sdtendpoint.ISdtEndpointPortType;

/**
 * Test class for end to end web service tests..
 *
 * @author Sally Vonka
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath*:/uk/gov/moj/sdt/producers/comx/spring*e2e.test.xml",
        "classpath*:/uk/gov/moj/sdt/utils/**/spring*.xml", "classpath*:/uk/gov/moj/sdt/transformers/**/spring*.xml"})
public class RequestBulkFeedbackTest extends AbstractWebServiceTest<BulkFeedbackRequestType, BulkFeedbackResponseType> {
    /**
     * Method to call remote request bulk feedback endpoint to be tested.
     */
    @Test
    public void testValidRequestBulkFeedbackB00000002() {
        this.callWebService(BulkFeedbackRequestType.class);
    }

    @Test
    public void testValidRequestBulkFeedbackA00000001() {
        this.callWebService(BulkFeedbackRequestType.class);
    }

    @Test
    public void testValidRequestBulkFeedbackB00000001() {
        this.callWebService(BulkFeedbackRequestType.class);
    }

    @Test
    public void testValidRequestBulkFeedbackC00000001() {
        this.callWebService(BulkFeedbackRequestType.class);
    }

    /**
     * Method to call remote request bulk feedback endpoint to be tested.
     */
    @Test
    public void testInvalidRequestBulkFeedback() {
        this.callWebService(BulkFeedbackRequestType.class);
    }

    @Override
    protected BulkFeedbackResponseType callTestWebService(final BulkFeedbackRequestType request) {
        // Get the SOAP proxy client.
        ISdtEndpointPortType client = getSdtEndpointClient();

        // Call the specific business method for this text - note that a single test can only use one web service
        // business method.
        return client.getBulkFeedback(request);
    }

    @Override
    protected JAXBElement<BulkFeedbackResponseType> wrapJaxbObject(final BulkFeedbackResponseType response) {
        // Use the provided factor to create a wrapped instance of the response.
        ObjectFactory objectFactory = new ObjectFactory();
        return objectFactory.createBulkFeedbackResponse(response);
    }

}
