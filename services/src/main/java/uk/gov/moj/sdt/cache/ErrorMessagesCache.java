/* Copyrights and Licenses
 * 
 * Copyright (c) 2013 by the Ministry of Justice. All rights reserved.
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
package uk.gov.moj.sdt.cache;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.gov.moj.sdt.dao.api.IGenericDao;
import uk.gov.moj.sdt.domain.api.IDomainObject;
import uk.gov.moj.sdt.domain.api.IErrorMessage;
import uk.gov.moj.sdt.domain.cache.AbstractCacheControl;

/**
 * A cache of all the error messages.
 * 
 * @author d301488/Robin Compston
 * 
 */
public class ErrorMessagesCache extends AbstractCacheControl
{
    /**
     * Logger object.
     */
    private static final Logger LOG = LoggerFactory.getLogger (ErrorMessagesCache.class);

    /**
     * The map of error messages.
     */
    private Map<String, IErrorMessage> errorMessages = new HashMap<String, IErrorMessage> ();

    /**
     * DAO to retrieve error messages.
     */
    private IGenericDao genericDao;

    /**
     * Get the map holding cached values.
     * 
     * @return the map holding cached values.
     */
    protected Map<String, IErrorMessage> getErrorMessages ()
    {
        return errorMessages;
    }

    @Override
    protected <DomainType extends IDomainObject> DomainType getSpecificValue (final Class<DomainType> domainType,
                                                                              final String errorMessageCode)
    {
        // Assume map is uninitialised if empty.
        if (this.getErrorMessages ().isEmpty ())
        {
            loadCache ();
        }

        LOG.debug ("Retrieving global parameter with key [" + errorMessageCode + "]");

        // Get the value of the named parameter.
        final Object someObject = this.getErrorMessages ().get (errorMessageCode);

        if (someObject == null)
        {
            LOG.warn ("Error message with key [" + errorMessageCode + "] not found.");
            return null;
        }

        DomainType domainObject = null;

        // Double check that the expected class matches the retrieved class.
        if (domainType.isAssignableFrom (someObject.getClass ()))
        {
            // Prepare object of correct type to return to caller.
            domainObject = domainType.cast (someObject);
        }
        else
        {
            // Unsupported entity type.
            throw new UnsupportedOperationException ("Expected class [" + domainType.getCanonicalName () +
                    "] does not match retrieved class [" + someObject.getClass ().getCanonicalName () + "].");
        }

        return domainObject;
    }

    @Override
    protected void loadCache ()
    {
        // This object should be a singleton but play safe and only let one instance at a time refresh the cache.
        synchronized (this.getErrorMessages ())
        {
            // Assume map is uninitialised if empty.
            if (this.getErrorMessages ().isEmpty ())
            {
                LOG.debug ("Loading error messages into cache.");

                // Retrieve all rows from error messages table.
                final IErrorMessage[] result = genericDao.query (IErrorMessage.class);

                for (IErrorMessage errorMessage : result)
                {
                    // Add all retrieved messages to a map, keyed by the Error Code.
                    this.getErrorMessages ().put (errorMessage.getErrorCode (), errorMessage);
                }
            }
        }
    }

    @Override
    protected void uncache ()
    {
        // This object should be a singleton but play safe and only let one instance at a time refresh the cache.
        synchronized (this.getErrorMessages ())
        {
            // Clear map but do not destroy it.
            this.getErrorMessages ().clear ();
        }
    }

    /**
     * Setter for generic DAO.
     * 
     * @param genericDao the genericDao to set.
     */
    public void setGenericDao (final IGenericDao genericDao)
    {
        this.genericDao = genericDao;
    }
}
