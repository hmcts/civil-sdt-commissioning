package uk.gov.moj.sdt.interceptors.service;

import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;
import uk.gov.moj.sdt.domain.api.IDomainObject;

@Component
public class PersistServiceRequestImpl implements IPersistServiceRequest {

    @Override
    public void persist(Object domainObject) throws DataAccessException {

    }

    @Override
    public <D extends IDomainObject> D fetch(Class<D> domainType, long id) throws DataAccessException {
        return null;
    }
}
