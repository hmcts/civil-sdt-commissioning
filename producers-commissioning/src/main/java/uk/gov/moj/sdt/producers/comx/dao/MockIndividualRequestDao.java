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

package uk.gov.moj.sdt.producers.comx.dao;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import uk.gov.moj.sdt.dao.api.IIndividualRequestDao;
import uk.gov.moj.sdt.domain.IndividualRequest;
import uk.gov.moj.sdt.domain.api.IBulkCustomer;
import uk.gov.moj.sdt.domain.api.IIndividualRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Mock Individual Request DAO class used in commissioning project.
 *
 * @author d130680
 *
 */
@Component("IndividualRequestDao")
public class MockIndividualRequestDao extends MockGenericDao implements IIndividualRequestDao
{

    /**
     * Pre-defined values for valid customer references.
     */
    private static final List<String> DUPLICATE_REFERENCE;

    static
    {

        DUPLICATE_REFERENCE = new ArrayList<String> ();

        DUPLICATE_REFERENCE.add ("duplicate");

    }

    @Override
    public IIndividualRequest getIndividualRequest (final IBulkCustomer bulkCustomer, final String customerReference,
                                                    final int dataRetention)
    {
        if (DUPLICATE_REFERENCE.contains (customerReference.toLowerCase ()))
        {
            return new IndividualRequest ();
        }
        else
        {
            return null;
        }

    }

    @Override
    public IIndividualRequest getRequestBySdtReference (final String sdtReferenceId) throws DataAccessException
    {
        // This method is implemented for the producers application only.
        return null;
    }

    @Override
    public List<IIndividualRequest> getPendingIndividualRequests (final int maxAllowedAttempts)
        throws DataAccessException
    {
        // This method is implemented for the producers application only.
        return null;
    }

    @Override
    public List<IIndividualRequest> getStaleIndividualRequests (final int maxAllowedAttempts)
        throws DataAccessException
    {
        // This method is implemented for the producers application only.
        return null;
    }

    @Override
    public long countStaleIndividualRequests(final int minimumAgeInMinutes) throws DataAccessException {
        // This method is implemented for the producers application only.
        return 0L;
    }
}
