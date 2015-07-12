package com.companyportal.social.domain.blog.search;

import com.companyportal.social.domain.blog.BlogEntry;
import org.springframework.data.solr.repository.Boost;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.List;

public interface BlogEntryCrudRepository extends SolrCrudRepository<BlogEntrySearchDto, String> {
    List<BlogEntrySearchDto> findByTitleContainsOrTextContains(@Boost(1.0f)String title, @Boost(0.5f)String text);
}
