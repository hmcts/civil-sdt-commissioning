package uk.gov.moj.sdt.ws._2013.sdt.sdtendpoint;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.7.5
 * 2014-12-02T14:31:19.752Z
 * Generated source version: 2.7.5
 * 
 */
@WebService(targetNamespace = "http://ws.sdt.moj.gov.uk/2013/sdt/SdtEndpoint", name = "ISdtEndpointPortType")
@XmlSeeAlso({uk.gov.moj.sdt.ws._2013.sdt.baseschema.ObjectFactory.class, uk.gov.moj.sdt.ws._2013.sdt.submitqueryresponseschema.ObjectFactory.class, uk.gov.moj.sdt.ws._2013.sdt.submitqueryrequestschema.ObjectFactory.class, uk.gov.moj.sdt.ws._2013.sdt.bulkresponseschema.ObjectFactory.class, uk.gov.moj.sdt.ws._2013.sdt.bulkfeedbackresponseschema.ObjectFactory.class, uk.gov.moj.sdt.ws._2013.sdt.bulkrequestschema.ObjectFactory.class, uk.gov.moj.sdt.ws._2013.sdt.bulkfeedbackrequestschema.ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface ISdtEndpointPortType {

    @WebResult(name = "submitQueryResponse", targetNamespace = "http://ws.sdt.moj.gov.uk/2013/sdt/SubmitQueryResponseSchema", partName = "submitQueryResponse")
    @WebMethod
    public uk.gov.moj.sdt.ws._2013.sdt.submitqueryresponseschema.SubmitQueryResponseType submitQuery(
        @WebParam(partName = "submitQueryRequest", name = "submitQueryRequest", targetNamespace = "http://ws.sdt.moj.gov.uk/2013/sdt/SubmitQueryRequestSchema")
        uk.gov.moj.sdt.ws._2013.sdt.submitqueryrequestschema.SubmitQueryRequestType submitQueryRequest
    );

    @WebResult(name = "bulkFeedbackResponse", targetNamespace = "http://ws.sdt.moj.gov.uk/2013/sdt/BulkFeedbackResponseSchema", partName = "bulkFeedbackResponse")
    @WebMethod
    public uk.gov.moj.sdt.ws._2013.sdt.bulkfeedbackresponseschema.BulkFeedbackResponseType getBulkFeedback(
        @WebParam(partName = "bulkFeedbackRequest", name = "bulkFeedbackRequest", targetNamespace = "http://ws.sdt.moj.gov.uk/2013/sdt/BulkFeedbackRequestSchema")
        uk.gov.moj.sdt.ws._2013.sdt.bulkfeedbackrequestschema.BulkFeedbackRequestType bulkFeedbackRequest
    );

    @WebResult(name = "bulkResponse", targetNamespace = "http://ws.sdt.moj.gov.uk/2013/sdt/BulkResponseSchema", partName = "bulkResponse")
    @WebMethod
    public uk.gov.moj.sdt.ws._2013.sdt.bulkresponseschema.BulkResponseType submitBulk(
        @WebParam(partName = "bulkRequest", name = "bulkRequest", targetNamespace = "http://ws.sdt.moj.gov.uk/2013/sdt/BulkRequestSchema")
        uk.gov.moj.sdt.ws._2013.sdt.bulkrequestschema.BulkRequestType bulkRequest
    );
}
