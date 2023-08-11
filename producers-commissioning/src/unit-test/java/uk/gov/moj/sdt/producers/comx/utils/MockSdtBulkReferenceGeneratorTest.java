package uk.gov.moj.sdt.producers.comx.utils;

import org.junit.jupiter.api.Test;
import uk.gov.moj.sdt.utils.AbstractSdtUnitTestBase;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MockSdtBulkReferenceGeneratorTest extends AbstractSdtUnitTestBase {

    private static final String TARGET_APP = "MCOL";

    private static final String REGEXP_SDT_BULK_REF = "^([A-Z]+)-\\d{14}-(\\d{9})$";
    private static final int GROUP_TARGET_APP = 1;
    private static final int GROUP_ID = 2;

    private final String[] bulkIds = new String[] {"000000001", "000000002", "000000003", "000000004", "000000005"};

    private Pattern patternSdtBulkRef;

    private MockSdtBulkReferenceGenerator mockSdtBulkReferenceGenerator;

    @Override
    protected void setUpLocalTests() {
        mockSdtBulkReferenceGenerator = new MockSdtBulkReferenceGenerator();
        patternSdtBulkRef = Pattern.compile(REGEXP_SDT_BULK_REF);
    }

    @Test
    void testGetSdtBulkReference() {
        String sdtBulkReference;

        // Not using a parameterised test because MockSdtBulkReferenceGenerator is reset
        // before each test which resets the bulk id sequence
        for (String expectedBulkId : bulkIds) {
            sdtBulkReference = mockSdtBulkReferenceGenerator.getSdtBulkReference(TARGET_APP);
            assertSdtBulkReference(sdtBulkReference, expectedBulkId);
        }
    }

    @Test
    void testGetSdtBulkReferenceReset() {
        // Call getSdtBulkReference() enough times to reach last bulk id in sequence
        for (int i = 1; i <= bulkIds.length; i++) {
            mockSdtBulkReferenceGenerator.getSdtBulkReference(TARGET_APP);
        }

        // Call getSdtBulkReference() again and check that bulk id has been reset to the first in sequence
        String sdtBulkReference = mockSdtBulkReferenceGenerator.getSdtBulkReference(TARGET_APP);
        assertSdtBulkReference(sdtBulkReference, bulkIds[0]);

    }

    private void assertSdtBulkReference(String sdtBulkReference, String expectedId) {
        Matcher matcher = patternSdtBulkRef.matcher(sdtBulkReference);

        assertTrue(matcher.matches(), "Generated SDT Bulk Reference does not match expected pattern");
        assertEquals(TARGET_APP,
                     matcher.group(GROUP_TARGET_APP),
                     "SDT Bulk Reference contains unexpected target application");
        assertEquals(expectedId, matcher.group(GROUP_ID), "SDT Bulk Reference contains unexpected id");
    }
}
