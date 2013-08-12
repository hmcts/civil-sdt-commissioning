/* Copyrights and Licenses
 * 
 * Copyright (c) 2012-2013 by the Ministry of Justice. All rights reserved.
 * Redistribution and use in source and binary forms, with or without modification, are permitted
 * provided that the following conditions are met:
 * - Redistributions of source code must retain the above copyright notice, this list of conditions
 * and the following disclaimer.
 * - Redistributions in binary form must reproduce the above copyright notice, this list of
 * conditions and the following disclaimer in the documentation and/or other materials
 * provided with the distribution.
 * - All advertising materials mentioning features or use of this software must display the
 * following acknowledgment: "This product includes Money Claims OnLine."
 * - Products derived from this software may not be called "Money Claims OnLine" nor may
 * "Money Claims OnLine" appear in their names without prior written permission of the
 * Ministry of Justice.
 * - Redistributions of any form whatsoever must retain the following acknowledgment: "This
 * product includes Money Claims OnLine."
 * This software is provided "as is" and any expressed or implied warranties, including, but
 * not limited to, the implied warranties of merchantability and fitness for a particular purpose are
 * disclaimed. In no event shall the Ministry of Justice or its contributors be liable for any
 * direct, indirect, incidental, special, exemplary, or consequential damages (including, but
 * not limited to, procurement of substitute goods or services; loss of use, data, or profits;
 * or business interruption). However caused any on any theory of liability, whether in contract,
 * strict liability, or tort (including negligence or otherwise) arising in any way out of the use of this
 * software, even if advised of the possibility of such damage.
 * 
 * $Id: $
 * $LastChangedRevision: $
 * $LastChangedDate: $
 * $LastChangedBy: $ */
package uk.gov.moj.sdt.domain;

import org.joda.time.LocalDateTime;

import uk.gov.moj.sdt.domain.api.IServiceRequest;

/**
 * Audit log for incoming and outgoing request.
 * 
 * @author d195274
 * 
 */
public class ServiceRequest extends AbstractDomainObject implements IServiceRequest
{

    /**
     * The time the request is received.
     */
    private LocalDateTime requestDateTime;

    /**
     * The time the response is sent.
     */
    private LocalDateTime responseDateTime;

    /**
     * The id of the bulk customer. Initially generated by SDT but included in each request by the client.
     */
    private String bulkCustomerId;

    /**
     * The request type. Must be accepted by SDT.
     */
    private String requestType;

    /**
     * The incoming message.
     */
    private String requestPayload;

    /**
     * The outgoing message.
     */
    private String responsePayload;

    /**
     * The SDT generated bulk reference.
     */
    private String bulkReference;

    /**
     * Internal system error.
     */
    private String internalSystemError;

    /**
     * Service request header.
     */
    private String serviceRequestHeader;

    /**
     * @see uk.gov.moj.sdt.domain.api.IServiceRequest#setRequestDateTime(org.joda.time.LocalDateTime)
     * @param requestDateTime the time the request is received.
     */
    @Override
    public void setRequestDateTime (final LocalDateTime requestDateTime)
    {
        this.requestDateTime = requestDateTime;
    }

    /**
     * @see uk.gov.moj.sdt.domain.api.IServiceRequest#setResponseDateTime(org.joda.time.LocalDateTime)
     * @param responseDateTime the time the response is sent.
     */
    @Override
    public void setResponseDateTime (final LocalDateTime responseDateTime)
    {
        this.responseDateTime = responseDateTime;
    }

    /**
     * @see uk.gov.moj.sdt.domain.api.IServiceRequest#setBulkCustomerId(int)
     * @param bulkCustomerId The id of the bulk customer.
     */
    @Override
    public void setBulkCustomerId (final String bulkCustomerId)
    {
        this.bulkCustomerId = bulkCustomerId;

    }

    /**
     * @see uk.gov.moj.sdt.domain.api.IServiceRequest#setRequestType(java.lang.String)
     * @param requestType The request type. Must be accepted by SDT.
     */
    @Override
    public void setRequestType (final String requestType)
    {
        this.requestType = requestType;
    }

    /**
     * @see uk.gov.moj.sdt.domain.api.IServiceRequest#setRequestPayload(java.lang.String)
     * @param requestPayload the incoming message.
     */
    @Override
    public void setRequestPayload (final String requestPayload)
    {
        this.requestPayload = requestPayload;
    }

    /**
     * @see uk.gov.moj.sdt.domain.api.IServiceRequest#setResponsePayload(java.lang.String)
     * @param responsePayload the outgoing message.
     */
    @Override
    public void setResponsePayload (final String responsePayload)
    {
        this.responsePayload = responsePayload;
    }

    /**
     * @see uk.gov.moj.sdt.domain.api.IServiceRequest#setBulkReference(java.lang.String)
     * @param bulkReference the SDT generated reference.
     */
    @Override
    public void setBulkReference (final String bulkReference)
    {
        this.bulkReference = bulkReference;
    }

    @Override
    public String getInternalSystemError ()
    {
        return internalSystemError;
    }

    @Override
    public void setInternalSystemError (final String internalSystemError)
    {
        this.internalSystemError = internalSystemError;
    }

    @Override
    public String getServiceRequestHeader ()
    {
        return serviceRequestHeader;
    }

    @Override
    public void setServiceRequestHeader (final String serviceRequestHeader)
    {
        this.serviceRequestHeader = serviceRequestHeader;
    }
}
