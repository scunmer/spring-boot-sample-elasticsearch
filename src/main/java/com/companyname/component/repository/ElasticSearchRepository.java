package com.companyname.component.repository;

import com.alibaba.fastjson.JSON;
import org.elasticsearch.action.bulk.BulkRequestBuilder;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.client.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * Created by Sun Jian on 2017/1/9.
 */

@Repository
public abstract class ElasticSearchRepository<T> {
    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticSearchRepository.class);

    @Autowired
    protected Client client;

    public boolean save(Iterable<T> entities) {
        BulkRequestBuilder bulkRequest = client.prepareBulk();
        String index = getIndex();
        String type = getType();
        entities.forEach(e -> {
            bulkRequest.add(client.prepareIndex(index, type).setSource(JSON.toJSONString(e)));
        });
        BulkResponse response = bulkRequest.get();
        if (response.hasFailures()) {
            LOGGER.error("Error save documents", response.buildFailureMessage());
            return false;
        }
        return true;
    }

    /**
     * Sub-Class should override this method and provide the underlying index name.
     * @return index name.
     */
    protected abstract String getIndex();

    /**
     * Sub-Class should override this method and provide the underlying type name of index.
     * @return type name
     */
    protected abstract String getType();


}
