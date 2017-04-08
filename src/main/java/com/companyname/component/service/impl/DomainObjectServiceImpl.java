package com.companyname.component.service.impl;

import com.companyname.component.domain.DomainObject;
import com.companyname.component.repository.impl.DomainObjectRepository;
import com.companyname.component.service.DomainObjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Sun Jian on 2017/1/9.
 */
@Service("domainObjectService")
public class DomainObjectServiceImpl implements DomainObjectService {

    @Autowired
    DomainObjectRepository domainObjectRepository;

    @Override
    public boolean save(Iterable<DomainObject> entities) {
        return domainObjectRepository.save(entities);
    }
}
