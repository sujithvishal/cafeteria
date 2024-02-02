package com.softwareag.cafeteria.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.ElasticsearchRepositoryConfigExtension;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@EnableElasticsearchRepositories
@Configuration
public class ElasticConfig extends ElasticsearchConfiguration {
    @Value("${ELASTICSEARCH_HOST:localhost}")
    private String host;
    @Value("${ELASTICSEARCH_PORT:9200}")
    private String port;
    @Override
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder()
                .connectedTo(host+":"+port)
                .build();
    }
}