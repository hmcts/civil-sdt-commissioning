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
package uk.gov.moj.sdt.services;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import uk.gov.moj.sdt.dao.api.IIndividualRequestDao;
import uk.gov.moj.sdt.domain.api.IGlobalParameter;
import uk.gov.moj.sdt.domain.api.IIndividualRequest;
import uk.gov.moj.sdt.domain.cache.api.ICacheable;
import uk.gov.moj.sdt.messaging.SdtMessage;
import uk.gov.moj.sdt.messaging.api.IMessageWriter;
import uk.gov.moj.sdt.messaging.api.ISdtMessage;
import uk.gov.moj.sdt.services.api.IRetryMessageSendService;

/**
 * Class implementing the interface IRetryMessageSendService.
 * 
 * @author Manoj Kulkarni
 * 
 */
@Transactional (propagation = Propagation.REQUIRES_NEW)
public class RetryMessageAlreadySentService implements IRetryMessageSendService
{

    /**
     * Logger object.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger (RetryMessageAlreadySentService.class);

    /**
     * The default forwarding attempts is set to 3. This value should never be used
     * but just in case the database global parameter is not set, this will be used.
     */
    private static final int DEFAULT_MAX_FORWARDING_ATTEMPTS = 3;

    /**
     * Individual Request Dao to perform operations on the individual request object.
     */
    private IIndividualRequestDao individualRequestDao;

    /**
     * This variable holding the message writer reference.
     */
    private IMessageWriter messageWriter;

    /**
     * The ICacheable reference to the global parameters cache.
     */
    private ICacheable globalParametersCache;

    @Override
    public void queueMessages ()
    {
        LOGGER.debug ("Queue Pending Message, getting the maximum forwarding attempts system parameter");

        final String maxForwardingAttemptsStr =
                this.getSystemParameter (IGlobalParameter.ParameterKey.MAX_FORWARDING_ATTEMPTS.name ());
        final int maxForwardingAttempts =
                maxForwardingAttemptsStr != null ? Integer.valueOf (maxForwardingAttemptsStr)
                        : DEFAULT_MAX_FORWARDING_ATTEMPTS;

        LOGGER.debug ("Now looking for pending individual request having reached [" + maxForwardingAttemptsStr +
                "] forwarding attempts");

        // Get list of pending individual requests.
        final List<IIndividualRequest> individualRequests =
                this.individualRequestDao.getPendingIndividualRequests (maxForwardingAttempts);

        // Loop through the list of the individual requests found.
        if ( !individualRequests.isEmpty ())
        {
            for (IIndividualRequest individualRequest : individualRequests)
            {
                // Create the SdtMessage required for sending message on the queue.
                final ISdtMessage sdtMessage = this.createSdtMessage (individualRequest.getSdtRequestReference ());

                // Now queue the message
                this.messageWriter.queueMessage (sdtMessage, individualRequest.getBulkSubmission ()
                        .getTargetApplication ().getTargetApplicationCode ());

                // Re-set the forwarding attempts on the individual request.
                individualRequest.resetForwardingAttempts ();

                LOGGER.debug ("Now re-queued pending individual request [" +
                        individualRequest.getSdtRequestReference () + "]");
            }

            // Persist the list of individual requests.
            this.individualRequestDao.persistBulk (individualRequests);
        }
        else
        {
            LOGGER.info ("No pending individual requests to process");
        }
    }

    /**
     * 
     * @param individualRequestDao the individual request dao object.
     */
    public void setIndividualRequestDao (final IIndividualRequestDao individualRequestDao)
    {
        this.individualRequestDao = individualRequestDao;
    }

    /**
     * 
     * @param messageWriter the message writer instance.
     */
    public void setMessageWriter (final IMessageWriter messageWriter)
    {
        this.messageWriter = messageWriter;
    }

    /**
     * 
     * @param globalParametersCache the global parameters cache instance.
     */
    public void setGlobalParametersCache (final ICacheable globalParametersCache)
    {
        this.globalParametersCache = globalParametersCache;
    }

    /**
     * Return the named parameter value from global parameters.
     * 
     * @param parameterName the name of the parameter.
     * @return value of the parameter name as stored in the database.
     */
    private String getSystemParameter (final String parameterName)
    {
        final IGlobalParameter globalParameter =
                this.globalParametersCache.getValue (IGlobalParameter.class, parameterName);

        if (globalParameter == null)
        {
            return null;
        }

        return globalParameter.getValue ();

    }

    /**
     * 
     * @param sdtRequestReference the SDT request reference of the individual request.
     * @return an SdtMessage instance containing the supplied SDT request reference
     */
    private ISdtMessage createSdtMessage (final String sdtRequestReference)
    {
        final ISdtMessage sdtMessage = new SdtMessage ();
        sdtMessage.setSdtRequestReference (sdtRequestReference);

        return sdtMessage;

    }

}
