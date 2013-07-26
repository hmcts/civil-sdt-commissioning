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
 * $Id$
 * $LastChangedRevision$
 * $LastChangedDate$
 * $LastChangedBy$ */
package uk.gov.moj.sdt.validators;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import uk.gov.moj.sdt.dao.api.IIndividualRequestDao;
import uk.gov.moj.sdt.domain.IndividualRequest;
import uk.gov.moj.sdt.domain.api.IBulkCustomer;
import uk.gov.moj.sdt.utils.visitor.api.ITree;
import uk.gov.moj.sdt.validators.api.IIndividualRequestValidator;
import uk.gov.moj.sdt.validators.exception.AbstractBusinessException;
import uk.gov.moj.sdt.validators.exception.SdtCustomerReferenceNotUniqueException;
import uk.gov.moj.sdt.visitor.AbstractDomainObjectVisitor;

/**
 * Implementation of {@link IIndividualRequestValidator}.
 * 
 * @author Saurabh Agarwal
 * 
 */
public class IndividualRequestValidator extends AbstractDomainObjectVisitor implements IIndividualRequestValidator
{

    /**
     * Logger instance.
     */
    private static final Log LOGGER = LogFactory.getLog (IndividualRequestValidator.class);

    /**
     * Individual request dao.
     */
    private IIndividualRequestDao individualRequestDao;

    /**
     * No-argument Constructor.
     */
    public IndividualRequestValidator ()
    {
    }

    @Override
    public void visit (final IndividualRequest individualRequest, final ITree tree)
    {
        // Do validation
        LOGGER.info ("visit(individualRequest)");
        final IBulkCustomer bulkCustomer = individualRequest.getBulkSubmission ().getBulkCustomer ();

        // Validate customer reference is unique across data retention period for individual request
        final String customerRequestReference = individualRequest.getCustomerRequestReference ();
        if ( !individualRequestDao.isCustomerReferenceUnique (bulkCustomer, customerRequestReference))
        {
            final List<String> replacements = new ArrayList<String> ();
            replacements.add (String.valueOf (customerRequestReference));
            replacements.add (String.valueOf (individualRequest.getId ()));
            // CHECKSTYLE:OFF
            throw new SdtCustomerReferenceNotUniqueException (
                    AbstractBusinessException.ErrorCode.SDT_CUSTOMER_REFRENCE_NOT_UNIQUE.toString (),
                    "SDT Customer Reference [{0}] was not unique across the data retention period for the Individual Request [{1}].",
                    replacements);
            // CHECKSTYLE:ON
        }
    }

    /**
     * Set individual request dao.
     * 
     * @param individualRequestDao individual request dao
     */
    public void setIndividualRequestDao (final IIndividualRequestDao individualRequestDao)
    {
        this.individualRequestDao = individualRequestDao;
    }

}
