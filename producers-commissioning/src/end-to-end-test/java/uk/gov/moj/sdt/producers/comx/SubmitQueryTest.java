package uk.gov.moj.sdt.producers.comx;

import javax.xml.bind.JAXBElement;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import uk.gov.moj.sdt.ws._2013.sdt.sdtendpoint.ISdtEndpointPortType;
import uk.gov.moj.sdt.ws._2013.sdt.submitqueryrequestschema.SubmitQueryRequestType;
import uk.gov.moj.sdt.ws._2013.sdt.submitqueryresponseschema.ObjectFactory;
import uk.gov.moj.sdt.ws._2013.sdt.submitqueryresponseschema.SubmitQueryResponseType;

/**
 * Test class for end to end web service tests..
 *
 * @author Robin Compston
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest
@ContextConfiguration(locations = {"classpath*:/uk/gov/moj/sdt/producers/comx/spring*e2e.test.xml",
        "classpath*:/uk/gov/moj/sdt/utils/**/spring*.xml", "classpath*:/uk/gov/moj/sdt/transformers/**/spring*.xml"})
public class SubmitQueryTest extends AbstractWebServiceTest<SubmitQueryRequestType, SubmitQueryResponseType> {

    /**
     * Method to call remote submit query endpoint to be tested.
     */
    @Test
    public void testValidRequestForMCOLDefence1() {
        this.callWebService(SubmitQueryRequestType.class);
    }

    /**
     * Method to call remote submit query endpoint to be tested.
     */
    @Test
    public void testValidRequestForMCOLDefence2() {
        this.callWebService(SubmitQueryRequestType.class);
    }

    /**
     * Method to call remote submit query endpoint to be tested.
     */
    @Test
    public void testValidRequestForMCOLDefence3() {
        this.callWebService(SubmitQueryRequestType.class);
    }

    /**
     * Method to call remote submit query endpoint to be tested.
     */
    @Test
    public void testValidRequestForMCOLDefence4() {
        this.callWebService(SubmitQueryRequestType.class);
    }

    /**
     * Method to call remote submit query endpoint to be tested.
     */
    @Test
    public void testValidRequestForMCOLDefence5() {
        this.callWebService(SubmitQueryRequestType.class);
    }

    /**
     * Method to call remote submit query endpoint to be tested.
     */
    @Test
    public void testValidRequestForMCOLDefence6() {
        this.callWebService(SubmitQueryRequestType.class);
    }

    @Override
    protected SubmitQueryResponseType callTestWebService(final SubmitQueryRequestType request) {
        // Get the SOAP proxy client.
        ISdtEndpointPortType client = getSdtEndpointClient();

        // Call the specific business method for this text - note that a single test can only use one web service
        // business method.
        return client.submitQuery(request);
    }

    @Override
    protected JAXBElement<SubmitQueryResponseType> wrapJaxbObject(final SubmitQueryResponseType response) {
        // Use the provided factor to create a wrapped instance of the response.
        ObjectFactory objectFactory = new ObjectFactory();
        return objectFactory.createSubmitQueryResponse(response);
    }
}
