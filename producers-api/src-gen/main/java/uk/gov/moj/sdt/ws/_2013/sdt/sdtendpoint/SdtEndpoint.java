package uk.gov.moj.sdt.ws._2013.sdt.sdtendpoint;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.7.5
 * 2014-08-13T09:35:13.691+01:00
 * Generated source version: 2.7.5
 * 
 */
@WebServiceClient(name = "SdtEndpoint", 
                  wsdlLocation = "src/main/resources/wsdl/SdtEndpoint.wsdl",
                  targetNamespace = "http://ws.sdt.moj.gov.uk/2013/sdt/SdtEndpoint") 
public class SdtEndpoint extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://ws.sdt.moj.gov.uk/2013/sdt/SdtEndpoint", "SdtEndpoint");
    public final static QName SdtEndpointPort = new QName("http://ws.sdt.moj.gov.uk/2013/sdt/SdtEndpoint", "SdtEndpointPort");
    static {
        URL url = SdtEndpoint.class.getResource("src/main/resources/wsdl/SdtEndpoint.wsdl");
        if (url == null) {
            url = SdtEndpoint.class.getClassLoader().getResource("src/main/resources/wsdl/SdtEndpoint.wsdl");
        } 
        if (url == null) {
            java.util.logging.Logger.getLogger(SdtEndpoint.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "src/main/resources/wsdl/SdtEndpoint.wsdl");
        }       
        WSDL_LOCATION = url;
    }

    public SdtEndpoint(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public SdtEndpoint(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public SdtEndpoint() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     *
     * @return
     *     returns ISdtEndpointPortType
     */
    @WebEndpoint(name = "SdtEndpointPort")
    public ISdtEndpointPortType getSdtEndpointPort() {
        return super.getPort(SdtEndpointPort, ISdtEndpointPortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns ISdtEndpointPortType
     */
    @WebEndpoint(name = "SdtEndpointPort")
    public ISdtEndpointPortType getSdtEndpointPort(WebServiceFeature... features) {
        return super.getPort(SdtEndpointPort, ISdtEndpointPortType.class, features);
    }

}
