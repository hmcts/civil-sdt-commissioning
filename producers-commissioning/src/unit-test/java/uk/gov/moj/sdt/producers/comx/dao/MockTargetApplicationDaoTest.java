package uk.gov.moj.sdt.producers.comx.dao;

import org.junit.jupiter.api.Test;
import uk.gov.moj.sdt.domain.api.ITargetApplication;
import uk.gov.moj.sdt.utils.AbstractSdtUnitTestBase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class MockTargetApplicationDaoTest extends AbstractSdtUnitTestBase {

    private static final String TARGET_APP_CODE_MCOL = "MCOL";

    private static final String TARGET_APP_NAME_MCOL = "MCOL";

    private MockTargetApplicationDao mockTargetApplicationDao;

    @Override
    protected void setUpLocalTests() {
        mockTargetApplicationDao = new MockTargetApplicationDao();
    }

    @Test
    void testGetTargetApplicationByCode() {

        ITargetApplication targetApplication =
            mockTargetApplicationDao.getTargetApplicationByCode(TARGET_APP_CODE_MCOL);

        assertNotNull(targetApplication, "TargetApplication should not be null");
        assertEquals(TARGET_APP_CODE_MCOL,
                     targetApplication.getTargetApplicationCode(),
                     "TargetApplication has unexpected target application code");
        assertEquals(TARGET_APP_NAME_MCOL,
                     targetApplication.getTargetApplicationName(),
                     "TargetApplication has unexpected target application name");
    }
}
