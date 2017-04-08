package com.companyname.component.repository.impl;

import com.companyname.component.domain.DomainObject;
import com.companyname.component.repository.ElasticSearchRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Sun Jian on 2017/1/9.
 */
@Repository("domainObjectRepository")
public class DomainObjectRepository extends ElasticSearchRepository<DomainObject> {
    private static final String INDEX_NAME = "index";
    private static final String TYPE = "type";

    @Override
    protected String getIndex() {
        return INDEX_NAME;
    }

    @Override
    protected String getType() {
        return TYPE;
    }
}
