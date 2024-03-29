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
import uk.gov.moj.sdt.dao.api.IBulkCustomerDao;
import uk.gov.moj.sdt.domain.BulkCustomer;
import uk.gov.moj.sdt.domain.BulkCustomerApplication;
import uk.gov.moj.sdt.domain.TargetApplication;
import uk.gov.moj.sdt.domain.api.IBulkCustomer;
import uk.gov.moj.sdt.domain.api.IBulkCustomerApplication;
import uk.gov.moj.sdt.domain.api.ITargetApplication;

import java.util.ArrayList;
import java.util.List;

/**
 * Mock Bulk Customer DAO class used in commissioning project.
 *
 * @author d130680
 *
 */
@Component("BulkCustomerDao")
public class MockBulkCustomerDao extends MockGenericDao implements IBulkCustomerDao
{
    /**
     * Collection of target application codes to be associated with customer.
     */
    private List<String> targetAppCodes = new ArrayList<>();

    public MockBulkCustomerDao() {
        targetAppCodes.add("MCOL");
    }

    /**
     * Mock the behaviour of Bulk customer DAO, returns a static array of bulk customer.
     *
     * @param sdtCustomerId the SDT ID to match when retrieving the bulk customer.
     * @return the bulk customer matching the given SDT ID.
     * @throws DataAccessException Hibernate exception
     */
    public IBulkCustomer getBulkCustomerBySdtId (final long sdtCustomerId) throws DataAccessException
    {

        final IBulkCustomer bulkCustomer = new BulkCustomer ();
        bulkCustomer.setSdtCustomerId (sdtCustomerId);
        bulkCustomer.setId (sdtCustomerId);

        for (String targetAppCode : targetAppCodes)
        {
            final IBulkCustomerApplication bulkCustomerApplication =
                    createBulkCustomerApplication (bulkCustomer, targetAppCode);
            bulkCustomer.getBulkCustomerApplications ().add (bulkCustomerApplication);
        }
        return bulkCustomer;

    }

    /**
     * Creates an instance of {@link IBulkCustomerApplication} for given {@link IBulkCustomer} and target application
     * code.
     *
     * @param bulkCustomer bulk customer for which bulk customer application is created
     * @param targetAppCode target application code
     * @return IBulkCustomerApplication
     */
    private IBulkCustomerApplication createBulkCustomerApplication (final IBulkCustomer bulkCustomer,
                                                                    final String targetAppCode)
    {

        final ITargetApplication targetApplication = new TargetApplication ();
        targetApplication.setTargetApplicationCode (targetAppCode);
        targetApplication.setTargetApplicationName (targetAppCode);

        final IBulkCustomerApplication bulkCustomerApplication = new BulkCustomerApplication ();
        bulkCustomerApplication.setBulkCustomer (bulkCustomer);
        bulkCustomerApplication.setTargetApplication (targetApplication);
        bulkCustomerApplication.setCustomerApplicationId (targetAppCode + "-id");

        return bulkCustomerApplication;
    }

    /**
     * Setter for targetAppCodes property. Used to configure target applications for customer.
     *
     * @param targetAppCodes target application codes.
     */
    public void setTargetAppCodes (final List<String> targetAppCodes)
    {
        this.targetAppCodes = targetAppCodes;
    }

}
