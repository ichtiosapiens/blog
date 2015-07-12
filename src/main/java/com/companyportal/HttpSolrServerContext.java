package com.companyportal;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;
import org.springframework.data.solr.server.support.EmbeddedSolrServerFactoryBean;


import javax.annotation.Resource;

@Configuration
@EnableSolrRepositories("com.companyportal.social.domain.blog.search")
@PropertySource("classpath:application.properties")
public class HttpSolrServerContext {

    @Resource
    private Environment environment;

    @Bean
    public EmbeddedSolrServerFactoryBean solrServerFactoryBean() {
       // System.setProperty("solr.solr.home", "/home/shalinsmangar/work/oss/branch-1.3/example/solr");
         EmbeddedSolrServerFactoryBean factory = new EmbeddedSolrServerFactoryBean();
        factory.setSolrHome(environment.getRequiredProperty("solr.solr.home"));
        return factory;
    }

    @Bean
    public SolrTemplate solrTemplate() throws Exception {
        return new SolrTemplate(solrServerFactoryBean().getObject());
    }
}