package uk.gov.moj.sdt.interceptors.service;

import org.springframework.stereotype.Component;
import uk.gov.moj.sdt.domain.api.IDomainObject;

@Component
public class PersistServiceRequestImpl implements IPersistServiceRequest {

    @Override
    public void persist(Object domainObject) {

    }

    @Override
    public <D extends IDomainObject> D fetch(Class<D> domainType, long id) {
        return null;
    }
}
