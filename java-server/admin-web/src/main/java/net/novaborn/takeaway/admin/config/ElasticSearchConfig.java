package net.novaborn.takeaway.admin.config;

import net.novaborn.takeaway.goods.entity.Goods;
import net.novaborn.takeaway.goods.es.IGoodsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import javax.annotation.PostConstruct;

//@Configuration
//@EnableElasticsearchRepositories(basePackages = "net.novaborn.takeaway")
public class ElasticSearchConfig {
    @Autowired
    private ElasticsearchRestTemplate elasticsearchTemplate;

    @PostConstruct
    public void createIndex() {
        elasticsearchTemplate.createIndex(Goods.class);
    }
}
