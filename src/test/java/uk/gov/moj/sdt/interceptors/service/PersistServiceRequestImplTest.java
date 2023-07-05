package uk.gov.moj.sdt.interceptors.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import uk.gov.moj.sdt.domain.ServiceRequest;
import uk.gov.moj.sdt.domain.api.IDomainObject;
import uk.gov.moj.sdt.producers.comx.dao.MockGenericDao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PersistServiceRequestImplTest {

    private PersistServiceRequestImpl persistServiceRequest;

    @Mock
    private MockGenericDao mockGenericDao;

    @BeforeEach
    void setUp() {
        persistServiceRequest = new PersistServiceRequestImpl(mockGenericDao);
    }

    @Test
    void shouldInvokePersistOnGenericDao() {
        Object domainObject = new Object();
        persistServiceRequest.persist(domainObject);
        verify(mockGenericDao).persist(domainObject);
    }

    @Test
    void shouldInvokeFetchOnGenericDao() {
        persistServiceRequest.fetch(ServiceRequest.class, 1L);
        verify(mockGenericDao).fetch(ServiceRequest.class, 1L);
    }
}
