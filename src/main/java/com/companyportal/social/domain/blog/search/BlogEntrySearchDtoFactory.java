package com.companyportal.social.domain.blog.search;

import com.companyportal.social.domain.blog.BlogEntry;
import org.apache.solr.client.solrj.beans.Field;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;

@Component
public class BlogEntrySearchDtoFactory {

    public BlogEntrySearchDto createBlogEntrySearchDto(BlogEntry blogEntry) {
        BlogEntrySearchDto dto = new BlogEntrySearchDto();
        dto.setEntryId(blogEntry.getEntryId());
        dto.setTitle(blogEntry.getTitle());
        dto.setText(blogEntry.getText());
        return dto;
    }


}
