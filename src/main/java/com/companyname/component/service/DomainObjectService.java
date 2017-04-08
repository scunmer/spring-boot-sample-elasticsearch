package com.companyname.component.service;

import com.companyname.component.domain.DomainObject;

/**
 * Created by Sun Jian on 2017/1/9.
 */
public interface DomainObjectService {

    boolean save(Iterable<DomainObject> entities);

}
