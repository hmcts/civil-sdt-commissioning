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

package uk.gov.moj.sdt.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import uk.gov.moj.sdt.domain.api.IBulkSubmission;
import uk.gov.moj.sdt.domain.api.IErrorLog;
import uk.gov.moj.sdt.domain.api.IIndividualRequest;
import uk.gov.moj.sdt.domain.api.IIndividualRequest.IndividualRequestStatus;
import uk.gov.moj.sdt.utils.AbstractSdtUnitTestBase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNull;
import static uk.gov.moj.sdt.domain.api.IIndividualRequest.IndividualRequestStatus.FORWARDED;
import static uk.gov.moj.sdt.domain.api.IIndividualRequest.IndividualRequestStatus.RECEIVED;

/**
 * Unit tests for {@link IndividualRequest}.
 *
 * @author Saurabh Agarwal
 */
@DisplayName("Writing Individual Request Tests")
public class IndividualRequestTest extends AbstractSdtUnitTestBase {
    /**
     * Test subject.
     */
    private IIndividualRequest individualRequest;

    private static final String STATUS_IS_INCORRECT = "Status is incorrect";
    private static final String FORWARDING_ATTEMPT_COUNT_IS_INCORRECT = "Forwarding attempt count is incorrect";
    private static final String UPDATED_DATE_SHOULD_BE_POPULATED = "Updated date should be populated";

    /**
     * Set up test data.
     */
    @BeforeEach
    @Override
    public void setUp() {
        individualRequest = new IndividualRequest();
    }

    private void assertsCommon(IndividualRequestStatus expectedStatus, Integer expectedForwardingAttempts) {
        assertEquals(expectedStatus.getStatus(), individualRequest.getRequestStatus(), STATUS_IS_INCORRECT);
        assertEquals(expectedForwardingAttempts, individualRequest.getForwardingAttempts(),
                FORWARDING_ATTEMPT_COUNT_IS_INCORRECT);
        assertNotNull(individualRequest.getUpdatedDate(), UPDATED_DATE_SHOULD_BE_POPULATED);
    }

    /**
     * Tests that forwarding attempts is incremented correctly.
     */
    @Test
    @DisplayName("Test Increment Forwarding Attempts")
    public void testIncrementForwardingAttempts() {
        individualRequest.incrementForwardingAttempts();

        assertEquals(IndividualRequestStatus.FORWARDED.getStatus(),
                individualRequest.getRequestStatus(),STATUS_IS_INCORRECT_MESSAGE);
        assertEquals(1, individualRequest.getForwardingAttempts(), FORWARDING_ATTEMPT_COUNT_MESSAGE);
        assertNotNull(individualRequest.getUpdatedDate(),"Updated date should be populated");

    }

    /**
     * Tests that request is marked as accepted correctly.
     */
    @Test
    @DisplayName("Test Mark Request As Accepted")
    public void testMarkRequestAsAccepted() {
        individualRequest.markRequestAsAccepted();

        assertEquals(IndividualRequestStatus.ACCEPTED.getStatus(),
                individualRequest.getRequestStatus(),STATUS_IS_INCORRECT_MESSAGE);
        assertEquals(0, individualRequest.getForwardingAttempts(),FORWARDING_ATTEMPT_COUNT_MESSAGE);
        assertNotNull(individualRequest.getUpdatedDate(),"Updated date should not be null");
        assertNotNull(individualRequest.getCompletedDate(),"Completed date should be populated");

    }

    /**
     * Tests that request is marked as initially accepted correctly.
     */
    @Test
    @DisplayName("Test Mark Request As Initially Accepted")
    public void testMarkRequestAsInitiallyAccepted() {
        individualRequest.markRequestAsInitiallyAccepted();

        assertEquals(IndividualRequestStatus.INITIALLY_ACCEPTED.getStatus(),
                individualRequest.getRequestStatus(),STATUS_IS_INCORRECT_MESSAGE);
        assertEquals(0, individualRequest.getForwardingAttempts(),FORWARDING_ATTEMPT_COUNT_MESSAGE);
        assertNotNull(individualRequest.getUpdatedDate(),"Updated date shouldn't be populated");

    }

