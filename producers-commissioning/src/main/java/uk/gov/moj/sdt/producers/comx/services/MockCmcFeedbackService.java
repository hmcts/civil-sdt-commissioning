package uk.gov.moj.sdt.producers.comx.services;

import org.springframework.stereotype.Component;
import uk.gov.moj.sdt.domain.api.IIndividualRequest;
import uk.gov.moj.sdt.services.api.ICMCFeedbackService;

@Component("cmcFeedbackService")
public class MockCmcFeedbackService implements ICMCFeedbackService {

    @Override
    public void cmcFeedback(IIndividualRequest individualRequest) {
        // No implementation required as commissioning doesn't use cmcFeedbackService.
        // This mock class is just to satisfy the required components so that the application can function.
    }
}
