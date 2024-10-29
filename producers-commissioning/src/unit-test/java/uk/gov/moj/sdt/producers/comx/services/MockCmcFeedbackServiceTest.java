package uk.gov.moj.sdt.producers.comx.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.moj.sdt.domain.api.IIndividualRequest;
import uk.gov.moj.sdt.utils.AbstractSdtUnitTestBase;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class MockCmcFeedbackServiceTest extends AbstractSdtUnitTestBase {

    @Mock
    private IIndividualRequest mockIndividualRequest;

    private MockCmcFeedbackService cmcFeedbackService;

    @Override
    protected void setUpLocalTests() {
        cmcFeedbackService = new MockCmcFeedbackService();
    }

    @Test
    void testCmcFeedback() {
        // cmcFeedback() has no implementation so just check that there were no interactions with the individual request
        cmcFeedbackService.cmcFeedback(mockIndividualRequest);

        verify(mockIndividualRequest, never()).getRequestStatus();
        verify(mockIndividualRequest, never()).getTargetApplicationResponse();
    }
}