    /**
     * Tests that request is marked as awaiting data correctly.
     */
    @Test
    @DisplayName("Test Mark Request As Awaiting Data")
    public void testMarkRequestAsAwaitingData() {
        individualRequest.markRequestAsAwaitingData();

        assertEquals(IndividualRequestStatus.AWAITING_DATA.getStatus(),
                individualRequest.getRequestStatus(),STATUS_IS_INCORRECT_MESSAGE);
        assertEquals(0, individualRequest.getForwardingAttempts(),FORWARDING_ATTEMPT_COUNT_MESSAGE);
        assertNotNull(individualRequest.getUpdatedDate(),"Updated date should be populated and not null");

    }

    /**
     * Tests that request is marked as rejected correctly.
     */
    @Test
    @DisplayName("Test Mark Request As Rejected")
    public void testMarkRequestAsRejected() {
        final IErrorLog errorLog = new ErrorLog();
        individualRequest.markRequestAsRejected(errorLog);

        assertEquals(IndividualRequestStatus.REJECTED.getStatus(),
                individualRequest.getRequestStatus(),STATUS_IS_INCORRECT_MESSAGE);
        assertEquals(0, individualRequest.getForwardingAttempts(),FORWARDING_ATTEMPT_COUNT_MESSAGE);
        assertNotNull(individualRequest.getUpdatedDate(),"Request Updated date should be populated");
        assertNotNull(individualRequest.getCompletedDate(),"Completed date should be populated");
        assertEquals(errorLog, individualRequest.getErrorLog(),"Error log should be populated");
        assertEquals(individualRequest,errorLog.getIndividualRequest(),"Individual request should be associated with error log");
    }

    @Test
    @DisplayName("Test Mark Request As Rejected")
    public void testMarkRequestAsRejectedErrorLog() {

        individualRequest.markRequestAsRejected(null);
        assertNull(individualRequest.getErrorLog(),"Error log should be null");

    }

    @Test
    @DisplayName("Test Individual Request Reference")
    public void testIndividualRequestReference() {

        individualRequest.setSdtBulkReference("REF0202");
        assertEquals(individualRequest.getSdtBulkReference(),"REF0202", "reference should be set");
        assertNull(individualRequest.getErrorLog(),"Error log should be null");

    }

    /**
     * Tests that forward attempt is reset correctly.
     */
    @Test
    @DisplayName("Test Reset Forwarding Attempts")
    public void testResetForwardingAttempts() {
        individualRequest.resetForwardingAttempts();

        assertEquals(IndividualRequestStatus.RECEIVED.getStatus(),
                individualRequest.getRequestStatus(),STATUS_IS_INCORRECT_MESSAGE);
        assertEquals(0, individualRequest.getForwardingAttempts(),FORWARDING_ATTEMPT_COUNT_MESSAGE);
        assertNotNull(individualRequest.getUpdatedDate(),"IndividualRequest Updated date should be populated");

    }

    /**
     * Tests that check for enqueuing request correct.
     */
    @Test
    @DisplayName("Test Is Enqueue-able")
    public void testIsEnqueueable() {
        individualRequest.setRequestStatus(IndividualRequestStatus.RECEIVED.getStatus());
        assertTrue(individualRequest.isEnqueueable(),"Request should be enqueue-able");

        individualRequest.setRequestStatus(IndividualRequestStatus.FORWARDED.getStatus());
        assertFalse(individualRequest.isEnqueueable(),"Request should not be enqueue-able");
    }

    /**
     * Tests that request reference is populated correctly.
     */
    @Test
    @DisplayName("Test Populate Sdt Request Reference")
    public void testPopulateSdtRequestReference() {
        final IBulkSubmission bulkSubmission = new BulkSubmission();
        bulkSubmission.setSdtBulkReference("BULK-REF");

        individualRequest.setBulkSubmission(bulkSubmission);
        individualRequest.setLineNumber(1);
        individualRequest.populateReferences();

        assertEquals( "BULK-REF-0000001",
                individualRequest.getSdtRequestReference(),"Request reference is incorrect");
        assertEquals( "BULK-REF",
                      individualRequest.getSdtBulkReference(),"Request reference is incorrect");
    }



    @Test
    @DisplayName("Test Request toString")
    public void testIndividualRequestToString(){

        assertNotNull(individualRequest.toString(),"Object to string should be populated");
    }

}
