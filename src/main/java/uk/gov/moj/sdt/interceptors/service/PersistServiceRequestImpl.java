package uk.gov.moj.sdt.interceptors.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import uk.gov.moj.sdt.domain.api.IDomainObject;
import uk.gov.moj.sdt.producers.comx.dao.MockGenericDao;

@Component
public class PersistServiceRequestImpl implements IPersistServiceRequest {

    private final MockGenericDao mockGenericDao;

    @Autowired
    public PersistServiceRequestImpl(@Qualifier("GenericDao") MockGenericDao mockGenericDao) {
        this.mockGenericDao = mockGenericDao;
    }

    @Override
    public void persist(Object domainObject) {
        mockGenericDao.persist(domainObject);
    }

    @Override
    public <D extends IDomainObject> D fetch(Class<D> domainType, long id) {
        return mockGenericDao.fetch(domainType, id);
    }
}
