package com.learn.elasticsearch.dl.config;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;


@Configuration
@EnableElasticsearchRepositories(basePackages = "com.learn.elasticsearch.dl.repository")
@ComponentScan(basePackages = "com.learn.elasticsearch")
public class Config extends AbstractElasticsearchConfiguration {


    @Value("${spring.elasticsearch.uris}")
    public String elasticsearchUrl;


    @Bean
    @Override
    public RestHighLevelClient elasticsearchClient() {
        final ClientConfiguration config= ClientConfiguration.builder()
                .connectedTo(elasticsearchUrl)
                .build();
        return RestClients.create(config).rest();
    }


}
