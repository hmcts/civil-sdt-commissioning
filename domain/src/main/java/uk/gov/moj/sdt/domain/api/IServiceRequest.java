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
package uk.gov.moj.sdt.domain.api;

import org.joda.time.LocalDateTime;

/**
 * Audit log entity representing information captured while processing requests
 * across the SDT and Government Gateway boundary.
 * 
 * @author David Turner
 */
public interface IServiceRequest extends IDomainObject {

	/**
	 * Set the datetime of the service request.
	 * 
	 * @param requestDateTime
	 *            the service request datetime.
	 */
	void setRequestDateTime(final LocalDateTime requestDateTime);

	/**
	 * Retrieve the response date time.
	 * 
	 * @return the request times
	 */
	LocalDateTime getRequestDateTime();

	/**
	 * Set the datetime of the service response.
	 * 
	 * @param responseDateTime
	 *            the service request datetime.
	 */
	void setResponseDateTime(final LocalDateTime responseDateTime);

	/**
	 * Retrieve the response date time.
	 * 
	 * @return the response time
	 */
	LocalDateTime getResponseDateTime();

	/**
	 * Set the bulk bulk customer ID.
	 * <p>
	 * Uniquely identifies the organisation on whose behalf a request is sent.
	 * Used to authenticate the connecting �user� within the SSL session.
	 * SDT is an attribute supplied in plain text.
	 * </p>
	 * <p>
	 * Used as reference in SDT for all stored data associated with requests.
	 * Value generated by CGI Ops Team Populated in LDAP & SDT DB by CGI Ops
	 * Team
	 * </p>
	 * <p>
	 * Format: NNNNNNNN
	 * </p>
	 * 
	 * @param bulkCustomerId
	 *            the unique numeric identifier for that bulk customer. This
	 *            value is fixed for the customer.
	 */
	void setBulkCustomerId(final String bulkCustomerId);

	/**
	 * Retrieve the bulk customer id.
	 * 
	 * @return bulk customer ids
	 */
	String getBulkCustomerId();

	/**
	 * Three possible request types. Submit Bulk Request Request Bulk Feedback
	 * Request Defence Details
	 * <p>
	 * Awaiting enlightenment
	 * </p>
	 * 
	 * @param requestType
	 *            one of three possible request types.
	 */
	void setRequestType(final String requestType);

	/**
	 * Retrieve request type.
	 * 
	 * @return request type.
	 */
	String getRequestType();

	/**
	 * The entire incoming message must be saved and so will be considered the
	 * payload (SOAP headers included).
	 * <p>
	 * The whole message must be stored in case it fails further validation or
	 * cannot be read. Storing the actual payload separately will be an
	 * unnecessary duplication.
	 * </p>
	 * 
	 * @param requestPayload
	 *            the entire message including SOAP headers.
	 */
	void setRequestPayload(final String requestPayload);

	/**
	 * Retrieve the payload.
	 * 
	 * @return the request payload.
	 */
	String getRequestPayload();

	/**
	 * The entire outgoing message must be saved and so will be considered the
	 * payload (SOAP headers included).
	 * <p>
	 * The whole message must be stored in case it fails further validation or
	 * cannot be read. Storing the actual payload separately will be an
	 * unnecessary duplication.
	 * </p>
	 * 
	 * @param responsePayload
	 *            the entire message including SOAP headers.
	 */
	void setResponsePayload(final String responsePayload);

	/**
	 * Retrieve the payload.
	 * 
	 * @return the response payload.
	 */
	String getResponsePayload();

	/**
	 * The SDT generated Bulk Reference will only ever be available on certain
	 * responses.
	 * <p>
	 * optional
	 * </p>
	 * 
	 * @param bulkReference
	 *            the SDT generated Bulk Reference
	 */
	void setBulkReference(final String bulkReference);

	/**
	 * Retrieve generated SDT reference.
	 * 
	 * @return SDT bulk reference.
	 */
	String getBulkReference();

}
