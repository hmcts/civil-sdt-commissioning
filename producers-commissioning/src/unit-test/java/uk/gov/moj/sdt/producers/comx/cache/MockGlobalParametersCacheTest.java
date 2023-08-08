package uk.gov.moj.sdt.producers.comx.cache;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import uk.gov.moj.sdt.domain.api.IGlobalParameter;
import uk.gov.moj.sdt.utils.AbstractSdtUnitTestBase;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class MockGlobalParametersCacheTest extends AbstractSdtUnitTestBase {

    private MockGlobalParametersCache mockGlobalParametersCache;

    @Override
    protected void setUpLocalTests() {
        mockGlobalParametersCache = new MockGlobalParametersCache();
    }

    @ParameterizedTest
    @MethodSource("globalParametersAndValues")
    void testGetValue(String key, String expectedValue) {
        IGlobalParameter globalParameter = mockGlobalParametersCache.getValue(IGlobalParameter.class, key);

        assertNotNull(globalParameter, "GlobalParameter should not be null");
        assertEquals(key, globalParameter.getName(), "Unexpected GlobalParameter name");
        assertEquals(expectedValue, globalParameter.getValue(), "Unexpected GlobalParameter value");
    }

    @Test
    void testGetValueNotFound() {
        IGlobalParameter globalParameter = mockGlobalParametersCache.getValue(IGlobalParameter.class, "does_not_exist");

        assertNull(globalParameter, "GlobalParameter should be null");
    }

    static Stream<Arguments> globalParametersAndValues() {
        return Stream.of(
            arguments(IGlobalParameter.ParameterKey.DATA_RETENTION_PERIOD.name(), "90"),
            arguments(IGlobalParameter.ParameterKey.CONTACT_DETAILS.name(), "TBD")
        );
    }
}
