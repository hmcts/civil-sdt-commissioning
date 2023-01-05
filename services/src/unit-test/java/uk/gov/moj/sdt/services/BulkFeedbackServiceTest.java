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
package uk.gov.moj.sdt.services;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import java.time.LocalDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import uk.gov.moj.sdt.dao.api.IBulkSubmissionDao;
import uk.gov.moj.sdt.domain.BulkCustomer;
import uk.gov.moj.sdt.domain.BulkFeedbackRequest;
import uk.gov.moj.sdt.domain.BulkSubmission;
import uk.gov.moj.sdt.domain.ErrorLog;
import uk.gov.moj.sdt.domain.GlobalParameter;
import uk.gov.moj.sdt.domain.IndividualRequest;
import uk.gov.moj.sdt.domain.ServiceRouting;
import uk.gov.moj.sdt.domain.ServiceType;
import uk.gov.moj.sdt.domain.TargetApplication;
import uk.gov.moj.sdt.domain.api.IBulkCustomer;
import uk.gov.moj.sdt.domain.api.IBulkFeedbackRequest;
import uk.gov.moj.sdt.domain.api.IBulkSubmission;
import uk.gov.moj.sdt.domain.api.IErrorLog;
import uk.gov.moj.sdt.domain.api.IErrorMessage;
import uk.gov.moj.sdt.domain.api.IGlobalParameter;
import uk.gov.moj.sdt.domain.api.IIndividualRequest.IndividualRequestStatus;
import uk.gov.moj.sdt.domain.api.IServiceRouting;
import uk.gov.moj.sdt.domain.api.IServiceType;
import uk.gov.moj.sdt.domain.api.ITargetApplication;
import uk.gov.moj.sdt.domain.cache.api.ICacheable;
import uk.gov.moj.sdt.utils.AbstractSdtUnitTestBase;
import uk.gov.moj.sdt.utils.SdtContext;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test class for BulkSubmissionService.
 *
 * @author Sally Vonka
 */
@ExtendWith(MockitoExtension.class)
class BulkFeedbackServiceTest extends AbstractSdtUnitTestBase {

    /**
     * Bulk Submission DAO property for looking up the bulk submission object.
     */
    @Mock
    private IBulkSubmissionDao mockBulkSubmissionDao;

    /**
     * Global parameter cache to retrieve data retention period.
     */
    @Mock
    private ICacheable mockGlobalParameterCache;

    /**
     * Bulk Feedback Service for testing.
     */
    private BulkFeedbackService bulkFeedbackService;

    /**
     * The IBulkFeedbackRequest.
     */
    private IBulkFeedbackRequest bulkFeedbackRequest;

    /**
     * Bulk Customer to use for the test.
     */
    private IBulkCustomer bulkCustomer;

    /**
     * The string reference for our bulk request.
     */
    private String reference = " Bulk reference in request ";

    /**
     * requestId.
     */
    private long requestId;

    /**
     * Data retention period.
     */
    private int dataRetentionPeriod;

    /**
     * Setup of the mock dao and injection of other objects.
     */
    @BeforeEach
    @Override
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        bulkFeedbackService = new BulkFeedbackService();

        bulkFeedbackService.setBulkSubmissionDao(mockBulkSubmissionDao);

        final IGlobalParameter globalParameterData = new GlobalParameter();
        globalParameterData.setName(IGlobalParameter.ParameterKey.DATA_RETENTION_PERIOD.name());
        globalParameterData.setValue("90");
        when(mockGlobalParameterCache.getValue(IGlobalParameter.class,
                        IGlobalParameter.ParameterKey.DATA_RETENTION_PERIOD.name())).thenReturn(globalParameterData);

        bulkFeedbackService.setGlobalParametersCache(mockGlobalParameterCache);
        dataRetentionPeriod = 90;

        // create a bulk customer
        bulkCustomer = new BulkCustomer();
        bulkCustomer.setSdtCustomerId(12345L);

        bulkFeedbackRequest = new BulkFeedbackRequest();

