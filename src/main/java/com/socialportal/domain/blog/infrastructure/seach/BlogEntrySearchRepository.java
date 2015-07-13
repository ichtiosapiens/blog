package com.socialportal.domain.blog.infrastructure.seach;

import org.springframework.data.solr.repository.Boost;
import org.springframework.data.solr.repository.SolrCrudRepository;

import java.util.List;

public interface BlogEntrySearchRepository extends SolrCrudRepository<BlogEntrySearchDto, String> {
    List<BlogEntrySearchDto> findByTitleContainsOrTextContains(@Boost(1.0f)String title, @Boost(0.5f)String text);
}
