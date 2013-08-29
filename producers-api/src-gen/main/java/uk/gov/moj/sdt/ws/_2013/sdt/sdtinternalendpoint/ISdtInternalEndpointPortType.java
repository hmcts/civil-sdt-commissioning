package uk.gov.moj.sdt.ws._2013.sdt.sdtinternalendpoint;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.bind.annotation.XmlSeeAlso;

/**
 * This class was generated by Apache CXF 2.7.5
 * 2013-08-29T11:32:19.386+01:00
 * Generated source version: 2.7.5
 * 
 */
@WebService(targetNamespace = "http://ws.sdt.moj.gov.uk/2013/sdt/SdtInternalEndpoint", name = "ISdtInternalEndpointPortType")
@XmlSeeAlso({uk.gov.moj.sdt.ws._2013.sdt.baseschema.ObjectFactory.class, uk.gov.moj.sdt.ws._2013.sdt.individualupdateresponseschema.ObjectFactory.class, uk.gov.moj.sdt.ws._2013.sdt.individualupdaterequestschema.ObjectFactory.class})
@SOAPBinding(parameterStyle = SOAPBinding.ParameterStyle.BARE)
public interface ISdtInternalEndpointPortType {

    @WebResult(name = "updateResponse", targetNamespace = "http://ws.sdt.moj.gov.uk/2013/sdt/IndividualUpdateResponseSchema", partName = "updateResponse")
    @WebMethod
    public uk.gov.moj.sdt.ws._2013.sdt.individualupdateresponseschema.UpdateResponseType updateItem(
        @WebParam(partName = "updateRequest", name = "updateRequest", targetNamespace = "http://ws.sdt.moj.gov.uk/2013/sdt/IndividualUpdateRequestSchema")
        uk.gov.moj.sdt.ws._2013.sdt.individualupdaterequestschema.UpdateRequestType updateRequest
    );
}
