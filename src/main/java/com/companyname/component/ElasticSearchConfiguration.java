package com.companyname.component;

import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotNull;
import java.net.InetAddress;

/**
 * Created by Sun Jian on 2017/1/9.
 */
@Configuration
@ConfigurationProperties("elasticsearch")
public class ElasticSearchConfiguration {

    private static final Logger LOGGER = LoggerFactory.getLogger(ElasticSearchConfiguration.class);

    @NotNull
    private String clusterName;

    @NotNull
    private String host;

    @NotNull
    private int port;

    @Bean
    public Settings settings() {
        LOGGER.info("Init elastic search setting, cluster name [{}]", clusterName);
        return Settings.builder().put("cluster.name", clusterName).put("client.transport.sniff", true).build();
    }

    @Bean
    public Client client() {
        try {
            TransportAddress transportAddress = new InetSocketTransportAddress(InetAddress.getByName(host), port);
            LOGGER.info("Init elastic client with host [{}], port [{}]", host, port);
            return new PreBuiltTransportClient(settings()).addTransportAddress(transportAddress);
        } catch (Exception e) {
            LOGGER.error("Error init elastic search client", e);
            System.exit(0);
            return null;
        }
    }

    public void setClusterName(String clusterName) {
        this.clusterName = clusterName;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
