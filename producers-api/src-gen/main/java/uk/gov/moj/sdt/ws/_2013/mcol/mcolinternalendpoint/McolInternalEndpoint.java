package uk.gov.moj.sdt.ws._2013.mcol.mcolinternalendpoint;

import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceFeature;
import javax.xml.ws.Service;

/**
 * This class was generated by Apache CXF 2.7.5
 * 2013-07-04T15:16:03.986+01:00
 * Generated source version: 2.7.5
 * 
 */
@WebServiceClient(name = "McolInternalEndpoint", 
                  wsdlLocation = "src/main/resources/wsdl/McolInternalEndpoint.wsdl",
                  targetNamespace = "http://ws.sdt.moj.gov.uk/2013/mcol/McolInternalEndpoint") 
public class McolInternalEndpoint extends Service {

    public final static URL WSDL_LOCATION;

    public final static QName SERVICE = new QName("http://ws.sdt.moj.gov.uk/2013/mcol/McolInternalEndpoint", "McolInternalEndpoint");
    public final static QName McolInternalEndpointPort = new QName("http://ws.sdt.moj.gov.uk/2013/mcol/McolInternalEndpoint", "McolInternalEndpointPort");
    static {
        URL url = McolInternalEndpoint.class.getResource("src/main/resources/wsdl/McolInternalEndpoint.wsdl");
        if (url == null) {
            url = McolInternalEndpoint.class.getClassLoader().getResource("src/main/resources/wsdl/McolInternalEndpoint.wsdl");
        } 
        if (url == null) {
            java.util.logging.Logger.getLogger(McolInternalEndpoint.class.getName())
                .log(java.util.logging.Level.INFO, 
                     "Can not initialize the default wsdl from {0}", "src/main/resources/wsdl/McolInternalEndpoint.wsdl");
        }       
        WSDL_LOCATION = url;
    }

    public McolInternalEndpoint(URL wsdlLocation) {
        super(wsdlLocation, SERVICE);
    }

    public McolInternalEndpoint(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public McolInternalEndpoint() {
        super(WSDL_LOCATION, SERVICE);
    }
    

    /**
     *
     * @return
     *     returns IMcolInternalEndpointPortType
     */
    @WebEndpoint(name = "McolInternalEndpointPort")
    public IMcolInternalEndpointPortType getMcolInternalEndpointPort() {
        return super.getPort(McolInternalEndpointPort, IMcolInternalEndpointPortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns IMcolInternalEndpointPortType
     */
    @WebEndpoint(name = "McolInternalEndpointPort")
    public IMcolInternalEndpointPortType getMcolInternalEndpointPort(WebServiceFeature... features) {
        return super.getPort(McolInternalEndpointPort, IMcolInternalEndpointPortType.class, features);
    }

}