        // Setup bulk request for test.
        bulkFeedbackRequest.setId(requestId);
        bulkFeedbackRequest.setSdtBulkReference(reference);
        bulkFeedbackRequest.setBulkCustomer(bulkCustomer);
    }

    @Test
    void testGetBulkFeedback() {
        // Activate Mock Generic Dao
        final IBulkSubmission bulkSubmission = this.createBulkSubmission();
        addValidIndividualRequest(bulkSubmission, "ICustReq124", IndividualRequestStatus.RECEIVED.getStatus());
        addValidIndividualRequest(bulkSubmission, "ICustReq125", IndividualRequestStatus.REJECTED.getStatus());
        addValidIndividualRequest(bulkSubmission, "ICustReq126", IndividualRequestStatus.RECEIVED.getStatus());

        // Tell the mock dao to return this request
        when(mockBulkSubmissionDao.getBulkSubmissionBySdtRef(bulkCustomer, reference, dataRetentionPeriod))
                .thenReturn(bulkSubmission);
        bulkFeedbackService.getBulkFeedback(bulkFeedbackRequest);

        final Map<String, String> targetApplicationRespMap = SdtContext.getContext().getTargetApplicationRespMap();

        assertEquals(3, targetApplicationRespMap.size());

        verify(mockBulkSubmissionDao).getBulkSubmissionBySdtRef(bulkCustomer, reference, dataRetentionPeriod);
    }

    /**
     * @return Bulk Submission object for the testing.
     */
    private IBulkSubmission createBulkSubmission() {
        final IBulkSubmission bulkSubmission = new BulkSubmission();
        final IBulkCustomer iBulkCustomer = new BulkCustomer();
        final ITargetApplication targetApp = new TargetApplication();

        targetApp.setId(1L);
        targetApp.setTargetApplicationCode("MCOL");
        targetApp.setTargetApplicationName("TEST_TargetApp");
        final Set<IServiceRouting> serviceRoutings = new HashSet<>();

        final ServiceRouting serviceRouting = new ServiceRouting();
        serviceRouting.setId(1L);
        serviceRouting.setWebServiceEndpoint("MCOL_END_POINT");

        final IServiceType serviceType = new ServiceType();
        serviceType.setId(1L);
        serviceType.setName("RequestTest1");
        serviceType.setDescription("RequestTestDesc1");
        serviceType.setStatus("RequestTestStatus");

        serviceRouting.setServiceType(serviceType);

        serviceRoutings.add(serviceRouting);

        targetApp.setServiceRoutings(serviceRoutings);

        bulkSubmission.setTargetApplication(targetApp);

        iBulkCustomer.setId(1L);
        iBulkCustomer.setSdtCustomerId(10L);

        bulkSubmission.setBulkCustomer(iBulkCustomer);

        bulkSubmission.setCreatedDate(LocalDateTime.now());
        bulkSubmission.setCustomerReference("TEST_CUST_REF");
        bulkSubmission.setId(1L);
        bulkSubmission.setNumberOfRequest(2);
        bulkSubmission.setPayload("TEST_XML");
        bulkSubmission.setSubmissionStatus("SUBMITTED");

        final IndividualRequest individualRequest = new IndividualRequest();
        individualRequest.setCompletedDate(LocalDateTime.now());
        individualRequest
                .setCreatedDate(LocalDateTime.now());
        individualRequest.setCustomerRequestReference("ICustReq123");
        individualRequest.setId(1L);
        individualRequest.setRequestStatus(IndividualRequestStatus.RECEIVED.getStatus());

        bulkSubmission.addIndividualRequest(individualRequest);

        return bulkSubmission;
    }

    /**
     * @param customerReference the customer reference number
     * @param bulkSubmission    bulk submission
     * @param status            of individual request
     */
    private void addValidIndividualRequest(final IBulkSubmission bulkSubmission, final String customerReference,
                                           final String status) {
        final IndividualRequest individualRequest = new IndividualRequest();
        individualRequest.setCompletedDate(LocalDateTime.now());
        individualRequest
                .setCreatedDate(LocalDateTime.now());
        individualRequest.setCustomerRequestReference(customerReference);
        individualRequest.setId(1L);
        individualRequest.setRequestStatus(status);
        if (IndividualRequestStatus.REJECTED.getStatus().equals(status)) {
            final IErrorLog errorLog =
                    new ErrorLog(IErrorMessage.ErrorCode.DUP_CUST_REQID.name(),
                            "Duplicate Unique Request Identifier submitted {0}");
            individualRequest.setErrorLog(errorLog);
        }
        individualRequest.setTargetApplicationResponse("<response></response>");

        bulkSubmission.addIndividualRequest(individualRequest);
    }

}
